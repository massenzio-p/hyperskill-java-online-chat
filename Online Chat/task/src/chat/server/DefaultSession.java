package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DefaultSession implements Session {

    private final Socket socket;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final UUID uuid;
    private final Queue<String> messageQueue = new ArrayDeque<>();

    public DefaultSession(Socket socket) {
        this.socket = socket;
        this.uuid = UUID.randomUUID();
    }

    public void start() {
        Thread receiveMessages = null;
        Thread sendMessages = null;

        try (var is = new DataInputStream(socket.getInputStream());
             var os = new DataOutputStream(socket.getOutputStream())) {

            receiveMessages = new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(is.readUTF());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            sendMessages = new Thread(() -> {
                String lastMessage;
                while (true) {
                    lastMessage = getLastMessage();
                    if (lastMessage != null) {
                        try {
                            os.writeUTF(lastMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            receiveMessages.start();
            sendMessages.start();
            receiveMessages.join();
            sendMessages.join();
        } catch (IOException | InterruptedException e) {
            receiveMessages.interrupt();
            sendMessages.interrupt();
            throw new RuntimeException(e);
        }
    }

    private synchronized String getLastMessage() {
        return messageQueue.poll();
    }

    private synchronized void putMessageIntoQueue(String msg) {
        this.messageQueue.add(msg);
    }

    @Override
    public void sessionSendMessage(String msg) {
        this.executor.submit(() -> this.putMessageIntoQueue(msg));
    }

    @Override
    public Runnable openSession() {
        return this::start;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
