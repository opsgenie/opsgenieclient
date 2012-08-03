package com.ifountain.opsgenie.client.model;

/**
 * Represents OpsGenie service response for a create alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:41 AM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#createAlert(CreateAlertRequest)
 */
public class CreateAlertResponse implements Response {
    private String alertId;

    /**
     * Id of the created alert
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the created alert.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }
}
