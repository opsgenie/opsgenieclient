package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a list alerts api call.
 *
 * @author Mustafa Sener
 * @version 22.04.2013 15:48
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
     * Returns the specified createdAfter time.
     * If created after set in request, response contains alerts which created after the specified time.
     */
    public Long getCreatedAfter() {
        return createdAfter;
    }

    /**
     * Sets the specified createdAfter time
     */
    public void setCreatedAfter(Long createdAfter) {
        this.createdAfter = createdAfter;
    }

    /**
     * Returns the specified updatedAfter time.
     * If updated after set in request, response contains alerts which last updated after the specified time.
     */
    public Long getUpdatedAfter() {
        return updatedAfter;
    }

    /**
     * Sets the specified updatedAfter time.
     */
    public void setUpdatedAfter(Long updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    /**
     * Returns the specified createdBefore time.
     * If created after set in request, response contains alerts which created before specified time.
     */
    public Long getCreatedBefore() {
        return createdBefore;
    }

    /**
     * Sets the specified createdBefore time
     */
    public void setCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
    }

    /**
     * Returns the specified updatedBefore time.
     * If updated before set in request, response contains alerts which last updated before specified time.
     */
    public Long getUpdatedBefore() {
        return updatedBefore;
    }

    /**
     * Sets the specified updatedBefore time.
     */
    public void setUpdatedBefore(Long updatedBefore) {
        this.updatedBefore = updatedBefore;
    }

    /**
     * Number of alerts to be retrieved. Default value is 20. Max value is 100.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the number of alerts to be retrieved.
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * The status of alerts to be retrieved.
     * If status value set in request, response contains alerts which have the specified status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the alerts to retrieved. Can be open/closed/acked/unacked/seen/notseen.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Returns the parameter that alerts will sort by. Can be updatedAt or createdAt
     */
    public SortBy getSortBy() {
        return sortBy;
    }

    /**
     * Sets the parameter that alerts will sort by. Can be updatedAt or createdAt
     */
    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Order of alerts to be retrieved. Can be asc or desc.
     */
    public SortOrder getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the order of alerts to be retrieved. Can be asc or desc.
     */
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
