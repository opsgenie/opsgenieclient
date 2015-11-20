package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseGetRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Base Container for the parameters to make a who is on call api call.
 *
 */
public abstract class AbstractWhoIsOnCallRequest<T extends BaseResponse> extends BaseGetRequest<T> {
    private String name;
    private Date time;
    private TimeZone timeZone;

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
     * Timezone for request
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone for request
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
    /**
     * @see com.ifountain.opsgenie.client.model.BaseGetRequest#_serialize()
     */
    public void _serialize(Map json){
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        if(timeZone != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, timeZone.getID());
        }
        if(time != null){
            SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
            sdf.setTimeZone(timeZone != null?timeZone:TimeZone.getTimeZone("UTC"));
            json.put(OpsGenieClientConstants.API.TIME, sdf.format(time));
        }
    }

    @Override
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
    }
}
