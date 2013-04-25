package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseGetRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public void _serialize(Map json) {
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetScheduleResponse createResponse() {
        return new GetScheduleResponse();
    }
}
