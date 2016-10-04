package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserComponentRequest;

/**
 * Container for the parameters to repeat notificationRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#changeNotificationRuleOrder(ChangeNotificationRuleOrderRequest)
 */
public class ChangeNotificationRuleOrderRequest extends BaseUserComponentRequest<ChangeNotificationRuleOrderResponse> {
    private String id;
    private Integer applyOrder;

    /**
     * Rest api uri of change notificationRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/changeOrder";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ChangeNotificationRuleOrderResponse createResponse() {
        return new ChangeNotificationRuleOrderResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getApplyOrder() {
        return applyOrder;
    }

    public void setApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
    }

}
