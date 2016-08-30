package com.ifountain.opsgenie.client.model.contact;

import org.codehaus.jackson.annotate.JsonProperty;

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
	@JsonProperty("method")
	public String getMethodValue(){
		if(method != null)
			return method.value();
		return null;
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
