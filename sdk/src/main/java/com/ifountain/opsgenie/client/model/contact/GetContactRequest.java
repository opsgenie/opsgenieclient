package com.ifountain.opsgenie.client.model.contact;


import javax.xml.bind.ValidationException;

import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a get contact api call.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#getContact(GetContactRequest)
 */
public class GetContactRequest extends BaseRequest<GetContactResponse> {
	private String username;
	private String userId;
	private String id;
    /**
     * Rest api uri of getting contact operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/contact";
	}
    /**
     * check the parameters for validation.
     * @throws ValidationException when id is null or both of the username and userId are null
     */
    @Override
    public boolean isValid() throws ValidationException {
    	if(this.id == null)
			throw new ValidationException("ValidationException[[ID] field should be provided.]");
    	else if(this.username == null && this.userId == null)
			throw new ValidationException("ValidationException[One of [userId] or [username] fields should be provided.]");
    	return super.isValid();
    }
	@Override
	public GetContactResponse createResponse() {
		return new GetContactResponse();
	}
    /**
     * Username of contact to be queried.
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets Username of contact to be queried.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * userId of contact to be queried.
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of contact to be queried.
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     * Id of contact to be queried.
     */
	public String getId() {
		return id;
	}
    /**
     * Sets Id of contact to be queried.
     */
	public void setId(String id) {
		this.id = id;
	}
	

}
