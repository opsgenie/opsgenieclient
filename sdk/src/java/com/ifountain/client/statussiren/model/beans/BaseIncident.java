package com.ifountain.client.statussiren.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseIncident bean
 * @author Tuba Ozturk
 * @version 30.4.2014 10:38
 */
public abstract class BaseIncident implements IBean {

    public enum State{
        open,
        closed
    }

    private long id;
    private String message;
    private Status status;
    private State state;
    private long createdAt;

    /**
     * The id of the incident
     */
    public long getId() {
        return id;
    }
    /**
     * Sets the id of the incident
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * The text of the incident
     */
    public String getMessage() {
        return message;
    }

    /*
     * Sets the text of the incident
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * The status of the incident
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the incident
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * The state of the incident
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the incident
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * The creation time of incident in milliseconds.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of incident in milliseconds.
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String,Object> json = new HashMap<String,Object>();
        json.put(ClientConstants.API.ID, getId());
        json.put(ClientConstants.API.MESSAGE, getMessage());
        json.put(ClientConstants.API.STATUS, getStatus().getValue());
        json.put(ClientConstants.API.STATE, getState().name());
        json.put(ClientConstants.API.CREATED_AT, getCreatedAt());
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        setId(((Number) map.get(ClientConstants.API.ID)).longValue());
        setMessage((String) map.get(ClientConstants.API.MESSAGE));
        setState(State.valueOf(((String) map.get(ClientConstants.API.STATE)).toLowerCase()));
        String status = (String) map.get(ClientConstants.API.STATUS);
        setStatus(Status.findByKey(status));
        setCreatedAt(((Number) map.get(ClientConstants.API.CREATED_AT)).longValue());
    }
}
