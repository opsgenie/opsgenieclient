package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import javax.xml.bind.ValidationException;

/**
 * Container for the parameters to make a create heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#updateHeartbeat(UpdateHeartbeatRequest)
 */
public class UpdateHeartbeatRequest extends AddHeartbeatRequest {

    /**
     * check the parameters for validation.
     * It will be overridden by necessary Requests.
     *
     * @throws ValidationException when heartbeat name is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (getName() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.NAME);
    }


    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddHeartbeatResponse createResponse() {
        return new UpdateHeartbeatResponse();
    }
}
