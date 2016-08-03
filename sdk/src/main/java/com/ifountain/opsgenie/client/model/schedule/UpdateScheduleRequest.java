package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an update schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateSchedule(UpdateScheduleRequest)
 */
public class UpdateScheduleRequest extends AddScheduleRequest {
    private String id;
    /**
     * Rest api uri of updating schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * Id of schedule to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public UpdateScheduleResponse createResponse() {
        return new UpdateScheduleResponse();
    }
    @Override
    public Map serialize() throws OpsGenieClientValidationException {
    	Map json = super.serialize();
    	if(getId() != null)
            json.put(OpsGenieClientConstants.API.ID, getId());
    	return json;
    }
}
