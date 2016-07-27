package com.ifountain.opsgenie.client.model.contact;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Contact;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;

/**
 * Container for the parameters to make an add contact api call.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#addContact(AddContactRequest)
 */
public class AddContactRequest extends BaseRequest<AddContactResponse> {
	private String username;
	private String userId;
	private Method method;
	private String to;

	/**
	 * Rest api uri of adding contact operation.
	 */
	public String getEndPoint() {
		return "/v1/json/user/contact";
	}

	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
	 */
	@Override
	public Map serialize() throws OpsGenieClientValidationException {
		Map json = super.serialize();
		if (getUsername() != null) {
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		}
		if (getUserId() != null) {
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
		}
		if (getMethod() != null) {
			json.put(OpsGenieClientConstants.API.METHOD, getMethod().value());
		}
		if (getTo() != null) {
			json.put(OpsGenieClientConstants.API.TO, getTo());
		}
		return json;
	}

	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	@Override
	public AddContactResponse createResponse() {
		return new AddContactResponse();
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

	public Contact.Method getMethod() {
		return method;
	}

	public void setMethod(Contact.Method method) {
		this.method = method;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
