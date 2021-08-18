package com.ifountain.opsgenie.client.model.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * Container for the parameters to make an add group api call.
 * groups are deprecated
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#addGroupMember(AddGroupMemberRequest)
 */
@Deprecated
public class AddGroupMemberRequest extends BaseRequest<AddGroupMemberResponse, AddGroupMemberRequest> {
    private String id;
    private String name;
    private List<String> users;


    /**
     * Rest api uri of adding group member operation.
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
     * Users to be added in String mode
     */
    @JsonProperty("users")
    public String getUsersString() {
        if (getUsers() != null)
            return Strings.join(getUsers(), ",");
        return null;
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddGroupMemberResponse createResponse() {
        return new AddGroupMemberResponse();
    }
}
