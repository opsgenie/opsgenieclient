package com.ifountain.client.model;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for container objects which provides request parameters for OpsGenie service calls.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 2:03 PM
 */
public abstract class BaseRequest<T extends BaseResponse> implements Request {
    private String apiKey;

    /**
     * Api key used for authenticating API requests.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the api key used for authenticating API requests.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Converts request to map
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ClientConstants.API.API_KEY, apiKey);
        return map;
    }

    /**
     * Create response object
     */
    public abstract T createResponse();
}
