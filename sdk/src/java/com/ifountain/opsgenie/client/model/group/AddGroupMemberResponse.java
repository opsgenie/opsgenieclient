package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add group request.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#addGroup(AddGroupRequest)
 */
public class AddGroupMemberResponse extends BaseResponse{
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
        id = (String) data.get(OpsGenieClientConstants.API.ID);
    }
}
