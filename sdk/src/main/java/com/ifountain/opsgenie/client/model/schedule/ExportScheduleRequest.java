package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Container for the parameters to make a schedule export api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#exportSchedule(com.ifountain.opsgenie.client.model.schedule.ExportScheduleRequest)
 */
public class ExportScheduleRequest extends BaseRequest<ExportScheduleResponse> {
    private String name;
    private String id;
    private TimeZone timeZone;
    private Locale locale;

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
    @Deprecated
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone for request
     */
    @Deprecated
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Locale for request
     */
    @Deprecated
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets locale for request.
     */
    @Deprecated
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
