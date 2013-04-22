package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.util.Map;

/**
 * Container for the parameters to make a list schedules api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(com.ifountain.opsgenie.client.model.schedule.ListScheduleRequest)
 */
public class ListScheduleRequest extends BaseRequest<ListScheduleResponse> {
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
    public ListScheduleResponse createResponse() {
        return new ListScheduleResponse();
    }
}
