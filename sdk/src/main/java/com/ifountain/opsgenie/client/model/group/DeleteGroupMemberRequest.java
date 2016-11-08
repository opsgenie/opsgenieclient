package com.ifountain.opsgenie.client.model.group;

/**
 * Container for the parameters to remove group member api call.
 * groups are deprecated
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroupMember(DeleteGroupMemberRequest)
 */
@Deprecated
public class DeleteGroupMemberRequest extends AddGroupMemberRequest {
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteGroupMemberResponse createResponse() {
        return new DeleteGroupMemberResponse();
    }
}
