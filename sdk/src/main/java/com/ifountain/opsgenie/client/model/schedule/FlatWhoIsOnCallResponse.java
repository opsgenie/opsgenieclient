package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall;

/**
 * Represents OpsGenie service response for flat who is on call request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#flatWhoIsOnCall(FlatWhoIsOnCallRequest)
 */
public class FlatWhoIsOnCallResponse extends BaseResponse {
    @JsonUnwrapped
    private FlatWhoIsOnCall whoIsOnCall;

    /**
     * Details of schedule flat on call
     *
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public FlatWhoIsOnCall getWhoIsOnCall() {
        return whoIsOnCall;
    }

    /**
     * Sets details of schedule flat on call
     *
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public void setWhoIsOnCall(FlatWhoIsOnCall whoIsOnCall) {
        this.whoIsOnCall = whoIsOnCall;
    }

}
