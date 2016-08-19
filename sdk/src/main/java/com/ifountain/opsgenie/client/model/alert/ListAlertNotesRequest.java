package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by user on 9/16/2014.
 */
public class ListAlertNotesRequest extends BaseAlertRequestWithId<ListAlertNotesResponse> {
    public enum SortOrder{
        asc,
        desc
    }
    private SortOrder order =SortOrder.asc;
    private Integer limit = 100;
    private String lastKey;
    @JsonProperty("order")
    public String getSortOrderName() {
    	if(order != null)
    		return order.name();
    	return null;
    }
    
    public SortOrder getSortOrder() {
        return order;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.order = sortOrder;
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
        return "/v1/json/alert/note";
    }
    
    @Override
    public void validate() throws OpsGenieClientValidationException { 
    	if(!(this.getId() != null
            || this.getAlertId() != null
            || this.getAlias() != null
    		|| this.getTinyId() != null) )
        throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ID,
        		OpsGenieClientConstants.API.ALERT_ID,OpsGenieClientConstants.API.ALIAS,OpsGenieClientConstants.API.TINY_ID);
    	super.validate();
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListAlertNotesResponse createResponse() {
        return new ListAlertNotesResponse();
    }
}
