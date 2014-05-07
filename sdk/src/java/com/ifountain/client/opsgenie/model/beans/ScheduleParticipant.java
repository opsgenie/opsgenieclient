package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * ScheduleParticipant bean
 */
public class ScheduleParticipant  implements IBean {
    public enum Type{
        user,
        group,
        escalation
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
     * Type of participant
     * Could be one of user, group, escalation
     * @see Type
     */
    public Type getType() {
        return type;
    }


    @Override
    public Map<String,Object> toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ClientConstants.API.PARTICIPANT, participant);
        if(type != null){
            json.put(ClientConstants.API.TYPE, type.name());
        }
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        if(map.containsKey(ClientConstants.API.PARTICIPANT)){
            participant = (String) map.get(ClientConstants.API.PARTICIPANT);
        }
        else{
            //FOR who is on call
            participant = (String) map.get(ClientConstants.API.NAME);
        }
        if(map.containsKey(ClientConstants.API.TYPE)){
            type = Type.valueOf(((String) map.get(ClientConstants.API.TYPE)).toLowerCase());
        }
    }
}
