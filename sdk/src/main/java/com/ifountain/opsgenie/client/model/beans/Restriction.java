package com.ifountain.opsgenie.client.model.beans;

/**
 * Restriction bean
 */
public class Restriction extends Bean {
    private Integer endHour;
    private Integer endMin;
    private Integer startHour;
    private Integer startMin;
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
    public Integer getEndMin() {
        return endMin;
    }

    /**
     * Sets endMinute of Restriction
     */
    public void setEndMin(Integer endMin) {
        this.endMin = endMin;
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
    public Integer getStartMin() {
        return startMin;
    }

    /**
     * Sets startMinute of Restriction
     */
    public void setStartMin(Integer startMin) {
        this.startMin = startMin;
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


    public Restriction withEndHour(Integer endHour) {
        this.endHour = endHour;
        return this;
    }

    public Restriction withEndMinute(Integer endMinute) {
        this.endMin = endMinute;
        return this;
    }

    public Restriction withStartHour(Integer startHour) {
        this.startHour = startHour;
        return this;
    }

    public Restriction withStartMinute(Integer startMinute) {
        this.startMin = startMinute;
        return this;
    }

    public Restriction withStartDay(DAY startDay) {
        this.startDay = startDay;
        return this;
    }

    public Restriction withEndDay(DAY endDay) {
        this.endDay = endDay;
        return this;
    }

}
