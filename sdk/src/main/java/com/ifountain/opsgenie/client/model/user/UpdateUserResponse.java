package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for update user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserResponse extends BaseResponse {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
