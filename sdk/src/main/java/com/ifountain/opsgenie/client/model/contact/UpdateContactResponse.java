package com.ifountain.opsgenie.client.model.contact;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseResponse;
/**
 * Represents OpsGenie service response for update contact request.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#updateContact(UpdateContactRequest)
 */
public class UpdateContactResponse extends BaseResponse {
	private String id;

	/**
	 * Id of the updated contact
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the updated contact
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
