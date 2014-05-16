package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.model.BaseRequest;

/**
 * Container for the parameters to make a List who is on call api call to get on call participants of all schedules.
 *
 * @author Mustafa Sener
 * @version 22.04.2013 15:10
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
