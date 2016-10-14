package com.ifountain.opsgenie.client.model.beans;

import java.util.List;

/**
 * Group bean
 */
public class Group extends BeanWithId {
    private String name;
    private List<String> users;

    /**
     * Name of group
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of group
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Users of group
     */
    public List<String> getUsers() {
        return users;
    }

    /**
     * Sets users of group
     */
    public void setUsers(List<String> users) {
        this.users = users;
    }

}
