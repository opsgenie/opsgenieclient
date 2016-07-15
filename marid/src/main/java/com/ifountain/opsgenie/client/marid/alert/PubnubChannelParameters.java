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
    private boolean proxyEnabled = false;
    private String proxyHost = null;
    private int proxyPort = -1;
    private String proxyUsername = null;
    private String proxyPassword = null;
    private String proxyProtocol = null;
    private boolean sslOn = false;

    public String getProxyProtocol() {
        return proxyProtocol;
    }

    public void setProxyProtocol(String proxyProtocol) {
        this.proxyProtocol = proxyProtocol;
    }

    public boolean isProxyEnabled() {
        return proxyEnabled;
    }

    public void setProxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled = proxyEnabled;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

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

    @Override
    public String toString() {
        return "PubnubChannelParameters{" +
                "channel='" + channel + '\'' +
                ", publishKey='" + publishKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", subscribeKey='" + subscribeKey + '\'' +
                ", cipherKey='" + cipherKey + '\'' +
                ", proxyEnabled=" + proxyEnabled +
                ", proxyHost='" + proxyHost + '\'' +
                ", proxyPort=" + proxyPort +
                ", proxyUsername='" + proxyUsername + '\'' +
                ", proxyPassword='" + proxyPassword + '\'' +
                ", proxyProtocol='" + proxyProtocol + '\'' +
                ", sslOn=" + sslOn +
                '}';
    }
}
