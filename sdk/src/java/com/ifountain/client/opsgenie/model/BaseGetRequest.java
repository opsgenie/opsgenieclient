package com.ifountain.client.opsgenie.model;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.model.BaseResponse;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 4/24/13
 * Time: 7:13 AM
 * To change this template use File | Settings | File Templates.
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
        int numberOfParentSerializeParams = data.size();
        if(getId() != null){
            data.put(ClientConstants.API.ID, getId());
        }
        _serialize(data);
        if(numberOfParentSerializeParams == data.size()){
            throw ClientValidationException.missingMandatoryProperty(ClientConstants.API.ID);
        }
        return data;
    }

    public abstract void _serialize(Map<String,Object> data);
}
