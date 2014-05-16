package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make an add group api call.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 09:38
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#addGroup(com.ifountain.client.opsgenie.model.group.AddGroupRequest)
 */
public class AddGroupRequest extends BaseRequest<AddGroupResponse> {
    private String name;
    private List<String> users;

    /**
     * Rest api uri of addding group operation.
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

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(getName() != null)
        {
            json.put(ClientConstants.API.NAME, getName());
        }
        if(getUsers() != null){
            json.put(ClientConstants.API.USERS, getUsers());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public AddGroupResponse createResponse() {
        return new AddGroupResponse();
    }
}
