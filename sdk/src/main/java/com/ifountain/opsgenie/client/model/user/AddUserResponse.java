package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.DataWithName;

/**
 * Represents OpsGenie service response for add user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserResponse extends BaseResponse {

    private String result;
    @JsonProperty("data")
    private DataWithName user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataWithName getUserData() {
        return user;
    }

    public void setUserData(DataWithName dataWithName) {
        this.user = dataWithName;
    }
}
