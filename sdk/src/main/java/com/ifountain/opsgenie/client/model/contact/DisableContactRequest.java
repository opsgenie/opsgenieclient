package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make an disable contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#disableContact(DisableContactRequest)
 */
public class DisableContactRequest extends BaseUserRequest<DisableContactResponse> {
    private String id;

    @Override
    public String getEndPoint() {
        return "/v1/json/user/contact/disable";
    }

    @Override
    public DisableContactResponse createResponse() {
        return new DisableContactResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
