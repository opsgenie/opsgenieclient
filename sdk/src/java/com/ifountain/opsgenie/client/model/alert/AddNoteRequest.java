package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make an add note api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:00 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addNote(AddNoteRequest)
 */
public class AddNoteRequest extends BaseRequest<AddNoteResponse> {
    private String alertId;
    private String alias;
    private String note;
    private String user;

    /**
     * Rest api uri of add note operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/note";
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
     * The note that will be added to alert.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note that will be added to alert.
     */
    public void setNote(String note) {
        this.note = note;
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map<String, Object> json  = super.serialize();
        json.put(OpsGenieClientConstants.API.NOTE, getNote());
        if (getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, getAlertId());
        if (getAlias() != null) json.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        if (getUser() != null) json.put(OpsGenieClientConstants.API.USER, getUser());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddNoteResponse createResponse() {
        return new AddNoteResponse();
    }
}
