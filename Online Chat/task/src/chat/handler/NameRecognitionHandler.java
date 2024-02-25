package chat.handler;

import java.util.StringTokenizer;

public class NameRecognitionHandler implements InputHandler {

    @Override
    public void handle(Command input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input.getRawCommand(), " ");
        String username = stringTokenizer.nextToken();
        input.setUsername(username);
        input.setUnprocessedPart(input.getRawCommand().replaceFirst(username, ""));
    }
}
