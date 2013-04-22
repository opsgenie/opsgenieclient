package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make an update forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateForwarding(com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json = super.serialize();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (getTimeZone() != null) {
            sdf.setTimeZone(getTimeZone());
            json.put(OpsGenieClientConstants.API.TIMEZONE, getTimeZone().getID());
        }
        if (getEndDate() != null) {
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(getEndDate()));
        }
        if (getStartDate() != null) {
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(getStartDate()));
        }
        json.put(OpsGenieClientConstants.API.FROM_USER, getFromUser());
        json.put(OpsGenieClientConstants.API.TO_USER, getToUser());
        json.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        json.put(OpsGenieClientConstants.API.ID, getId());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public UpdateForwardingResponse createResponse() {
        return new UpdateForwardingResponse();
    }
}
