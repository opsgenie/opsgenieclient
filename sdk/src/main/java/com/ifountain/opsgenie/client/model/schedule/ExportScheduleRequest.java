package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseGetRequest;

import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make a schedule export api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#exportSchedule(com.ifountain.opsgenie.client.model.schedule.ExportScheduleRequest)
 */
public class ExportScheduleRequest extends BaseGetRequest<ExportScheduleResponse> {
    private String name;
    private TimeZone timeZone;
    private Locale locale;
    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule/export";
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
     * Locale for request
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets locale for request.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public void _serialize(Map json){
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        if(timeZone != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, timeZone.getID());
        }
        if(locale != null){
            json.put(OpsGenieClientConstants.API.LOCALE, locale.toString());
        }
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ExportScheduleResponse createResponse() {
        return new ExportScheduleResponse();
    }
}
