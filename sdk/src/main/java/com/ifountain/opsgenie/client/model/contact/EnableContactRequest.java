package com.ifountain.opsgenie.client.model.contact;

/**
 * Container for the parameters to make an enable contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#enableContact(EnableContactRequest)
 */
public class EnableContactRequest extends BaseContactRequestWithId<EnableContactResponse, EnableContactRequest> {

    @Override
    public String getEndPoint() {
        return "/v1/json/user/contact/enable";
    }

    @Override
    public EnableContactResponse createResponse() {
        return new EnableContactResponse();
    }
}
