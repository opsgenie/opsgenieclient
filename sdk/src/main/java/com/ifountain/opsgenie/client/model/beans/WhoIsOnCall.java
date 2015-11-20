package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * WhoIsOnCall bean
 */
public class WhoIsOnCall extends BaseWhoIsOnCall{
    private List<WhoIsOnCallScheduleParticipant> participants;
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
        Map<String, Object> json = super.toMap();
        WhoIsOnCallScheduleParticipant.participantsToMap(json, participants);
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        super.fromMap(map);
        participants = WhoIsOnCallScheduleParticipant.participantsFromMap(map);
    }
}
