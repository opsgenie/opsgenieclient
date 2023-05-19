package com.ifountain.opsgenie.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.http.HttpHeaders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for container objects which provides content parameters for
 * OpsGenie service calls.
 *
 * @author Sezgin Kucukkaraaslan
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public abstract class BaseRequest<T extends BaseResponse,K extends BaseRequest> implements Request {
    @JsonIgnore
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

    @JsonIgnore
    public Map<String,Object> getRequestParams(){
        return new HashMap<>();
    }

    @JsonIgnore
    public Map<String,String> getReqHeadersForGetOrDelete(){
        return addGenieKey();
    }

    @JsonIgnore
    public Map<String,String> getReqHeadersForPostOrPatch(){
        Map<String, String> headers = addGenieKey();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        return headers;
    }

    private Map<String, String> addGenieKey() {
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","GenieKey "+apiKey);
        return headers;
    }

    public static boolean isValidDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
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
