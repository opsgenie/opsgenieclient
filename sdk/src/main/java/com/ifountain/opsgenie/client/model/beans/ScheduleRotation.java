package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private List<ScheduleRotationRestriction> restrictions;
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
     * Restriction list of schedule rotation
     *
     * @see ScheduleRotationRestriction
     */
    public List<ScheduleRotationRestriction> getRestrictions() {
        if (restrictions != null && restrictions.size() > 0) {
            Collections.sort(restrictions, new Comparator<ScheduleRotationRestriction>() {
                @Override
                public int compare(ScheduleRotationRestriction o1, ScheduleRotationRestriction o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
        }
        return restrictions;
    }

    /**
     * Sets restriction list of schedule rotation
     *
     * @see ScheduleRotationRestriction
     */
    public void setRestrictions(List<ScheduleRotationRestriction> restrictions) {
        this.restrictions = restrictions;
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

    public ScheduleRotation withRestrictions(List<ScheduleRotationRestriction> restrictions) {
        this.restrictions = restrictions;
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

    public enum RotationType {
        weekly, daily, hourly
    }

}
