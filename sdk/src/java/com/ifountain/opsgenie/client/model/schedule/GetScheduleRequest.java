package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.util.Map;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest)
 */
public class GetScheduleRequest extends BaseRequest<GetScheduleResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * Id of schedule to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule to be queried.
     */
    public void setId(String id) {
        this.id = id;
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
    public Map serialize() {
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
    public GetScheduleResponse createResponse() {
        return new GetScheduleResponse();
    }
}
