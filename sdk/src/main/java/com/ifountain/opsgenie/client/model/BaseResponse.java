package com.ifountain.opsgenie.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ifountain.opsgenie.client.util.JsonUtils;


import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseResponse implements Response {
    private boolean success = true;
    private long took = 0;
    @JsonIgnore
    private String json;

    private String requestId;

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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
     * @deprecated Use fromJson
     *
     */

    @Deprecated
    public void deserialize(Map data) throws ParseException {
        try {
            fromJson(JsonUtils.toJson(data));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParseException(e.getMessage(), -1);
        }
    }

    /**
     * Convert json data to response
     */
    public void fromJson(String json) throws IOException, ParseException {
        JsonUtils.fromJson(this, json);
        this.json = json;
    }
}
