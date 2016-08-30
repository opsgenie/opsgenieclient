package com.ifountain.opsgenie.client.model.schedule;
/**
 * Container for the parameters to make a list who is on call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallRequest extends AbstractListWhoIsOnCallRequest<ListWhoIsOnCallResponse> {
    private final boolean flat=false;
    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListWhoIsOnCallResponse createResponse() {
        return new ListWhoIsOnCallResponse();
    }
    
	public boolean isFlat() {
		return flat;
	}
}
