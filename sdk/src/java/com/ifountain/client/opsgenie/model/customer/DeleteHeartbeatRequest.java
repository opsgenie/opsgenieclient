package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete heartbeat monitor api call.
 *
 * @author  Mustafa Sener
 * @version 30.04.2013 22:36
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#deleteHeartbeat(DeleteHeartbeatRequest)
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(source != null){
            json.put(ClientConstants.API.SOURCE, source);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public DeleteHeartbeatResponse createResponse() {
        return new DeleteHeartbeatResponse();
    }
}
