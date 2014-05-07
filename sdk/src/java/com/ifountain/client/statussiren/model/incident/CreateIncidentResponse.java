package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents StatusSiren service response for a create incident request.
 * @author Tuba Ozturk
 * @version 30.4.2014 11:45
 * @see com.ifountain.client.statussiren.IStatusSirenClient#createIncident(CreateIncidentRequest)
 */
public class CreateIncidentResponse extends BaseResponse {
    private long incidentId;

    /**
     * Id of the created Incident
     */
    public long getIncidentId() {
        return incidentId;
    }

    /**
     * Sets the id of the incident
     */
    public void setIncidentId(long incidentId) {
        this.incidentId = incidentId;
    }

    @Override
    /**
     * @see BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        if(data.containsKey(ClientConstants.API.INCIDENT_ID)){
            setIncidentId(((Number) data.get(ClientConstants.API.INCIDENT_ID)).longValue());
        }
    }
}
