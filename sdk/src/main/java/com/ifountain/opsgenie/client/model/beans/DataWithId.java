package com.ifountain.opsgenie.client.model.beans;

public class DataWithId {
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataWithId(String id) {
        this.id = id;
    }

    public DataWithId() {
    }

    public DataWithId withId(String id){
        this.id = id;
        return this;
    }
}
