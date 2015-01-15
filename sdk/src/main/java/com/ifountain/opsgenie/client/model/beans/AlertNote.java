package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Alert bean
 */
public class AlertNote implements IBean {
    private String owner;
    private String note;
    private long createdAt;


    /**
     * @return Owner of the comment
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the comment
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return The note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return The creation time of the note in nanoseconds
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of the note in nanoseconds
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(OpsGenieClientConstants.API.CREATED_AT, getCreatedAt());
        json.put(OpsGenieClientConstants.API.OWNER, getOwner());
        json.put(OpsGenieClientConstants.API.NOTE, getNote());
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        setOwner((String) map.get(OpsGenieClientConstants.API.OWNER));
        setNote((String) map.get(OpsGenieClientConstants.API.NOTE));
        setCreatedAt(((Number) map.get(OpsGenieClientConstants.API.CREATED_AT)).longValue());
    }
}
