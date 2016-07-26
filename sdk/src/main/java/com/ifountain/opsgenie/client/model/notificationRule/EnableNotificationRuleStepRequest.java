package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make an enable/disable notificationRuleStep api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleStepOpsGenieClient#enableNotificationRuleStep(com.ifountain.opsgenie.client.model.notificationRuleStep.EnableNotificationRuleStepRequest)
 */
public class EnableNotificationRuleStepRequest extends BaseRequest<EnableNotificationRuleStepResponse> {
	private String username;
	private String userId;
	private String ruleId;
	private String id;
    private boolean enable;
    /**
     * Rest api uri of enable/disable notificationRuleStep operation.
     */
	@Override
	public String getEndPoint() {
        if (enable) {
            return "/v1/json/user/notificationRule/step/enable";
        } else {
            return "/v1/json/user/notificationRule/step/disable";
        }
	}
	@Override
	public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if (getUsername() != null) 
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		if (getUserId() != null) 
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
		if (getRuleId() != null) 
			json.put(OpsGenieClientConstants.API.RULE_ID, getRuleId());
        if(getId() != null)
        	json.put(OpsGenieClientConstants.API.ID, getId());
        return json;
	}

	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	public EnableNotificationRuleStepResponse createResponse() {
		return new EnableNotificationRuleStepResponse();
	}
    /**
     *  username of notificationRuleStep
     */
	public String getUsername() {
		return username;
	}
    /**
     *  Sets username of notificationRuleStep
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     *  userId of notificationRuleStep
     */
	public String getUserId() {
		return userId;
	}
    /**
     *  Sets userId of notificationRuleStep
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     *  Id of notificationRuleStep
     */
	public String getId() {
		return id;
	}
    /**
     *  Sets Id of notificationRuleStep
     */
	public void setId(String id) {
		this.id = id;
	}
    /**
     *  enable of notificationRuleStep
     */
	public boolean isEnable() {
		return enable;
	}
    /**
     *  Sets enable of notificationRuleStep
     */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	
	

}
