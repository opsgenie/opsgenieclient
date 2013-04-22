package com.ifountain.opsgenie.client.model.group;

/**
 * Container for the parameters to make an remove group memberapi call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroupMember(DeleteGroupMemberRequest)
 */
public class DeleteGroupMemberRequest extends AddGroupMemberRequest{
    /**
     * Rest api uri of removing group member operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group/member";
    }


    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteGroupMemberResponse createResponse() {
        return new DeleteGroupMemberResponse();
    }
}
