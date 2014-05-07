package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.opsgenie.model.BaseGetRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#getSchedule(com.ifountain.client.opsgenie.model.schedule.GetScheduleRequest)
 */
public class GetScheduleRequest extends BaseGetRequest<GetScheduleResponse> {
    private String name;
    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
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
    public void _serialize(Map<String,Object> json) {
        if(name != null){
            json.put(ClientConstants.API.NAME, name);
        }
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetScheduleResponse createResponse() {
        return new GetScheduleResponse();
    }
}
