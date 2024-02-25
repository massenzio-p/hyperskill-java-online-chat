package chat.handler;

public interface Command {

    String getRawCommand();
    String getUsername();
    String getUnprocessedPart();

    void setUsername(String username);
    void setUnprocessedPart(String unprocessedPart);
}
