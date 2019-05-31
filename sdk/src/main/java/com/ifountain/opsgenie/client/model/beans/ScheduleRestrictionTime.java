package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Deprecated
public class ScheduleRestrictionTime {
    private int hour;
    private int minute;

    @JsonCreator
    public static ScheduleRestrictionTime fromTime(String time) {
        if (time != null) {
            ScheduleRestrictionTime scheduleRestrictionTime = new ScheduleRestrictionTime();
            String[] timeArray = time.split(":", -1);
            scheduleRestrictionTime.setHour(Integer.parseInt(timeArray[0]));
            scheduleRestrictionTime.setMinute(Integer.parseInt(timeArray[1]));
            return scheduleRestrictionTime;
        }
        return null;
    }

    @JsonValue
    public String getTimeStr() {
        StringBuffer time = new StringBuffer();
        if (hour < 10) {
            time.append("0");
        }
        time.append(hour).append(":");
        if (minute < 10) {
            time.append("0");
        }
        time.append(minute);
        return time.toString();
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }


    public ScheduleRestrictionTime withHour(int hour) {
        this.hour = hour;
        return this;
    }

    public ScheduleRestrictionTime withMinute(int minute) {
        this.minute = minute;
        return this;
    }


}
