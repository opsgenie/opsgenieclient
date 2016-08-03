package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a list alert logs api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertLogs(ListAlertLogsRequest)
 */
public class ListAlertLogsRequest extends BaseAlertRequestWithId<ListAlertLogsResponse> {
    public enum SortOrder{
        asc,
        desc
    }
    private SortOrder order =SortOrder.asc;
    private Integer limit = 100;
    private String lastKey;

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
        return "/v1/json/alert/log";
    }

    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map resp = super.serialize();
        if(!(resp.containsKey(OpsGenieClientConstants.API.ID)
                || resp.containsKey(OpsGenieClientConstants.API.ALERT_ID)
                ||resp.containsKey(OpsGenieClientConstants.API.ALIAS)
        || resp.containsKey(OpsGenieClientConstants.API.TINY_ID)) )
        {
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
        }
        if (getSortOrder() != null)
            resp.put(OpsGenieClientConstants.API.ORDER, getSortOrder().name());
        return resp;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListAlertLogsResponse createResponse() {
        return new ListAlertLogsResponse();
    }
}
