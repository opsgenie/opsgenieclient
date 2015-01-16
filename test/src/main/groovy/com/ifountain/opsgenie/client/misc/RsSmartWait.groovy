package com.ifountain.opsgenie.client.misc

import com.ifountain.comp.utils.SmartWait
import com.ifountain.opsgenie.client.test.util.misc.SmartWait

class RsSmartWait extends SmartWait{

    public static void waitForClosure(Closure closure) throws InterruptedException
    {
    	SmartWait.waitFor(new ClosureWaitAction(closure));
    }

    public static void waitForClosure(Closure closure, long totalSleepMillis) throws InterruptedException
    {
    	SmartWait.waitFor(new ClosureWaitAction(closure), totalSleepMillis);
    }

    public static void waitForClosure(Closure closure, long totalSleepMillis, long iterationSleepMillis ) throws InterruptedException
    {
    	SmartWait.waitFor(new ClosureWaitAction(closure), totalSleepMillis, iterationSleepMillis);
    }

    public static void waitForClosure(long totalSleepMillis, Closure closure) throws InterruptedException
    {
        SmartWait.waitFor(new ClosureWaitAction(closure), totalSleepMillis);
    }

    public static void waitForClosure(long totalSleepMillis, long iterationSleepMillis, Closure closure) throws InterruptedException
    {
        SmartWait.waitFor(new ClosureWaitAction(closure), totalSleepMillis, iterationSleepMillis);
    }
}
