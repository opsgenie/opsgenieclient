package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group bean
 */
public class Group  implements IBean {
    private String id;
    private String name;
    private List<String> users;

    /**
     * Id of group
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of group
     */
    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ClientConstants.API.NAME, name);
        json.put(ClientConstants.API.ID, id);
        if(users != null){
            json.put(ClientConstants.API.USERS, users);
        }
        return json;
    }

    @Override
    public void fromMap(Map resp) throws ParseException {
        setId((String) resp.get(ClientConstants.API.ID));
        setName((String) resp.get(ClientConstants.API.NAME));
        setUsers((List<String>) resp.get(ClientConstants.API.USERS));
    }
}
