package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.OpsGenieClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Schedule;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list schedules request.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#listSchedules(ListSchedulesRequest)
 */
public class ListSchedulesResponse extends BaseResponse{
    private List<Schedule> schedules;

    /**
     * List of schedules
     * @see com.ifountain.client.opsgenie.model.beans.Schedule
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * Sets list of schedules
     * @see com.ifountain.client.opsgenie.model.beans.Schedule
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> scheduleMaps = (List<Map>) data.get(OpsGenieClientConstants.API.SCHEDULES);
        if(scheduleMaps != null){
            schedules = new ArrayList<Schedule>();
            for(Map scheduleMap:scheduleMaps){
                Schedule schedule = new Schedule();
                schedule.fromMap(scheduleMap);
                schedules.add(schedule);
            }
        }
    }
}
