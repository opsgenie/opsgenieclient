package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Group;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get group request.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 09:38
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#getGroup(com.ifountain.client.opsgenie.model.group.GetGroupRequest)
 */
public class GetGroupResponse extends BaseResponse{
    private Group group;

    /**
     * Details of group
     * @see com.ifountain.client.opsgenie.model.beans.Group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets details of group
     * @see com.ifountain.client.opsgenie.model.beans.Group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        group = new Group();
        group.fromMap(data);
    }
}
