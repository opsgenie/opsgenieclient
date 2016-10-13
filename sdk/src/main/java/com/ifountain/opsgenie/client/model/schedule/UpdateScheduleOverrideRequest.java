package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make an update schedule override api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateScheduleOverride(UpdateScheduleOverrideRequest)
 */
public class UpdateScheduleOverrideRequest extends AddScheduleOverrideRequest{
    @Override
    public UpdateScheduleOverrideResponse createResponse() {
        return new UpdateScheduleOverrideResponse();
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when alias is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (getAlias() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ALIAS);
    }
}
