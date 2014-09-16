package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Alert bean
 */
public class AlertNote implements IBean {
    private String user;
    private String note;
    private long createdAt;


    /**
     * @return User of the comment
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user of the comment
     */
    public void setUser(String user) {
        this.user = user;
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
        json.put(OpsGenieClientConstants.API.USER, getUser());
        json.put(OpsGenieClientConstants.API.NOTE, getNote());
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        setUser((String) map.get(OpsGenieClientConstants.API.USER));
        setNote((String) map.get(OpsGenieClientConstants.API.NOTE));
        setCreatedAt(((Number) map.get(OpsGenieClientConstants.API.CREATED_AT)).longValue());
    }
}
