package com.ifountain.opsgenie.client.model.beans;

import java.util.Date;

/**
 * Heartbeat bean
 */
public class Heartbeat extends Bean {
    private String name;
    private Date lastHeartbeat;
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
     * Last heartbeat recieve time
     */
    public Date getLastHeartbeat() {
        return lastHeartbeat;
    }

    /**
     * Sets last heartbeat receieve time
     */
    public void setLastHeartbeat(Date lastHeartbeat) {
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

    public Heartbeat withName(String name) {
        this.name = name;
        return this;
    }

    public Heartbeat withLastHeartbeat(Date lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
        return this;
    }

    public Heartbeat withExpired(Boolean expired) {
        this.expired = expired;
        return this;
    }

    public Heartbeat withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Heartbeat withStatus(String status) {
        this.status = status;
        return this;
    }

    public Heartbeat withDescription(String description) {
        this.description = description;
        return this;
    }

    public Heartbeat withInterval(Integer interval) {
        this.interval = interval;
        return this;
    }

    public Heartbeat withIntervalUnit(IntervalUnit intervalUnit) {
        this.intervalUnit = intervalUnit;
        return this;
    }

    public enum IntervalUnit {
        minutes, hours, days
    }

}
