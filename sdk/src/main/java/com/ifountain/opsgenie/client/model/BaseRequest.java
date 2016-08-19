package com.ifountain.opsgenie.client.model;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.ValidationException;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Base class for container objects which provides content parameters for OpsGenie service calls.
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
     * check the parameters for validation.
     * It will be overridden by necessary Requests.
     * @throws ValidationException when api key is null!
     */
    @JsonIgnore
    public void validate() throws OpsGenieClientValidationException{
    	if(apiKey == null)
    		throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.API_KEY);
    }

    /**
     * Sets the customer key used for authenticating API requests.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @deprecated
     * Use getApiKey
     */
    @JsonIgnore
    public String getCustomerKey() {
        return apiKey;
    }

    /**
     * @deprecated
     * Use setApiKey
     */
    public void setCustomerKey(String apiKey) {
        setApiKey(apiKey);
    }

    /**
     * convertes request to map
     */
    public Map serialize() throws OpsGenieClientValidationException {
    	validate();
    	ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		return  new TreeMap(mapper.convertValue(this, Map.class));
    }

    /**
     * Create response object
     */
    public abstract T createResponse();
}
