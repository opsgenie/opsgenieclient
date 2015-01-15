package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Group;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list group request.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(ListGroupsRequest)
 */
public class ListGroupsResponse extends BaseResponse {
    private List<Group> groups;

    /**
     * List of groups
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Sets list of groups
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> groupsData = (List<Map>) data.get(OpsGenieClientConstants.API.GROUPS);
        groups = new ArrayList<Group>();
        for(Map groupData:groupsData){
            Group group = new Group();
            group.fromMap(groupData);
            groups.add(group);
        }
    }
}
