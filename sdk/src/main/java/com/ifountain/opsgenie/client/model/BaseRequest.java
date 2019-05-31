package com.ifountain.opsgenie.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.util.JsonUtils;

import java.util.Map;

/**
 * Base class for container objects which provides content parameters for
 * OpsGenie service calls.
 *
 * @author Sezgin Kucukkaraaslan
 */
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public abstract class BaseRequest<T extends BaseResponse,K extends BaseRequest> implements Request {
    private String apiKey;

    /**
     * Api key used for authenticating API requests.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the customer key used for authenticating API requests.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Sets the customer key used for authenticating API requests.
     */
    public K withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return (K) this;
    }

    /**
     * check the parameters for validation. It will be overridden by necessary
     * Requests.
     *
     * @throws OpsGenieClientValidationException when api key is null!
     */
    @JsonIgnore
    public void validate() throws OpsGenieClientValidationException {
        if (apiKey == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.API_KEY);
    }

    /**
     * @deprecated Use getApiKey
     */
    @JsonIgnore
    @Deprecated
    public String getCustomerKey() {
        return apiKey;
    }

    /**
     * @deprecated Use setApiKey
     */
    @Deprecated
    public void setCustomerKey(String apiKey) {
        setApiKey(apiKey);
    }

    /**
     * convertes request to map
     */
    @Deprecated
    public Map serialize() throws OpsGenieClientValidationException {
        validate();
        try {
            return JsonUtils.toMap(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create response object
     */
    public abstract T createResponse();
}
