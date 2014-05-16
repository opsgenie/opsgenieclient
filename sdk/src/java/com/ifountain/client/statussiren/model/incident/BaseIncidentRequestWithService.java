package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.model.BaseResponse;

import java.util.Map;

/**
 * Container for the service parameter. Requests contain service parameter extends from this request.
 * @author Tuba Ozturk
 * @version 2.5.2014 17:15
 */
public abstract class BaseIncidentRequestWithService<T extends BaseResponse> extends BaseRequest<T> {
    private String service;

    /**
     * Service that incident belongs to.
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the service that incident belongs to.
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String, Object> serialize() throws ClientValidationException {
        Map<String, Object> json = super.serialize();
        if(getService() != null){
            json.put(ClientConstants.API.SERVICE,getService());
        }
        return json;
    }
}
