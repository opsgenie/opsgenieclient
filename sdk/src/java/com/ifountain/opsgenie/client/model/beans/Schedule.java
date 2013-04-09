package com.ifountain.opsgenie.client.model.beans;

import java.util.List;
import java.util.TimeZone;

/**
 * Schedule bean
 */
public class Schedule {
    private String id;
    private String name;
    private TimeZone timeZone;
    private boolean enabled;
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
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable/disable state of schedule
     */
    public void setEnabled(boolean enabled) {
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
}
