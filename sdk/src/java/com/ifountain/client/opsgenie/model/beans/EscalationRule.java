package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.model.IBean;
import com.ifountain.client.OpsGenieClientConstants;

import java.util.HashMap;
import java.util.Map;

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
     * One of user, group
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
    public Map toMap() {
        Map<String, Object> ruleMap = new HashMap<String, Object>();
        ruleMap.put(OpsGenieClientConstants.API.NOTIFY, notify);
        if(type != null){
            ruleMap.put(OpsGenieClientConstants.API.TYPE, type.name());
        }
        ruleMap.put(OpsGenieClientConstants.API.DELAY, delay);
        return ruleMap;
    }

    @Override
    public void fromMap(Map map) {
        notify = (String) map.get(OpsGenieClientConstants.API.NOTIFY);
        if(map.containsKey(OpsGenieClientConstants.API.TYPE)){
            type = Type.valueOf(((String) map.get(OpsGenieClientConstants.API.TYPE)).toLowerCase());
        }
        delay = ((Number) map.get(OpsGenieClientConstants.API.DELAY)).intValue();
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
