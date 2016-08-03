package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get group api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#getGroup(GetGroupRequest)
 */
public class GetGroupRequest extends BaseRequest<GetGroupResponse> {
    private String name;
    private String id;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Rest api uri of getting group operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
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
    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetGroupResponse createResponse() {
        return new GetGroupResponse();
    }
}
