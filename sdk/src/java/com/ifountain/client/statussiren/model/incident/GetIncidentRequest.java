package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get incident api call.
 * @author Tuba Ozturk
 * @version 30.4.2014 13:35
 * @see com.ifountain.client.statussiren.IStatusSirenClient#getIncident(GetIncidentRequest)
 */
public class GetIncidentRequest extends BaseIncidentRequestWithId<GetIncidentResponse> {
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    @Override
    public GetIncidentResponse createResponse() {
        return new GetIncidentResponse();
    }

    /**
     * Rest api uri of get incident operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/incident";
    }

    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String, Object> serialize() throws ClientValidationException {
        return super.serialize();
    }
}
