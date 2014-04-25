package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.ClientValidationException;
import com.ifountain.client.OpsGenieClientConstants;
import com.ifountain.client.model.BaseRequest;

import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make a set heartbeat configuration api call.
 *
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#setHeartbeatConfig(com.ifountain.client.opsgenie.model.customer.SetHeartbeatConfigRequest)
 */
public class SetHeartbeatConfigRequest extends BaseRequest<SetHeartbeatConfigResponse> {
    private Boolean enabled;
    private String message;
    private List<String> recipients;


    /**
     * Enable state
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable state
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /*
        * message of heartbeat alert
         */
    public String getMessage() {
        return message;
    }

    /*
    * Sets message of heartbeat alert
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /*
    * Recipients  of heartbeat alert
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /*
    * Sets recipients  of heartbeat alert
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }


    @Override
    public Map serialize() throws ClientValidationException {
        Map data = super.serialize();
        if(message != null){
            data.put(OpsGenieClientConstants.API.MESSAGE, message);
        }
        if(enabled != null){
            data.put(OpsGenieClientConstants.API.ENABLED, enabled);
        }
        if(recipients != null){
            data.put(OpsGenieClientConstants.API.RECIPIENTS, recipients);
        }
        return data;
    }

    /**
     * Rest api uri of set heartbeat configuration operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat/config";
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public SetHeartbeatConfigResponse createResponse() {
        return new SetHeartbeatConfigResponse();
    }
}