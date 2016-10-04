package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Group;

import java.util.List;

/**
 * Represents OpsGenie service response for list group request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(ListGroupsRequest)
 */
public class ListGroupsResponse extends BaseResponse {
    private List<Group> groups;

    /**
     * List of groups
     *
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Sets list of groups
     *
     * @see com.ifountain.opsgenie.client.model.beans.Group
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
