package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a delete alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/4/12 3:38 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#deleteAlert(DeleteAlertRequest)
 */
public class DeleteAlertRequest extends BaseAlertRequestWithSource<DeleteAlertResponse> {
    private String user;

    /**
     * Rest api uri of delete alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    /**
     * The user who is performing the delete alert operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the delete alert operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map parameters = super.serialize();
        if (getUser() != null)
            parameters.put(OpsGenieClientConstants.API.USER, getUser());
        return parameters;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteAlertResponse createResponse() {
        return new DeleteAlertResponse();
    }
}
