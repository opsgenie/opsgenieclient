package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.List;

/**
 * Container for the parameters to make an add group api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#addGroup(com.ifountain.opsgenie.client.model.group.AddGroupRequest)
 */
public class AddGroupRequest extends BaseRequest<AddGroupResponse> {
    private String name;
    private List<String> users;


    /**
     * Rest api uri of adding group operation.
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddGroupResponse createResponse() {
        return new AddGroupResponse();
    }
}
