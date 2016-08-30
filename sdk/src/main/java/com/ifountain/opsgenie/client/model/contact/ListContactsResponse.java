package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Contact;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list contact request.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#listContacts(ListContactsRequest)
 */
public class ListContactsResponse extends BaseResponse {
    private List<Contact> contacts;

    /**
     * List of contacts
     *
     * @see Contact
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets list of contacts
     *
     * @see Contact
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> contactsData = (List<Map>) data.get(OpsGenieClientConstants.API.USER_CONTACTS);
        contacts = new ArrayList<Contact>();
        for (Map contactData : contactsData) {
            Contact contact = new Contact();
            contact.fromMap(contactData);
            contacts.add(contact);
        }
    }

}
