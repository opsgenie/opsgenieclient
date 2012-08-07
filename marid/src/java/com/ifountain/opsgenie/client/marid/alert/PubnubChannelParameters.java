package com.ifountain.opsgenie.client.marid.alert;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/6/12 1:53 PM
 */
public class PubnubChannelParameters {
    private String channel = "";
    private String publishKey = "";
    private String secretKey = "";
    private String subscribeKey = "";
    private String cipherKey = "";
    private boolean sslOn = false;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

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
