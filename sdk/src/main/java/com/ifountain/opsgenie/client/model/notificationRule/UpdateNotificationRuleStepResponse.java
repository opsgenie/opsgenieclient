package com.ifountain.opsgenie.client.model.notificationRule;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseResponse;
/**
 * Represents OpsGenie service response for update notificationRuleStep request.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleStepOpsGenieClient#updateNotificationRuleStep(UpdateNotificationRuleStepRequest)
 */
public class UpdateNotificationRuleStepResponse extends BaseResponse {
	private String id;

	/**
	 * Id of the updated notificationRuleStep
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the updated notificationRuleStep
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void deserialize(Map data) throws ParseException {
		super.deserialize(data);
		id = (String) data.get("id");
	}
}
