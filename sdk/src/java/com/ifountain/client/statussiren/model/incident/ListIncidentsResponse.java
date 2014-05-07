package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.statussiren.model.beans.ListIncident;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the StatusSiren service response for a list incidents request.
 * @author Tuba Ozturk
 * @version 30.4.2014 13:36
 * @see com.ifountain.client.statussiren.IStatusSirenClient#listIncidents(ListIncidentsRequest)
 */
public class ListIncidentsResponse extends BaseResponse {
    private List<ListIncident> incidents;

    public List<ListIncident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<ListIncident> incidents) {
        this.incidents = incidents;
    }

    /**
     * @see com.ifountain.client.model.BaseResponse#deserialize(java.util.Map)
     */
    @Override
    public void deserialize(Map<String,Object> data) throws ParseException {
        super.deserialize(data);
        List<Map<String,Object>> incidentMaps = (List<Map<String, Object>>) data.get(ClientConstants.API.INCIDENTS);
        if(incidentMaps != null){
            incidents = new ArrayList<ListIncident>();
            for(Map<String,Object> incidentMap : incidentMaps){
                ListIncident listIncident = new ListIncident();
                listIncident.fromMap(incidentMap);
                incidents.add(listIncident);
            }
        }
    }
}
