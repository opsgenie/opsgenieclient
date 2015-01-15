package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Heartbeat;

import java.util.Map;

/**
 * Container for the parameters to make a create heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#addHeartbeat(com.ifountain.opsgenie.client.model.customer.AddHeartbeatRequest)
 */
public class AddHeartbeatRequest extends BaseRequest<AddHeartbeatResponse> {
    private String name;
    private Boolean enabled;
    private Integer interval;
    private Heartbeat.IntervalUnit intervalUnit = Heartbeat.IntervalUnit.minutes;
    private String description;
    /**
     * Rest api uri of adding heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat";
    }

    /**
     * Return name of heartbeat monitor
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of heartbeat monitor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return enable/disable state of heartbeat monitor
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enable/disable state of heartbeat monitor
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Return interval of heartbeat monitor
     */
    public Integer getInterval() {
        return interval;
    }

    /**
     * Sets the interval of heartbeat monitor
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /**
     * Return interval unit of heartbeat monitor
     */
    public Heartbeat.IntervalUnit getIntervalUnit() {
        return intervalUnit;
    }

    /**
     * Sets the interval unit of heartbeat monitor
     */
    public void setIntervalUnit(Heartbeat.IntervalUnit intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    /**
     * Return description of heartbeat monitor
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of heartbeat monitor
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddHeartbeatResponse createResponse() {
        return new AddHeartbeatResponse();
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        if(enabled != null){
            json.put(OpsGenieClientConstants.API.ENABLED, enabled);
        }
        if(interval != null){
            json.put(OpsGenieClientConstants.API.INTERVAL, interval);
        }
        if(intervalUnit != null){
            json.put(OpsGenieClientConstants.API.INTERVAL_UNIT, intervalUnit.name());
        }
        if(description != null){
            json.put(OpsGenieClientConstants.API.DESCRIPTION, description);
        }
        return json;
    }

}
