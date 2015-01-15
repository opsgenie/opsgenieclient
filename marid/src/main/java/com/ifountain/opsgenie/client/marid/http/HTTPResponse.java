package com.ifountain.opsgenie.client.marid.http;

import org.jboss.netty.handler.codec.http.HttpResponseStatus;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/16/12
 * Time: 10:40 AM
 */
public class HTTPResponse {
    public static final byte[] EMPTY_CONTENT = new byte[0];
    private String contentType;
    private HttpResponseStatus status = HttpResponseStatus.OK;
    private byte[] content = EMPTY_CONTENT;
    private int contentLength = 0;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
        if(content != null){
            contentLength = content.length;
        }
        else{
            contentLength = 0;
        }
    }

    public void setContent(String content) {
        this.setContent(content.getBytes());
    }

    public int getContentLength() {
        return contentLength;
    }
}
