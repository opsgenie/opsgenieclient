package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a resolve incident api call.
 * @author Tuba Ozturk
 * @version 30.4.2014 13:31
 */
public class ResolveIncidentRequest extends BaseIncidentRequestWithId<ResolveIncidentResponse> {
    private String message;

    /**
     * Resolve message for incident.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets resolve message for incident.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ResolveIncidentResponse createResponse() {
        return new ResolveIncidentResponse();
    }

    /**
     * Rest api uri of resolve alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/incident/resolve";
    }

    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    @Override
    public Map<String, Object> serialize() throws ClientValidationException {
        Map<String, Object> json = super.serialize();
        if(getMessage() != null){
            json.put(ClientConstants.API.MESSAGE,getMessage());
        }
        return json;
    }
}
