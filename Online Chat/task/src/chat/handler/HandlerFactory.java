package chat.handler;

public interface HandlerFactory {

    enum HandlerType {
        COMPOUND, NAME_HANDLER, COMMAND_HANDLER
    }

    static HandlerFactory getDefaultFactory() {
        return new DefaultHandlerFactory();
    }

    InputHandler getHandler(HandlerType type);
}
