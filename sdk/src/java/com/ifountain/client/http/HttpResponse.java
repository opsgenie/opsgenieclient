package com.ifountain.client.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 2:35 PM
 */
public class HttpResponse {
    private byte[] content;
    private Map<String, String> headers = new HashMap<String, String>();
    private int statusCode;

    public byte[] getContent() {
        return content;
    }
    public String getContentAsString() {
        return new String(content);
    }



    public void setContent(byte[] content) {
        this.content = content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
