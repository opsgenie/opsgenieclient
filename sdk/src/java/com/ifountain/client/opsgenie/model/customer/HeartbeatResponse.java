package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents the OpsGenie service response for an heartbeat request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:07 PM
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#heartbeat(HeartbeatRequest)
 */
public class HeartbeatResponse extends BaseResponse {
    private long heartbeat;

    /**
     * Heartbeat time in milliseconds.
     */
    public long getHeartbeat() {
        return heartbeat;
    }

    /**
     * Sets heartbeat time in milliseconds.
     */
    public void setHeartbeat(long heartbeat) {
        this.heartbeat = heartbeat;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        heartbeat = ((Number) data.get("heartbeat")).longValue();
    }
}
