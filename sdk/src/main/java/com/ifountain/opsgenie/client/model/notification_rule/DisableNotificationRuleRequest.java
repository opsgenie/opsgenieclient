package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make an disable notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#disableNotificationRule(com.ifountain.opsgenie.client.model.notification_rule.DisableNotificationRuleRequest)
 */
public class DisableNotificationRuleRequest extends BaseRequest<DisableNotificationRuleResponse> {
	private String username;
	private String userId;
	private String id;
    /**
     * Rest api uri of disable notificationRule operation.
     */
	@Override
	public String getEndPoint() {
           return "/v1/json/user/notificationRule/disable";
	}

	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	public DisableNotificationRuleResponse createResponse() {
		return new DisableNotificationRuleResponse();
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
}
