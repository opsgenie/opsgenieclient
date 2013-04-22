package com.ifountain.opsgenie.client.model;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for container objects which provides request parameters for OpsGenie service calls.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 2:03 PM
 */
public abstract class BaseRequest<T extends BaseResponse> implements Request {
    private String customerKey;

    /**
     * Customer key used for authenticating API requests.
     */
    public String getCustomerKey() {
        return customerKey;
    }

    /**
     * Sets the tustomer key used for authenticating API requests.
     */
    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    /**
     * convertes request to map
     */
    public Map serialize(){
        Map map = new HashMap();
        map.put(OpsGenieClientConstants.API.CUSTOMER_KEY, customerKey);
        return map;
    }

    /**
     * Create response object
     */
    public abstract T createResponse();
}
