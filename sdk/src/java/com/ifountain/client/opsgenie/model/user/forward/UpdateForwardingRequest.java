package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.ClientValidationException;
import com.ifountain.client.OpsGenieClientConstants;

import java.util.Map;

/**
 * Container for the parameters to make an update forwarding api call.
 *
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#updateForwarding(com.ifountain.client.opsgenie.model.user.forward.UpdateForwardingRequest)
 */
public class UpdateForwardingRequest extends AddForwardingRequest{
    private String id;
    /**
     * Rest api uri of update forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Id of forwarding setting to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.ID, getId());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public UpdateForwardingResponse createResponse() {
        return new UpdateForwardingResponse();
    }
}
