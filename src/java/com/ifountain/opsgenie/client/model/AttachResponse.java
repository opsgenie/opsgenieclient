package com.ifountain.opsgenie.client.model;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 3:38 PM
 */
public class AttachResponse implements Response{
    private boolean success = true;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
