package com.ifountain.opsgenie.client.model.schedule;

/**
 * Container for the parameters to make a list flat who is on call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listFlatWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListFlatWhoIsOnCallRequest)
 */
public class ListFlatWhoIsOnCallRequest extends AbstractListWhoIsOnCallRequest<ListFlatWhoIsOnCallResponse> {

    private final boolean flat=true;

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListFlatWhoIsOnCallResponse createResponse() {
        return new ListFlatWhoIsOnCallResponse();
    }

	public boolean isFlat() {
		return flat;
	}
}
