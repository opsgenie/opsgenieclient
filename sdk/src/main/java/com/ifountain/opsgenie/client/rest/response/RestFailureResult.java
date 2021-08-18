package com.ifountain.opsgenie.client.rest.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RestFailureResult {

    @JsonProperty("message")
    private String message;

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("took")
    private long took;

    @JsonIgnore
    private int statusCode;

    @JsonIgnore
    private String rawData;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("errors")
    private Map<String, String> errors;

    public String getMessage() {
        return message;
    }

    public RestFailureResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public RestFailureResult setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public long getTook() {
        return took;
    }

    public RestFailureResult setTook(long took) {
        this.took = took;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public RestFailureResult setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getRawData() {
        return rawData;
    }

    public RestFailureResult setRawData(String rawData) {
        this.rawData = rawData;
        return this;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public RestFailureResult setErrors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public RestFailureResult setCode(Integer code) {
        this.code = code;
        return this;
    }
}
