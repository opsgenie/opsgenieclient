package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container for the parameters to make a list alert notes api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertNotes(ListAlertNotesRequest)
 */
public class ListAlertNotesRequest extends BaseAlertRequestWithId<ListAlertNotesResponse> {
    public enum SortOrder {
        asc, desc
    }

    @JsonIgnore
    private SortOrder sortOrder = SortOrder.asc;
    private Integer limit = 100;
    private String lastKey;

    @JsonProperty("order")
    public String getSortOrderName() {
        if (sortOrder != null)
            return sortOrder.name();
        return null;
    }

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
}
