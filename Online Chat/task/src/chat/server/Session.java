package chat.server;

import java.util.UUID;

interface Session {

    Runnable openSession();

    UUID getUuid();

    void sessionSendMessage(String msg);

    void close();
}
