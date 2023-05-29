package com.ifountain.opsgenie.client.model.beans;

public class BaseUserObj extends BeanWithId{
    private String username;

    /**
     * Username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public BaseUserObj withUsername(String username) {
        this.username = username;
        return this;
    }
}
