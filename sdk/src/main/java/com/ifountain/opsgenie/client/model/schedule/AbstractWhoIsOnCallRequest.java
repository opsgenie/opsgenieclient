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
 */
public abstract class AbstractWhoIsOnCallRequest<T extends BaseResponse> extends BaseRequest<T> {
    private String name;
    private Date time;
    private TimeZone timezone;
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
            sdf.setTimeZone(timezone != null ? timezone : TimeZone.getTimeZone("UTC"));
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
     * Timezone Id for request
     */
    @JsonProperty("timezone")
    public String getTimeZoneId() {
        if (timezone != null)
            return timezone.getID();
        return null;
    }

    /**
     * Timezone for request
     */
    public TimeZone getTimeZone() {
        return timezone;
    }

    /**
     * Sets timezone for request
     */
    public void setTimeZone(TimeZone timezone) {
        this.timezone = timezone;
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
