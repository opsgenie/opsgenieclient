package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make a list notificationRules api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#listNotificationRule(ListNotificationRulesRequest)
 */
public class ListNotificationRulesRequest extends BaseUserRequest<ListNotificationRulesResponse> {

    /**
     * Rest api uri of listing notificationRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListNotificationRulesResponse createResponse() {
        return new ListNotificationRulesResponse();
    }

}
