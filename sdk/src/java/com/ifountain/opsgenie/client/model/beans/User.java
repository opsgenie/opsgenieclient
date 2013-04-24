package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * User bean
 */
public class User  implements IBean{
    public static enum Role{
        admin,
        user
    }
    public static enum State{
        active,
        waitingverification,
        inactive
    }
    private String id;
    private String username;
    private State state;
    private String fullname;
    private TimeZone timeZone;
    private Role role;
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
     * @see State
     */
    public State getState() {
        return state;
    }

    /**
     * Sets state of user
     * @see State
     */
    public void setState(State state) {
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
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public User.Role getRole() {
        return role;
    }

    /**
     * Sets role of user
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public void setRole(User.Role role) {
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

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(OpsGenieClientConstants.API.ID, getId());
        json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
        json.put(OpsGenieClientConstants.API.FULLNAME, getFullname());
        if(getState() != null){
            json.put(OpsGenieClientConstants.API.STATE, getState().name());
        }
        if(getRole() != null){
            json.put(OpsGenieClientConstants.API.ROLE, getRole().name());
        }
        if(getTimeZone() != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, getTimeZone().getID());
        }
        if(getGroups() != null){
            json.put(OpsGenieClientConstants.API.GROUPS, getGroups());
        }
        if(getEscalations() != null){
            json.put(OpsGenieClientConstants.API.ESCALATIONS, getEscalations());
        }
        if(getSchedules() != null){
            json.put(OpsGenieClientConstants.API.SCHEDULES, getSchedules());
        }
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        setId((String) map.get(OpsGenieClientConstants.API.ID));
        setUsername((String) map.get(OpsGenieClientConstants.API.USERNAME));
        setFullname((String) map.get(OpsGenieClientConstants.API.FULLNAME));
        if(map.containsKey(OpsGenieClientConstants.API.ROLE)){
            setRole(User.Role.valueOf(((String) map.get(OpsGenieClientConstants.API.ROLE)).toLowerCase()));
        }
        if(map.containsKey(OpsGenieClientConstants.API.STATE)){
            setState(State.valueOf(((String) map.get(OpsGenieClientConstants.API.STATE)).toLowerCase()));
        }
        setGroups((List<String>) map.get(OpsGenieClientConstants.API.GROUPS));
        setEscalations((List<String>) map.get(OpsGenieClientConstants.API.ESCALATIONS));
        setSchedules((List<String>) map.get(OpsGenieClientConstants.API.SCHEDULES));
        if(map.get(OpsGenieClientConstants.API.TIMEZONE) != null){
            setTimeZone(TimeZone.getTimeZone((String) map.get(OpsGenieClientConstants.API.TIMEZONE)));
        }
    }
}
