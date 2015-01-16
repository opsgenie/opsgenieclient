package com.ifountain.opsgenie.client.http;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/8/12 10:47 AM
 */
public class HttpTimeoutResponse extends HttpTestResponse {
    private long timeout;

    public HttpTimeoutResponse(String content, long timeout) {
        super(content);
        this.timeout = timeout;
    }

    public HttpTimeoutResponse(byte[] content, long timeout) {
        super(content);
        this.timeout = timeout;
    }

    public HttpTimeoutResponse(byte[] content, int status, long timeout) {
        super(content, status);
        this.timeout = timeout;
    }

    public HttpTimeoutResponse(byte[] content, int status, String contentType, long timeout) {
        super(content, status, contentType);
        this.timeout = timeout;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
