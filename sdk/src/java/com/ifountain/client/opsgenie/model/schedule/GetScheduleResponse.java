package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Schedule;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get schedule request.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#getSchedule(com.ifountain.client.opsgenie.model.schedule.GetScheduleRequest)
 */
public class GetScheduleResponse extends BaseResponse{
    private Schedule schedule;

    /**
     * Details of schedule
     * @see com.ifountain.client.opsgenie.model.beans.Schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Sets details of schedule
     * @see com.ifountain.client.opsgenie.model.beans.Schedule
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        schedule = new Schedule();
        schedule.fromMap(data);
    }
}
