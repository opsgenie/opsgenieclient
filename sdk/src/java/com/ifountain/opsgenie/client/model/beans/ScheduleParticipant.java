package com.ifountain.opsgenie.client.model.beans;

/**
 * ScheduleParticipant bean
 */
public class ScheduleParticipant {
    private String name;
    private String type;

    /**
     * Name of participant
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of participant
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Type of participant
     * Could be one of user, group, escalation
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type of participant
     * Could be one of user, group, escalation
     */
    public void setType(String type) {
        this.type = type;
    }
}
