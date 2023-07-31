package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
public enum DAY {
    monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    @JsonCreator
    public static DAY fromName(String name) {
        for (DAY day : DAY.values()) {
            if (day.name().equalsIgnoreCase(name))
                return day;
        }
        return null;
    }

    @JsonValue
    public String value() {
        return this.name();
    }
}