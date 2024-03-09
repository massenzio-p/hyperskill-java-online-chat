package chat.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DefaultSessionHandler extends Thread {

    private volatile SessionRepository sessionRepository;
    private final ServerConfig config;

    public DefaultSessionHandler(SessionRepository sessionRepository,
                                 ServerConfig serverConfig) {
        super("Session Handler");
        this.sessionRepository = sessionRepository;
        this.config = serverConfig;
    }

    @Override
    public void run() {
        List<Thread> sessions = new ArrayList<>();
        try (var sc = new ServerSocket(config.port(), config.backlog(), InetAddress.getByName(config.address()))) {
            System.out.println("Server started!");
            while (!isInterrupted()) {
                // accept a socket and create a session
                Socket socket = sc.accept();
                Session newSession = new DefaultSession(socket);
                // store and run the session
                sessionRepository.putSession(newSession);
                Thread sessionThread = new Thread(newSession.openSession(), "Session " + newSession.getUuid());
                sessionThread.start();
                sessions.add(sessionThread);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sessions.forEach(Thread::interrupt);
        }
    }
}
