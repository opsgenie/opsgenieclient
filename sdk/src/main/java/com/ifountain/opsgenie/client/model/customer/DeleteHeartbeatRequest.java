package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a delete heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#deleteHeartbeat(DeleteHeartbeatRequest)
 */
public class DeleteHeartbeatRequest extends BaseRequest<DeleteHeartbeatResponse, DeleteHeartbeatRequest> {
    private String name;

    /**
     * Rest api uri of deleting heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat";
    }

    /**
     * Name of heartbeat monitor to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of heartbeat monitor to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }

    public DeleteHeartbeatRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @deprecated Use getName
     */
    @Deprecated
    public String getSource() {
        return getName();
    }

    /**
     * @deprecated Use setName
     */
    @Deprecated
    public void setSource(String source) {
        setName(source);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteHeartbeatResponse createResponse() {
        return new DeleteHeartbeatResponse();
    }
}
