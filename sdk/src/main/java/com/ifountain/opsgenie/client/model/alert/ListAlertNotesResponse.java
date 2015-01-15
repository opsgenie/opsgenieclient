package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.AlertNote;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for list alert notes request.
 *
 * @author Halit Okumus
 * @version 9/16/14 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertNotes(ListAlertNotesRequest)
 */
public class ListAlertNotesResponse extends BaseResponse {
    private String lastKey;

    /**
     * @return alert note objects
     * @see AlertNote
     */
    public List<AlertNote> getAlertNotes() {
        return alertNotes;
    }

    /**
     * Sets alert note objects
     * @see AlertNote
     */
    public void setAlertNotes(List<AlertNote> alertNotes) {
        this.alertNotes = alertNotes;
    }

    private List<AlertNote> alertNotes;

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        lastKey = (String) data.get(OpsGenieClientConstants.API.LAST_KEY);
        List<Map> notesMap = (List<Map>) data.get(OpsGenieClientConstants.API.NOTES);
        alertNotes = new ArrayList<AlertNote>();
        for(Map logData:notesMap){
            AlertNote note = new AlertNote();
            note.fromMap(logData);
            alertNotes.add(note);
        }
    }
}
