package com.ifountain.opsgenie.client.model.beans;

public class TeamRoutingRuleNotify extends Bean {
    private String name;
    private String id;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
