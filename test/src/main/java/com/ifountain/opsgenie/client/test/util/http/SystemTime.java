package com.ifountain.opsgenie.client.test.util.http;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 4/25/12
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class SystemTime {
    private static SystemTimeProvider timeProvider;
    private SystemTime(){}
    static {
        reset();
    }
    public static void reset() {
        timeProvider = new SystemTimeProvider() {
            @Override
            public long currentTimeInMillis() {
                return System.currentTimeMillis();
            }

            @Override
            public long nanoTime() {
                return System.nanoTime();
            }
        };
    }

    public static long currentTimeMillis() {
        return timeProvider.currentTimeInMillis();
    }

    public static long nanoTime() {
        return timeProvider.nanoTime();
    }

    public static void setTimeProvider(SystemTimeProvider timeProvider) {
        SystemTime.timeProvider = timeProvider;
    }
}
