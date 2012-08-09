package com.ifountain.opsgenie.client.model;

/**
 * Represents the OpsGenie service response for an heartbeat request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:07 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#heartbeat(HeartbeatRequest)
 */
public class HeartbeatResponse implements Response {
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