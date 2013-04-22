package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Escalation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list escalations request.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#listEscalations(ListEscalationsRequest)
 */
public class ListEscalationsResponse extends BaseResponse{
    private List<Escalation> escalations;

    /**
     * List of escalations
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public List<Escalation> getEscalations() {
        return escalations;
    }

    /**
     * Sets list of escalations
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public void setEscalations(List<Escalation> escalations) {
        this.escalations = escalations;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> escalationsData = (List<Map>) data.get(OpsGenieClientConstants.API.ESCALATIONS);
        if(escalationsData != null){
        escalations = new ArrayList<Escalation>();
            for(Map escalationData:escalationsData){
                Escalation escalation = new Escalation();
                escalation.fromMap(escalationData);
                escalations.add(escalation);
            }
        }
    }
}
