package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

import javax.xml.bind.ValidationException;

/**
 * Container for the parameters to make a get schedule override api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 10:42 AM
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getScheduleOverride(GetScheduleOverrideRequest)
 */
public class GetScheduleOverrideRequest extends BaseRequest<GetScheduleOverrideResponse> {
    private String alias;
    private String schedule;

    /**
     * Rest api uri of get schedule override operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule/override";
    }

    /**
     * A user defined identifier for the schedule override.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the schedule override.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }


    /**
     * Id or the name of the schedule that the override belongs to.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the id or the name of the schedule that the override belongs to.
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    @Override
    public boolean isValid() throws ValidationException {
    	if(this.alias == null)
			throw new ValidationException("ValidationException[[Alias] field should be provided.]");
    	else if(this.schedule == null)
			throw new ValidationException("ValidationException[[Schedule] field should be provided.]");
    	return super.isValid();
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.SCHEDULE, getSchedule());
        json.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        return json;
    }
    
    


    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetScheduleOverrideResponse createResponse() {
        return new GetScheduleOverrideResponse();
    }
}
