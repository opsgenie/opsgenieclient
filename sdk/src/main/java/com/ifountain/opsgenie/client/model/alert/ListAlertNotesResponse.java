package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.AlertNote;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Represents the OpsGenie service response for list alert notes request.
 *
 * @author Halit Okumus
 * @version 9/16/14 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertNotes(ListAlertNotesRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.ListAlertNotesResponse}
 */
@Deprecated
public class ListAlertNotesResponse extends BaseResponse {
    private String lastKey;
    @JsonProperty("notes")
    private List<AlertNote> alertNotes;

    /**
     * @return alert note objects
     * @see AlertNote
     */
    public List<AlertNote> getAlertNotes() {
        return alertNotes;
    }

    /**
     * Sets alert note objects
     *
     * @see AlertNote
     */
    public void setAlertNotes(List<AlertNote> alertNotes) {
        this.alertNotes = alertNotes;
    }

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }

}
