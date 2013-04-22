package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make a delete alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/4/12 3:38 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#deleteAlert(DeleteAlertRequest)
 */
public class DeleteAlertRequest extends BaseRequest<DeleteAlertResponse> {
    private String alertId;
    private String alias;
    private String user;

    /**
     * The id of the alert that will be deleted.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that will be deleted. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * User defined identifier of the alert that will be deleted.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that will be deleted. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Rest api uri of delete alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    /**
     * The user who is performing the delete alert operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the delete alert operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map parameters = super.serialize();
        if (getAlertId() != null)
            parameters.put(OpsGenieClientConstants.API.ALERT_ID, getAlertId());
        if (getAlias() != null)
            parameters.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        if (getUser() != null)
            parameters.put(OpsGenieClientConstants.API.USER, getUser());
        return parameters;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteAlertResponse createResponse() {
        return new DeleteAlertResponse();
    }
}
