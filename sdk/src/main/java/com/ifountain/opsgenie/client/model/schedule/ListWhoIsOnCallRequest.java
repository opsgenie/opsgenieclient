package com.ifountain.opsgenie.client.model.schedule;

/**
 * Container for the parameters to make a list who is on call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallRequest extends AbstractListWhoIsOnCallRequest<ListWhoIsOnCallResponse> {

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListWhoIsOnCallResponse createResponse() {
        return new ListWhoIsOnCallResponse();
    }

    public boolean isFlat() {
        return false;
    }
}
