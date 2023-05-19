package com.ifountain.opsgenie.client.model.beans;

public class BaseDataObj extends Bean{
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseDataObj(String id) {
        this.id = id;
    }

    public BaseDataObj() {
    }

    public BaseDataObj withId(String id){
        this.id = id;
        return this;
    }
}
