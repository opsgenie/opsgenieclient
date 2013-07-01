package com.ifountain.opsgenie.client.util;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

import java.util.jar.Manifest;

/**
 * Client configuration options such as proxy settings, user agent string, max retry attempts, etc.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 2:53 PM
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public class ClientConfiguration {
    private ClientProxyConfiguration clientProxyConfiguration;
    private static String staticUserAgent;
    private int maxConnections = 50;
    private int socketTimeout = 50000;
    private int connectionTimeout = 50000;
    private int socketSendBufferSizeHint = 0;
    private int socketReceiveBufferSizeHint = 0;

    private String userAgent = initializeUserAgent();
    private HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler();

    /**
     * Returns the HTTP user agent header to send with all requests.
     */


    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the HTTP user agent header
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Returns the maximum number of allowed open HTTP connections.
     */
    public int getMaxConnections() {
        return maxConnections;
    }

    /**
     * Sets the maximum number of allowed open HTTP connections.
     */
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    /**
     * Returns the amount of time to wait (in milliseconds) for data to be transfered over an established, open connection before the connection times out and is closed.
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * Sets the amount of time to wait (in milliseconds) for data to be transfered over an established, open connection before the connection times out and is closed.
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * Returns the amount of time to wait (in milliseconds) when initially establishing a connection before giving up and timing out.
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the amount of time to wait (in milliseconds) when initially establishing a connection before giving up and timing out.
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Returns the optional size hints (in bytes) for the low level TCP send buffer.
     */
    public int getSocketSendBufferSizeHint() {
        return socketSendBufferSizeHint;
    }

    /**
     * Sets the optional size hints (in bytes) for the low level TCP send buffer.
     */
    public void setSocketSendBufferSizeHint(int socketSendBufferSizeHint) {
        this.socketSendBufferSizeHint = socketSendBufferSizeHint;
    }

    /**
     * Returns the optional size hints (in bytes) for the low level TCP receive buffer.
     */
    public int getSocketReceiveBufferSizeHint() {
        return socketReceiveBufferSizeHint;
    }

    /**
     * Sets the optional size hints (in bytes) for the low level TCP receive buffer.
     */
    public void setSocketReceiveBufferSizeHint(int socketReceiveBufferSizeHint) {
        this.socketReceiveBufferSizeHint = socketReceiveBufferSizeHint;
    }

    /**
     * Returns Apache Http Client retry handler implementation.
     */
    public HttpRequestRetryHandler getRetryHandler() {
        return retryHandler;
    }

    /**
     * Sets Apache Http Client retry handler. Default is org.apache.http.client.impl.DefaultHttpRequestRetryHandler with 3 retries.
     */
    public void setRetryHandler(HttpRequestRetryHandler retryHandler) {
        this.retryHandler = retryHandler;
    }

    /**
     * Returns proxy configuration
     */
    public ClientProxyConfiguration getClientProxyConfiguration() {
        return clientProxyConfiguration;
    }

    /**
     * Sets proxy configuration
     */
    public void setClientProxyConfiguration(ClientProxyConfiguration clientProxyConfiguration) {
        this.clientProxyConfiguration = clientProxyConfiguration;
    }

    private static String initializeUserAgent() {
        if (staticUserAgent == null) {
            staticUserAgent = createUserAgentFromManifest(ClientConfiguration.class);
        }
        return staticUserAgent;
    }
    
    public static String createUserAgentFromManifest(Class manifestFileClass){
        Manifest manifest = ManifestUtils.loadManifest(manifestFileClass);
        Object sdkVersion = manifest.getMainAttributes().getValue("Implementation-Version");
        Object sdkProductName = manifest.getMainAttributes().getValue("Implementation-Product");
        if(sdkVersion == null) sdkVersion = "1.0";
        if(sdkProductName == null) sdkProductName = "opsgenie-sdk-java";
        return createUserAgent(String.valueOf(sdkProductName), String.valueOf(sdkVersion));
    }
    public static String createUserAgent(String agentName, String agentVersion){
        StringBuilder buffer = new StringBuilder(1024);
        buffer.append(agentName).append("/").append(agentVersion);
        buffer.append(" (");
        buffer.append(System.getProperty("os.name")).append(" ").append(System.getProperty("os.version"));
        buffer.append("; ");
        buffer.append(System.getProperty("java.vm.name")).append(" ").append(System.getProperty("java.vm.version"));
        if(System.getProperty("user.language") != null){
            buffer.append("; ").append(System.getProperty("user.language"));
        }
        if(System.getProperty("user.region") != null){
            buffer.append("; ").append(System.getProperty("user.region"));
        }
        buffer.append(")");
        return buffer.toString();
    }


}
