package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make an enable contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#enableContact(EnableContactRequest)
 */
public class EnableContactRequest extends BaseUserRequest<EnableContactResponse> {
    private String id;

    @Override
    public String getEndPoint() {
        return "/v1/json/user/contact/enable";
    }

    @Override
    public EnableContactResponse createResponse() {
        return new EnableContactResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
