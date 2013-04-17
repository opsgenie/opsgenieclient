package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#getAlert(GetAlertRequest)
 */
public class GetAlertRequest extends BaseRequest {
    private String alertId;
    private String alias;

    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    /**
     * The id of the alert that will be retreived.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that will be retreived. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * User defined identifier of the alert that will be retreived.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that will be retreived. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}
