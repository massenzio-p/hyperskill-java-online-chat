package chat.handler;

class DefaultHandlerFactory implements HandlerFactory {

    @Override
    public InputHandler getHandler(HandlerType type) {
        return switch (type) {
            case NAME_HANDLER -> new NameRecognitionHandler();
            case COMMAND_HANDLER -> new CommandHandler();
            case COMPOUND -> new CompoundHandler(this);
        };
    }
}
