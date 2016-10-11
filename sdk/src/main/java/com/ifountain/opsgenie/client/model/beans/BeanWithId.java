package com.ifountain.opsgenie.client.model.beans;

/**
 * Base class for beans which have id property
 */
public abstract class BeanWithId extends Bean {
    private String id;

    /**
     * Id of user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of user
     */
    public void setId(String id) {
        this.id = id;
    }
}
