package com.ifountain.opsgenie.client.model.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Contact;

import java.util.List;

/**
 * Represents OpsGenie service response for list contact request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#listContact(ListContactsRequest)
 */
public class ListContactsResponse extends BaseResponse {

    @JsonProperty("data")
    private List<Contact> userContacts;

    /**
     * List of contacts
     *
     * @see Contact
     */
    public List<Contact> getUserContacts() {
        return userContacts;
    }

    /**
     * Sets list of contacts
     *
     * @see Contact
     */
    public void setUserContacts(List<Contact> contacts) {
        this.userContacts = contacts;
    }

}
