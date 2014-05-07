package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.statussiren.model.beans.Status;

import java.util.Map;

/**
 * Container for the parameters to make a update incident api call.
 * @author Tuba Ozturk
 * @version 30.4.2014 13:32
 */
public class UpdateIncidentRequest extends BaseIncidentRequestWithId<UpdateIncidentResponse> {
    private String message;
    private Status status;

    /**
     * Incident text.
     */
    public String getMessage() {
        return message;
    }

    /**
     *  Sets incident text.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Status of the incident
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the incident.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    @Override
    public Map<String, Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(getMessage() != null){
            json.put(ClientConstants.API.MESSAGE,getMessage());
        }
        if(getStatus() != null){
            json.put(ClientConstants.API.STATUS,getStatus().getValue());
        }
        return json;
    }

    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateIncidentResponse createResponse() {
        return new UpdateIncidentResponse();
    }

    /**
     * Rest api uri of update incident operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/incident/update";
    }
}
