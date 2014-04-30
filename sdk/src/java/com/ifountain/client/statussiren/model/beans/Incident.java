package com.ifountain.client.statussiren.model.beans;

import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.Map;

/**
 * Incident bean
 * @author Tuba Ozturk
 * @version 30.4.2014 10:38
 */
public class Incident implements IBean {

    private String id;
    private String service;
    private String message;
    private String description;
    private long insertedAt;

    @Override
    public Map toMap() {
        return null;
    }

    @Override
    public void fromMap(Map map) throws ParseException {

    }
}
