package com.ifountain.client.statussiren.model.incident;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a list incidents api call.
 * @author Tuba Ozturk
 * @version 30.4.2014 13:36
 */
public class ListIncidentsRequest extends BaseIncidentRequestWithService<ListIncidentsResponse> {
    public enum Order {
        asc,
        desc
    }
    private Long createdAfter;
    private Long createdBefore;
    private Integer limit;
    private Order order;

    public Long getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Long createdAfter) {
        this.createdAfter = createdAfter;
    }

    public Long getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListIncidentsResponse createResponse() {
        return new ListIncidentsResponse();
    }

    /**
     * Rest api uri of list incident operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/incident";
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
        if (order != null)
            parameters.put(ClientConstants.API.ORDER, order.name());
        if (limit != null)
            parameters.put(ClientConstants.API.LIMIT, limit);

        return parameters;
    }
}
