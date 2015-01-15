package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Group;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get group request.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#getGroup(com.ifountain.opsgenie.client.model.group.GetGroupRequest)
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

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        group = new Group();
        group.fromMap(data);
    }
}
