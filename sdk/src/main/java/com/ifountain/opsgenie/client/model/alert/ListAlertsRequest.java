package com.ifountain.opsgenie.client.model.alert;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make a list alerts api call.
 *
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlerts(com.ifountain.opsgenie.client.model.alert.ListAlertsRequest)
 */
public class ListAlertsRequest extends AlertsRequest<ListAlertsResponse> {
    public enum SortBy{
        createdAt,
        updatedAt
    }
    public enum SortOrder{
        asc,
        desc
    }

    private SortBy sortBy;
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map parameters = super.serialize();
        if (sortBy != null)
            parameters.put(OpsGenieClientConstants.API.SORT_BY, sortBy.name());
        if (sortOrder != null)
            parameters.put(OpsGenieClientConstants.API.ORDER, sortOrder.name());
        return parameters;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListAlertsResponse createResponse() {
        return new ListAlertsResponse();
    }
}
