package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#deleteHeartbeat(DeleteHeartbeatRequest)
 */
public class DeleteHeartbeatRequest extends BaseRequest<DeleteHeartbeatResponse> {
    private String source;
    /**
     * Rest api uri of deleting heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat";
    }


    /**
     * Source of heartbeat monitor to be deleted.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets source of heartbeat monitor to be deleted.
     */
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(source != null){
            json.put(OpsGenieClientConstants.API.SOURCE, source);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteHeartbeatResponse createResponse() {
        return new DeleteHeartbeatResponse();
    }
}
