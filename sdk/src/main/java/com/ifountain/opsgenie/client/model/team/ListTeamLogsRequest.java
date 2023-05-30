package com.ifountain.opsgenie.client.model.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.IdentifierType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Container for the parameters to make a list team logs api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(ListTeamLogsRequest)
 */
public class ListTeamLogsRequest extends BaseRequest<ListTeamLogsResponse, ListTeamLogsRequest> {
    private String identifier;
    private String identifierType;
    @JsonProperty("order")
    private SortOrder sortOrder = SortOrder.asc;
    private Integer limit = 20;
    private String offset;

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when name and id are both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(Objects.nonNull(identifierType) && Objects.isNull(IdentifierType.getFromValues(identifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.IDENTIFIER_TYPE);
        if (identifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
    }

    public Map<String,Object> getRequestParams() {
        Map<String, Object> params = new HashMap<>();
        if (Objects.nonNull(identifierType))
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE, identifierType);
        else
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE, OpsGenieClientConstants.API.ID);
        if(limit!=null && limit>=20 && limit<=100)
            params.put("limit",limit);
        if(limit!=null && limit>100)
            params.put("limit",100);
        if(limit!=null && limit<20)
            params.put("limit",20);
        if(sortOrder!=null)
            params.put("order",sortOrder);
        if(offset!=null)
            params.put("offset",offset);
        return params;
    }


    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/teams/"+identifier+"/logs";
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public ListTeamLogsResponse createResponse() {
        return new ListTeamLogsResponse();
    }

    public ListTeamLogsRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public ListTeamLogsRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }

    public ListTeamLogsRequest withSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public ListTeamLogsRequest withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ListTeamLogsRequest withOffset(String offset) {
        this.offset = offset;
        return this;
    }

    public enum SortOrder {
        asc, desc
    }

}
