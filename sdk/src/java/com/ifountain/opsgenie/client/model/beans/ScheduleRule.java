package com.ifountain.opsgenie.client.model.beans;

import java.util.Date;
import java.util.List;

/**
 * ScheduleRule bean
 */
public class ScheduleRule {
    private Date startDate;
    private String rotationType;
    private String rotationLength;
    private List<ScheduleParticipant> participants;
    private List<ScheduleRuleRestriction> restrictions;

    /**
     * Start date of schedule rule
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of schedule rule
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Rotation type of schedule rule
     * Could be one of hourly, daily, weekly
     */
    public String getRotationType() {
        return rotationType;
    }

    /**
     * Sets rotation type of schedule rule
     * Could be one of hourly, daily, weekly
     */
    public void setRotationType(String rotationType) {
        this.rotationType = rotationType;
    }

    /**
     * Rotation length of schedule rule
     */
    public String getRotationLength() {
        return rotationLength;
    }

    /**
     * Sets rotation length of schedule rule
     */
    public void setRotationLength(String rotationLength) {
        this.rotationLength = rotationLength;
    }

    /**
     * Participants of of schedule rule
     * @see ScheduleParticipant
     */
    public List<ScheduleParticipant> getParticipants() {
        return participants;
    }

    /**
     * Sets participants of schedule rule
     * @see ScheduleParticipant
     */
    public void setParticipants(List<ScheduleParticipant> participants) {
        this.participants = participants;
    }

    /**
     * Restriction list of schedule rule
     * @see ScheduleRuleRestriction
     */
    public List<ScheduleRuleRestriction> getRestrictions() {
        return restrictions;
    }

    /**
     * Sets restriction list of schedule rule
     * @see ScheduleRuleRestriction
     */
    public void setRestrictions(List<ScheduleRuleRestriction> restrictions) {
        this.restrictions = restrictions;
    }
}
