package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a list team logs api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 1:51 PM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(ListTeamLogsRequest)
 */
public class ListTeamLogsRequest extends BaseRequest<ListTeamLogsResponse>{
    public enum SortOrder{
        asc,
        desc
    }
    private String id;
    private String name;
    private SortOrder sortOrder =SortOrder.asc;
    private Integer limit = 100;
    private String lastKey;

    /**
     * Id of the team.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the team.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the team.
     */
    public void setName(String name) {
        this.name = name;
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

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }

    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team/log";
    }

    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map resp = super.serialize();
        if(!(resp.containsKey(OpsGenieClientConstants.API.ID) || resp.containsKey(OpsGenieClientConstants.API.NAME))){
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
        }
        if (getId() != null)
            resp.put(OpsGenieClientConstants.API.ID, getId());
        if (getName() != null)
            resp.put(OpsGenieClientConstants.API.NAME, getName());
        if (getLastKey() != null)
            resp.put(OpsGenieClientConstants.API.LAST_KEY, getLastKey());
        if (getSortOrder() != null)
            resp.put(OpsGenieClientConstants.API.ORDER, getSortOrder().name());
        if (getLimit() != null)
            resp.put(OpsGenieClientConstants.API.LIMIT, getLimit());
        return resp;
    }

    @Override
    public ListTeamLogsResponse createResponse() {
        return new ListTeamLogsResponse();
    }

}
