package com.ifountain.opsgenie.client.model.beans;

/**
 * ScheduleParticipant bean
 */
public class ScheduleParticipant extends Bean {
    private Type type;
    private String id;
    private String name;
    private String username;
    public ScheduleParticipant() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(Type type) {
        this.type = type;
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

    public ScheduleParticipant withId(String id) {
        this.id = id;
        return this;
    }
    public ScheduleParticipant withUsername(String username) {
        this.username = username;
        return this;
    }
    public ScheduleParticipant withName(String name) {
        this.name = name;
        return this;
    }

    public ScheduleParticipant withType(Type type) {
        this.type = type;
        return this;
    }

    public enum Type {
        user, group, escalation, schedule, team, none
    }

}
