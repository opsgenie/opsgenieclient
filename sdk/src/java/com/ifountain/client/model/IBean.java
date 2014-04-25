package com.ifountain.client.model;

import java.text.ParseException;
import java.util.Map;

public interface IBean {
    public Map toMap();
    public void fromMap(Map map) throws ParseException;
}
