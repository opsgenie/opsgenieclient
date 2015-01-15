package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add forwarding request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:41 AM
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingResponse extends BaseResponse {
    private String id;
    private String alias;

    /**
     * Id of the created forwarding
     */
    public String getId() {
        return id;
    }

    /**
     * Id of the created forwarding
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Alias of the created forwarding
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Alias of the created forwarding
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(OpsGenieClientConstants.API.ID);
        alias = (String) data.get(OpsGenieClientConstants.API.ALIAS);
    }
}
