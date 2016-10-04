package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for add forwarding request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addScheduleOverride(AddScheduleOverrideRequest)
 */
public class AddScheduleOverrideResponse extends BaseResponse {
    private String alias;

    /**
     * Alias of the created override
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Alias of the created override
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

}
