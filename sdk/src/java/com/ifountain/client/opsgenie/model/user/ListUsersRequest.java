package com.ifountain.client.opsgenie.model.user;

import com.ifountain.client.model.BaseRequest;

/**
 * Container for the parameters to make a list users api call.
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#listUsers(ListUsersRequest)
 */
public class ListUsersRequest extends BaseRequest<ListUsersResponse> {
    /**
     * Rest api uri of listing user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListUsersResponse createResponse() {
        return new ListUsersResponse();
    }
}
