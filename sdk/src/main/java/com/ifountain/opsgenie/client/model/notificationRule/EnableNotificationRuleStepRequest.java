package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make an enable notificationRuleStep api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleStepOpsGenieClient#enableNotificationRuleStep(com.ifountain.opsgenie.client.model.notificationRuleStep.EnableNotificationRuleStepRequest)
 */
public class EnableNotificationRuleStepRequest extends BaseRequest<EnableNotificationRuleStepResponse> {
	private String username;
	private String userId;
	private String ruleId;
	private String id;

	/**
	 * Rest api uri of enable/disable notificationRuleStep operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule/step/enable";
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
		if (getId() != null)
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

	public String getId() {
		return id;
	}

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
