package com.ifountain.opsgenie.client.util;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Manifest;

/**
 * Client configuration options such as proxy settings, user agent string, max retry attempts, etc.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 2:53 PM
 * @see com.ifountain.opsgenie.client.OpsGenieClient
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
     * Returns the optional proxy host the client will connect through.
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Sets the optional proxy host the client will connect through.
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * Returns the optional proxy port the client will connect through.
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * Sets the optional proxy port the client will connect through.
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * Returns the optional proxy user name to use if connecting through a proxy.
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * Sets the optional proxy user name to use if connecting through a proxy.
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    /**
     * Returns the optional proxy password to use when connecting through a proxy.
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * Sets the optional proxy password to use when connecting through a proxy.
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    /**
     * Returns the optional Windows domain name for configuring an NTLM proxy.
     */
    public String getProxyDomain() {
        return proxyDomain;
    }

    /**
     * Sets the optional Windows domain name for configuration an NTML proxy.
     */
    public void setProxyDomain(String proxyDomain) {
        this.proxyDomain = proxyDomain;
    }

    /**
     * Returns the optional Windows workstation name for configuring NTLM proxy support.
     */
    public String getProxyWorkstation() {
        return proxyWorkstation;
    }

    /**
     * Sets the optional Windows workstation name for configuring NTLM proxy support.
     */
    public void setProxyWorkstation(String proxyWorkstation) {
        this.proxyWorkstation = proxyWorkstation;
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
