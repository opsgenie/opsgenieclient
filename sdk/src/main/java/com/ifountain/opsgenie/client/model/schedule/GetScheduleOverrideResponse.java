package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.ScheduleOverride;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get schedule override request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 10:41 AM
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getScheduleOverride(GetScheduleOverrideRequest)
 */
public class GetScheduleOverrideResponse extends BaseResponse {
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
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        scheduleOverride = new ScheduleOverride();
        scheduleOverride.fromMap(data);
    }

}
