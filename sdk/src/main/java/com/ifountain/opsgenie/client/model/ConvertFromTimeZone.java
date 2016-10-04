package com.ifountain.opsgenie.client.model;

import java.text.ParseException;

public interface ConvertFromTimeZone extends ObjectWithTimeZone {
    public void setTime() throws ParseException;
}
