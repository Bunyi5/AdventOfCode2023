package helper;

import java.util.concurrent.TimeUnit;

public class Timer {

    private static long start = 0L;

    public static void startTimer() {
        start = System.nanoTime();
    }

    public static void endTimer(TimeUnit timeUnit) {
        System.out.println("Timer: " + timeUnit.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));
    }
}
