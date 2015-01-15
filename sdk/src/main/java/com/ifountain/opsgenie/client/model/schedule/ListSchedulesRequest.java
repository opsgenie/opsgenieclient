package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list schedules api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(ListSchedulesRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListSchedulesResponse createResponse() {
        return new ListSchedulesResponse();
    }
}
