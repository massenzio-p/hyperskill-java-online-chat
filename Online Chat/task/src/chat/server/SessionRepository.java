package chat.server;

import java.util.Collection;

public interface SessionRepository {

    void putSession(Session newSession);

    Collection<Session> getAllSessions();

    void clear();
}
