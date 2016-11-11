package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make an delete forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteForwarding(DeleteForwardingRequest)
 */
public class DeleteForwardingRequest extends BaseRequest<DeleteForwardingResponse, DeleteForwardingRequest> {
    private String id;
    private String alias;

    /**
     * Rest api uri of delete forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Id of forwarding setting to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A user defined identifier for the forwarding. Provides ability to assign a known identifier
     * and later use this identifier to get forwarding details.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the forwarding.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteForwardingResponse createResponse() {
        return new DeleteForwardingResponse();
    }

    public DeleteForwardingRequest withId(String id) {
        this.id = id;
        return this;
    }

    public DeleteForwardingRequest withAlias(String alias) {
        this.alias = alias;
        return this;
    }
}
