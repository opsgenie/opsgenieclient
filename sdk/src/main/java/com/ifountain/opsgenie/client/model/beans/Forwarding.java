package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Forwarding bean
 */
public class Forwarding extends BeanWithId implements ConvertFromTimeZone {
    private String alias;
    private BaseUserObj fromUser;
    private BaseUserObj toUser;
    private String startDate;
    private String endDate;
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
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of forwarding will be started
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of forwarding will be discarded
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of forwarding will be discarded
     */
    public void setEndDate(String endDate) {
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
                endDate = sdf.format(sdf.parse(endDateString));
            if (startDateString != null)
                startDate = sdf.format(sdf.parse(startDateString));
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

    public Forwarding withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public Forwarding withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Forwarding withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

}
