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
    private TimeZone timeZone;
    private Boolean enabled;
    private List<ScheduleRule> rules;

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
     * Rules of schedule
     * @see ScheduleRule
     */
    public List<ScheduleRule> getRules() {
        return rules;
    }

    /**
     * Sets rules of schedule
     * @see ScheduleRule
     */
    public void setRules(List<ScheduleRule> rules) {
        this.rules = rules;
    }

    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.ID, id);
        json.put(OpsGenieClientConstants.API.NAME, name);
        if(timeZone != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, timeZone.getID());
        }
        json.put(OpsGenieClientConstants.API.ENABLED, enabled);
        if(rules != null){
            List<Map> ruleMaps = new ArrayList<Map>();
            for(ScheduleRule rule:rules) {
                ruleMaps.add(rule.toMap());
            }
            json.put(OpsGenieClientConstants.API.RULES, ruleMaps);
        }
        return null;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        id = (String) map.get(OpsGenieClientConstants.API.ID);
        name = (String) map.get(OpsGenieClientConstants.API.NAME);
        if(map.containsKey(OpsGenieClientConstants.API.ENABLED)){
            enabled = (Boolean) map.get(OpsGenieClientConstants.API.ENABLED);
        }
        if(map.containsKey(OpsGenieClientConstants.API.TIMEZONE)){
            String timezoneId = (String) map.get(OpsGenieClientConstants.API.TIMEZONE);
            timeZone = TimeZone.getTimeZone(timezoneId);
        }
        List<Map> ruleMaps = (List<Map>) map.get(OpsGenieClientConstants.API.RULES);
        if(ruleMaps != null){
            rules = new ArrayList<ScheduleRule>();
            for(Map ruleMap:ruleMaps) {
                ScheduleRule rule = new ScheduleRule();
                rule.setScheduleTimeZone(timeZone);
                rule.fromMap(ruleMap);
                rules.add(rule);
            }
        }
    }
}
