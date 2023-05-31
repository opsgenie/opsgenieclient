package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.DataWithName;

/**
 * Represents OpsGenie service response for add team request.
 *
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeam(AddTeamRequest)
 */
public class AddTeamMemberResponse extends BaseResponse{
    private String result;
	private DataWithName data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataWithName getData() {
        return data;
    }

    public void setData(DataWithName data) {
        this.data = data;
    }
}
