package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.model.IBean;
import com.ifountain.client.OpsGenieClientConstants;

import java.util.*;

/**
 * Escalation bean
 */
public class Heartbeat implements IBean {
    private String source;
    private Date lastHeartbeat;
    private boolean expired;

    /*
    * Source of heartbeat monitor
     */
    public String getSource() {
        return source;
    }

    /*
    * Sets source of heartbeat monitor
     */
    public void setSource(String source) {
        this.source = source;
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

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(OpsGenieClientConstants.API.SOURCE, source);
        json.put(OpsGenieClientConstants.API.LAST_HEARTBEAT, lastHeartbeat);
        json.put(OpsGenieClientConstants.API.EXPIRED, expired);
        return json;
    }

    @Override
    public void fromMap(Map resp) {
        setSource((String) resp.get(OpsGenieClientConstants.API.SOURCE));
        setLastHeartbeat(new Date(((Number) resp.get(OpsGenieClientConstants.API.LAST_HEARTBEAT)).longValue()));
        setExpired((Boolean) resp.get(OpsGenieClientConstants.API.EXPIRED));
    }
}
