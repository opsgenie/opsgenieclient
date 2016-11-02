package com.ifountain.opsgenie.client.model.beans;

/**
 * BaseWhoIsOnCall Bean
 */
public abstract class BaseWhoIsOnCall extends Bean {
    private String name;
    private String id;
    private String type;

    /**
     * Name of schedule
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Id of schedule
     */
    public String getId() {
        return id;
    }

    /**
     * Type of WhoIsOnCall
     */
    public String getType() {
        return type;
    }


    public BaseWhoIsOnCall withName(String name) {
        this.name = name;
        return this;
    }

    public BaseWhoIsOnCall withId(String id) {
        this.id = id;
        return this;
    }

    public BaseWhoIsOnCall withType(String type) {
        this.type = type;
        return this;
    }

}
