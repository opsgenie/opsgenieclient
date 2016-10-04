package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;
import org.codehaus.jackson.annotate.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Base Container for the parameters to make a who is on call api call.
 *
 * @author Mehmet Mustafa Demir
 */
public abstract class AbstractWhoIsOnCallRequest<T extends BaseResponse> extends BaseRequest<T> {
    private String name;
    private Date time;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private String id;

    /**
     * Id of object to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of object to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Target date in string form of WhoIsOnCall request
     */
    @JsonProperty("time")
    public String getTimeString() {
        if (time != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
            sdf.setTimeZone(timeZone != null ? timeZone : TimeZone.getTimeZone("UTC"));
            return sdf.format(time);
        }
        return null;
    }

    /**
     * Target date of WhoIsOnCall request
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets target date of WhoIsOnCall request
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * TimeZone for request
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timeZone for request
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Name of schedule to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
    }
}
