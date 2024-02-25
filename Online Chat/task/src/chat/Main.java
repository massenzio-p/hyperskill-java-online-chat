package chat;

import chat.handler.ConsoleCommand;
import chat.handler.HandlerFactory;
import chat.handler.InputHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HandlerFactory handlerFactory = HandlerFactory.getDefaultFactory();
        InputHandler compoundHandler = handlerFactory.getHandler(HandlerFactory.HandlerType.COMPOUND);

        String input;
        while (true) {
            input = scanner.nextLine();
            compoundHandler.handle(new ConsoleCommand(input));
        }
    }
}
