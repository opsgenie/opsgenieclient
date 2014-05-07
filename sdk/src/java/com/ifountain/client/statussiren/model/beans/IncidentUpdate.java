package com.ifountain.client.statussiren.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * IncidentUpdate bean
 * @author Tuba Ozturk
 * @version 2.5.2014 11:02
 */
public class IncidentUpdate implements IBean {
    private String message;
    private long createdAt;

    /*
     * Update message
     */
    public String getMessage() {
        return message;
    }

    /*
    * Sets the update message
    */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * The creation time of IncidentUpdate in milliseconds.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of IncidentUpdate in milliseconds.
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> json = new HashMap<String,Object>();
        json.put(ClientConstants.API.MESSAGE,getMessage());
        json.put(ClientConstants.API.CREATED_AT,getCreatedAt());
        return json;
    }

    @Override
    public void fromMap(Map<String, Object> map) throws ParseException {
        setMessage((String) map.get(ClientConstants.API.MESSAGE));
        setCreatedAt(((Number) map.get(ClientConstants.API.CREATED_AT)).longValue());
    }
}
