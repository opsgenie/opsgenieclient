package com.ifountain.client.model;

import com.ifountain.client.ClientConstants;

import java.text.ParseException;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/14/12 4:08 PM
 */
public abstract class BaseResponse implements Response{
    private boolean success = true;
    private long took = 0;
    private String json;

    /**
     * True if the operation is successful.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the operation success state.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns the number of milliseconds that have elapsed during the operation.
     */
    public long getTook() {
        return took;
    }

    /**
     * Sets the number of milliseconds that have elapsed during the operation.
     */
    public void setTook(long took) {
        this.took = took;
    }

    /**
     * Raw JSON data
     */
    public String getJson() {
        return json;
    }

    /**
     * Sets raw JSON data
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Convert map data to response
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        if(data.containsKey(ClientConstants.API.TOOK)){
            took = ((Number)data.get(ClientConstants.API.TOOK)).longValue();
        }
    }
}
