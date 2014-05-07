package com.ifountain.client.opsgenie.model.escalation;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.opsgenie.model.BaseGetRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get escalation api call.
 *
 * @see com.ifountain.client.opsgenie.IEscalationOpsGenieClient#getEscalation(GetEscalationRequest)
 */
public class GetEscalationRequest extends BaseGetRequest<GetEscalationResponse> {
    private String name;
    /**
     * Rest api uri of getting escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
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

    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    @Override
    public void _serialize(Map<String,Object> json) {
        if(getName() != null){
            json.put(ClientConstants.API.NAME, getName());
        }
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetEscalationResponse createResponse() {
        return new GetEscalationResponse();
    }
}
