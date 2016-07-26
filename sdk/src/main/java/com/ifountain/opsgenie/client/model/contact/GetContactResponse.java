package com.ifountain.opsgenie.client.model.contact;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Contact;
/**
 * Represents OpsGenie service response for get contact request.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#getContact(GetContactRequest)
 */
public class GetContactResponse extends BaseResponse {
	private Contact contact;
    /**
     * Details of contact
     * @see Contact
     */
	public Contact getContact() {
		return contact;
	}    
	@Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        contact = new Contact();
        contact.fromMap(data);
    }
    /**
     * Sets details of contact
     * @see Contact
     */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	

}
