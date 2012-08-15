package com.ifountain.opsgenie.client.marid.http;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/20/12
 * Time: 2:45 PM
 */
public class HTTPRequest {
    private Map<String, String> parameters;
    private Map<String, Object> contentParameters;
    private String url;
    private SocketAddress remoteAddress;
    private ChannelBuffer channelBuffer;
    private Map headers;
    private String method;
    public HTTPRequest(SocketAddress remoteAddress, ChannelBuffer buffer) {
        this.channelBuffer = buffer;
        this.remoteAddress = remoteAddress;
    }

    public String getClientIpAddress() {
        if(remoteAddress instanceof InetSocketAddress){
            return ((InetSocketAddress)remoteAddress).getAddress().getHostAddress();
        }
        return remoteAddress.toString();
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getParameter(String parameterName) {
        return getParameters().get(parameterName);
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getUrlWithMethod() {
        StringBuilder builder = new StringBuilder();
        builder.append(method).append("-").append(url);
        return builder.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setChannelBuffer(ChannelBuffer buffer) {

    }

    public String getContent() {
        return channelBuffer.toString(CharsetUtil.UTF_8);
    }

    public byte[] getContentAsByte() {
        return channelBuffer.toByteBuffer().array();
    }


    public Map getHeaders() {
        return headers;
    }

    public String getHeader(String headerName) {
        return (String) getHeaders().get(headerName);
    }

    public void setHeaders(Map headers) {
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void appendParameters(Map<String, String> params) {
        if (this.parameters == null) {
            this.parameters = params;
        } else {
            this.parameters.putAll(params);
        }
    }

    public Map<String, Object> getContentParameters() {
        return contentParameters;
    }

    public void setContentParameters(Map<String, Object> contentParameters) {
        this.contentParameters = contentParameters;
    }
}
