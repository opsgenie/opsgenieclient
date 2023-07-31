package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Schedule;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Represents OpsGenie service response for list schedules request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(ListSchedulesRequest)
 */
public class ListSchedulesResponse extends BaseResponse {

    @JsonProperty("data")
    private List<Schedule> schedules;
    private List<String> expandable;
    public List<String> getExpandable() {
        return expandable;
    }
    public void setExpandable(List<String> expandable) {
        this.expandable = expandable;
    }

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
    public void fromJson(String json) throws IOException, ParseException {
        super.fromJson(json);
        if (schedules != null)
            for (Schedule schedule : schedules)
                schedule.setTime();
    }
}
