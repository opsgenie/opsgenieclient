package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Heartbeat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list heartbeats request.
 *
 * @author Mustafa Sener
 * @version 30.04.2013 22:36
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#listHeartbeats(ListHeartbeatsRequest)
 */
public class ListHeartbeatsResponse extends BaseResponse{
    private List<Heartbeat> heartbeats;

    /**
     * Heartbeat objects
     * @see Heartbeat
     */
    public List<Heartbeat> getHeartbeats() {
        return heartbeats;
    }

    /**
     * Sets heartbeat objects
     * @see Heartbeat
     */
    public void setHeartbeats(List<Heartbeat> heartbeats) {
        this.heartbeats = heartbeats;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        List<Map<String, Object>> sourcesData = (List<Map<String, Object>>) data.get(ClientConstants.API.SOURCES);
        heartbeats = new ArrayList<Heartbeat>();
        for(Map<String, Object> sourceData:sourcesData){
            Heartbeat heartbeat = new Heartbeat();
            heartbeat.fromMap(sourceData);
            heartbeats.add(heartbeat);
        }
    }
}
