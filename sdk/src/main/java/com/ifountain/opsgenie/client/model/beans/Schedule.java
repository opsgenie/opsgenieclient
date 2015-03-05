package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.*;

/**
 * Schedule bean
 */
public class Schedule  implements IBean{
    private String id;
    private String name;
    private String team;
    private TimeZone timeZone;
    private Boolean enabled;
    private List<ScheduleLayer> layers;

    /**
     * Id of schedule
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * Timezone of schedule
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone of schedule
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Returns enable/disable state of schedule
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable/disable state of schedule
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Layers of schedule
     * @see ScheduleLayer
     */
    public List<ScheduleLayer> getLayers() {
        return layers;
    }

    /**
     * Sets layers of schedule
     * @see ScheduleLayer
     */
    public void setLayers(List<ScheduleLayer> layers) {
        this.layers = layers;
    }

    /**
     * Name of the team that schedule belongs to, if any
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the name of the team that schedule belongs to.
     */
    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.ID, id);
        json.put(OpsGenieClientConstants.API.NAME, name);
        json.put(OpsGenieClientConstants.API.TEAM, team);
        if(timeZone != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, timeZone.getID());
        }
        json.put(OpsGenieClientConstants.API.ENABLED, enabled);
        if(layers != null){
            List<Map> layerMaps = new ArrayList<Map>();
            for(ScheduleLayer layer: layers) {
                layerMaps.add(layer.toMap());
            }
            json.put(OpsGenieClientConstants.API.LAYERS, layerMaps);
        }
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        id = (String) map.get(OpsGenieClientConstants.API.ID);
        name = (String) map.get(OpsGenieClientConstants.API.NAME);
        team = (String) map.get(OpsGenieClientConstants.API.TEAM);
        if(map.containsKey(OpsGenieClientConstants.API.ENABLED)){
            enabled = (Boolean) map.get(OpsGenieClientConstants.API.ENABLED);
        }
        if(map.containsKey(OpsGenieClientConstants.API.TIMEZONE)){
            Object timezoneObj = map.get(OpsGenieClientConstants.API.TIMEZONE);
            if(timezoneObj instanceof TimeZone){
                timeZone = (TimeZone) timezoneObj;
            }
            else{
                timeZone = TimeZone.getTimeZone(String.valueOf(timezoneObj));
            }
        }
        List<Map> layerMaps = (List<Map>) map.get(OpsGenieClientConstants.API.RULES);
        if(layerMaps != null){
            layers = new ArrayList<ScheduleLayer>();
            for(Map layerMap:layerMaps) {
                ScheduleLayer layer = new ScheduleLayer();
                layer.setScheduleTimeZone(timeZone);
                layer.fromMap(layerMap);
                layers.add(layer);
            }
        }
    }
}
