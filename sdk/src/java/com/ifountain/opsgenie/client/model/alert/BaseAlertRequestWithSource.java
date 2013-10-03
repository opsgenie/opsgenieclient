package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.util.Map;

public abstract class BaseAlertRequestWithSource<T extends BaseResponse> extends BaseAlertRequestWithId<T>{
    private String source;

    /**
     * The source of action.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of action.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map parameters = super.serialize();
        if (getSource() != null)
            parameters.put(OpsGenieClientConstants.API.SOURCE, getSource());
        return parameters;
    }

}
