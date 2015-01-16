package com.ifountain.opsgenie.client.test.util.http;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 4/25/12
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SystemTimeProvider {
    public long currentTimeInMillis();
    public long nanoTime();
}
