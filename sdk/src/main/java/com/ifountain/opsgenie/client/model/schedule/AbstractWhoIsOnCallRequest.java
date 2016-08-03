package com.ifountain.opsgenie.client.model.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Base Container for the parameters to make a who is on call api call.
 *
 */
public abstract class AbstractWhoIsOnCallRequest<T extends BaseResponse> extends BaseRequest<T> {
    private String name;
    private Date time;
    private TimeZone timeZone;    
    private String id;
    
    @Override
    public Map serialize() throws OpsGenieClientValidationException {
		Map json = new HashMap();
		if (getApiKey() != null) 
			json.put(OpsGenieClientConstants.API.API_KEY, getApiKey());
		if(getName() != null)
            json.put(OpsGenieClientConstants.API.NAME, getName());
        if(getTimeZone() != null)
            json.put(OpsGenieClientConstants.API.TIMEZONE, getTimeZone().getID());
        if(time != null){
            SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
            sdf.setTimeZone(timeZone != null?timeZone:TimeZone.getTimeZone("UTC"));
            json.put(OpsGenieClientConstants.API.TIME, sdf.format(time));
        }
        if(getId() != null)
            json.put(OpsGenieClientConstants.API.ID, getId());
    	return json;
    }
    
    
    
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
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
    }
}
