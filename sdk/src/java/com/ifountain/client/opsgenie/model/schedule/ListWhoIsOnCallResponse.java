package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.WhoIsOnCall;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for lit who is on call request.
 *
 *  * @author Mustafa Sener
 * @version 22.04.2013 15:10
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.client.opsgenie.model.schedule.ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallResponse extends BaseResponse{
    private List<WhoIsOnCall> whoIsOnCallList;

    /**
     * Returns on call participants of all schedules as list
     * @see com.ifountain.client.opsgenie.model.beans.WhoIsOnCall
     */
    public List<WhoIsOnCall> getWhoIsOnCallList() {
        return whoIsOnCallList;
    }

    /**
     * Sets the on call participants of all schedules.
     * @see com.ifountain.client.opsgenie.model.beans.WhoIsOnCall
     */
    public void setWhoIsOnCallList(List<WhoIsOnCall> whoIsOnCallList) {
        this.whoIsOnCallList = whoIsOnCallList;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        List<Map<String,Object>> oncallMaps = (List<Map<String,Object>>) data.get(ClientConstants.API.ON_CALLS);
        if(oncallMaps != null){
            whoIsOnCallList = new ArrayList<WhoIsOnCall>();
            for(Map <String,Object> oncallMap:oncallMaps) {
                WhoIsOnCall whoIsOnCall = new WhoIsOnCall();
                whoIsOnCall.fromMap(oncallMap);
                whoIsOnCallList.add(whoIsOnCall);
            }
        }
    }
}
