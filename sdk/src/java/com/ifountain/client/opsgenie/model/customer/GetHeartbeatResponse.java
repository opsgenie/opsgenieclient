package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Heartbeat;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get heartbeat request.
 *
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#getHeartbeat(com.ifountain.client.opsgenie.model.customer.GetHeartbeatRequest)
 */
public class GetHeartbeatResponse extends BaseResponse{
    private Heartbeat heartbeat;

    /**
     * Heartbeat object
     * @see Heartbeat
     */
    public Heartbeat getHeartbeat() {
        return heartbeat;
    }

    /**
     * Sets heartbeat object
     * @see Heartbeat
     */
    public void setHeartbeat(Heartbeat heartbeat) {
        this.heartbeat = heartbeat;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        heartbeat = new Heartbeat();
        heartbeat.fromMap(data);
    }
}
