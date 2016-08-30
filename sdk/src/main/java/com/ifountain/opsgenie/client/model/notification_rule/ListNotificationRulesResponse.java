package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.NotificationRule;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list notificationRule request.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#listNotificationRules(ListNotificationRulesRequest)
 */
public class ListNotificationRulesResponse extends BaseResponse {
    private List<NotificationRule> notificationRules;

    /**
     * List of notificationRules
     *
     * @see NotificationRule
     */
    public List<NotificationRule> getNotificationRules() {
        return notificationRules;
    }

    /**
     * Sets list of notificationRules
     *
     * @see NotificationRule
     */
    public void setNotificationRules(List<NotificationRule> notificationRules) {
        this.notificationRules = notificationRules;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        if (data != null) {
            notificationRules = new ArrayList<NotificationRule>();
            List<Map> notificationRulesData = (List<Map>) data.get(OpsGenieClientConstants.API.RULES);
            for (Map notificationRuleData : notificationRulesData) {
                NotificationRule notificationRule = new NotificationRule();
                notificationRule.fromMap(notificationRuleData);
                notificationRules.add(notificationRule);
            }
        }
    }

}
