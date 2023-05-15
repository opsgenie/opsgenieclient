package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.DataWithIdAndName;

/**
 * Represents OpsGenie service response for add user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserResponse extends BaseResponse {

    private String result;

    @JsonProperty("data")
    private DataWithIdAndName dataWithIdAndName;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataWithIdAndName getUserData() {
        return dataWithIdAndName;
    }

    public void setUserData(DataWithIdAndName dataWithIdAndName) {
        this.dataWithIdAndName = dataWithIdAndName;
    }
}
