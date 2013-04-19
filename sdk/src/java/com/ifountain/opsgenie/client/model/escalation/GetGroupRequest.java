package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get group api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#getGroup(com.ifountain.opsgenie.client.model.escalation.GetGroupRequest)
 */
public class GetGroupRequest extends BaseRequest {
    private String id;
    private String name;
    /**
     * Rest api uri of getting group operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
    }

    /**
     * Id of group to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of group to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of group to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of group to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }
}
