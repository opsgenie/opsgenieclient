package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponseWithId;

/**
 * Represents OpsGenie service response for a create alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#createAlert(CreateAlertRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.SuccessResponse}
 */
@Deprecated
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
