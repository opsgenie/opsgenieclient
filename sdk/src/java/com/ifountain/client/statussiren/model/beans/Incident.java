package com.ifountain.client.statussiren.model.beans;

import com.ifountain.client.ClientConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Incident Bean. GetIncident response contains Incident bean
 *
 * @author Tuba Ozturk
 * @version 2.5.2014 11:28
 * @see com.ifountain.client.statussiren.model.beans.IncidentUpdate
 * @see com.ifountain.client.statussiren.model.beans.BaseIncident
 */
public class Incident extends BaseIncident {
    private List<IncidentUpdate> updates;

    /**
     * List of updates of the Incident
     */
    public List<IncidentUpdate> getUpdates() {
        return updates;
    }
    /**
     * Sets the list of updates of the Incident
     */
    public void setUpdates(List<IncidentUpdate> updates) {
        this.updates = updates;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> json = super.toMap();
        List<Map<String,Object>> updateList = new ArrayList<Map<String, Object>>();
        for(IncidentUpdate incidentUpdate: updates){
            Map<String, Object> updateMap = incidentUpdate.toMap();
            updateList.add(updateMap);
        }
        json.put(ClientConstants.API.UPDATES,updateList);
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        super.fromMap(map);
        List<Map<String, Object>> updatesMap = (List<Map<String, Object>>) map.get(ClientConstants.API.UPDATES);
        updates = new ArrayList<IncidentUpdate>();
        for(Map<String,Object> updateMap :updatesMap){
            IncidentUpdate incidentUpdate = new IncidentUpdate();
            incidentUpdate.fromMap(updateMap);
            updates.add(incidentUpdate);
        }
    }
}
