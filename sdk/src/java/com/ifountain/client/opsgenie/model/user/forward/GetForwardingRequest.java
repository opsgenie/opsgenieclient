package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get forwarding api call.
 *
 * @author Mustafa Sener
 * @version 22.03.2013 08:46
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#getForwarding(GetForwardingRequest)
 */
public class GetForwardingRequest extends BaseRequest<GetForwardingResponse> {
    private String id;
    private String alias;

    /**
     * Rest api uri of get forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Id of forwarding setting to be returned.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting to be returned.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A user defined identifier for the forwarding.
     * Provides ability to assign a known id and later use this id to get forwarding details.
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

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        json.put(ClientConstants.API.ID, getId());
        json.put(ClientConstants.API.ALIAS, getAlias());
        return json;
    }


    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetForwardingResponse createResponse() {
        return new GetForwardingResponse();
    }
}
