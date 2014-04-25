package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientValidationException;
import com.ifountain.client.OpsGenieClientConstants;
import com.ifountain.client.model.BaseResponse;

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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        Map parameters = super.serialize();
        if (getSource() != null)
            parameters.put(OpsGenieClientConstants.API.SOURCE, getSource());
        return parameters;
    }

}
