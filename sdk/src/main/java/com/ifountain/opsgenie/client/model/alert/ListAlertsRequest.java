package com.ifountain.opsgenie.client.model.alert;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * Container for the parameters to make a list alerts api call.
 *
 * @author Mehmet Mustafa Demir mehmetdemircs@gmail.com
 * @version 18/08/16 1:30 PM
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
    @JsonIgnore
    private SortOrder sortOrder;

    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }
	@JsonProperty("sortBy")
    public String getSortByName() {
    	if(sortBy != null)
    		return sortBy.name();
    	return null;
    }
    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

	@JsonProperty("order")
    public String getSortOrderName() {
    	if(sortOrder != null)
    		return sortOrder.name();
    	return null;
    }
    
    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListAlertsResponse createResponse() {
        return new ListAlertsResponse();
    }
}
