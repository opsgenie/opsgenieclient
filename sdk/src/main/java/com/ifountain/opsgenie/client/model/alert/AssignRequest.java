package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make an assign ownership call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#assign(AssignRequest)
 */
public class AssignRequest extends AddNoteRequest {
    private String owner;

    /**
     * Rest api uri of assign ownership operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/assign";
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AssignResponse createResponse() {
        return new AssignResponse();
    }
}
