package com.ifountain.opsgenie.client.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestSuccessResult<T> {

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty
    private Double took;

    @JsonProperty("code")
    private int code;

    @JsonProperty("data")
    private T data;

    @JsonIgnore
    private int statusCode;

    @JsonIgnore
    private String rawData;

    public String getRequestId() {
        return requestId;
    }

    public RestSuccessResult setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestSuccessResult setData(T data) {
        this.data = data;
        return this;
    }

    public Double getTook() {
        return took;
    }

    public RestSuccessResult setTook(Double took) {
        this.took = took;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public RestSuccessResult setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getRawData() {
        return rawData;
    }

    public RestSuccessResult setRawData(String rawData) {
        this.rawData = rawData;
        return this;
    }
}
