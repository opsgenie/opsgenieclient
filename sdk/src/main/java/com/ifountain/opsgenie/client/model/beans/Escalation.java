package com.ifountain.opsgenie.client.model.beans;

import java.util.List;

/**
 * Escalation bean
 */
public class Escalation extends BeanWithId {
    private String name;
    private String team;
    private String description;
    private List<EscalationRule> rules;
    private Integer repeatInterval;

    /**
     * Name of escalation
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of escalation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Rules of escalation
     *
     * @see EscalationRule
     */
    public List<EscalationRule> getRules() {
        return rules;
    }

    /**
     * Sets the rules of escalation
     *
     * @see EscalationRule
     */
    public void setRules(List<EscalationRule> rules) {
        this.rules = rules;
    }

    /**
     * Name of the team that escalation belongs to, if any
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the name of the team that escalation belongs to.
     */
    public void setTeam(String team) {
        this.team = team;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

}
