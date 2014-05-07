package com.ifountain.client.opsgenie.model.group;

/**
 * Container for the parameters to make an remove group member api call.
 *
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#deleteGroupMember(DeleteGroupMemberRequest)
 */
public class DeleteGroupMemberRequest extends AddGroupMemberRequest{
    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public DeleteGroupMemberResponse createResponse() {
        return new DeleteGroupMemberResponse();
    }
}
