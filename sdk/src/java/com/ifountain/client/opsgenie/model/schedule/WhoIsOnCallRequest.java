package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.opsgenie.model.BaseGetRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make a who is on call api call.
 * One of schedule name or schedule id field will be sufficient for this request.
 *
 * @author Mustafa Sener
 * @version 22.04.2013 15:10
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#whoIsOnCall(com.ifountain.client.opsgenie.model.schedule.WhoIsOnCallRequest)
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
        return "/v1/json/schedule/whoIsOnCall";
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public void _serialize(Map<String,Object> json){
        if(name != null){
            json.put(ClientConstants.API.NAME, name);
        }
        if(timeZone != null){
            json.put(ClientConstants.API.TIMEZONE, timeZone.getID());
        }
        if(time != null){
            SimpleDateFormat sdf = new SimpleDateFormat(ClientConstants.Common.API_DATE_FORMAT);
            sdf.setTimeZone(timeZone != null?timeZone:TimeZone.getTimeZone("UTC"));
            json.put(ClientConstants.API.TIME, sdf.format(time));
        }

    }

    @Override
    public List<String> getMandatoryProperties() {
        List<String> mandatoryProperyList = super.getMandatoryProperties();
        mandatoryProperyList.add(ClientConstants.API.NAME);
        return mandatoryProperyList;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public WhoIsOnCallResponse createResponse() {
        return new WhoIsOnCallResponse();
    }
}
