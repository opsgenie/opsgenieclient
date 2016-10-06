package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Schedule;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents OpsGenie service response for get schedule request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(GetScheduleRequest)
 */
public class GetScheduleResponse extends BaseResponse {
    @JsonUnwrapped
    private Schedule schedule;

    /**
     * Details of schedule
     *
     * @see com.ifountain.opsgenie.client.model.beans.Schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Sets details of schedule
     *
     * @see com.ifountain.opsgenie.client.model.beans.Schedule
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public void fromJson(String json) throws IOException, ParseException {
        super.fromJson(json);
        schedule.setTime();
    }
}
