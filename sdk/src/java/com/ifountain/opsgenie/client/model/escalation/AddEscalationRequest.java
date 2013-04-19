package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.List;

/**
 * Container for the parameters to make an add group api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#addGroup(AddEscalationRequest)
 */
public class AddEscalationRequest extends BaseRequest {
    private String name;
    private List<String> users;


    /**
     * Rest api uri of addding user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
    }

    /**
     * Name of group
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of group
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Users of group
     */
    public List<String> getUsers() {
        return users;
    }

    /**
     * Sets users of group
     */
    public void setUsers(List<String> users) {
        this.users = users;
    }
}
