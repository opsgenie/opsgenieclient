package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make a list schedules api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(ListSchedulesRequest)
 */
public class ListSchedulesRequest extends BaseRequest<ListSchedulesResponse, ListSchedulesRequest> {
    private String expand;
    public String getExpand() {
        return expand;
    }
    public void setExpand(String expand) {
        this.expand = expand;
    }

    /**
     * Rest api uri of listing schedules operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/schedules";
    }

    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(StringUtils.isNotEmpty(expand))
            params.put(OpsGenieClientConstants.API.EXPAND,expand);
        return params;
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(StringUtils.isNotEmpty(expand) && !expand.equalsIgnoreCase(OpsGenieClientConstants.API.ROTATION))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.EXPAND);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListSchedulesResponse createResponse() {
        return new ListSchedulesResponse();
    }
}
