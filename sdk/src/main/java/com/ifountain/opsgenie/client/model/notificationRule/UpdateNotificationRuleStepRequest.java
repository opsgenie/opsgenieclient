package com.ifountain.opsgenie.client.model.notificationRule;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Container for the parameters to make an update notificationRuleStep api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleStepOpsGenieClient#updateNotificationRuleStep(UpdateNotificationRuleStepRequest)
 */
public class UpdateNotificationRuleStepRequest extends AddNotificationRuleStepRequest {
	@JsonSerialize(include=Inclusion.ALWAYS)
	private String id;
	
	/**
	 * Rest api uri of updating notificationRuleStep operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule/step";
	}

	@Override
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	public UpdateNotificationRuleStepResponse createResponse() {
		return new UpdateNotificationRuleStepResponse();
	}
	/**
	 * Id of notificationRuleStep to be updated
	 */
	public String getId() {
		return id;
	}
	/**
	 * Sets Id of notificationRuleStep to be updated
	 */
	public void setId(String id) {
		this.id = id;
	}
	

}
