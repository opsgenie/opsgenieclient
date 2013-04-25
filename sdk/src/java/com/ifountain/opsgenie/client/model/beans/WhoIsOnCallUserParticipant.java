package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.Map;

/**
 * Schedule bean
 */
public class WhoIsOnCallUserParticipant extends ScheduleParticipant{
    private Boolean forwarded;

    public Boolean getForwarded() {
        return forwarded;
    }

    public void setForwarded(Boolean forwarded) {
        this.forwarded = forwarded;
    }

    @Override
    public Map toMap() {
        Map<String, Object> json = super.toMap();
        json.put(OpsGenieClientConstants.API.FORWARDED, forwarded);
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        super.fromMap(map);
        if(map.containsKey(OpsGenieClientConstants.API.FORWARDED)){
            forwarded = (Boolean) map.get(OpsGenieClientConstants.API.FORWARDED);
        }
    }
}
