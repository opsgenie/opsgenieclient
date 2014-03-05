package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Schedule bean
 */
public class WhoIsOnCall implements IBean{
    private String name;
    private String id;
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
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.NAME, name);
        if(id != null){
            json.put(OpsGenieClientConstants.API.ID, id);
        }
        WhoIsOnCallScheduleParticipant.participantsToMap(json, participants);
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        name = (String) map.get(OpsGenieClientConstants.API.NAME);
        id = (String) map.get(OpsGenieClientConstants.API.ID);
        participants = WhoIsOnCallScheduleParticipant.participantsFromMap(map);
    }
}
