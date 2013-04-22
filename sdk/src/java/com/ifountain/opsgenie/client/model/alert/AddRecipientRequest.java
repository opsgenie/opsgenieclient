package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make an add recipient call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 1/9/13 4:03 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addRecipient(AddRecipientRequest)
 */
public class AddRecipientRequest extends BaseRequest<AddRecipientResponse> {
    private String alertId;
    private String alias;
    private String user;
    private String recipient;
    private String note;

    /**
     * The id of the alert that the new recipient will be added.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that the new recipient will be added. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * User defined identifier of the alert that the new recipient will be added.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that the new recipient will be added. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Rest api uri of assign ownership operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/recipient";
    }

    /**
     * The user who is performing the add recipient operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add recipient operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * The recipient that will be added.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the recipient that will be added.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
        json.put(OpsGenieClientConstants.API.RECIPIENT, getRecipient());
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
    public AddRecipientResponse createResponse() {
        return new AddRecipientResponse();
    }
}
