package com.ifountain.opsgenie.client.model.beans;

public class DataObjWithAlias extends Bean{
    private String alias;
    private String id;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
