package com.ifountain.opsgenie.client.model.beans;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public class EscalationRule extends Bean {
    public static enum Type {
        user, group, schedule, team
    }

    public static enum NotifyType {
        Default("default"), users("users"), admins("admins"), next("next"), previous("previous");
        private String value;

        NotifyType(String value) {
            this.value = value;
        }

        @JsonCreator
        public static NotifyType fromValue(String value) {
            if (value == null)
                return NotifyType.Default;
            for (NotifyType notifyType : values()) {
                if (notifyType.value().toLowerCase().equals(value.toLowerCase()))
                    return notifyType;
            }
            return NotifyType.Default;
        }

        @JsonValue
        public String value() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static enum NotifyCondition {
        IF_NOT_ACKED("if-not-acked"), IF_NOT_CLOSED("if-not-closed");
        private String value;

        NotifyCondition(String value) {
            this.value = value;
        }

        @JsonCreator
        public static NotifyCondition fromValue(String value) {
            if (value == null)
                return NotifyCondition.IF_NOT_ACKED;
            for (NotifyCondition notifyType : values()) {
                if (notifyType.value().toLowerCase().equals(value.toLowerCase()))
                    return notifyType;
            }
            return NotifyCondition.IF_NOT_ACKED;
        }

        @JsonValue
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
    private NotifyCondition notifyCondition = NotifyCondition.IF_NOT_ACKED;
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
     * Sets the notification type of team or schedule in escalation. Possible
     * values for schedule are: default, next, previous. Possible values for
     * team are: default, users, admins.
     */
    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * Type of escalation rule member One of user, group
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EscalationRule that = (EscalationRule) o;

        if (delay != that.delay)
            return false;
        if (notify != null ? !notify.equals(that.notify) : that.notify != null)
            return false;
        if (type != that.type)
            return false;
        if (notifyType != that.notifyType)
            return false;

        return true;
    }

    public NotifyCondition getNotifyCondition() {
        return notifyCondition;
    }

    public void setNotifyCondition(NotifyCondition notifyCondition) {
        this.notifyCondition = notifyCondition;
    }
}
