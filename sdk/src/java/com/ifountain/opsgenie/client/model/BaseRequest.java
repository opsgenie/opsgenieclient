package com.ifountain.opsgenie.client.model;

/**
 * Base class for container objects which provides request parameters for OpsGenie service calls.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 2:03 PM
 */
public abstract class BaseRequest implements Request {
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
}
