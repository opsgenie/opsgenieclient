package com.ifountain.opsgenie.client.model.alert;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container for the parameters to make a list alerts api call.
 *
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlerts(ListAlertsRequest)
 */
public class ListAlertsRequest extends AlertsRequest<ListAlertsResponse> {
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
