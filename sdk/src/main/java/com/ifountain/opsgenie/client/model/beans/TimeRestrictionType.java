package com.ifountain.opsgenie.client.model.beans;
import com.fasterxml.jackson.annotation.JsonCreator;
public enum TimeRestrictionType{
    TIME_OF_DAY("time-of-day"),
    WEEKDAY_AND_TIME_OF_DAY("weekday-and-time-of-day");

    TimeRestrictionType(String value){
        this.value = value;
    }
    private String value;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TimeRestrictionType getFromValues(String value) {
        for (TimeRestrictionType b : TimeRestrictionType.values()) {
            if (String.valueOf(b.value).equals(value)) {
                return b;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}