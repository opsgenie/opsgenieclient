package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Group;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list group request.
 *
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#listGroups(ListGroupsRequest)
 */
public class ListGroupsResponse extends BaseResponse {
    private List<Group> groups;

    /**
     * List of groups
     * @see com.ifountain.client.opsgenie.model.beans.Group
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Sets list of groups
     * @see com.ifountain.client.opsgenie.model.beans.Group
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        List<Map<String, Object>> groupsData = (List<Map<String, Object>>) data.get(ClientConstants.API.GROUPS);
        groups = new ArrayList<Group>();
        for(Map<String, Object> groupData:groupsData){
            Group group = new Group();
            group.fromMap(groupData);
            groups.add(group);
        }
    }
}
