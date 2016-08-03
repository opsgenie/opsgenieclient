package com.ifountain.opsgenie.client.model.escalation;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make an update escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#updateEscalation(UpdateEscalationRequest)
 */
public class UpdateEscalationRequest extends AddEscalationRequest {
    private String id;
    /**
     * Rest api uri of updating escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    /**
     * Id of escalation to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public UpdateEscalationResponse createResponse() {
        return new UpdateEscalationResponse();
    }
    @Override
    public Map serialize() throws OpsGenieClientValidationException {
    	Map json = super.serialize();
    	if(getId() != null)
            json.put(OpsGenieClientConstants.API.ID, getId());
    	return json;
    }
}
