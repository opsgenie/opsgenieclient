package com.ifountain.opsgenie.client.model.contact;

/**
 * Container for the parameters to make an disable contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#disableContact(DisableContactRequest)
 */
public class DisableContactRequest extends BaseContactRequestWithId<DisableContactResponse, DisableContactRequest> {

    @Override
    public String getEndPoint() {
        return "/v1/json/user/contact/disable";
    }

    @Override
    public DisableContactResponse createResponse() {
        return new DisableContactResponse();
    }

}
