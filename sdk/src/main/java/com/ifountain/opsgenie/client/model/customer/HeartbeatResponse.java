package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents the OpsGenie service response for an heartbeat request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#heartbeat(HeartbeatRequest)
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

}
