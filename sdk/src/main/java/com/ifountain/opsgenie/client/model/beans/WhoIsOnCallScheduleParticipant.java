package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Abdurrahim on 04.03.2014.
 */
@Deprecated
public class WhoIsOnCallScheduleParticipant extends ScheduleParticipant {

    private List<WhoIsOnCallScheduleParticipant> participants;
    private Integer escalationTime;
    private String notifyType;

    private String id;
    /**
     * Id of participant
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

    /**
     * Escalation time of escalation member in minutes
     * Notify member x minutes after alert is created
     */
    public Integer getEscalationTime() {
        return escalationTime;
    }

    /**
     * Notify type of the escalation member
     * Can be "default", "next", "previous", "admins", "users"
     */
    public String getNotifyType() {
        return notifyType;
    }

    @Override
    public Map toMap() {
        Map<String, Object> json = super.toMap();
        json.put(OpsGenieClientConstants.API.ID, id);
        if(escalationTime != null){
            json.put(OpsGenieClientConstants.API.ESCALATION_TIME, escalationTime);
        }
        if(notifyType != null){
            json.put(OpsGenieClientConstants.API.NOTIFY_TYPE, notifyType);
        }
        participantsToMap(json, participants);
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        super.fromMap(map);
        if (map.containsKey(OpsGenieClientConstants.API.NAME)) {
            setParticipant((String) map.get(OpsGenieClientConstants.API.NAME));
        }

        if(map.containsKey(OpsGenieClientConstants.API.ID)){
            id = (String) map.get(OpsGenieClientConstants.API.ID);
        }
        if(map.containsKey(OpsGenieClientConstants.API.ESCALATION_TIME)){
            escalationTime = (Integer) map.get(OpsGenieClientConstants.API.ESCALATION_TIME);
        }
        if(map.containsKey(OpsGenieClientConstants.API.NOTIFY_TYPE)){
            notifyType = (String) map.get(OpsGenieClientConstants.API.NOTIFY_TYPE);
        }
        this.participants = participantsFromMap(map);
    }

    public static void participantsToMap(Map<String, Object> json, List<WhoIsOnCallScheduleParticipant> participantList) {
        if(participantList != null){
            List<Map> participantMaps = new ArrayList<Map>();
            for(ScheduleParticipant participant:participantList) {
                participantMaps.add(participant.toMap());
            }
            json.put(OpsGenieClientConstants.API.PARTICIPANTS, participantMaps);
        }
    }

    public static List<WhoIsOnCallScheduleParticipant> participantsFromMap(Map map) throws ParseException{
        List<WhoIsOnCallScheduleParticipant> participantList = null;
        List<Map> participantMaps = (List<Map>) map.get(OpsGenieClientConstants.API.PARTICIPANTS);
        if(participantMaps != null){
            participantList = new ArrayList<WhoIsOnCallScheduleParticipant>();
            for(Map participantMap:participantMaps) {
                WhoIsOnCallScheduleParticipant participant;
                if(Type.user.name().equals(participantMap.get(OpsGenieClientConstants.API.TYPE)))
                {
                    participant = new WhoIsOnCallUserParticipant();
                }
                else{
                    participant = new WhoIsOnCallScheduleParticipant();
                }
                participant.fromMap(participantMap);
                participantList.add(participant);
            }
        }
        return participantList;
    }
}
