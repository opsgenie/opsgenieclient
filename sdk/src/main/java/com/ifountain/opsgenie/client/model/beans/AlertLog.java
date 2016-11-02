package com.ifountain.opsgenie.client.model.beans;

/**
 * Alert log bean
 */
public class AlertLog extends Bean {
    private String log;
    private String owner;
    private String logType;
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
     * Gets log type
     */
    public String getLogType() {
        return logType;
    }

    /**
     * Sets log type
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * The owner of the alert log
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the alert log
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

    public AlertLog withLog(String log) {
        this.log = log;
        return this;
    }

    public AlertLog withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public AlertLog withLogType(String logType) {
        this.logType = logType;
        return this;
    }

    public AlertLog withCreatedAt(long createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
