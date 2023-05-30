package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 2:01 PM
 */
public class TeamLog extends Bean {
    private String log;
    private String owner;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdDate;

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
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation time of the log in milliseconds.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public TeamLog withLog(String log) {
        this.log = log;
        return this;
    }

    public TeamLog withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public TeamLog withCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

}
