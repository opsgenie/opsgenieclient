package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make an acknowledge alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:17 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#acknowledge(AcknowledgeRequest)
 */
public class AcknowledgeRequest extends BaseAlertRequestWithId<AcknowledgeResponse> {
    private String user;
    private String note;

    /**
     * Rest api uri of acknowledge alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/acknowledge";
    }

    /**
     * The user who is performing the acknowledge alert operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the acknowledge alert operation.
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
        Map json = super.serialize();
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
    public AcknowledgeResponse createResponse() {
        return new AcknowledgeResponse();
    }
}
