package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

/**
 * Container for the parameters to make a list groups api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(com.ifountain.opsgenie.client.model.group.ListGroupRequest)
 */
public class ListGroupRequest extends BaseRequest<ListGroupResponse> {
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
    public ListGroupResponse createResponse() {
        return new ListGroupResponse();
    }
}
