package com.ifountain.opsgenie.client.model.contact;


import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make a list contacts api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#listContact(ListContactsRequest)
 */
public class ListContactsRequest extends BaseUserRequest<ListContactsResponse> {

    /**
     * Rest api uri of listing contact operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/contact";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListContactsResponse createResponse() {
        return new ListContactsResponse();
    }

}
