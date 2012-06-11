package com.ifountain.opsgenie.client.model;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 9:41 AM
 */
public class CreateAlertResponse implements Response{
    private String alertId;

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }
}
