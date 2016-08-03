package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a schedule export api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#exportSchedule(com.ifountain.opsgenie.client.model.schedule.ExportScheduleRequest)
 */
public class ExportScheduleRequest extends BaseRequest<ExportScheduleResponse> {
    private String name;
    private String id;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule/export";
    }



    /**
     * Name of schedule to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ExportScheduleResponse createResponse() {
        return new ExportScheduleResponse();
    }
}
