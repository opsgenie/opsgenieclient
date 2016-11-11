package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list schedules api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(ListSchedulesRequest)
 */
public class ListSchedulesRequest extends BaseRequest<ListSchedulesResponse, ListSchedulesRequest> {
    /**
     * Rest api uri of listing schedules operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListSchedulesResponse createResponse() {
        return new ListSchedulesResponse();
    }
}
