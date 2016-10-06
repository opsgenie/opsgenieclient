package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.NotificationRule;

import java.util.List;

/**
 * Represents OpsGenie service response for list notificationRule request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#listNotificationRule(ListNotificationRulesRequest)
 */
public class ListNotificationRulesResponse extends BaseResponse {
    private List<NotificationRule> rules;

    /**
     * List of notificationRules
     *
     * @see NotificationRule
     */
    public List<NotificationRule> getRules() {
        return rules;
    }

    /**
     * Sets list of notificationRules
     *
     * @see NotificationRule
     */
    public void setRules(List<NotificationRule> rules) {
        this.rules = rules;
    }
}
