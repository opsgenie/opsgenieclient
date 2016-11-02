package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;

import org.codehaus.jackson.annotate.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Forwarding bean
 */
public class Forwarding extends BeanWithId implements ConvertFromTimeZone {
    private String alias;
    private String fromUser;
    private String toUser;
    private Date startDate;
    private Date endDate;
    @JsonProperty("timezone")
    private TimeZone timeZone;

    /**
     * A user defined identifier for the Provides ability to assign a known id
     * and later use this id to get forwarding details.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Username of user which forwarding is created for
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets username of user who forwarding is created for
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username of user who forwarding will be directed to
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Sets username of user who forwarding will be directed to
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * Start date of forwarding will be started
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of forwarding will be started
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of forwarding will be discarded
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of forwarding will be discarded
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

    public Forwarding withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public Forwarding withFromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Forwarding withToUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    public Forwarding withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Forwarding withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Forwarding withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

}
