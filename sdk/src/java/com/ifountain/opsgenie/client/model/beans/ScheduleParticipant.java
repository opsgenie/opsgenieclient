package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * ScheduleParticipant bean
 */
public class ScheduleParticipant  implements IBean{
    public enum Type{
        user,
        group,
        escalation
    }
    private String name;
    private Type type;

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
     * @see Type
     */
    public Type getType() {
        return type;
    }


    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.NAME, name);
        if(type != null){
            json.put(OpsGenieClientConstants.API.TYPE, type.name());
        }
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        name = (String) map.get(OpsGenieClientConstants.API.PARTICIPANT);
        if(map.containsKey(OpsGenieClientConstants.API.TYPE)){
            type = Type.valueOf(((String) map.get(OpsGenieClientConstants.API.TYPE)).toLowerCase());
        }
    }
}
