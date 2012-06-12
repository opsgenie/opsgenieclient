package com.ifountain.opsgenie.client;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 1:42 PM
 */
public class OpsGenieClientException extends Exception {
    private int code;

    public OpsGenieClientException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
