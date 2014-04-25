package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.model.BaseRequest;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.client.opsgenie.model.schedule.ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallRequest extends BaseRequest<ListWhoIsOnCallResponse> {
    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule/whoIsOnCall";
    }


    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListWhoIsOnCallResponse createResponse() {
        return new ListWhoIsOnCallResponse();
    }
}
