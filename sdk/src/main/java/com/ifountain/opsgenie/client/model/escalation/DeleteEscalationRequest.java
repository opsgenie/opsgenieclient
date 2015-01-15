package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#deleteEscalation(DeleteEscalationRequest)
 */
public class DeleteEscalationRequest extends BaseRequest<DeleteEscalationResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of deleting escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    /**
     * Id of escalation to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of escalation to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of escalation to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getId());
        }
        if(getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, getName());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteEscalationResponse createResponse() {
        return new DeleteEscalationResponse();
    }
}
