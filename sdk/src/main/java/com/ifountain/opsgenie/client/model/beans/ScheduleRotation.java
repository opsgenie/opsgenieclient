package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * ScheduleRotation bean
 */
public class ScheduleRotation extends BeanWithId implements ObjectWithTimeZone {
    private Date startDate;
    private Date endDate;
    private RotationType rotationType;
    private Integer rotationLength;
    private List<ScheduleParticipant> participants;
    @JsonProperty("restrictions")
    private List<Restriction> scheduleRotationRestrictions;
    private TimeZone scheduleTimeZone;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the optional end date of schedule rotation
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of schedule rotation. Optional.
     */
    public void setEndDate(Date endDate) {
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
    @JsonProperty("participants")
    public List<String> getParticipantsNames() {
        if (participants == null)
            return null;
        List<String> participantList = new ArrayList<String>();
        for (ScheduleParticipant participant : participants)
            participantList.add(participant.getParticipant());
        return participantList;
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

    /**
     * @deprecated use getScheduleRotationRestrictions()
     */
    @Deprecated
    @JsonIgnore
    public List<ScheduleRotationRestriction> getRestrictions() {
        if (scheduleRotationRestrictions == null) {
            return null;
        }
        List<ScheduleRotationRestriction> deprecatedList = new ArrayList<ScheduleRotationRestriction>();
        for (Restriction restriction : scheduleRotationRestrictions) {
            ScheduleRotationRestriction newRestriction = new ScheduleRotationRestriction();
            newRestriction.setEndDay(restriction.getEndDay());
            newRestriction.setStartDay(restriction.getStartDay());
            newRestriction.setStartHour(restriction.getStartHour());
            newRestriction.setStartMin(restriction.getStartMinute());
            newRestriction.setEndHour(restriction.getEndHour());
            newRestriction.setEndMin(restriction.getEndMinute());

            deprecatedList.add(newRestriction);
        }
        return deprecatedList;
    }

    /**
     * @deprecated use setScheduleRotationRestrictions()
     */
    @Deprecated
    @JsonIgnore
    public void setRestrictions(List<ScheduleRotationRestriction> restrictions) {
        if (restrictions == null) {
            this.scheduleRotationRestrictions = null;
        }
        scheduleRotationRestrictions = new ArrayList<Restriction>();
        for (ScheduleRotationRestriction deprecatedRestriction : restrictions) {
            Restriction newRestriction = new Restriction();
            newRestriction.withEndDay(deprecatedRestriction.getEndDay())
                    .withStartDay(deprecatedRestriction.getStartDay())
                    .withEndHour(deprecatedRestriction.getEndHour())
                    .withEndMinute(deprecatedRestriction.getEndMin())
                    .withStartHour(deprecatedRestriction.getStartHour())
                    .withStartMinute(deprecatedRestriction.getStartMin());
            scheduleRotationRestrictions.add(newRestriction);
        }
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

    public ScheduleRotation withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public ScheduleRotation withEndDate(Date endDate) {
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

    @Deprecated
    public ScheduleRotation withRestrictions(List<ScheduleRotationRestriction> restrictions) {
        setRestrictions(restrictions);
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

    public List<Restriction> getScheduleRotationRestrictions() {
        return scheduleRotationRestrictions;
    }

    public void setScheduleRotationRestrictions(List<Restriction> scheduleRotationRestrictions) {
        this.scheduleRotationRestrictions = scheduleRotationRestrictions;
    }

    public ScheduleRotation withScheduleRotationRestrictions(List<Restriction> restrictions) {
        this.scheduleRotationRestrictions = restrictions;
        return this;
    }

    public enum RotationType {
        weekly, daily, hourly
    }


}
