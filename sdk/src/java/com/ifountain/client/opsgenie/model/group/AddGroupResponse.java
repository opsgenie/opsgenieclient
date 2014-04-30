package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add group request.
 *
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#addGroup(com.ifountain.client.opsgenie.model.group.AddGroupRequest)
 */
public class AddGroupResponse extends BaseResponse{
    private String id;
    /**
     * Id of the added group
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added group
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(ClientConstants.API.ID);
    }
}
