package com.ifountain.opsgenie.client.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.TimeZone;

public interface ObjectWithTimeZone {
    @JsonIgnore
    public TimeZone getObjectTimeZone();
}
