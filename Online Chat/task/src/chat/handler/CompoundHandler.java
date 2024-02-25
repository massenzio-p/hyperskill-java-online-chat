package chat.handler;

import java.util.ArrayList;
import java.util.List;

class CompoundHandler implements InputHandler {

    private final List<InputHandler> handlerChain;

    CompoundHandler(HandlerFactory handlerFactory) {
        this.handlerChain = new ArrayList<>();
        // Order matters
        this.handlerChain.add(handlerFactory.getHandler(HandlerFactory.HandlerType.NAME_HANDLER));
        this.handlerChain.add(handlerFactory.getHandler(HandlerFactory.HandlerType.COMMAND_HANDLER));
    }

    @Override
    public void handle(Command command) {
        for (var handler : this.handlerChain) {
            handler.handle(command);
        }
    }
}
