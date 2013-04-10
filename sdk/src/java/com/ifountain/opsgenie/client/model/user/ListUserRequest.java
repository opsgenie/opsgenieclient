package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list users api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listUsers(ListUserRequest)
 */
public class ListUserRequest extends BaseRequest {
    /**
     * Rest api uri of listing user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }
}
