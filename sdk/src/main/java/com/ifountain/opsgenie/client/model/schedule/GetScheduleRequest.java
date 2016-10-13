package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(GetScheduleRequest)
 */
public class GetScheduleRequest extends BaseRequest<GetScheduleResponse> {
    private String name;
    private String id;


    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when api key is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (name == null && id == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
    }

    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }


    /**
     * Name of schedule to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Id of object to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of object to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public GetScheduleResponse createResponse() {
        return new GetScheduleResponse();
    }

}
