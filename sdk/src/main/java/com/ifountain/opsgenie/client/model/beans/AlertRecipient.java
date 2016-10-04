package com.ifountain.opsgenie.client.model.beans;

/**
 * Alert bean
 */
public class AlertRecipient extends Bean {
    private String username;
    private String state;
    private long stateChangedAt;
    private String method;

    /**
     * Returns username of alert recipient
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of alert recipient
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns state of alert recipient
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state of alert recipient
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns state change date of alert recipient
     */
    public long getStateChangedAt() {
        return stateChangedAt;
    }

    /**
     * Sets state change date of alert recipient
     */
    public void setStateChangedAt(long stateChangedAt) {
        this.stateChangedAt = stateChangedAt;
    }

    /**
     * Returns method of alert recipient
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method of alert recipient
     */
    public void setMethod(String method) {
        this.method = method;
    }

}
