package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * ScheduleRuleRestriction bean
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 */
public class ScheduleRuleRestriction  implements IBean {
    public enum DAY{
        monday,
        tuesday,
        wednesday,
        thursday,
        friday,
        saturday,
        sunday
    }
    private DAY startDay;
    private int startHour;
    private int startMin;
    private DAY endDay;
    private int endHour;
    private int endMin;

    /**
     * Start day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public DAY getStartDay() {
        return startDay;
    }

    /**
     * Sets start day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public void setStartDay(DAY startDay) {
        this.startDay = startDay;
    }

    /**
     * Start hour of restriction
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Sets start hour of restriction
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * Start min of restriction
     */
    public int getStartMin() {
        return startMin;
    }

    /**
     * Sets start min of restriction
     */
    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    /**
     * End hour of restriction
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * Sets end hour of restriction
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    /**
     * End min of restriction
     */
    public int getEndMin() {
        return endMin;
    }

    /**
     * Sets end min of restriction
     */
    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    /**
     * End day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public DAY getEndDay() {
        return endDay;
    }

    /**
     * Sets end day of restriction
     * One of monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    public void setEndDay(DAY endDay) {
        this.endDay = endDay;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ClientConstants.API.START_DAY, startDay.name());
        json.put(ClientConstants.API.END_DAY, endDay.name());
        json.put(ClientConstants.API.START_TIME, createTimeStr(startHour, startMin));
        json.put(ClientConstants.API.END_TIME, createTimeStr(endHour, endMin));
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        startDay = DAY.valueOf(((String) map.get(ClientConstants.API.START_DAY)).toLowerCase());
        endDay = DAY.valueOf(((String) map.get(ClientConstants.API.END_DAY)).toLowerCase());
        String startTime =  (String) map.get(ClientConstants.API.START_TIME);
        String endTime =  (String) map.get(ClientConstants.API.END_TIME);
        String[] startTimeParts = startTime.split(":", -1);
        String[] endTimeParts = endTime.split(":", -1);
        startHour = Integer.parseInt(startTimeParts[0]);
        startMin = Integer.parseInt(startTimeParts[1]);
        endHour = Integer.parseInt(endTimeParts[0]);
        endMin = Integer.parseInt(endTimeParts[1]);
    }

    private String createTimeStr(int hour, int min){
        StringBuffer time = new StringBuffer();
        if(hour < 10){
            time.append("0");
        }
        time.append(hour).append(":");
        if(min < 10){
            time.append("0");
        }
        time.append(min);
        return time.toString();
    }
}
