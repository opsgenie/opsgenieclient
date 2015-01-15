package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.*;

/**
 * Heartbeat bean
 */
public class Heartbeat implements IBean{
    public static enum IntervalUnit {
        minutes,
        hours,
        days
    }
    private String id;
    private String name;
    private Date lastHeartbeat;
    private Boolean expired;
    private Boolean enabled;
    private String status;
    private String description;
    private Integer interval;
    private IntervalUnit intervalUnit;

    /**
     * Id of heartbeat
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the Id of heartbeat
     */
    public void setId(String id) {
        this.id = id;
    }

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

    /*
        * Name of heartbeat monitor
         */
    public String getName() {
        return name;
    }

    /*
    * Sets the name of heartbeat monitor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @deprecated
    * User getName
    **/
    public String getSource() {
        return getName();
    }

    /**
     * @deprecated
    * Use setName
     **/
    public void setSource(String source) {
        setName(source);
    }

    /*
    * Last heartbeat recieve time
     */
    public Date getLastHeartbeat() {
        return lastHeartbeat;
    }

    /*
    * Sets last heartbeat receieve time
     */
    public void setLastHeartbeat(Date lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    /*
    * Returns true if heartbeat is expired
     */
    public boolean isExpired() {
        return expired;
    }

    /*
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



    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(OpsGenieClientConstants.API.SOURCE, name);
        json.put(OpsGenieClientConstants.API.LAST_HEARTBEAT, lastHeartbeat);
        json.put(OpsGenieClientConstants.API.EXPIRED, expired);
        json.put(OpsGenieClientConstants.API.ENABLED, enabled);
        json.put(OpsGenieClientConstants.API.DESCRIPTION, description);
        json.put(OpsGenieClientConstants.API.STATUS, status);
        json.put(OpsGenieClientConstants.API.ID, id);
        json.put(OpsGenieClientConstants.API.INTERVAL, interval);
        json.put(OpsGenieClientConstants.API.INTERVAL_UNIT, intervalUnit != null? intervalUnit.name():null);
        return json;
    }

    @Override
    public void fromMap(Map resp) {
        setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        setLastHeartbeat(new Date(((Number) resp.get(OpsGenieClientConstants.API.LAST_HEARTBEAT)).longValue()));
        setEnabled((Boolean) resp.get(OpsGenieClientConstants.API.ENABLED));
        setDescription((String) resp.get(OpsGenieClientConstants.API.DESCRIPTION));
        setStatus((String) resp.get(OpsGenieClientConstants.API.STATUS));
        setExpired("Expired".equals(getStatus()));
        setId((String) resp.get(OpsGenieClientConstants.API.ID));
        setInterval(((Number) resp.get(OpsGenieClientConstants.API.INTERVAL)).intValue());
        if(resp.containsKey(OpsGenieClientConstants.API.INTERVAL_UNIT)){
            setIntervalUnit(IntervalUnit.valueOf((String) resp.get(OpsGenieClientConstants.API.INTERVAL_UNIT)));
        }
    }

}
