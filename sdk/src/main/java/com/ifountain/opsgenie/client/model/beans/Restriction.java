package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Restriction bean
 */
public class Restriction extends Bean {
    private Integer endHour;
    @JsonProperty("endMin")
    private Integer endMinute;
    private Integer startHour;
    @JsonProperty("startMin")
    private Integer startMinute;
    private DAY startDay;
    private DAY endDay;

    public void validateRestriction(TimeRestrictionType restrictionType) throws OpsGenieClientValidationException {
        if(endHour == null || endMinute == null || startHour == null || startMinute == null){
            throw OpsGenieClientValidationException.error("startHour, startMinute, endHour, endMinute cannot be empty");
        }
        if(restrictionType.equals(TimeRestrictionType.WEEKDAY_AND_TIME_OF_DAY) && (endDay == null || startDay == null)){
            throw OpsGenieClientValidationException.error("startDay, endDay cannot be empty");
        }
        if(startHour >24)
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.RESTRICTION_START_HOUR);
        if(endHour >24)
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.RESTRICTION_END_HOUR);
        //Minutes may take 0 or 30 as value. Otherwise they will be converted to nearest 0 or 30 automatically. So startMin and endMin do not require validation check
    }

    /**
     * endHour of Restriction
     */
    public Integer getEndHour() {
        return endHour;
    }

    /**
     * Sets endHour of Restriction
     */
    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    /**
     * endMinute of Restriction
     */
    public Integer getEndMinute() {
        return endMinute;
    }

    /**
     * Sets endMinute of Restriction
     */
    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    /**
     * startHour of Restriction
     */
    public Integer getStartHour() {
        return startHour;
    }

    /**
     * Sets startHour of Restriction
     */
    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    /**
     * startMinute of Restriction
     */
    public Integer getStartMinute() {
        return startMinute;
    }

    /**
     * Sets startMinute of Restriction
     */
    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }

    /**
     * startDay of Restriction
     */
    public DAY getStartDay() {
        return startDay;
    }

    /**
     * Sets startDay of Restriction
     */
    public void setStartDay(DAY startDay) {
        this.startDay = startDay;
    }

    /**
     * endDay of Restriction
     */
    public DAY getEndDay() {
        return endDay;
    }

    /**
     * Sets endDay of Restriction
     */
    public void setEndDay(DAY endDay) {
        this.endDay = endDay;
    }


    public Restriction withEndHour(Integer endHour) {
        this.endHour = endHour;
        return this;
    }

    public Restriction withEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
        return this;
    }

    public Restriction withStartHour(Integer startHour) {
        this.startHour = startHour;
        return this;
    }

    public Restriction withStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
        return this;
    }

    public Restriction withStartDay(DAY startDay) {
        this.startDay = startDay;
        return this;
    }

    public Restriction withEndDay(DAY endDay) {
        this.endDay = endDay;
        return this;
    }

}
