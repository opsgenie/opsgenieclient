package com.ifountain.opsgenie.client.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make a list alert notes api call.
 *
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertNotes(ListAlertNotesRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.ListAlertNotesRequest}
 */
@Deprecated
public class ListAlertNotesRequest extends BaseAlertRequestWithId<ListAlertNotesResponse, ListAlertNotesRequest> {
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
        return "/v1/json/alert/note";
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
    public ListAlertNotesResponse createResponse() {
        return new ListAlertNotesResponse();
    }

    public ListAlertNotesRequest withSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public ListAlertNotesRequest withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ListAlertNotesRequest withLastKey(String lastKey) {
        this.lastKey = lastKey;
        return this;
    }

    public enum SortOrder {
        asc, desc
    }
}
