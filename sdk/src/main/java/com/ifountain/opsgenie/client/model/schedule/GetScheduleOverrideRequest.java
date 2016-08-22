package com.ifountain.opsgenie.client.model.schedule;

import javax.xml.bind.ValidationException;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

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
     * check the parameters for validation.
     * It will be overridden by necessary Requests.
     * @throws ValidationException when alias is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
    	super.validate();
    	if(alias == null)
    		throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ALIAS);
    }

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
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetScheduleOverrideResponse createResponse() {
        return new GetScheduleOverrideResponse();
    }
}
