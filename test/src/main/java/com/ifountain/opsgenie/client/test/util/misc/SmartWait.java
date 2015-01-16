package com.ifountain.opsgenie.client.test.util.misc;

import com.ifountain.opsgenie.client.misc.WaitAction;

public class SmartWait {

    public static final long DEFAULT_TOTAL_SLEEP_MILLIS = 10000;
    public static final long DEFAULT_ITERATION_SLEEP_MILLIS = 10;

    //waits for 10000 millis , 1000 iterations
    public static void waitFor(WaitAction waitAction) throws InterruptedException {
        waitFor(waitAction, DEFAULT_TOTAL_SLEEP_MILLIS);
    }

    //waits for given total millis , totalSleep/10 iterations
    public static void waitFor(WaitAction waitAction, long totalSleepMillis) throws InterruptedException {
        waitFor(waitAction, totalSleepMillis, DEFAULT_ITERATION_SLEEP_MILLIS);
    }

    //waits for given total millis , totalSleep/10 iterations
    public static void waitFor(long totalSleepMillis,WaitAction waitAction) throws InterruptedException {
        waitFor(waitAction, totalSleepMillis, DEFAULT_ITERATION_SLEEP_MILLIS);
    }
    //waits for given total millis , totalSleep/iterationSleep iterations
    public static void waitFor(long totalSleepMillis, long iterationSleepMillis, WaitAction waitAction) throws InterruptedException {
       waitFor(waitAction, totalSleepMillis, iterationSleepMillis);
    }

    //waits for given total millis , totalSleep/iterationSleep iterations
    public static void waitFor(WaitAction waitAction, long totalSleepMillis, long iterationSleepMillis) throws InterruptedException {

        long currentTime = System.currentTimeMillis();
        Throwable lastError = null;
        while (System.currentTimeMillis() - currentTime < totalSleepMillis) {
            try
            {
                waitAction.check();
                return;
                
            }
            catch(Throwable t)
            {
                lastError = t;
            }
            Thread.sleep(iterationSleepMillis);
        }
        if(lastError != null)
        {
            if(lastError.getClass().getName().equals("junit.framework.AssertionFailedError"))
            {
                throw (Error)lastError;
            }
            else
            {
                throw new RuntimeException(lastError);
            }
        }
        throw new RuntimeException("Wait for action failed :(");
    }
}
