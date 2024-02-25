package chat.handler;

public class ConsoleCommand implements Command {

    private final String rawCommand;
    private String username;
    private String unprocessedPart;

    public ConsoleCommand(String rawCommand) {
        this.rawCommand = rawCommand;
        this.unprocessedPart = rawCommand;
    }

    @Override
    public String getRawCommand() {
        return this.rawCommand;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getUnprocessedPart() {
        return this.unprocessedPart;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setUnprocessedPart(String unprocessedPart) {
        this.unprocessedPart = unprocessedPart;
    }
}
