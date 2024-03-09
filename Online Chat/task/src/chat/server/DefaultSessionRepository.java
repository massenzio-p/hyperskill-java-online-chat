package chat.server;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSessionRepository implements SessionRepository {

    private static final ConcurrentHashMap<UUID, Session> sessionStorage = new ConcurrentHashMap<>();
    private static SessionRepository instance;


    private DefaultSessionRepository() {
    }

    public static SessionRepository getInstance() {
        if (instance == null) {
            instance = new DefaultSessionRepository();
        }
        return instance;
    }

    @Override
    public void putSession(Session newSession) {
        sessionStorage.put(newSession.getUuid(), newSession);
    }

    @Override
    public Collection<Session> getAllSessions() {
        return sessionStorage.values();
    }

    @Override
    public void clear() {
        this.sessionStorage.clear();
    }
}
