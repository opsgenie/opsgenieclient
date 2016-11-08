package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Group;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * Represents OpsGenie service response for get group request.
 * groups are deprecated
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#getGroup(com.ifountain.opsgenie.client.model.group.GetGroupRequest)
 */
@Deprecated
public class GetGroupResponse extends BaseResponse {
    @JsonUnwrapped
    private Group group;

    /**
     * Details of group
     *
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets details of group
     *
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

}
