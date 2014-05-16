package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.statussiren.model.beans.Incident;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents the StatusSiren service response for a get incident request.
 * @author Tuba Ozturk
 * @version 30.4.2014 13:35
 * @see com.ifountain.client.statussiren.IStatusSirenClient#getIncident(GetIncidentRequest)
 */
public class GetIncidentResponse extends BaseResponse {
    private Incident incident;

    /**
     * Incident with specified id
     */
    public Incident getIncident() {
        return incident;
    }

    /**
     * Sets the incident that will return as response
     */
    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        incident = new Incident();
        incident.fromMap((Map<String, Object>) data.get(ClientConstants.API.INCIDENT));
    }
}
