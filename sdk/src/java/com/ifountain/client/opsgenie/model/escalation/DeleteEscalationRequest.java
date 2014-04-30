package com.ifountain.client.opsgenie.model.escalation;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete escalation api call.
 *
 * @see com.ifountain.client.opsgenie.IEscalationOpsGenieClient#deleteEscalation(DeleteEscalationRequest)
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        Map json = super.serialize();
        if(getId() != null){
            json.put(ClientConstants.API.ID, getId());
        }
        if(getName() != null){
            json.put(ClientConstants.API.NAME, getName());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public DeleteEscalationResponse createResponse() {
        return new DeleteEscalationResponse();
    }
}
