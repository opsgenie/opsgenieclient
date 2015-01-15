package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.WhoIsOnCall;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get schedule request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#whoIsOnCall(WhoIsOnCallRequest)
 */
public class WhoIsOnCallResponse extends BaseResponse{
    private WhoIsOnCall whoIsOnCall;

    /**
     * Details of schedule oncall
     * @see com.ifountain.opsgenie.client.model.beans.WhoIsOnCall
     */
    public WhoIsOnCall getWhoIsOnCall() {
        return whoIsOnCall;
    }

    /**
     * Sets details of schedule oncall
     * @see com.ifountain.opsgenie.client.model.beans.WhoIsOnCall
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
