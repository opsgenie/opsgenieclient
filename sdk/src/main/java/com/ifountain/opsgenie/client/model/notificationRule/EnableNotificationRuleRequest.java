package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make an enable/disable notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#enableNotificationRule(com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleRequest)
 */
public class EnableNotificationRuleRequest extends BaseRequest<EnableNotificationRuleResponse> {
	private String username;
	private String userId;
	private String id;
    private boolean enable;
    /**
     * Rest api uri of enable/disable notificationRule operation.
     */
	@Override
	public String getEndPoint() {
        if (enable) {
            return "/v1/json/user/notificationRule/enable";
        } else {
            return "/v1/json/user/notificationRule/disable";
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
	public EnableNotificationRuleResponse createResponse() {
		return new EnableNotificationRuleResponse();
	}
    /**
     *  username of notificationRule
     */
	public String getUsername() {
		return username;
	}
    /**
     *  Sets username of notificationRule
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     *  userId of notificationRule
     */
	public String getUserId() {
		return userId;
	}
    /**
     *  Sets userId of notificationRule
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     *  Id of notificationRule
     */
	public String getId() {
		return id;
	}
    /**
     *  Sets Id of notificationRule
     */
	public void setId(String id) {
		this.id = id;
	}
    /**
     *  enable of notificationRule
     */
	public boolean isEnable() {
		return enable;
	}
    /**
     *  Sets enable of notificationRule
     */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	

}
