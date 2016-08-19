package com.ifountain.opsgenie.client.model.user.forward;


import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * Container for the parameters to make an update forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateForwarding(com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest)
 */
public class UpdateForwardingRequest extends AddForwardingRequest{
	@JsonSerialize(include=Inclusion.ALWAYS)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public UpdateForwardingResponse createResponse() {
        return new UpdateForwardingResponse();
    }
}
