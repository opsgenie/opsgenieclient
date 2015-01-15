package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a take ownership api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:32 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#takeOwnership(TakeOwnershipRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
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
    public TakeOwnershipResponse createResponse() {
        return new TakeOwnershipResponse();
    }

}
