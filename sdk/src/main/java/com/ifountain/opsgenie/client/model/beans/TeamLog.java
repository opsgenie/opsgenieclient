package com.ifountain.opsgenie.client.model.beans;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 2:01 PM
 */
public class TeamLog extends Bean {
    private String log;
    private String owner;
    private long createdAt;

    /**
     * Gets log message
     */
    public String getLog() {
        return log;
    }

    /**
     * Sets log message
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * The owner of the team log
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the team log
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * The creation time of log in milliseconds.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of the log in milliseconds.
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }


    public TeamLog withLog(String log) {
        this.log = log;
        return this;
    }

    public TeamLog withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public TeamLog withCreatedAt(long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

}
