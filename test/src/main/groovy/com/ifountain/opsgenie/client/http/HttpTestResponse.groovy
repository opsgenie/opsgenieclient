package com.ifountain.opsgenie.client.http;

import org.apache.http.HttpStatus;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 10:55 AM
 */
public class HttpTestResponse {
    private String contentType = "text/plain; charset=UTF-8";
    private int status = HttpStatus.SC_OK;
    private byte[] content;

    public HttpTestResponse(String content) {
        this(content.getBytes());
    }

    public HttpTestResponse(byte[] content) {
        this.content = content;
    }

    public HttpTestResponse(byte[] content, int status) {
        this(content);
        this.status = status;
    }

    public HttpTestResponse(byte[] content, int status, String contentType) {
        this(content, status);
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public int getStatus() {
        return status;
    }

    public byte[] getContent() {
        return content;
    }
}
