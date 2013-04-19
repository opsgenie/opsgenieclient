package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for add group request.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#addGroup(com.ifountain.opsgenie.client.model.group.AddGroupRequest)
 */
public class AddGroupResponse extends BaseResponse{
    private String id;
    /**
     * Id of the added group
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added group
     */
    public void setId(String id) {
        this.id = id;
    }
}
