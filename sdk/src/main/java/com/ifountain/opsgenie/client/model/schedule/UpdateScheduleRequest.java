package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make an update schedule api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateSchedule(UpdateScheduleRequest)
 */
public class UpdateScheduleRequest extends AddScheduleRequest {
    private String id;

    /**
     * Rest api uri of updating schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
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
     * Id of schedule to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateScheduleResponse createResponse() {
        return new UpdateScheduleResponse();
    }
}
