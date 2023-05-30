package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for delete schedule request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteSchedule(DeleteScheduleRequest)
 */
public class DeleteScheduleResponse extends BaseResponse{
    private String result;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
