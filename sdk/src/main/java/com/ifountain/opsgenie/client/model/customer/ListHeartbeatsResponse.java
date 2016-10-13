package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Heartbeat;

import java.util.List;

/**
 * Represents OpsGenie service response for list heartbeat request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#listHeartbeats(ListHeartbeatsRequest)
 */
public class ListHeartbeatsResponse extends BaseResponse {
    private List<Heartbeat> heartbeats;

    /**
     * Heartbeat objects
     *
     * @see Heartbeat
     */
    public List<Heartbeat> getHeartbeats() {
        return heartbeats;
    }

    /**
     * Sets heartbeat objects
     *
     * @see Heartbeat
     */
    public void setHeartbeats(List<Heartbeat> heartbeats) {
        this.heartbeats = heartbeats;
    }

}
