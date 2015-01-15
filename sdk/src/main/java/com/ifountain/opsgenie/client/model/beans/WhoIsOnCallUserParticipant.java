package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.Map;

/**
 * Schedule bean
 */
public class WhoIsOnCallUserParticipant extends WhoIsOnCallScheduleParticipant{
    private Boolean forwarded;
    private WhoIsOnCallUserParticipant forwardedFrom;

    public Boolean getForwarded() {
        return forwarded;
    }

    public void setForwarded(Boolean forwarded) {
        this.forwarded = forwarded;
    }

    public WhoIsOnCallUserParticipant getForwardedFrom(){
        return forwardedFrom;
    }

    @Override
    public Map toMap() {
        Map<String, Object> json = super.toMap();
        if(forwarded != null){
            json.put(OpsGenieClientConstants.API.FORWARDED, forwarded);
        }
        if(forwardedFrom != null){
            json.put(OpsGenieClientConstants.API.FORWARDED_FROM, forwarded);
        }
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        super.fromMap(map);
        if(map.containsKey(OpsGenieClientConstants.API.FORWARDED)){
            forwarded = (Boolean) map.get(OpsGenieClientConstants.API.FORWARDED);
        }
        if(map.containsKey(OpsGenieClientConstants.API.FORWARDED_FROM)){
            forwardedFrom = new WhoIsOnCallUserParticipant();
            forwardedFrom.fromMap((Map)map.get(OpsGenieClientConstants.API.FORWARDED_FROM));
        }
    }
}
