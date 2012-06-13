package com.ifountain.opsgenie.client.util;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 2:53 PM
 */
public class ClientConfiguration {
    private static String staticUserAgent;
    private int maxConnections = 50;
    private int socketTimeout = 50000;
    private int connectionTimeout = 50000;
    private int socketSendBufferSizeHint = 0;
    private int socketReceiveBufferSizeHint = 0;
    private String proxyHost = null;
    private int proxyPort = -1;
    private String proxyUsername = null;
    private String proxyPassword = null;
    private String proxyDomain = null;
    private String proxyWorkstation = null;

    private String userAgent = initializeUserAgent();
    private HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler();

    public String getUserAgent() {
        return userAgent;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSocketSendBufferSizeHint() {
        return socketSendBufferSizeHint;
    }

    public void setSocketSendBufferSizeHint(int socketSendBufferSizeHint) {
        this.socketSendBufferSizeHint = socketSendBufferSizeHint;
    }

    public int getSocketReceiveBufferSizeHint() {
        return socketReceiveBufferSizeHint;
    }

    public void setSocketReceiveBufferSizeHint(int socketReceiveBufferSizeHint) {
        this.socketReceiveBufferSizeHint = socketReceiveBufferSizeHint;
    }

    public HttpRequestRetryHandler getRetryHandler() {
        return retryHandler;
    }

    public void setRetryHandler(HttpRequestRetryHandler retryHandler) {
        this.retryHandler = retryHandler;
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

    public String getProxyDomain() {
        return proxyDomain;
    }

    public void setProxyDomain(String proxyDomain) {
        this.proxyDomain = proxyDomain;
    }

    public String getProxyWorkstation() {
        return proxyWorkstation;
    }

    public void setProxyWorkstation(String proxyWorkstation) {
        this.proxyWorkstation = proxyWorkstation;
    }

    private static String initializeUserAgent() {
        if(staticUserAgent == null){
            StringBuilder buffer = new StringBuilder(1024);
            buffer.append("opsgenie-sdk-java/1.0");
            buffer.append(" ");
            buffer.append(System.getProperty("os.name").replace(' ', '_')).append("/").append(System.getProperty("os.version").replace(' ', '_'));
            buffer.append(" ");
            buffer.append(System.getProperty("java.vm.name").replace(' ', '_')).append("/").append(System.getProperty("java.vm.version").replace(' ', '_'));

            String region = "";
            try {
                region = " " + System.getProperty("user.language").replace(' ', '_') + "_" + System.getProperty("user.region").replace(' ', '_');
            } catch (Exception ignored) {
            }
            buffer.append(region);
            staticUserAgent = buffer.toString();
        }
        return staticUserAgent;
    }


}
