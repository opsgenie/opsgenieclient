package com.ifountain.opsgenie.client.model.beans;

import java.util.List;

/**
 * Escalation bean
 */
public class Escalation {
    private String id;
    private String name;
    private List<EscalationRule> rules;

    /**
     * Id of escalation
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * @see EscalationRule
     */
    public List<EscalationRule> getRules() {
        return rules;
    }

    /**
     * Sets the rules of escalation
     * @see EscalationRule
     */
    public void setRules(List<EscalationRule> rules) {
        this.rules = rules;
    }
}
