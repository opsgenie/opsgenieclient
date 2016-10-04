package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Schedule;
import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Represents OpsGenie service response for list schedules request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(ListSchedulesRequest)
 */
public class ListSchedulesResponse extends BaseResponse {
    private List<Schedule> schedules;

    /**
     * List of schedules
     *
     * @see com.ifountain.opsgenie.client.model.beans.Schedule
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * Sets list of schedules
     *
     * @see com.ifountain.opsgenie.client.model.beans.Schedule
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public void fromJson(String json) throws JsonProcessingException, IOException, ParseException {
        super.fromJson(json);
        if (schedules != null)
            for (Schedule schedule : schedules)
                schedule.setTime();
    }
}
