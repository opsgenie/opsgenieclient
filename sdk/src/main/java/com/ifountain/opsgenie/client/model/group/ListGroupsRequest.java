package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list groups api call.
 *
 * @author Mehmet Mustafa Demir
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListGroupsResponse createResponse() {
        return new ListGroupsResponse();
    }
}
