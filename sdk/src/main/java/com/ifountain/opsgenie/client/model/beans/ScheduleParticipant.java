package com.ifountain.opsgenie.client.model.beans;

/**
 * ScheduleParticipant bean
 */
public class ScheduleParticipant extends Bean {
    private String participant;
    private Type type;

    public ScheduleParticipant() {

    }

    public ScheduleParticipant(String participant) {
        this.participant = participant;
    }

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

    public ScheduleParticipant withParticipant(String participant) {
        this.participant = participant;
        return this;
    }

    public ScheduleParticipant withType(Type type) {
        this.type = type;
        return this;
    }

    public enum Type {
        user, group, escalation, schedule, team
    }

}
