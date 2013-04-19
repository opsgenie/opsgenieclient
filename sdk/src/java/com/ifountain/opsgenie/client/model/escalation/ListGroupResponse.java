package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.beans.Group;

import java.util.List;

/**
 * Represents OpsGenie service response for list group request.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(com.ifountain.opsgenie.client.model.escalation.ListGroupRequest)
 */
public class ListGroupResponse {
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
}
