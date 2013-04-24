package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.HashMap;
import java.util.Map;

public class EscalationRule  implements IBean{
    public static enum Type{
        user,
        group
    }
    private String name;
    private Type type;
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
     * @see Type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of escalation rule member
     * One of user, group
     * @see Type
     */
    public void setType(Type type) {
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

    @Override
    public Map toMap() {
        Map<String, Object> ruleMap = new HashMap<String, Object>();
        ruleMap.put(OpsGenieClientConstants.API.NAME, name);
        ruleMap.put(OpsGenieClientConstants.API.TYPE, type);
        ruleMap.put(OpsGenieClientConstants.API.DELAY, delay);
        return ruleMap;
    }

    @Override
    public void fromMap(Map map) {
        name = (String) map.get(OpsGenieClientConstants.API.NAME);
        type = Type.valueOf(((String) map.get(OpsGenieClientConstants.API.TYPE)).toLowerCase());
        delay = ((Number) map.get(OpsGenieClientConstants.API.DELAY)).intValue();
    }
}
