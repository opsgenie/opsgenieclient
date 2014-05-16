package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.model.BaseRequest;

/**
 * Container for the parameters to make a list groups api call.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 09:38
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#listGroups(ListGroupsRequest)
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
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListGroupsResponse createResponse() {
        return new ListGroupsResponse();
    }
}
