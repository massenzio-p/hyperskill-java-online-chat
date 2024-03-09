package chat;

import java.util.concurrent.Future;

public class Utility {

    public static void cancelFutures(Future<?>... futures) {
        for (var future : futures) {
            if (future != null && !future.isCancelled() && !future.isDone()) {
                future.cancel(true);
            }
        }
    }
}
