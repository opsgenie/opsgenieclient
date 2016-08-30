package com.ifountain.opsgenie.client.model.notificationRule;

import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a delete notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#deleteNotificationRule(DeleteNotificationRuleRequest)
 */
public class DeleteNotificationRuleRequest extends BaseRequest<DeleteNotificationRuleResponse> {
	private String username;
	private String userId;
	private String id;
    /**
     * Rest api uri of deleting notificationRule operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule";
	}

	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	public DeleteNotificationRuleResponse createResponse() {
		return new DeleteNotificationRuleResponse();
	}
    /**
     * username of notificationRule to be deleted.
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets username of notificationRule to be deleted.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * userId of notificationRule to be deleted.
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of notificationRule to be deleted.
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     * Id of notificationRule to be deleted.
     */
	public String getId() {
		return id;
	}
    /**
     * Sets Id of notificationRule to be deleted.
     */
	public void setId(String id) {
		this.id = id;
	}
	

}
