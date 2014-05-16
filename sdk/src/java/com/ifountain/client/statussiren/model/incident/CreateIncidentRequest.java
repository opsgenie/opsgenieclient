package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.statussiren.model.Status;

import java.util.Map;

/**
 * Container for the parameters to make a create incident api call.
 * @author Tuba Ozturk
 * @version 30.4.2014 10:37
 * @see com.ifountain.client.statussiren.IStatusSirenClient#createIncident(CreateIncidentRequest)
 */
public class CreateIncidentRequest extends BaseIncidentRequestWithService<CreateIncidentResponse> {
    private String message;
    private String description;
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

    /*
    * Detailed incident information.
    */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the detailed incident information.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    @Override
    public CreateIncidentResponse createResponse() {
        return new CreateIncidentResponse();
    }

    /**
     * Rest api uri of incident create operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/incident";
    }


    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    @Override
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        json.put(ClientConstants.API.MESSAGE,getMessage());
        if(getDescription() != null){
            json.put(ClientConstants.API.DESCRIPTION,getDescription());
        }
        if(getStatus() != null){
            json.put(ClientConstants.API.STATUS,getStatus().getValue());
        }
        return json;
    }
}
