package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientValidationException;
import com.ifountain.client.OpsGenieClientConstants;

import java.util.Map;

/**
 * Container for the parameters to make a take ownership api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:32 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#takeOwnership(TakeOwnershipRequest)
 */
public class TakeOwnershipRequest extends BaseAlertRequestWithSource<TakeOwnershipResponse> {
    private String user;
    private String note;

    /**
     * Rest api uri of take ownership operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/takeOwnership";
    }

    /**
     * The user who is performing the take ownership operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the take ownership operation.
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        Map json = super.serialize();
        if (getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, getUser());
        if (getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, getNote());
        return json;
    }
    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public TakeOwnershipResponse createResponse() {
        return new TakeOwnershipResponse();
    }

}
