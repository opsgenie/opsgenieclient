package com.ifountain.opsgenie.client.model.contact;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

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
	@JsonIgnore
	public String getEndPoint() {
		return "/v1/json/user/contact";
	}

	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
	 */
	@Override
	public Map serialize() throws OpsGenieClientValidationException {
		Map jsonMap = super.serialize();
		if (getMethod() != null) 
			jsonMap.put(OpsGenieClientConstants.API.METHOD, getMethod().value());
		return jsonMap;
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
