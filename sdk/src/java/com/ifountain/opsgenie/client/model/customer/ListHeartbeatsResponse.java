package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Heartbeat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list heartbeat request.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#listHeartbeats(ListHeartbeatsRequest)
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
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> sourcesData = (List<Map>) data.get(OpsGenieClientConstants.API.SOURCES);
        heartbeats = new ArrayList<Heartbeat>();
        for(Map sourceData:sourcesData){
            Heartbeat heartbeat = new Heartbeat();
            heartbeat.fromMap(sourceData);
            heartbeats.add(heartbeat);
        }
    }
}
