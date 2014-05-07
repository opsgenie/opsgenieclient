package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * RenotifyRecipient bean
 */
public class RenotifyRecipient implements IBean {
    public enum Type{
        user,
        group
    }
    private String recipient;
    private Type type;

    /**
     * Name of recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets name of recipient
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Type of recipient
     * Could be one of user, group, escalation
     * @see Type
     */
    public Type getType() {
        return type;
    }


    @Override
    public Map<String,Object> toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ClientConstants.API.RECIPIENT, recipient);
        if(type != null){
            json.put(ClientConstants.API.TYPE, type.name());
        }
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        recipient = (String) map.get(ClientConstants.API.RECIPIENT);
        if(map.containsKey(ClientConstants.API.TYPE)){
            type = Type.valueOf(((String) map.get(ClientConstants.API.TYPE)).toLowerCase());
        }
    }
}
