package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.AlertNote;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * Represents the OpsGenie service response for list alert notes request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertNotes(ListAlertNotesRequest)
 */
public class ListAlertNotesResponse extends BaseResponse {
    private String lastKey;
    private List<AlertNote> notes;

    /**
     * @deprecated Use getNotes
     */
    @Deprecated
    @JsonIgnore
    public List<AlertNote> getAlertNotes() {
        return notes;
    }

    /**
     * @deprecated Use setNotes
     */
    @Deprecated
    @JsonIgnore
    public void setAlertNotes(List<AlertNote> alertNotes) {
        this.notes = alertNotes;
    }

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }

    public List<AlertNote> getNotes() {
        return notes;
    }

    public void setNotes(List<AlertNote> notes) {
        this.notes = notes;
    }
}
