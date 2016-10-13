package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
/**
 * Container for the parameters to make an update escalation api call.
 *
 * @author Sezgin Kucukkaraaslan
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
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateEscalationResponse createResponse() {
        return new UpdateEscalationResponse();
    }
}
