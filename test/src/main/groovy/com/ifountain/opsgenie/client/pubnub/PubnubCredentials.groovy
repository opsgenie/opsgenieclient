package com.ifountain.opsgenie.client.pubnub;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/7/12 11:39 AM
 */
public class PubnubCredentials {
    private String publishKey = "";
    private String secretKey = "";
    private String subscribeKey = "";
    private String cipherKey = "";
    private boolean sslOn = false;

    public String getPublishKey() {
        return publishKey;
    }

    public void setPublishKey(String publishKey) {
        this.publishKey = publishKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSubscribeKey() {
        return subscribeKey;
    }

    public void setSubscribeKey(String subscribeKey) {
        this.subscribeKey = subscribeKey;
    }

    public String getCipherKey() {
        return cipherKey;
    }

    public void setCipherKey(String cipherKey) {
        this.cipherKey = cipherKey;
    }

    public boolean isSslOn() {
        return sslOn;
    }

    public void setSslOn(boolean sslOn) {
        this.sslOn = sslOn;
    }
}
