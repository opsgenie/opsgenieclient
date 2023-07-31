package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.IdentifierType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Container for the parameters to make a get schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(GetScheduleRequest)
 */
public class GetScheduleRequest extends BaseRequest<GetScheduleResponse, GetScheduleRequest> {
    private String identifier;
    private String identifierType;

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when name and id are both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(StringUtils.isNotEmpty(identifierType) && Objects.isNull(IdentifierType.getFromValues(identifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.IDENTIFIER_TYPE);
        if (StringUtils.isEmpty(identifier))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
    }
    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(StringUtils.isNotEmpty(identifierType))
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,identifierType);
        else
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,OpsGenieClientConstants.API.ID);
        return params;
    }

    /**
     * Rest api uri of getting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/schedules/"+ identifier;
    }
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public GetScheduleResponse createResponse() {
        return new GetScheduleResponse();
    }

    public GetScheduleRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public GetScheduleRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }

}
