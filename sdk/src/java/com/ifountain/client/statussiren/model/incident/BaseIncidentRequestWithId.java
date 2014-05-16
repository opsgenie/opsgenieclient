package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseResponse;

import java.util.Map;

/**
 * Container for the incidentId parameter. Requests contain incidentId parameter extends from this request.
 * @author Tuba Ozturk
 * @version 2.5.2014 17:14
 */
public abstract class BaseIncidentRequestWithId<T extends BaseResponse> extends BaseIncidentRequestWithService<T> {
    private long incidentId;

    /**
     * Id of the Incident
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
    public Map<String, Object> serialize() throws ClientValidationException {
        Map<String, Object> json = super.serialize();
        json.put(ClientConstants.API.INCIDENT_ID,getIncidentId());
        return json;
    }
}
