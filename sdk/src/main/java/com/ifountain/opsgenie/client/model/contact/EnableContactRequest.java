package com.ifountain.opsgenie.client.model.contact;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make an enable/disable contact api call.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#enableContact(com.ifountain.opsgenie.client.model.contact.EnableContactRequest)
 */
public class EnableContactRequest extends BaseRequest<EnableContactResponse> {
	private String username;
	private String userId;
	private String id;
    private boolean enable;
    /**
     * Rest api uri of enable/disable contact operation.
     */
	@Override
	public String getEndPoint() {
        if (enable) {
            return "/v1/json/user/contact/enable";
        } else {
            return "/v1/json/user/contact/disable";
        }
	}
	@Override
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
	public EnableContactResponse createResponse() {
		return new EnableContactResponse();
	}
    /**
     *  username of contact
     */
	public String getUsername() {
		return username;
	}
    /**
     *  Sets username of contact
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     *  userId of contact
     */
	public String getUserId() {
		return userId;
	}
    /**
     *  Sets userId of contact
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     *  Id of contact
     */
	public String getId() {
		return id;
	}
    /**
     *  Sets Id of contact
     */
	public void setId(String id) {
		this.id = id;
	}
    /**
     *  enable of contact
     */
	public boolean isEnable() {
		return enable;
	}
    /**
     *  Sets enable of contact
     */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	

}
