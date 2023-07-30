package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import org.apache.commons.lang3.StringUtils;

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
    public void validateParticipant() throws OpsGenieClientValidationException {
        if(type == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.PARTICIPANT_TYPE);
        if(type.equals(ScheduleParticipant.Type.team) || type.equals(ScheduleParticipant.Type.escalation)){
            if(StringUtils.isEmpty(id) && StringUtils.isEmpty(name)){
                throw OpsGenieClientValidationException.error("For participant type team/escalation either team/escalation name or id must be provided.");
            }
        }
        else if(type!= ScheduleParticipant.Type.none
                && StringUtils.isEmpty(id) && StringUtils.isEmpty(username)){
            throw OpsGenieClientValidationException.error("Username or id must be provided.");
        }
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
