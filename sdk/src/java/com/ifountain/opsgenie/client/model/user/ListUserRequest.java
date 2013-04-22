package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

/**
 * Container for the parameters to make a list users api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listUsers(ListUserRequest)
 */
public class ListUserRequest extends BaseRequest<ListUserResponse> {
    /**
     * Rest api uri of listing user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListUserResponse createResponse() {
        return new ListUserResponse();
    }
}
