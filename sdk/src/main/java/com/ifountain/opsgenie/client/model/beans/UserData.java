package com.ifountain.opsgenie.client.model.beans;

public class UserData {
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

    public UserData withName(String name){
        this.name=name;
        return this;
    }
    public UserData withId(String id){
        this.id=id;
        return this;
    }
}
