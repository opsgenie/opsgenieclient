package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;

import java.text.ParseException;
import java.util.Map;

/**
 * Who Is On Call User Participant Bean.
 * 
 * @author Mustafa Sener
 * @version 22.04.2014 15:10
 */
public class WhoIsOnCallUserParticipant extends WhoIsOnCallScheduleParticipant{
    private Boolean forwarded;
    private WhoIsOnCallUserParticipant forwardedFrom;

    /**
     * Returns the on call participant is forwarded from another user or not
     */
    public Boolean getForwarded() {
        return forwarded;
    }

    /*
     * Sets on call participant is forwarded from another user or not
     */
    public void setForwarded(Boolean forwarded) {
        this.forwarded = forwarded;
    }

    /**
     * Returns the user who make the forwarding action
     */
    public WhoIsOnCallUserParticipant getForwardedFrom(){
        return forwardedFrom;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String, Object> json = super.toMap();
        if(forwarded != null){
            json.put(ClientConstants.API.FORWARDED, forwarded);
        }
        if(forwardedFrom != null){
            json.put(ClientConstants.API.FORWARDED_FROM, forwarded);
        }
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        super.fromMap(map);
        if(map.containsKey(ClientConstants.API.FORWARDED)){
            forwarded = (Boolean) map.get(ClientConstants.API.FORWARDED);
        }
        if(map.containsKey(ClientConstants.API.FORWARDED_FROM)){
            forwardedFrom = new WhoIsOnCallUserParticipant();
            forwardedFrom.fromMap((Map<String,Object>)map.get(ClientConstants.API.FORWARDED_FROM));
        }
    }
}
