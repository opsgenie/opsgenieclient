package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make an disable notificationRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#disableNotificationRule(com.ifountain.opsgenie.client.model.notification_rule.DisableNotificationRuleRequest)
 */
public class DisableNotificationRuleRequest extends BaseUserRequest<DisableNotificationRuleResponse> {
	private String id;

    /**
     * Rest api uri of disable notificationRule operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule/disable";
	}

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	@Override
	public DisableNotificationRuleResponse createResponse() {
		return new DisableNotificationRuleResponse();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
