package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int SERVER_PORT = 8839;

    public static void main(String[] args) {
        System.out.println("Client started!");
        Scanner scanner = new Scanner(System.in);
        Thread getMessage = null;
        Thread sendMessage = null;
        do {
            try (var socket = new Socket(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
                 var is = new DataInputStream(socket.getInputStream());
                 var os = new DataOutputStream(socket.getOutputStream())) {

                getMessage = new Thread(() -> {
                    String input;
                    while (true) {
                        try {
                            input = is.readUTF();
                            System.out.println(input);
                        } catch (IOException e) {
                            break;
                        }
                    }
                });
                sendMessage = new Thread(() -> {
                    while (true) {
                        try {
                            os.writeUTF(scanner.nextLine());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                getMessage.start();
                sendMessage.start();
                getMessage.join();
                sendMessage.join();
            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (IOException | InterruptedException e) {
                if (sendMessage != null)
                    sendMessage.interrupt();
                if (getMessage != null)
                    getMessage.interrupt();
                throw new RuntimeException(e);
            }
        } while (true);
    }
}
