package com.ifountain.opsgenie.client.model;

/**
 * Represents the OpsGenie service response for an add note request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:00 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#addNote(AddNoteRequest)
 */
public class AddNoteResponse implements Response {
    private boolean success = true;

    /**
     * True if the operation is successful.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the operation success state.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
