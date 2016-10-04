package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Container for the parameters to snooze alert api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#snooze(SnoozeRequest)
 */
public class SnoozeRequest extends AddNoteRequest {
    private Date endDate;
    @JsonIgnore
    private TimeZone timeZone;

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

}
