package com.ifountain.opsgenie.client.http;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.util.CharsetUtil;

import java.util.Map;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 10:49 AM
 */
public class HttpTestRequest {
    private Map<String, String> parameters;
    private String url;
    private ChannelBuffer channelBuffer;
    private Map headers;
    private String method;
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HttpTestRequest(ChannelBuffer buffer) {
        this.channelBuffer = buffer;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
