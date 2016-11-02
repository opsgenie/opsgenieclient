package com.ifountain.opsgenie.client.model.beans;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * ScheduleRotationRestriction bean
 */
public class ScheduleRotationRestriction extends Bean {
    private DAY startDay;
    private DAY endDay;
    private ScheduleRestrictionTime startTime;
    private ScheduleRestrictionTime endTime;

    public ScheduleRotationRestriction() {
        super();
        startTime = new ScheduleRestrictionTime();
        endTime = new ScheduleRestrictionTime();
    }

    /**
     * Start day of restriction One of monday, tuesday, wednesday, thursday,
     * friday, saturday, sunday
     */
    public DAY getStartDay() {
        return startDay;
    }

    /**
     * Sets start day of restriction One of monday, tuesday, wednesday,
     * thursday, friday, saturday, sunday
     */
    public void setStartDay(DAY startDay) {
        this.startDay = startDay;
    }

    /**
     * @deprecated Use getStartTime
     */
    @Deprecated
    @JsonIgnore
    public int getStartHour() {
        if (startTime == null)
            return 0;
        return startTime.getHour();
    }

    /**
     * @deprecated Use setStartTime
     */
    @Deprecated
    @JsonIgnore
    public void setStartHour(int startHour) {
        if (startTime == null)
            startTime = new ScheduleRestrictionTime();
        this.startTime.setHour(startHour);
    }

    /**
     * @deprecated Use getStartTime
     */
    @Deprecated
    @JsonIgnore
    public int getStartMin() {
        if (startTime == null)
            return 0;
        return startTime.getMinute();
    }

    /**
     * @deprecated Use setStartTime
     */
    @Deprecated
    @JsonIgnore
    public void setStartMin(int startMin) {
        if (startTime == null)
            startTime = new ScheduleRestrictionTime();
        this.startTime.setMinute(startMin);
    }

    /**
     * @deprecated Use getEndTime
     */
    @Deprecated
    @JsonIgnore
    public int getEndHour() {
        if (endTime == null)
            return 0;
        return endTime.getHour();
    }

    /**
     * @deprecated Use setEndTime
     */
    @Deprecated
    @JsonIgnore
    public void setEndHour(int endHour) {
        if (endTime == null)
            endTime = new ScheduleRestrictionTime();
        this.endTime.setHour(endHour);
    }

    /**
     * @deprecated Use getEndTime
     */
    @Deprecated
    @JsonIgnore
    public int getEndMin() {
        if (endTime == null)
            return 0;
        return endTime.getMinute();
    }

    /**
     * @deprecated Use setEndTime
     */
    @Deprecated
    @JsonIgnore
    public void setEndMin(int endMin) {
        if (endTime == null)
            endTime = new ScheduleRestrictionTime();
        this.endTime.setMinute(endMin);
    }

    /**
     * End day of restriction One of monday, tuesday, wednesday, thursday,
     * friday, saturday, sunday
     */
    public DAY getEndDay() {
        return endDay;
    }

    /**
     * Sets end day of restriction One of monday, tuesday, wednesday, thursday,
     * friday, saturday, sunday
     */
    public void setEndDay(DAY endDay) {
        this.endDay = endDay;
    }

    public ScheduleRestrictionTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ScheduleRestrictionTime startTime) {
        this.startTime = startTime;
    }

    public ScheduleRestrictionTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ScheduleRestrictionTime endTime) {
        this.endTime = endTime;
    }

    public ScheduleRotationRestriction withStartDay(DAY startDay) {
        this.startDay = startDay;
        return this;
    }

    public ScheduleRotationRestriction withEndDay(DAY endDay) {
        this.endDay = endDay;
        return this;
    }

    public ScheduleRotationRestriction withStartTime(ScheduleRestrictionTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public ScheduleRotationRestriction withEndTime(ScheduleRestrictionTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public enum DAY {
        monday, tuesday, wednesday, thursday, friday, saturday, sunday;

        @JsonCreator
        public static DAY fromName(String name) {
            for (DAY day : DAY.values()) {
                if (day.name().equalsIgnoreCase(name))
                    return day;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return this.name();
        }
    }

}
