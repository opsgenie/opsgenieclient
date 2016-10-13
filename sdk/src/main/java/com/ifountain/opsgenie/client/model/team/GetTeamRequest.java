package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;


/**
 * Container for the parameters to make a get team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeam(GetTeamRequest)
 */
public class GetTeamRequest extends BaseRequest<GetTeamResponse> {
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

    /**
     * Rest api uri of getting team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team";
    }

    /**
     * Name of team to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of team to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public GetTeamResponse createResponse() {
        return new GetTeamResponse();
    }

}
