package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.DataObjWithName;

/**
 * Represents OpsGenie service response for add user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserResponse extends BaseResponse {

    private String result;
    @JsonProperty("data")
    private DataObjWithName user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataObjWithName getUserData() {
        return user;
    }

    public void setUserData(DataObjWithName dataObjWithName) {
        this.user = dataObjWithName;
    }
}
