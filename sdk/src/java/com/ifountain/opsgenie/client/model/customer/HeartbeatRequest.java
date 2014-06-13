package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a heartbeat api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:06 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#heartbeat(HeartbeatRequest)
 */
public class HeartbeatRequest extends BaseRequest<HeartbeatResponse> {
    String name;
    /*
    * Name of heartbeat monitor
     */
    public String getName() {
        return name;
    }

    /**
    * Sets name of heartbeat monitor
     **/
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @deprecated
    * Source of heartbeat monitor
     */
    public String getSource() {
        return getName();
    }

    /**
     * @deprecated
    * Use setName
     **/
    public void setSource(String source) {
        setName(source);
    }

    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map res = super.serialize();
        if(name != null){
            res.put(OpsGenieClientConstants.API.NAME, name);
        }
        return res;
    }

    /**
     * Rest api uri of heartbeat operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat/send";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public HeartbeatResponse createResponse() {
        return new HeartbeatResponse();
    }
}
