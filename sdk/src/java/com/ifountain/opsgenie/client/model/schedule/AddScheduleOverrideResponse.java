package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 *  Represents OpsGenie service response for add forwarding request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 8:54 AM
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addScheduleOverride(AddScheduleOverrideRequest)
 */
public class AddScheduleOverrideResponse extends BaseResponse {
    private String alias;

    /**
     * Alias of the created override
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Alias of the created override
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        alias = (String) data.get(OpsGenieClientConstants.API.ALIAS);
    }
}
