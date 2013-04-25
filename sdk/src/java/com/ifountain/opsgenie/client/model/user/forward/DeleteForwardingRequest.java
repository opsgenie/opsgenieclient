package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make an delete forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteForwarding(DeleteForwardingRequest)
 */
public class DeleteForwardingRequest extends BaseRequest<DeleteForwardingResponse> {
    private String id;

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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.ID, getId());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteForwardingResponse createResponse() {
        return new DeleteForwardingResponse();
    }
}
