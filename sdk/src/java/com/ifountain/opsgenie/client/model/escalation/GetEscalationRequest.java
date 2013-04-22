package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.alert.AcknowledgeResponse;

import java.util.Map;

/**
 * Container for the parameters to make a get escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#getEscalation(GetEscalationRequest) 
 */
public class GetEscalationRequest extends BaseRequest<GetEscalationResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of getting escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    /**
     * Id of escalation to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of escalation to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of escalation to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
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
    public GetEscalationResponse createResponse() {
        return new GetEscalationResponse();
    }
}
