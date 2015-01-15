package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an add note api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:00 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addNote(AddNoteRequest)
 */
public class AddNoteRequest extends BaseAlertRequestWithSource<AddNoteResponse> {
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
    public Map serialize() throws OpsGenieClientValidationException {
        Map<String, Object> json  = super.serialize();
        json.put(OpsGenieClientConstants.API.NOTE, getNote());
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
