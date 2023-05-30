package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.ScheduleResponseData;

/**
 * Represents OpsGenie service response for add schedule request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
 */
public class AddScheduleResponse extends BaseResponse {
    @JsonProperty("data")
    private ScheduleResponseData scheduleData;

    public ScheduleResponseData getScheduleData() {
        return scheduleData;
    }

    public void setScheduleData(ScheduleResponseData scheduleData) {
        this.scheduleData = scheduleData;
    }
}
