package com.ifountain.opsgenie.client.model;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 2:03 PM
 */
public abstract class BaseRequest implements Request{
    private String customerKey;

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }
}
