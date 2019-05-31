package com.ifountain.opsgenie.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * Base class for container objects which provides http parameters for OpsGenie service calls.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 2:03 PM
 */
public abstract class BaseRequestWithHttpParameters<T extends BaseResponse, K extends BaseRequestWithHttpParameters> extends BaseRequest<T, K> implements Request {
    @JsonIgnore
    private Map<String, Object> httpParameters;

    /**
     * http parameters to be sent with OpsGenie service calls
     */
    public Map<String, Object> getHttpParameters() {
        return httpParameters;
    }

    /**
     * Sets the http parameters to be sent with OpsGenie service calls
     */
    public void setHttpParameters(Map<String, Object> httpParameters) {
        this.httpParameters = httpParameters;
    }

    public K withHttpParameters(Map<String, Object> httpParameters) {
        this.httpParameters = httpParameters;
        return (K) this;
    }
}
