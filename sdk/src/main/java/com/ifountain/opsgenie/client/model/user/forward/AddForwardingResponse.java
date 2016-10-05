package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseResponseWithId;

/**
 * Represents OpsGenie service response for add forwarding request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingResponse extends BaseResponseWithId {
    private String alias;
    /**
     * Alias of the created forwarding
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Alias of the created forwarding
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

}
