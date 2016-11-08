package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a delete group api call.
 * groups are deprecated
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroup(com.ifountain.opsgenie.client.model.group.DeleteGroupRequest)
 */
@Deprecated
public class DeleteGroupRequest extends BaseRequest<DeleteGroupResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of deleting group operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
    }

    /**
     * Id of group to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of group to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of group to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of group to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteGroupResponse createResponse() {
        return new DeleteGroupResponse();
    }
}
