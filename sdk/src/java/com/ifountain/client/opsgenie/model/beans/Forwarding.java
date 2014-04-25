package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.model.IBean;
import com.ifountain.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Forwarding bean
 */
public class Forwarding  implements IBean {
    private String id;
    private String alias;
    private String fromUser;
    private String toUser;
    private Date startDate;
    private Date endDate;
    private TimeZone timeZone;

    /**
     * Id of forwarding setting
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A user defined identifier for the 
     * Provides ability to assign a known id and later use this id to get forwarding details.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the 
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Username of user  which forwarding is created for
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets username of user who forwarding is created for
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username of user who forwarding will be directed to
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Sets username of user who forwarding will be directed to
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * Start date of forwarding will be started
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of forwarding will be started
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of forwarding will be discarded
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of forwarding will be discarded
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
        json.put(OpsGenieClientConstants.API.ID, getId());
        json.put(OpsGenieClientConstants.API.FROM_USER, getFromUser());
        json.put(OpsGenieClientConstants.API.TO_USER, getToUser());
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
        setId((String) map.get(OpsGenieClientConstants.API.ID));
        setAlias((String) map.get(OpsGenieClientConstants.API.ALIAS));
        setFromUser((String) map.get(OpsGenieClientConstants.API.FROM_USER));
        setToUser((String) map.get(OpsGenieClientConstants.API.TO_USER));
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
