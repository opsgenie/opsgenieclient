package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;
import java.util.TimeZone;

/**
 * Container for the parameters to snooze alert api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#snooze(SnoozeRequest)
 */
public class SnoozeRequest extends BaseAlertRequestWithNoteAndUserAndSource<SnoozeResponse, SnoozeRequest> implements ObjectWithTimeZone {
    private Date endDate;
    @JsonIgnore
    private TimeZone timeZone;

    /**
     * Timezone to determine snooze request end date. If not given GMT is used.
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine snooze request end date. If not given GMT is
     * used.
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String getEndPoint() {
        return "/v1/json/alert/snooze";
    }

    @Override
    public SnoozeResponse createResponse() {
        return new SnoozeResponse();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SnoozeRequest withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public SnoozeRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }
}
