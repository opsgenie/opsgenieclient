package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * ScheduleRotation bean
 */
public class ScheduleRotation extends BeanWithId implements ObjectWithTimeZone {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date endDate;
    @JsonProperty("type")
    private RotationType rotationType;
    @JsonProperty("length")
    private Integer rotationLength = 1;
    private List<ScheduleParticipant> participants;
    private TimeRestriction timeRestriction;
    private TimeZone scheduleTimeZone;
    private String name;

    public void validateScheduleRotation() throws OpsGenieClientValidationException {
        if(rotationType == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ROTATION_TYPE);
        validateStartAndEndDate();
        validateParticipants();
        if(timeRestriction!=null){
            timeRestriction.validateTimeRestriction();
        }
    }

    public void validateStartAndEndDate() throws OpsGenieClientValidationException {
        if(startDate == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.START_DATE);
        if(endDate!=null && startDate.after(endDate))
            throw OpsGenieClientValidationException.error("Rotation end time should be later than start time.");
    }

    public void validateParticipants() throws OpsGenieClientValidationException {
        if(participants == null || participants.isEmpty())
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.PARTICIPANTS);
        for(ScheduleParticipant participant : participants){
            participant.validateParticipant();
        }
    }

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
