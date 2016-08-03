package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make an disable contact api call.
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#disableContact(com.ifountain.opsgenie.client.model.contact.DisableContactRequest)
 */
public class DisableContactRequest extends BaseRequest<DisableContactResponse> {
	private String username;
	private String userId;
	private String id;
	
	@Override
	public String getEndPoint() {
		return "/v1/json/user/contact/disable";
	}
	@Override
	public DisableContactResponse createResponse() {
		return new DisableContactResponse();
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
