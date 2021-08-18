package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 10:59 AM
 */
public class ScheduleOverride extends Bean implements ConvertFromTimeZone {

    private String alias;
    private String user;
    private Date startDate;
    private Date endDate;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private List<String> rotationIds;

    /**
     * User defined identifier for the override.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier for the override.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /*
     * Rotation id list that override will apply.
     */
    public List<String> getRotationIds() {
        return rotationIds;
    }

    /*
     * Sets rotation id list that override will apply.
     */
    public void setRotationIds(List<String> rotationIds) {
        this.rotationIds = rotationIds;
    }

    /**
     * Username of user whom the overrided is created for.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets username of user whom the override is created for.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Start date of schedule override.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of the schedule override.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of the schedule override.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of the schedule override.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Timezone to determine forwarding start and end dates
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine forwarding start and end dates
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public void setTime() throws ParseException {
        if (getObjectTimeZone() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
            String endDateString = null, startDateString = null;
            if (endDate != null)
                endDateString = sdf.format(endDate);
            if (startDate != null)
                startDateString = sdf.format(startDate);
            sdf.setTimeZone(getObjectTimeZone());
            if (endDateString != null)
                endDate = sdf.parse(endDateString);
            if (startDateString != null)
                startDate = sdf.parse(startDateString);
        }

    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }


    public ScheduleOverride withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public ScheduleOverride withUser(String user) {
        this.user = user;
        return this;
    }

    public ScheduleOverride withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public ScheduleOverride withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public ScheduleOverride withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public ScheduleOverride withRotationIds(List<String> rotationIds) {
        this.rotationIds = rotationIds;
        return this;
    }

}
