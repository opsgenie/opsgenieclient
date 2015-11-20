package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseWhoIsOnCall Bean
 */
public abstract class BaseWhoIsOnCall implements IBean{
    private String name;
    private String id;
    private String type;

    /**
     * Name of schedule
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Id of schedule
     */
    public String getId() {
        return id;
    }

    /**
     * Type of WhoIsOnCall
     */
    public String getType() {
        return type;
    }

    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.NAME, name);
        if(id != null){
            json.put(OpsGenieClientConstants.API.ID, id);
        }
        if(type != null){
            json.put(OpsGenieClientConstants.API.TYPE, type);
        }
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        name = (String) map.get(OpsGenieClientConstants.API.NAME);
        id = (String) map.get(OpsGenieClientConstants.API.ID);
        type = (String) map.get(OpsGenieClientConstants.API.TYPE);
    }
}
