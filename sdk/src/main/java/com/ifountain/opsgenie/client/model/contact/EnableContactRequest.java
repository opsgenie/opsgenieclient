package com.ifountain.opsgenie.client.model.contact;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make an enable contact api call.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#enableContact(com.ifountain.opsgenie.client.model.contact.EnableContactRequest)
 */
public class EnableContactRequest extends BaseRequest<EnableContactResponse> {
	private String username;
	private String userId;
	private String id;

	/**
	 * Rest api uri of enable contact operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/contact/enable";
	}

	@Override
	public Map serialize() throws OpsGenieClientValidationException {
		Map json = super.serialize();
		if (getUsername() != null)
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		if (getUserId() != null)
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
		if (getId() != null)
			json.put(OpsGenieClientConstants.API.ID, getId());
		return json;
	}

	@Override
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	public EnableContactResponse createResponse() {
		return new EnableContactResponse();
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
