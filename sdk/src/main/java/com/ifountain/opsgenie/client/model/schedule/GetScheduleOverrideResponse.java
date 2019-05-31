package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.ScheduleOverride;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents OpsGenie service response for get schedule override request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getScheduleOverride(GetScheduleOverrideRequest)
 */
public class GetScheduleOverrideResponse extends BaseResponse {
    @JsonUnwrapped
    private ScheduleOverride scheduleOverride;

    /**
     * Gets schedule override object.
     */
    public ScheduleOverride getScheduleOverride() {
        return scheduleOverride;
    }

    /**
     * Sets schedule override object.
     */
    public void setScheduleOverride(ScheduleOverride scheduleOverride) {
        this.scheduleOverride = scheduleOverride;
    }

    @Override
    public void fromJson(String json) throws IOException, ParseException {
        super.fromJson(json);
        scheduleOverride.setTime();
    }

}
