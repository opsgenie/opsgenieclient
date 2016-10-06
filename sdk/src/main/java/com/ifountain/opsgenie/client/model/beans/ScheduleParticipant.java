package com.ifountain.opsgenie.client.model.beans;

/**
 * ScheduleParticipant bean
 */
public class ScheduleParticipant extends Bean {
    public enum Type {
        user, group, escalation, schedule, team
    }

    private String participant;
    private Type type;

    /**
     * Name of participant
     */
    public String getParticipant() {
        return participant;
    }

    /**
     * Sets name of participant
     */
    public void setParticipant(String participant) {
        this.participant = participant;
    }

    /**
     * Type of participant Could be one of user, group, escalation, schedule,
     * team
     *
     * @see Type
     */
    public Type getType() {
        return type;
    }

}
