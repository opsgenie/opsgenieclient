package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an assign ownership call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:36 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#assign(AssignRequest)
 */
public class AssignRequest extends BaseAlertRequestWithSource<AssignResponse> {
    private String user;
    private String owner;
    private String note;

    /**
     * Rest api uri of assign ownership operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/assign";
    }

    /**
     * The user who is performing the assign ownership operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the assign ownership operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * The user who will be the owner of the alert after the operation.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the user who will be the owner of the alert after the operation.
     */
    public void setOwner(String owner) {
        this.owner = owner;
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AssignResponse createResponse() {
        return new AssignResponse();
    }
}
