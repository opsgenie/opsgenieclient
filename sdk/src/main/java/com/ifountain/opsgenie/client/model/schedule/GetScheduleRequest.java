package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest)
 */
public class GetScheduleRequest extends BaseRequest<GetScheduleResponse> {
    private String name;
    private String id;
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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetScheduleResponse createResponse() {
        return new GetScheduleResponse();
    }

}
