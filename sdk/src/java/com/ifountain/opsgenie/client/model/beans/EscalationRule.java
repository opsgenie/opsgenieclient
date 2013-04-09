package com.ifountain.opsgenie.client.model.beans;

/**
 * Created with IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 4/9/13
 * Time: 4:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class EscalationRule {
    private String name;
    private String type;
    private int delay;

    /**
     * Name of escalation rule member
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of escalation rule member
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Type of escalation rule member
     * One of user, group
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of escalation rule member
     * One of user, group
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Delay of escalation rule
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Sets the delay of escalation rule
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
