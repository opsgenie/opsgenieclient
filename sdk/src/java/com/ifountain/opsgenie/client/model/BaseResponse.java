package com.ifountain.opsgenie.client.model;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/14/12 4:08 PM
 */
public class BaseResponse implements Response{
    private boolean success = true;
    private long took = 0;

    /**
     * True if the operation is successful.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the operation success state.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns the number of milliseconds that have elapsed during the operation.
     */
    public long getTook() {
        return took;
    }

    /**
     * Sets the number of milliseconds that have elapsed during the operation.
     */
    public void setTook(long took) {
        this.took = took;
    }
}
