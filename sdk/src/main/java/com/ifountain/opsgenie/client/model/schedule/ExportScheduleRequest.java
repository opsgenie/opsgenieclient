package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.TimeZone;

/**
 * Container for the parameters to make a schedule export api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#exportSchedule(ExportScheduleRequest)
 */
public class ExportScheduleRequest extends BaseRequest<ExportScheduleResponse, ExportScheduleRequest> {
    private String name;
    private String id;
    @JsonProperty("timezone")
    private TimeZone timeZone;

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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ExportScheduleResponse createResponse() {
        return new ExportScheduleResponse();
    }


    /**
     * Timezone for request
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone for request
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public ExportScheduleRequest withName(String name) {
        this.name = name;
        return this;
    }

    public ExportScheduleRequest withId(String id) {
        this.id = id;
        return this;
    }

    public ExportScheduleRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }
}
