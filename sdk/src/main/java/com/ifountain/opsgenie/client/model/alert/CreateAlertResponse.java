package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponseWithId;

/**
 * Represents OpsGenie service response for a create alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#createAlert(CreateAlertRequest)
 */
public class CreateAlertResponse extends BaseResponseWithId {

    /**
     * Id of the created alert
     */
    public String getAlertId() {
        return getId();
    }

    /**
     * Sets the id of the created alert.
     */
    public void setAlertId(String alertId) {
        this.setId(alertId);
    }

}
