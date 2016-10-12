package com.ifountain.opsgenie.client.model.beans;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Heartbeat bean
 */
public class Heartbeat extends Bean {
    private String name;
    @JsonProperty("lastHeartbeat")
    private Long lastHeartbeat;
    private Boolean expired;
    private Boolean enabled;
    private String status;
    private String description;
    private Integer interval;
    private IntervalUnit intervalUnit;

    /**
     * Status of heartbeat
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of heartbeat
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Name of heartbeat monitor
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
     * @deprecated use getLastHeartbeatTime
     */
    @Deprecated
    @JsonIgnore
    public Date getLastHeartbeat() {
        if (lastHeartbeat == null)
            return null;
        return new Date(lastHeartbeat);
    }

    /**
     * @deprecated use setLastHeartbeatTime
     */
    @Deprecated
    @JsonIgnore
    public void setLastHeartbeat(Date lastHeartbeat) {
        if (lastHeartbeat == null) {
            this.lastHeartbeat = null;
        } else {
            this.lastHeartbeat = lastHeartbeat.getTime();
        }
    }

    /**
     * Last heartbeat recieve time
     */
    @JsonIgnore
    public Long getLastHeartbeatTime() {
        return lastHeartbeat;
    }

    /**
     * Sets last heartbeat receieve time
     */
    public void setLastHeartbeat(Long lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    /**
     * Returns true if heartbeat is expired
     */
    public Boolean isExpired() {
        return expired;
    }

    /**
     * Set expired state of heartbeat
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
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

    public enum IntervalUnit {
        minutes, hours, days
    }

}
