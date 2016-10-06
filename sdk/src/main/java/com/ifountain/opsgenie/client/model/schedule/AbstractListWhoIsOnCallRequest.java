package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Base Container for the parameters to make a list flat who is on call.
 *
 * @author Mehmet Mustafa Demir
 */
public abstract class AbstractListWhoIsOnCallRequest <T extends BaseResponse> extends BaseRequest<T> {
    /**
     * Rest api uri of lsiting on calls of schedules operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
    }
}
