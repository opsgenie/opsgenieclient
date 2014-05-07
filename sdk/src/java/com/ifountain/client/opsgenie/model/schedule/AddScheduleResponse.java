package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add schedule request.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
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
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(ClientConstants.API.ID);
    }
}
