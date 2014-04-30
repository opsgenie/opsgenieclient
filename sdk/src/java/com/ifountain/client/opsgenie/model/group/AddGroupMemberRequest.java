package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.util.Strings;

import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make an add group api call.
 *
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#addGroupMember(AddGroupMemberRequest)
 */
public class AddGroupMemberRequest extends BaseRequest<AddGroupMemberResponse> {
    private String id;
    private String name;
    private List<String> users;


    /**
     * Rest api uri of addding group member operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group/member";
    }

    /**
     * Id of group which members will be added
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of group which members will be added
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of group which members will be added
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of group which members will be added
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Users to be added
     */
    public List<String> getUsers() {
        return users;
    }

    /**
     * Sets users to be added
     */
    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        Map json = super.serialize();
        if(getId() != null)
        {
            json.put(ClientConstants.API.ID, getId());
        }
        if(getName() != null)
        {
            json.put(ClientConstants.API.NAME, getName());
        }
        if(getUsers() != null){
            json.put(ClientConstants.API.USERS, Strings.join(getUsers(),","));
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public AddGroupMemberResponse createResponse() {
        return new AddGroupMemberResponse();
    }
}
