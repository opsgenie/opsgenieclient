package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a list alerts api call.
 *
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#listAlerts(com.ifountain.client.opsgenie.model.alert.ListAlertsRequest)
 */
public class ListAlertsRequest extends BaseRequest<ListAlertsResponse> {
    public enum SortBy{
        createdAt,
        updatedAt
    }
    public enum SortOrder{
        asc,
        desc
    }
    public enum Status{
        open,
        closed,
        acked,
        unacked,
        seen,
        notseen
    }
    private Long createdAfter;
    private Long updatedAfter;
    private Long createdBefore;
    private Long updatedBefore;
    private Integer limit;
    private Status status;
    private SortBy sortBy;
    private SortOrder sortOrder;

    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    /**
     * Returns the value of the specified time to list all alerts created after it.
     */
    public Long getCreatedAfter() {
        return createdAfter;
    }

    /**
     * Sets the value of the specified time to list all alerts created after it.
     */
    public void setCreatedAfter(Long createdAfter) {
        this.createdAfter = createdAfter;
    }

    public Long getUpdatedAfter() {
        return updatedAfter;
    }

    public void setUpdatedAfter(Long updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    public Long getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Long getUpdatedBefore() {
        return updatedBefore;
    }

    public void setUpdatedBefore(Long updatedBefore) {
        this.updatedBefore = updatedBefore;
    }

    /**
     * Number of alerts to be retrieved. Default value is 20. Max value is 100.
     */
    public Integer getLimit() {
        return limit;
    }


    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> parameters = super.serialize();
        if (createdAfter != null)
            parameters.put(ClientConstants.API.CREATED_AFTER, createdAfter);
        if (createdBefore != null)
            parameters.put(ClientConstants.API.CREATED_BEFORE, createdBefore);
        if (updatedAfter != null)
            parameters.put(ClientConstants.API.UPDATED_AFTER, updatedAfter);
        if (updatedBefore != null)
            parameters.put(ClientConstants.API.UPDATED_BEFORE, updatedBefore);
        if (sortBy != null)
            parameters.put(ClientConstants.API.SORT_BY, sortBy.name());
        if (sortOrder != null)
            parameters.put(ClientConstants.API.ORDER, sortOrder.name());
        if (limit != null)
            parameters.put(ClientConstants.API.LIMIT, limit);
        if (status != null)
            parameters.put(ClientConstants.API.STATUS, status.name());
        return parameters;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListAlertsResponse createResponse() {
        return new ListAlertsResponse();
    }
}
