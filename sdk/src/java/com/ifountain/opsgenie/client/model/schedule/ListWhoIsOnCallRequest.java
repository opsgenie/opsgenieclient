package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallRequest extends BaseRequest<ListWhoIsOnCallResponse> {
    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
    }


    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListWhoIsOnCallResponse createResponse() {
        return new ListWhoIsOnCallResponse();
    }
}
