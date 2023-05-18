package com.ifountain.opsgenie.client.model.beans;

public class DataObjWithName extends BaseDataObj {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataObjWithName withName(String name){
        this.name=name;
        return this;
    }
}
