package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add schedule request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
 */
public class AddScheduleResponse extends BaseResponse{
    private String id;
    /**
     * Id of the added schedule
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added schedule
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(OpsGenieClientConstants.API.ID);
    }
}
