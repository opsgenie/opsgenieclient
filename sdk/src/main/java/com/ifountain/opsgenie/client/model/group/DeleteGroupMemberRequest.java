package com.ifountain.opsgenie.client.model.group;

/**
 * Container for the parameters to remove group member api call.
 *
 * @author Mehmet Mustafa Demir
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
