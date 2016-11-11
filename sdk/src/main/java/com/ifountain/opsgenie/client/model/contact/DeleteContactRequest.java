package com.ifountain.opsgenie.client.model.contact;


/**
 * Container for the parameters to make a delete contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#deleteContact(DeleteContactRequest)
 */
public class DeleteContactRequest extends BaseContactRequestWithId<DeleteContactResponse, DeleteContactRequest> {

    /**
     * Rest api uri of deleting contact operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/contact";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteContactResponse createResponse() {
        return new DeleteContactResponse();
    }

}
