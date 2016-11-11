package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Base Container for the parameters to make a list flat who is on call.
 */
public abstract class AbstractListWhoIsOnCallRequest<T extends BaseResponse, K extends AbstractListWhoIsOnCallRequest> extends BaseRequest<T, K> {
    /**
     * Rest api uri of lsiting on calls of schedules operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
    }
}
