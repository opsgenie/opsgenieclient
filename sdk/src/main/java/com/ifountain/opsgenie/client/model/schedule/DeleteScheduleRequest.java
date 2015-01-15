package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteSchedule(com.ifountain.opsgenie.client.model.schedule.DeleteScheduleRequest)
 */
public class DeleteScheduleRequest extends BaseRequest<DeleteScheduleResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of deleting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * Id of schedule to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of schedule to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        if(id != null){
            json.put(OpsGenieClientConstants.API.ID, id);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteScheduleResponse createResponse() {
        return new DeleteScheduleResponse();
    }
}
