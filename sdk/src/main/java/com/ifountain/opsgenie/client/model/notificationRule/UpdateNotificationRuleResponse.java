package com.ifountain.opsgenie.client.model.notificationRule;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseResponse;
/**
 * Represents OpsGenie service response for update notificationRule request.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRule(UpdateNotificationRuleRequest)
 */
public class UpdateNotificationRuleResponse extends BaseResponse {
	private String id;

	/**
	 * Id of the updated notificationRule
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the updated notificationRule
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
