package com.ifountain.opsgenie.client.model.group;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Container for the parameters to make an update group api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#updateGroup(com.ifountain.opsgenie.client.model.group.UpdateGroupRequest)
 */
public class UpdateGroupRequest extends AddGroupRequest {
	@JsonSerialize(include=Inclusion.ALWAYS)
    private String id;
    /**
     * Rest api uri of updating group operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
    }

    /**
     * Id of group to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of group to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public UpdateGroupResponse createResponse() {
        return new UpdateGroupResponse();
    }
}
