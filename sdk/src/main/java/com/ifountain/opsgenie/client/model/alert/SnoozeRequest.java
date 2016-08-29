package com.ifountain.opsgenie.client.model.alert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

public class SnoozeRequest extends BaseAlertRequestWithSource<SnoozeResponse> {
    private String user;
    private String note;
    private Date endDate;
    @JsonIgnore
    private TimeZone timeZone;
    /**
     * The user who is performing the add note operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add note operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Additional alert note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional alert note.
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * End date of the snooze request.
     */
	@JsonProperty("endDate")
    public String getEndDateString() {
        if (getEndDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
            if (getTimeZone() != null)
                sdf.setTimeZone(getTimeZone());
            else
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return sdf.format(getEndDate());
        }
        return null;
    }
	
    /**
     * Timezone to determine snooze request end date. If not given GMT is used.
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine snooze request end date. If not given GMT is used.
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

}
