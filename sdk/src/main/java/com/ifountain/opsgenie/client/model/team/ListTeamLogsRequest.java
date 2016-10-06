package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseRequest;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container for the parameters to make a list team logs api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(ListTeamLogsRequest)
 */
public class ListTeamLogsRequest extends BaseRequest<ListTeamLogsResponse> {
    public enum SortOrder {
        asc, desc
    }

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

}
