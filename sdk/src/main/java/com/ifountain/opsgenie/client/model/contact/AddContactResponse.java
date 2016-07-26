package com.ifountain.opsgenie.client.model.contact;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseResponse;


/**
 * Represents OpsGenie service response for add contact request.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#addContact(AddContactRequest)
 */
public class AddContactResponse extends BaseResponse {
	private String id;

	/**
	 * Id of the added contact
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the added contact
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void deserialize(Map data) throws ParseException {
		super.deserialize(data);
		id = (String) data.get("id");
	}

}
