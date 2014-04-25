package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.WhoIsOnCall;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get schedule request.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#whoIsOnCall(WhoIsOnCallRequest)
 */
public class WhoIsOnCallResponse extends BaseResponse{
    private WhoIsOnCall whoIsOnCall;

    /**
     * Details of schedule oncall
     * @see com.ifountain.client.opsgenie.model.beans.WhoIsOnCall
     */
    public WhoIsOnCall getWhoIsOnCall() {
        return whoIsOnCall;
    }

    /**
     * Sets details of schedule oncall
     * @see com.ifountain.client.opsgenie.model.beans.WhoIsOnCall
     */
    public void setWhoIsOnCall(WhoIsOnCall whoIsOnCall) {
        this.whoIsOnCall = whoIsOnCall;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        whoIsOnCall = new WhoIsOnCall();
        whoIsOnCall.fromMap(data);
    }
}
