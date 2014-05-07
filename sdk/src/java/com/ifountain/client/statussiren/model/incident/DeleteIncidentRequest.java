package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.text.ParseException;
import java.util.Map;

/**
 * Container for the parameters to make a delete incident api call.
 * @author Tuba Ozturk
 * @version 30.4.2014 13:31
 */
public class DeleteIncidentRequest extends BaseIncidentRequestWithId<DeleteIncidentResponse> {

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public DeleteIncidentResponse createResponse() {
        return new DeleteIncidentResponse();
    }

    /**
     * Rest api uri of delete incident operation.
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
