package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a list forwarding api call.
 *
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
     * Username of user who forwarding is created for
     */
    public void setUser(String user) {
        this.user = user;
    }


    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        Map json = super.serialize();
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
