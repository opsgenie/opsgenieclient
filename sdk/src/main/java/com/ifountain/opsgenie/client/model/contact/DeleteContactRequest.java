package com.ifountain.opsgenie.client.model.contact;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a delete contact api call.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#deleteContact(DeleteContactRequest)
 */
public class DeleteContactRequest extends BaseRequest<DeleteContactResponse> {
	private String username;
	private String userId;
	private String id;
    /**
     * Rest api uri of deleting contact operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/contact";
	}
	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
	public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if (getUsername() != null) 
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		if (getUserId() != null) 
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
        if(getId() != null)
        	json.put(OpsGenieClientConstants.API.ID, getId());
        return json;
	}

	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	public DeleteContactResponse createResponse() {
		return new DeleteContactResponse();
	}
    /**
     * username of contact to be deleted.
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets username of contact to be deleted.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * userId of contact to be deleted.
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of contact to be deleted.
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     * Id of contact to be deleted.
     */
	public String getId() {
		return id;
	}
    /**
     * Sets Id of contact to be deleted.
     */
	public void setId(String id) {
		this.id = id;
	}
	

}
