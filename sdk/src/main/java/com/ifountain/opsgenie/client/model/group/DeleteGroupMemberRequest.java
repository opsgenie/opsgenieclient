package com.ifountain.opsgenie.client.model.group;

/**
 * Container for the parameters to make an remove group memberapi call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroupMember(DeleteGroupMemberRequest)
 */
public class DeleteGroupMemberRequest extends AddGroupMemberRequest{
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteGroupMemberResponse createResponse() {
        return new DeleteGroupMemberResponse();
    }
}
