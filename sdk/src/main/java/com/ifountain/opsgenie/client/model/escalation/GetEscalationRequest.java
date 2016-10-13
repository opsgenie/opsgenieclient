package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;


/**
 * Container for the parameters to make a get escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#getEscalation(GetEscalationRequest)
 */
public class GetEscalationRequest extends BaseRequest<GetEscalationResponse> {
    private String name;
    private String id;

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when name and id are both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (name == null && id == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public GetEscalationResponse createResponse() {
        return new GetEscalationResponse();
    }
}
