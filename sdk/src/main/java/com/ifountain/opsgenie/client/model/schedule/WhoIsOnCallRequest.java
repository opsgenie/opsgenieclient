package com.ifountain.opsgenie.client.model.schedule;

/**
 * Container for the parameters to make a who is on call api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#whoIsOnCall(WhoIsOnCallRequest)
 */
public class WhoIsOnCallRequest extends AbstractWhoIsOnCallRequest<WhoIsOnCallResponse> {
    public WhoIsOnCallResponse createResponse() {
        return new WhoIsOnCallResponse();
    }

    public boolean isFlat() {
        return false;
    }
}
