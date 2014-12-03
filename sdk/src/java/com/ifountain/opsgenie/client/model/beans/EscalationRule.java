package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.HashMap;
import java.util.Map;

public class EscalationRule implements IBean {
    public static enum Type {
        user,
        group,
        schedule
    }

    public static enum NotifyType {
        Default("default"), users("users"), members("members"), next("next"), previous("previous");
        private String value;

        NotifyType(String value) {
            this.value = value;
        }

        public static NotifyType fromValue(String value) {
            for (NotifyType notifyType : values()) {
                if (notifyType.value().equals(value)) return notifyType;
            }
            return NotifyType.Default;
        }

        public String value() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private String notify;
    private NotifyType notifyType = NotifyType.Default;
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
     * Gets the notification type of team or schedule in escalation.
     */
    public NotifyType getNotifyType() {
        return notifyType;
    }

    /**
     * Sets the notification type of team or schedule in escalation.
     * Possible values for schedule are: default, next, previous.
     * Possible values for team are: default, users, members.
     */
    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * Type of escalation rule member
     * One of user, group
     *
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
        if (type != null) {
            ruleMap.put(OpsGenieClientConstants.API.TYPE, type.name());
        }
        if (notifyType != null) {
            ruleMap.put(OpsGenieClientConstants.API.NOTIFY_TYPE, notifyType.toString());
        }
        ruleMap.put(OpsGenieClientConstants.API.DELAY, delay);
        return ruleMap;
    }

    @Override
    public void fromMap(Map map) {
        notify = (String) map.get(OpsGenieClientConstants.API.NOTIFY);
        if (map.containsKey(OpsGenieClientConstants.API.TYPE)) {
            type = Type.valueOf(((String) map.get(OpsGenieClientConstants.API.TYPE)).toLowerCase());
        }
        if (map.containsKey(OpsGenieClientConstants.API.NOTIFY_TYPE)) {
            notifyType = NotifyType.fromValue((String) map.get(OpsGenieClientConstants.API.NOTIFY_TYPE));
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
        if (notifyType != that.notifyType) return false;

        return true;
    }
}
