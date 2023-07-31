package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Forwarding bean
 */
public class Forwarding extends BeanWithId implements ConvertFromTimeZone {
    private String alias;
    private BaseUserObj fromUser;
    private BaseUserObj toUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
     * gets user which forwarding is created for
     */
    public BaseUserObj getFromUser() {
        return fromUser;
    }

    /**
     * Sets user who forwarding is created for
     */
    public void setFromUser(BaseUserObj fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * User who forwarding will be directed to
     */
    public BaseUserObj getToUser() {
        return toUser;
    }

    /**
     * Sets user who forwarding will be directed to
     */
    public void setToUser(BaseUserObj toUser) {
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
            sdf.setTimeZone(getObjectTimeZone());
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

    public Forwarding withFromUser(BaseUserObj fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Forwarding withToUser(BaseUserObj toUser) {
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
