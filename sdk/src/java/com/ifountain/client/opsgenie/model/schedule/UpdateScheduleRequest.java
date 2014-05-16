package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an update schedule api call.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 11:39
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#updateSchedule(UpdateScheduleRequest)
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(id != null){
            json.put(ClientConstants.API.ID, id);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public UpdateScheduleResponse createResponse() {
        return new UpdateScheduleResponse();
    }
}
