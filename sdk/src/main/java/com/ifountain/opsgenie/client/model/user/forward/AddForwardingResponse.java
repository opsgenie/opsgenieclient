package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.DataWithAliasAndId;

/**
 * Represents OpsGenie service response for add forwarding request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingResponse extends BaseResponse {
    private DataWithAliasAndId data;
    /**
     * Alias and id of the created forwarding
     */
    public DataWithAliasAndId getData() {
        return data;
    }

    /**
     * Alias and id of the created forwarding
     */
    public void setData(DataWithAliasAndId data) {
        this.data = data;
    }
}
