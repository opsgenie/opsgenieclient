package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list groups api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(ListGroupsRequest)
 */
public class ListGroupsRequest extends BaseRequest<ListGroupsResponse> {
    /**
     * Rest api uri of listing groups operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListGroupsResponse createResponse() {
        return new ListGroupsResponse();
    }
}
