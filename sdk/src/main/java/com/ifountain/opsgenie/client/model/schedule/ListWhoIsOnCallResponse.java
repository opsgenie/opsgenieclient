package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.WhoIsOnCall;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for get schedule request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallResponse extends BaseResponse{
    private List<WhoIsOnCall> whoIsOnCallList;

    /**
     * Details of schedule
     * @see com.ifountain.opsgenie.client.model.beans.WhoIsOnCall
     */
    public List<WhoIsOnCall> getWhoIsOnCallList() {
        return whoIsOnCallList;
    }

    /**
     * Sets details of schedule
     * @see com.ifountain.opsgenie.client.model.beans.WhoIsOnCall
     */
    public void setWhoIsOnCallList(List<WhoIsOnCall> whoIsOnCallList) {
        this.whoIsOnCallList = whoIsOnCallList;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> oncallMaps = (List<Map>) data.get(OpsGenieClientConstants.API.ON_CALLS);
        if(oncallMaps != null){
            whoIsOnCallList = new ArrayList<WhoIsOnCall>();
            for(Map oncallMap:oncallMaps) {
                WhoIsOnCall whoIsOnCall = new WhoIsOnCall();
                whoIsOnCall.fromMap(oncallMap);
                whoIsOnCallList.add(whoIsOnCall);
            }
        }
    }
}
