package chat.handler;

import java.util.StringTokenizer;

class CommandHandler implements InputHandler {

    @Override
    public void handle(Command input) {
        StringTokenizer tokenizer = new StringTokenizer(input.getUnprocessedPart(), " ");
        String command = tokenizer.nextToken();
        String payload = input.getUnprocessedPart().replaceFirst(command, "").trim();

        switch (command) {
            case "joined" -> {}
            case "sent" -> {
                System.out.printf("%s: %s%n", input.getUsername(), payload);
            }
            case "disconnected" -> {}
            case "left" -> {}
        }

        input.setUnprocessedPart("");
    }
}
