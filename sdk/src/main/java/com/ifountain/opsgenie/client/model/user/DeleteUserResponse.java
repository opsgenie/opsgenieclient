package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for delete user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteUser(DeleteUserRequest)
 */
public class DeleteUserResponse extends BaseResponse{
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DeleteUserResponse withResult(String result) {
        this.result = result;
        return this;
    }
}
