package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add team request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 11:59 AM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeam(AddTeamRequest)
 */
public class AddTeamResponse extends BaseResponse {
    private String id;

    /**
     * Id of the added team.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added team.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(OpsGenieClientConstants.API.ID);
    }
}
