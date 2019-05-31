package com.ifountain.opsgenie.client.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make a list alert logs api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertLogs(ListAlertLogsRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.ListAlertLogsRequest}
 */
@Deprecated
public class ListAlertLogsRequest extends BaseAlertRequestWithId<ListAlertLogsResponse, ListAlertLogsRequest> {
    @JsonProperty("order")
    private SortOrder sortOrder = SortOrder.asc;
    private Integer limit = 100;
    private String lastKey;

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
        return "/v1/json/alert/log";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        if (!(this.getId() != null || this.getAlias() != null || this.getTinyId() != null))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ID,
                    OpsGenieClientConstants.API.ALIAS, OpsGenieClientConstants.API.TINY_ID);
        super.validate();
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListAlertLogsResponse createResponse() {
        return new ListAlertLogsResponse();
    }

    public ListAlertLogsRequest withSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public ListAlertLogsRequest withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ListAlertLogsRequest withLastKey(String lastKey) {
        this.lastKey = lastKey;
        return this;
    }

    public enum SortOrder {
        asc, desc
    }
}
