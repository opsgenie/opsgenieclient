package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a delete notificationRuleStep api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleStepOpsGenieClient#deleteNotificationRuleStep(DeleteNotificationRuleStepRequest)
 */
public class DeleteNotificationRuleStepRequest extends BaseRequest<DeleteNotificationRuleStepResponse> {
	private String username;
	private String userId;
	private String ruleId;
	private String id;
    /**
     * Rest api uri of deleting notificationRuleStep operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule/Step";
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
		if (getRuleId() != null) 
			json.put(OpsGenieClientConstants.API.RULE_ID, getRuleId());
		if (getId() != null) 
			json.put(OpsGenieClientConstants.API.ID, getId());
        return json;
	}

	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	public DeleteNotificationRuleStepResponse createResponse() {
		return new DeleteNotificationRuleStepResponse();
	}
    /**
     * username of notificationRuleStep to be deleted.
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets username of notificationRuleStep to be deleted.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * userId of notificationRuleStep to be deleted.
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of notificationRuleStep to be deleted.
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     * Id of notificationRuleStep to be deleted.
     */
	public String getId() {
		return id;
	}
    /**
     * Sets Id of notificationRuleStep to be deleted.
     */
	public void setId(String id) {
		this.id = id;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	

}
