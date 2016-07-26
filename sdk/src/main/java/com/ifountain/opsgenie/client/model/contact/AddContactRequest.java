package com.ifountain.opsgenie.client.model.contact;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Contact;
/**
 * Container for the parameters to make an add contact api call.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#addContact(AddContactRequest)
 */
public class AddContactRequest extends BaseRequest<AddContactResponse> {
	private String username;
	private String userId;
	private Contact.Method method;
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
			json.put(OpsGenieClientConstants.API.METHOD, getMethod().name());
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
	/**
	 * UserName of contact
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets userName of contact
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * userID of contact
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * Sets userID of contact
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * Method of contact
	 */
	public Contact.Method getMethod() {
		return method;
	}
	/**
	 * set Method of contact
	 */
	public void setMethod(Contact.Method method) {
		this.method = method;
	}
	/**
	 * To of contact
	 */
	public String getTo() {
		return to;
	}
	/**
	 * Set To of contact
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	

}
