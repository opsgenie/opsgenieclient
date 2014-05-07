package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Schedule bean
 */
public class WhoIsOnCall implements IBean {
    private String name;
    private String id;
    private String type;
    private List<WhoIsOnCallScheduleParticipant> participants;

    /**
     * Name of schedule
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Id of schedule
     */
    public String getId() {
        return id;
    }

    /**
     * Type of WhoIsOnCall
     */
    public String getType() {
        return type;
    }


    /**
     * OnCall participants
     * @return
     */
    public List<WhoIsOnCallScheduleParticipant> getParticipants() {
        return participants;
    }

    /**
     * Sets OnCall participants
     * @param participants
     */
    public void setParticipants(List<WhoIsOnCallScheduleParticipant> participants) {
        this.participants = participants;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ClientConstants.API.NAME, name);
        if(id != null){
            json.put(ClientConstants.API.ID, id);
        }
        if(type != null){
            json.put(ClientConstants.API.TYPE, type);
        }
        WhoIsOnCallScheduleParticipant.participantsToMap(json, participants);
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        name = (String) map.get(ClientConstants.API.NAME);
        id = (String) map.get(ClientConstants.API.ID);
        type = (String) map.get(ClientConstants.API.TYPE);
        participants = WhoIsOnCallScheduleParticipant.participantsFromMap(map);
    }
}
