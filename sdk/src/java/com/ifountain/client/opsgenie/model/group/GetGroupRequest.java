package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.OpsGenieClientConstants;
import com.ifountain.client.opsgenie.model.BaseGetRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get group api call.
 *
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#getGroup(GetGroupRequest)
 */
public class GetGroupRequest extends BaseGetRequest<GetGroupResponse> {
    private String name;
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public void _serialize(Map json) {
        if(getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, getName());
        }
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetGroupResponse createResponse() {
        return new GetGroupResponse();
    }
}
