package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#getEscalation(GetEscalationRequest) 
 */
public class GetEscalationRequest extends BaseRequest {
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
}
