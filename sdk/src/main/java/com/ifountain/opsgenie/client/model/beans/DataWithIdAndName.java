package com.ifountain.opsgenie.client.model.beans;

public class DataWithIdAndName {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataWithIdAndName withName(String name){
        this.name=name;
        return this;
    }
    public DataWithIdAndName withId(String id){
        this.id=id;
        return this;
    }
}