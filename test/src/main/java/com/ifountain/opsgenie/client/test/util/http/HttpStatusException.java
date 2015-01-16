/**
 * Created on Jan 4, 2007
 *
 * Author Sezgin kucukkaraaslan
 */
package com.ifountain.opsgenie.client.test.util.http;

import org.apache.http.client.ClientProtocolException;

public class HttpStatusException extends ClientProtocolException {
    private static final long serialVersionUID = -4036160410322780007L;
    private int status;
    private byte[] content;

    public HttpStatusException(String message, int status, byte[] content) {
        super(message);
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return super.toString() + ". Content:\n" +
                new String(content);
    }
}
