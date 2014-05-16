package com.ifountain.client.opsgenie.model.group;

/**
 * Container for the parameters to make a remove group member api call.
 *
 * @author Mustafa Sener
 * @version 22.04.2013 14:36
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
