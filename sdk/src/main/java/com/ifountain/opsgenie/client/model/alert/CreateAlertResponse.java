package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for a create alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#createAlert(CreateAlertRequest)
 */
public class CreateAlertResponse extends BaseResponse {
    private String id;

    /**
     * Id of the created alert
     */
    public String getAlertId() {
        return id;
    }

    /**
     * Sets the id of the created alert.
     */
    public void setAlertId(String alertId) {
        this.id = alertId;
    }

    /**
     * Id of the created alert
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the created alert.
     */
    public void setId(String id) {
        this.id = id;
    }

}
