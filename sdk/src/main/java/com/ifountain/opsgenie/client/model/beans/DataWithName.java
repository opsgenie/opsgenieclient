package com.ifountain.opsgenie.client.model.beans;

public class DataWithName extends BaseDataObj {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataWithName withName(String name){
        this.name=name;
        return this;
    }
}
