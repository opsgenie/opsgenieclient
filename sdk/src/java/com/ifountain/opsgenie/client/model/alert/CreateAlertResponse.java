package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for a create alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:41 AM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#createAlert(CreateAlertRequest)
 */
public class CreateAlertResponse extends BaseResponse {
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
