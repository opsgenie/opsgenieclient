package com.ifountain.client.model;

import java.text.ParseException;
import java.util.Map;

public interface IBean {
    public Map<String,Object> toMap();
    public void fromMap(Map<String,Object> map) throws ParseException;
}
