package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Heartbeat;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get heartbeat request.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getHeartbeat(com.ifountain.opsgenie.client.model.customer.GetHeartbeatRequest)
 */
public class GetHeartbeatResponse extends BaseResponse {
    private Heartbeat heartbeat;

    /**
     * Heartbeat object
     *
     * @see Heartbeat
     */
    public Heartbeat getHeartbeat() {
        return heartbeat;
    }

    /**
     * Sets heartbeat object
     *
     * @see Heartbeat
     */
    public void setHeartbeat(Heartbeat heartbeat) {
        this.heartbeat = heartbeat;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        System.out.println("Data = " + data);
        super.deserialize(data);
        heartbeat = new Heartbeat();
        heartbeat.fromMap(data);
    }
}
