package com.ifountain.client.opsgenie.model;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.model.BaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Mustafa Sener
 * @version 25.04.2013 15:53
 */
public abstract class BaseGetRequest<T extends BaseResponse> extends BaseRequest<T> {
    private String id;

    /**
     * Id of object to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of object to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public final Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> data =  super.serialize();
        if(getId() != null){
            data.put(ClientConstants.API.ID, getId());
        }
        _serialize(data);
        List<String> mandatoryProperties = getMandatoryProperties();
        boolean mandatoryPropertyGiven = mandatoryPropertyCheck(data,mandatoryProperties);
        if(!mandatoryPropertyGiven){
            throw ClientValidationException.shouldSpecifyAtLeastOneOf(mandatoryProperties.toString());
        }
        return data;
    }

    public abstract void _serialize(Map<String,Object> data);

    public List<String> getMandatoryProperties(){
        List<String> mandatoryProperties = new ArrayList<String>();
        mandatoryProperties.add(ClientConstants.API.ID);
        return mandatoryProperties;
    }

    private boolean mandatoryPropertyCheck(Map<String,Object> data, List<String> mandatoryProperties){
        for(String propertyName: mandatoryProperties){
            if(data.containsKey(propertyName)){
                return true;
            }
        }
        return false;
    }


}
