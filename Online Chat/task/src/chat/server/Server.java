package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.LongBinaryOperator;

public class Server {

    private static final int PORT = 8839;
    private static final int BACKLOG = 50;
    private static final String ADDRESS = "127.0.0.1";


    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig(ADDRESS, PORT, BACKLOG);
        Socket socket;
        try (var sc = new ServerSocket(
                serverConfig.port(),
                serverConfig.backlog(),
                InetAddress.getByName(serverConfig.address()))) {
            System.out.println("Server started!");
            socket = sc.accept();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Thread receiveMessage = null;
        try (var is = new DataInputStream(socket.getInputStream());
             var os = new DataOutputStream(socket.getOutputStream())){
            receiveMessage = new Thread(() -> {
                String input;
                while (true) {
                    try {
                        input = is.readUTF();
                        System.out.println(input);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "Receive msg");
            receiveMessage.start();

            Scanner scanner = new Scanner(System.in);
            String input;
            while (!Thread.currentThread().isInterrupted()) {
                input = scanner.nextLine();
                os.writeUTF(input);
            }
        } catch (Exception e) {
            receiveMessage.interrupt();
            throw new RuntimeException(e);
        }


//        System.out.println(binaryOperator.applyAsLong(10, 12));

/*        Scanner scanner = new Scanner(System.in);
        HandlerFactory handlerFactory = HandlerFactory.getDefaultFactory();
        InputHandler compoundHandler = handlerFactory.getHandler(HandlerFactory.HandlerType.COMPOUND);



        String input;
        while (true) {
            input = scanner.nextLine();
            compoundHandler.handle(new ConsoleCommand(input));
        }*/
    }

    public static LongBinaryOperator binaryOperator = (x, y) -> {
        BiFunction<Long, BiFunction, Long> newFun = (num, function) -> {
            if (num == y) return y;
            return num + (long) function.apply(num + 1, function);
        };
        return newFun.apply(x, newFun);
    };
}
