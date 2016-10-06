package com.ifountain.opsgenie.client.util;

import com.ifountain.opsgenie.client.http.DefaultOpsgenieRequestRetryHandler;
import com.ifountain.opsgenie.client.http.OpsgenieRequestRetryHandler;
import org.apache.http.auth.Credentials;

import java.util.List;
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
    private List<Integer> httpsPorts;
    private int maxConnections = 50;
    private int socketTimeout = 50000;
    private Credentials credentials = null;
    private int connectionTimeout = 50000;
    private int socketSendBufferSizeHint = 0;
    private int socketReceiveBufferSizeHint = 0;

    private String userAgent = initializeUserAgent();
    private OpsgenieRequestRetryHandler retryHandler = new DefaultOpsgenieRequestRetryHandler();

    /**
     * Returns https ports bypassing certification check
     */
    public List<Integer> getHttpsPorts() {
        return httpsPorts;
    }

    /**
     * sets https ports bypassing certification check
     */
    public ClientConfiguration setHttpsPorts(List<Integer> httpsPorts) {
        this.httpsPorts = httpsPorts;
        return this;
    }

    /**
     * Returns the credentials for http client.
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets http client credentials.
     */
    public ClientConfiguration setCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    /**
     * Returns the HTTP user agent header to send with all requests.
     */


    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the HTTP user agent header
     */
    public ClientConfiguration setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
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
    public ClientConfiguration setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
        return this;
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
    public ClientConfiguration setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
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
    public ClientConfiguration setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
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
    public ClientConfiguration setSocketSendBufferSizeHint(int socketSendBufferSizeHint) {
        this.socketSendBufferSizeHint = socketSendBufferSizeHint;
        return this;
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
    public ClientConfiguration setSocketReceiveBufferSizeHint(int socketReceiveBufferSizeHint) {
        this.socketReceiveBufferSizeHint = socketReceiveBufferSizeHint;
        return this;
    }

    /**
     * Returns Apache Http Client retry handler implementation.
     */
    public OpsgenieRequestRetryHandler getRetryHandler() {
        return retryHandler;
    }

    /**
     * Sets Apache Http Client retry handler. Default is org.apache.http.client.impl.DefaultHttpRequestRetryHandler with 3 retries.
     */
    public ClientConfiguration setRetryHandler(OpsgenieRequestRetryHandler retryHandler) {
        this.retryHandler = retryHandler;
        return this;
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
    public ClientConfiguration setClientProxyConfiguration(ClientProxyConfiguration clientProxyConfiguration) {
        this.clientProxyConfiguration = clientProxyConfiguration;
        return this;
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
