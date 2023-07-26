package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.DataObjWithAlias;

/**
 * Represents OpsGenie service response for add forwarding request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingResponse extends BaseResponse {
    private DataObjWithAlias data;
    /**
     * Alias and id of the created forwarding
     */
    public DataObjWithAlias getData() {
        return data;
    }

    /**
     * Alias and id of the created forwarding
     */
    public void setData(DataObjWithAlias data) {
        this.data = data;
    }
}
