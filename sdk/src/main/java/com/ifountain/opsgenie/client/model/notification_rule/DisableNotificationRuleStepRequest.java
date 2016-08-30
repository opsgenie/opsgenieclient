package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make an enable/disable notificationRuleStep
 * api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#disableNotificationRuleStep(DisableNotificationRuleStepRequest)
 */
public class DisableNotificationRuleStepRequest extends BaseRequest<DisableNotificationRuleStepResponse> {
	private String username;
	private String userId;
	private String ruleId;
	private String id;

	/**
	 * Rest api uri of enable/disable notificationRuleStep operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule/step/disable";
	}

	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	@Override
	public DisableNotificationRuleStepResponse createResponse() {
		return new DisableNotificationRuleStepResponse();
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
