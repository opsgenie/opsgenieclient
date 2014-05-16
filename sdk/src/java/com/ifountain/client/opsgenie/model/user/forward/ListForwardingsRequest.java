package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a list forwardings api call.
 * It returns all forwarding settings belong to a user or a customer.
 *
 * @author Mustafa Sener
 * @version 22.03.2013 08:46
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#listForwardings(com.ifountain.client.opsgenie.model.user.forward.ListForwardingsRequest)
 */
public class ListForwardingsRequest extends BaseRequest<ListForwardingsResponse> {
    private String user;

    /**
     * Rest api uri of list forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Username of user who forwarding is created for.
     * If not specified all forwardings belongs to customer will be returned
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets username of user who forwarding is created for
     */
    public void setUser(String user) {
        this.user = user;
    }


    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String, Object> serialize() throws ClientValidationException {
        Map<String, Object> json = super.serialize();
        if(getUser() != null){
            json.put(ClientConstants.API.USER, getUser());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListForwardingsResponse createResponse() {
        return new ListForwardingsResponse();
    }

}
