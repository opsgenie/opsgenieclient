package com.ifountain.opsgenie.client.marid.http;

import org.apache.commons.codec.binary.Base64;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.littleshoot.proxy.*;

import java.net.PasswordAuthentication;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 7/30/12 10:05 AM
 */
public class HttpProxy {
    private DefaultHttpProxyServer proxyServer;
    private HttpProxyConfig config;

    public HttpProxy(HttpProxyConfig config) {
        this.config = config;
        initProxyServer();
    }

    private void initProxyServer(){
        if(config.getClientProxyConfiguration() != null){
            final String chainProxyString = config.getClientProxyConfiguration().getProxyHost()+":"+config.getClientProxyConfiguration().getProxyPort();
            final boolean needAuthentication = config.getClientProxyConfiguration().getProxyUsername() != null && config.getClientProxyConfiguration().getProxyPassword() != null;
            final String authStr;
            if(needAuthentication){
                String usernamePass = (config.getClientProxyConfiguration().getProxyUsername()+":"+config.getClientProxyConfiguration().getProxyPassword());
                authStr = new String(Base64.encodeBase64(usernamePass.getBytes()));
            }
            else {
                authStr = null;
            }
            final HttpRequestFilter requestFilter = new HttpRequestFilter() {

                public void filter(HttpRequest httpRequest) {
                    if(needAuthentication){
                        httpRequest.addHeader("Proxy-Authorization", "Basic "+authStr);
                    }
                }
            };
            proxyServer = new DefaultHttpProxyServer(this.config.getPort(), new HttpResponseFilters() {
                public HttpFilter getFilter(String hostAndPort) {
                    return null;
                }
            }, new ChainProxyManager() {
                public void onCommunicationError(String hostAndPort) {
                }
                public String getChainProxy(HttpRequest request) {
                    if (needAuthentication && request.getMethod().equals(HttpMethod.CONNECT)) {
                        requestFilter.filter(request);
                    }
                    return chainProxyString;
                }
            }, null, requestFilter);
        }
        else{
            proxyServer = new DefaultHttpProxyServer(this.config.getPort());
        }
        if(config.getHost() != null){
            proxyServer.setHost(config.getHost());
        }
        if (this.config.getUsername() != null && this.config.getUsername() != null) {
            proxyServer.addProxyAuthenticationHandler(new ProxyAuthorizationHandler() {
                @Override
                public boolean authenticate(String userName, String password) {
                    return HttpProxy.this.config.getUsername().equals(userName) && HttpProxy.this.config.getPassword().equals(password);
                }
            });
        }
    }

    public void start() {
        proxyServer.start();
    }

    public void stop() {
        proxyServer.stop();
    }

}
