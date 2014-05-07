package com.ifountain.client.statussiren.model.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tuba Ozturk
 * @version 2.5.2014 18:26
 */
public enum Status{
    OPERATIONAL("operational"),
    PARTIAL_OUTAGE("partial-outage"),
    DEGRADED("degraded"),
    OUTAGE("outage");

    private String value;

    public String getValue() {
        return value;
    }

    public static Map<String, Status> getMap() {
        return map;
    }

    Status(String value) {
        this.value = value;
    }

    private static final Map<String,Status> map;
    static {
        map = new HashMap<String,Status>();
        for (Status v : Status.values()) {
            map.put(v.value, v);
        }
    }
    public static Status findByKey(String i) {
        return map.get(i);
    }

}



