package com.ifountain.opsgenie.client.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Container for the parameters to make a list alerts api call.
 *
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlerts(ListAlertsRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.ListAlertsRequest}
 */
@Deprecated
public class ListAlertsRequest extends AlertsRequest<ListAlertsResponse, ListAlertsRequest> {
    @JsonProperty("sortBy")
    private SortBy sortBy;
    @JsonProperty("order")
    private SortOrder sortOrder;

    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }


    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public ListAlertsRequest withSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public ListAlertsRequest withSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListAlertsResponse createResponse() {
        return new ListAlertsResponse();
    }

    public enum SortBy {
        createdAt, updatedAt
    }

    public enum SortOrder {
        asc, desc
    }
}
