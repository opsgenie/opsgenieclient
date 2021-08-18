package com.ifountain.opsgenie.client.model.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list team logs api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(ListTeamLogsRequest)
 */
public class ListTeamLogsRequest extends BaseRequest<ListTeamLogsResponse, ListTeamLogsRequest> {
    private String id;
    private String name;
    @JsonProperty("order")
    private SortOrder sortOrder = SortOrder.asc;
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
    public ListTeamLogsResponse createResponse() {
        return new ListTeamLogsResponse();
    }

    public ListTeamLogsRequest withId(String id) {
        this.id = id;
        return this;
    }

    public ListTeamLogsRequest withName(String name) {
        this.name = name;
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

    public ListTeamLogsRequest withLastKey(String lastKey) {
        this.lastKey = lastKey;
        return this;
    }

    public enum SortOrder {
        asc, desc
    }

}
