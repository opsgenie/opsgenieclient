package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Heartbeat.IntervalUnit;

/**
 * Container for the parameters to make a create heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#addHeartbeat(AddHeartbeatRequest)
 */
public class AddHeartbeatRequest extends BaseRequest<AddHeartbeatResponse, AddHeartbeatRequest> {
    private String name;
    private Boolean enabled;
    private Integer interval;
    private IntervalUnit intervalUnit = IntervalUnit.minutes;
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
    public IntervalUnit getIntervalUnit() {
        return intervalUnit;
    }

    /**
     * Sets the interval unit of heartbeat monitor
     */
    public void setIntervalUnit(IntervalUnit intervalUnit) {
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddHeartbeatResponse createResponse() {
        return new AddHeartbeatResponse();
    }

    public AddHeartbeatRequest withName(String name) {
        this.name = name;
        return this;
    }

    public AddHeartbeatRequest withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AddHeartbeatRequest withInterval(Integer interval) {
        this.interval = interval;
        return this;
    }

    public AddHeartbeatRequest withIntervalUnit(IntervalUnit intervalUnit) {
        this.intervalUnit = intervalUnit;
        return this;
    }

    public AddHeartbeatRequest withDescription(String description) {
        this.description = description;
        return this;
    }

}
