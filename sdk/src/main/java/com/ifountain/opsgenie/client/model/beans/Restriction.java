package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.beans.ScheduleRotationRestriction.DAY;

/**
 * Restriction bean
 */
public class Restriction extends Bean {
    private Integer endHour;
    private Integer endMinute;
    private Integer startHour;
    private Integer startMinute;
    private DAY startDay;
    private DAY endDay;

    /**
     * endHour of Restriction
     */
    public Integer getEndHour() {
        return endHour;
    }

    /**
     * Sets endHour of Restriction
     */
    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    /**
     * endMinute of Restriction
     */
    public Integer getEndMinute() {
        return endMinute;
    }

    /**
     * Sets endMinute of Restriction
     */
    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    /**
     * startHour of Restriction
     */
    public Integer getStartHour() {
        return startHour;
    }

    /**
     * Sets startHour of Restriction
     */
    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    /**
     * startMinute of Restriction
     */
    public Integer getStartMinute() {
        return startMinute;
    }

    /**
     * Sets startMinute of Restriction
     */
    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }

    /**
     * startDay of Restriction
     */
    public DAY getStartDay() {
        return startDay;
    }

    /**
     * Sets startDay of Restriction
     */
    public void setStartDay(DAY startDay) {
        this.startDay = startDay;
    }

    /**
     * endDay of Restriction
     */
    public DAY getEndDay() {
        return endDay;
    }

    /**
     * Sets endDay of Restriction
     */
    public void setEndDay(DAY endDay) {
        this.endDay = endDay;
    }

}
