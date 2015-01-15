package com.ifountain.opsgenie.client.marid.http;

import com.ifountain.opsgenie.client.util.ClientProxyConfiguration;
import org.littleshoot.proxy.DefaultHttpProxyServer;
import org.littleshoot.proxy.ProxyAuthorizationHandler;

public class HttpProxyConfig {
    private String host = "127.0.0.1";
    private int port;
    private String username;
    private String password;
    private ClientProxyConfiguration clientProxyConfiguration;

    public HttpProxyConfig(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientProxyConfiguration getClientProxyConfiguration() {
        return clientProxyConfiguration;
    }

    public void setClientProxyConfiguration(ClientProxyConfiguration clientProxyConfiguration) {
        this.clientProxyConfiguration = clientProxyConfiguration;
    }
}
