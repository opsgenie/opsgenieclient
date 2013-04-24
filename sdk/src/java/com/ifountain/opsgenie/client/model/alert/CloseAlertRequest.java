package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make a close alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 2:05 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#closeAlert(CloseAlertRequest)
 */
public class CloseAlertRequest extends BaseAlertRequestWithId<CloseAlertResponse> {
    private String user;
    private String note;

    /**
     * Rest api uri of close alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/close";
    }

    /**
     * The user who is performing the close alert operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the close alert operation.
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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json  = super.serialize();
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
    public CloseAlertResponse createResponse() {
        return new CloseAlertResponse();
    }
}
