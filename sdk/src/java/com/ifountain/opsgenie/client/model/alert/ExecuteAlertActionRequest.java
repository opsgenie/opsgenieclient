package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make an execute aler action api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 10/30/12 4:59 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#executeAlertAction(ExecuteAlertActionRequest)
 */
public class ExecuteAlertActionRequest extends BaseRequest<ExecuteAlertActionResponse> {
    private String alertId;
    private String alias;
    private String action;
    private String user;
    private String note;

    /**
     * Rest api uri of execute alert action operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/executeAction";
    }

    /**
     * The id of the alert that the note will be added.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that the note will be added. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * The user defined identifier of the alert that the note will be added.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that the note will be added. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * The action to be executed.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action to be executed.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * The user who is performing the add note operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add note operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Additional alert note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional alert note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.ACTION, getAction());
        if (getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, getAlertId());
        if (getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        if (getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, getUser());
        if (getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, getNote());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ExecuteAlertActionResponse createResponse() {
        return new ExecuteAlertActionResponse();
    }
}
