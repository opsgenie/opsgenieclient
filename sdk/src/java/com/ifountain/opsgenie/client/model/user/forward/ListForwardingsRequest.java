package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make a list forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listForwardings(com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json = super.serialize();
        if(getUser() != null){
            json.put(OpsGenieClientConstants.API.USER, getUser());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListForwardingsResponse createResponse() {
        return new ListForwardingsResponse();
    }

}
