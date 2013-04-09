package com.ifountain.opsgenie.client.model.beans;

/**
 * ScheduleRuleRestriction bean
 */
public class ScheduleRuleRestriction {
    private String startDay;
    private String startTime;
    private String endDay;
    private String endTime;

    /**
     * Start day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public String getStartDay() {
        return startDay;
    }

    /**
     * Sets start day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    /**
     * Start time of restriction
     * Format: HH:mm
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets start time of restriction
     * Format: HH:mm
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * End day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public String getEndDay() {
        return endDay;
    }

    /**
     * Sets end day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    /**
     * End time of restriction
     * Format: HH:mm
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets end time of restriction
     * Format: HH:mm
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
