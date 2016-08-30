package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;


/**
 * Represents OpsGenie service response for add contact request.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleStepOpsGenieClient#addNotificationRuleStep(AddNotificationRuleStepRequest)
 */
public class AddNotificationRuleStepResponse extends BaseResponse {
    private String id;

    /**
     * Id of the added contact
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added notificationRuleStep
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
