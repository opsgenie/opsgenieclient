package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.*;

/**
 * Schedule bean
 */
public class WhoIsOnCall implements IBean{
    private String name;
    private List<ScheduleParticipant> participants;

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
     * OnCall participants
     * @return
     */
    public List<ScheduleParticipant> getParticipants() {
        return participants;
    }

    /**
     * Sets OnCall participants
     * @param participants
     */
    public void setParticipants(List<ScheduleParticipant> participants) {
        this.participants = participants;
    }

    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.NAME, name);
        if(participants != null){
            List<Map> participantMaps = new ArrayList<Map>();
            for(ScheduleParticipant participant:participants) {
                participantMaps.add(participant.toMap());
            }
            json.put(OpsGenieClientConstants.API.PARTICIPANTS, participantMaps);
        }
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        name = (String) map.get(OpsGenieClientConstants.API.NAME);
        List<Map> participantMaps = (List<Map>) map.get(OpsGenieClientConstants.API.PARTICIPANTS);
        if(participantMaps != null){
            participants = new ArrayList<ScheduleParticipant>();
            for(Map participantMap:participantMaps) {
                ScheduleParticipant participant;
                if(ScheduleParticipant.Type.user.name().equals(participantMap.get(OpsGenieClientConstants.API.TYPE)))
                {
                    participant = new WhoIsOnCallUserParticipant();
                }
                else{
                    participant = new ScheduleParticipant();
                }
                participant.fromMap(participantMap);
                participants.add(participant);
            }
        }
    }
}
