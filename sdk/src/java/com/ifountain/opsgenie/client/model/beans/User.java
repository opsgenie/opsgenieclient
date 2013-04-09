package com.ifountain.opsgenie.client.model.beans;

import java.util.List;
import java.util.TimeZone;

/**
 * User bean
 */
public class User {
    private String id;
    private String username;
    private String state;
    private String fullname;
    private TimeZone timeZone;
    private String role;
    private List<String> groups;
    private List<String> escalations;
    private List<String> schedules;

    /**
     * Id of user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * State of user
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state of user
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Fullname of user
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Sets fullname of user
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Timezone of user
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone of user
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Role of user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role of user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Groups of user
     */
    public List<String> getGroups() {
        return groups;
    }

    /**
     * Sets groups of user
     */
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    /**
     * Escalations of user
     */
    public List<String> getEscalations() {
        return escalations;
    }

    /**
     * Sets escalations of user
     */
    public void setEscalations(List<String> escalations) {
        this.escalations = escalations;
    }

    /**
     * Schedules of user
     */
    public List<String> getSchedules() {
        return schedules;
    }

    /**
     * Sets schedules of user
     */
    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }
}
