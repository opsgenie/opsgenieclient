package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 10:59 AM
 */
public class ScheduleOverride implements IBean{

    private String alias;
    private String user;
    private Date startDate;
    private Date endDate;
    private TimeZone timeZone;

    /**
     * User defined identifier for the override.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier for the override.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Username of user  whom the overrided is created for.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets username of user whom the override is created for.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Start date of schedule override.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of the schedule override.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of the schedule override.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of the schedule override.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Timezone to determine forwarding start and end dates
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine forwarding start and end dates
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public Map toMap() {
        Map<String, String> json = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (timeZone != null) {
            sdf.setTimeZone(timeZone);
            json.put(OpsGenieClientConstants.API.TIMEZONE, timeZone.getID());
        }
        if (endDate != null) {
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(endDate));
        }
        if (startDate != null) {
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(startDate));
        }
        json.put(OpsGenieClientConstants.API.USER, getUser());
        json.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if(map.containsKey(OpsGenieClientConstants.API.TIMEZONE)){

            Object timezoneObj = map.get(OpsGenieClientConstants.API.TIMEZONE);
            if(timezoneObj instanceof TimeZone){
                timeZone = (TimeZone) timezoneObj;
            }
            else{
                timeZone = TimeZone.getTimeZone(String.valueOf(timezoneObj));
            }
            sdf.setTimeZone(timeZone);
        }
        setAlias((String) map.get(OpsGenieClientConstants.API.ALIAS));
        setUser((String) map.get(OpsGenieClientConstants.API.USER));
        Object dateObj = map.get(OpsGenieClientConstants.API.START_DATE);
        if(dateObj != null){
            if(dateObj instanceof Date){
                setStartDate((Date) dateObj);
            }
            else{
                setStartDate(sdf.parse(String.valueOf(dateObj)));
            }
        }

        dateObj = map.get(OpsGenieClientConstants.API.END_DATE);
        if(dateObj != null){
            if(dateObj instanceof Date){
                setEndDate((Date) dateObj);
            }
            else{
                setEndDate(sdf.parse(String.valueOf(dateObj)));
            }
        }
    }
}
