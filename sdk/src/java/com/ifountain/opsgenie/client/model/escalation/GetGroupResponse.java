package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Group;

/**
 * Represents OpsGenie service response for get group request.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#getGroup(com.ifountain.opsgenie.client.model.escalation.GetGroupRequest)
 */
public class GetGroupResponse extends BaseResponse{
    private Group group;

    /**
     * Details of group
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets details of group
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public void setGroup(Group group) {
        this.group = group;
    }
}
