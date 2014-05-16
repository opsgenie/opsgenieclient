package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Escalation Rule Bean
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 */
public class EscalationRule  implements IBean {
    public static enum Type{
        user,
        group,
        schedule
    }
    private String notify;
    private Type type;
    private int delay;

    /**
     * Name of escalation rule member
     */
    public String getNotify() {
        return notify;
    }

    /**
     * Sets the name of escalation rule member
     */
    public void setNotify(String notify) {
        this.notify = notify;
    }

    /**
     * Type of escalation rule member
     * One of user, group, schedule.
     * @see Type
     */
    public Type getType() {
        return type;
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
    public Map<String,Object> toMap() {
        Map<String, Object> ruleMap = new HashMap<String, Object>();
        ruleMap.put(ClientConstants.API.NOTIFY, notify);
        if(type != null){
            ruleMap.put(ClientConstants.API.TYPE, type.name());
        }
        ruleMap.put(ClientConstants.API.DELAY, delay);
        return ruleMap;
    }

    @Override
    public void fromMap(Map<String,Object> map) {
        notify = (String) map.get(ClientConstants.API.NOTIFY);
        if(map.containsKey(ClientConstants.API.TYPE)){
            type = Type.valueOf(((String) map.get(ClientConstants.API.TYPE)).toLowerCase());
        }
        delay = ((Number) map.get(ClientConstants.API.DELAY)).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EscalationRule that = (EscalationRule) o;

        if (delay != that.delay) return false;
        if (notify != null ? !notify.equals(that.notify) : that.notify != null) return false;
        if (type != that.type) return false;

        return true;
    }
}
