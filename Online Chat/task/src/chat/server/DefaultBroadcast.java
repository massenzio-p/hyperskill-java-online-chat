package chat.server;

import java.util.Objects;

class DefaultBroadcast implements Broadcast {

    private final SessionRepository sessionRepository;

    public DefaultBroadcast(SessionRepository sessionRepository) {
        Objects.requireNonNull(sessionRepository);
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void sendBroadCastMessage(String msg) {
        sessionRepository.getAllSessions().forEach(session -> session.sessionSendMessage(msg));
    }
}
