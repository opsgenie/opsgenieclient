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
 * Container for the parameters to make a delete schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteSchedule(DeleteScheduleRequest)
 */
public class DeleteScheduleRequest extends BaseRequest<DeleteScheduleResponse, DeleteScheduleRequest> {
    private String identifier;
    private String identifierType;

    /**
     * Rest api uri of deleting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/schedules/"+ identifier;
    }

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
    public DeleteScheduleResponse createResponse() {
        return new DeleteScheduleResponse();
    }

    public DeleteScheduleRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public DeleteScheduleRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }
}
