package chat.server;

public record ServerConfig(String address, int port, int backlog) {
}
