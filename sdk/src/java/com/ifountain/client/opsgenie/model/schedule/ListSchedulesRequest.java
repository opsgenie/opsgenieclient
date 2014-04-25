package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.model.BaseRequest;

/**
 * Container for the parameters to make a list schedules api call.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#listSchedules(ListSchedulesRequest)
 */
public class ListSchedulesRequest extends BaseRequest<ListSchedulesResponse> {
    /**
     * Rest api uri of listing schedules operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListSchedulesResponse createResponse() {
        return new ListSchedulesResponse();
    }
}
