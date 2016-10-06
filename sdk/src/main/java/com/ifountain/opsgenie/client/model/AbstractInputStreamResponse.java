package com.ifountain.opsgenie.client.model;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/14/12 4:08 PM
 */
public abstract class AbstractInputStreamResponse extends BaseResponse{

    private InputStream inputStream;

    /**
     * Stream data of the response content.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets the stream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Convert map data to response
     */
    @Deprecated
    public void deserialize(Map data) throws ParseException {
        throw new UnsupportedOperationException("unsupported method deserialize");
    }
}
