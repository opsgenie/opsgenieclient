package com.ifountain.opsgenie.client.model.notificationRule;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.NotificationRule;
/**
 * Represents OpsGenie service response for get notificationRule request.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#getNotificationRule(GetNotificationRuleRequest)
 */
public class GetNotificationRuleResponse extends BaseResponse {
	private NotificationRule notificationRule;
    /**
     * Details of notificationRule
     * @see NotificationRule
     */
	public NotificationRule getNotificationRule() {
		return notificationRule;
	}    
	@Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        notificationRule = new NotificationRule();
        notificationRule.fromMap(data);
    }
    /**
     * Sets details of notificationRule
     * @see NotificationRule
     */
	public void setNotificationRule(NotificationRule notificationRule) {
		this.notificationRule = notificationRule;
	}
	

}
