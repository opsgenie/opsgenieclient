package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.HeartbeatConfig;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get heartbeat config request.
 *
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#getHeartbeat(GetHeartbeatRequest)
 */
public class GetHeartbeatConfigResponse extends BaseResponse{
    private HeartbeatConfig heartbeatConfig;

    /**
     * Heartbeat config object
     * @see com.ifountain.client.opsgenie.model.beans.Heartbeat
     */
    public HeartbeatConfig getHeartbeatConfig() {
        return heartbeatConfig;
    }

    /**
     * Sets heartbeat config object
     * @see com.ifountain.client.opsgenie.model.beans.Heartbeat
     */
    public void setHeartbeatConfig(HeartbeatConfig heartbeatConfig) {
        this.heartbeatConfig = heartbeatConfig;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        heartbeatConfig = new HeartbeatConfig();
        heartbeatConfig.fromMap(data);
    }
}
