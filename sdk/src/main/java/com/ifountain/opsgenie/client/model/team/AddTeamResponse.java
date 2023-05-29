package com.ifountain.opsgenie.client.model.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponseWithId;
import com.ifountain.opsgenie.client.model.beans.DataWithName;

/**
 * Represents OpsGenie service response for add team request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeam(AddTeamRequest)
 */
public class AddTeamResponse extends BaseResponseWithId {
    private String result;
    @JsonProperty("data")
    private DataWithName teamData;
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataWithName getTeamData() {
        return teamData;
    }

    public void setTeamData(DataWithName teamData) {
        this.teamData = teamData;
    }
}
