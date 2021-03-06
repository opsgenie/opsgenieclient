package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make a take ownership api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#takeOwnership(TakeOwnershipRequest)
 * @deprecated As of release 2.8.0, will not be supported
 */
@Deprecated
public class TakeOwnershipRequest extends BaseAlertRequestWithParameters<TakeOwnershipResponse, TakeOwnershipRequest> {

    /**
     * Rest api uri of take ownership operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/takeOwnership";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public TakeOwnershipResponse createResponse() {
        return new TakeOwnershipResponse();
    }

}
