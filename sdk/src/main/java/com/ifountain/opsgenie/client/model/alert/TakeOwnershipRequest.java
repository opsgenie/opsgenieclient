package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make a take ownership api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#takeOwnership(TakeOwnershipRequest)
 */
public class TakeOwnershipRequest extends AddNoteRequest {

    /**
     * Rest api uri of take ownership operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/takeOwnership";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public TakeOwnershipResponse createResponse() {
        return new TakeOwnershipResponse();
    }

}
