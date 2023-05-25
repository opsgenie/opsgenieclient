package com.ifountain.opsgenie.client.model.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Contact;

/**
 * Represents OpsGenie service response for get contact request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#getContact(GetContactRequest)
 */
public class GetContactResponse extends BaseResponse {

    @JsonProperty("data")
    private Contact contact;

    /**
     * Details of contact
     *
     * @see Contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets details of contact
     *
     * @see Contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
