package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseResponseWithId;

/**
 * Represents OpsGenie service response for update forwarding request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateForwarding(com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest)
 */
public class UpdateForwardingResponse extends BaseResponseWithId {
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
