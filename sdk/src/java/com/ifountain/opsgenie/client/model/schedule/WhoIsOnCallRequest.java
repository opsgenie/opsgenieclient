package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseGetRequest;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#whoIsOnCall(com.ifountain.opsgenie.client.model.schedule.WhoIsOnCallRequest)
 */
public class WhoIsOnCallRequest extends BaseGetRequest<WhoIsOnCallResponse> {
    private String name;
    private Date time;
    private TimeZone timeZone;
    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
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
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public WhoIsOnCallResponse createResponse() {
        return new WhoIsOnCallResponse();
    }
}
