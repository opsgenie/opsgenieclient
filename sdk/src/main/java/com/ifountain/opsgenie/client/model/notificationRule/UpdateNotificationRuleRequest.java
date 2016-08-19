package com.ifountain.opsgenie.client.model.notificationRule;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Container for the parameters to make an update notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRule(UpdateNotificationRuleRequest)
 */
public class UpdateNotificationRuleRequest extends AddNotificationRuleRequest {
	@JsonSerialize(include=Inclusion.ALWAYS)
	private String id;
	
	/**
	 * Rest api uri of updating notificationRule operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule";
	}

	@Override
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	public UpdateNotificationRuleResponse createResponse() {
		return new UpdateNotificationRuleResponse();
	}
	/**
	 * Id of notificationRule to be updated
	 */
	public String getId() {
		return id;
	}
	/**
	 * Sets Id of notificationRule to be updated
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
