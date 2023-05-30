package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.TimeZone;

/**
 * ScheduleRotation bean
 */
public class ScheduleRotation extends BeanWithId implements ObjectWithTimeZone {
    private String startDate;
    private String endDate;
    @JsonProperty("type")
    private RotationType rotationType;
    @JsonProperty("length")
    private Integer rotationLength = 1;
    private List<ScheduleParticipant> participants;
    private TimeRestriction timeRestriction;
    private TimeZone scheduleTimeZone;
    private String name;

    public TimeRestriction getTimeRestriction() {
        return timeRestriction;
    }
    public void setTimeRestriction(TimeRestriction timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the optional end date of schedule rotation
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of schedule rotation. Optional.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Rotation type of schedule rotation Could be one of hourly, daily, weekly
     *
     * @see RotationType
     */
    public RotationType getRotationType() {
        return rotationType;
    }

    /**
     * Sets rotation type of schedule rotation Could be one of hourly, daily,
     * weekly
     *
     * @see RotationType
     */
    public void setRotationType(RotationType rotationType) {
        this.rotationType = rotationType;
    }

    /**
     * Rotation length of schedule rotation
     */
    public Integer getRotationLength() {
        return rotationLength;
    }

    /**
     * Sets rotation length of schedule rotation
     */
    public void setRotationLength(Integer rotationLength) {
        this.rotationLength = rotationLength;
    }


    /**
     * Participants of schedule rotation
     *
     * @see ScheduleParticipant
     */
    public List<ScheduleParticipant> getParticipants() {
        return participants;
    }

    /**
     * Sets participants of schedule rotation
     *
     * @see ScheduleParticipant
     */
    public void setParticipants(List<ScheduleParticipant> participants) {
        this.participants = participants;
    }

    public TimeZone getScheduleTimeZone() {
        return scheduleTimeZone;
    }

    /**
     * Will be set by schedule
     *
     * @param scheduleTimeZone -schedule time zone
     */
    public void setScheduleTimeZone(TimeZone scheduleTimeZone) {
        this.scheduleTimeZone = scheduleTimeZone;
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return scheduleTimeZone;
    }

    public ScheduleRotation withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public ScheduleRotation withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public ScheduleRotation withRotationType(RotationType rotationType) {
        this.rotationType = rotationType;
        return this;
    }

    public ScheduleRotation withRotationLength(Integer rotationLength) {
        this.rotationLength = rotationLength;
        return this;
    }

    public ScheduleRotation withParticipants(List<ScheduleParticipant> participants) {
        this.participants = participants;
        return this;
    }

    public ScheduleRotation withScheduleTimeZone(TimeZone scheduleTimeZone) {
        this.scheduleTimeZone = scheduleTimeZone;
        return this;
    }

    public ScheduleRotation withName(String name) {
        this.name = name;
        return this;
    }

    public ScheduleRotation withTimeRestriction(TimeRestriction timeRestriction) {
        this.timeRestriction = timeRestriction;
        return this;
    }

    public enum RotationType {
        weekly, daily, hourly
    }


}
