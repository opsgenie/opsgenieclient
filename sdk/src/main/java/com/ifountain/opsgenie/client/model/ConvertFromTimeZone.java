package com.ifountain.opsgenie.client.model;

import java.text.ParseException;

public interface ConvertFromTimeZone extends ObjectWithTimeZone {
    void setTime() throws ParseException;
}
