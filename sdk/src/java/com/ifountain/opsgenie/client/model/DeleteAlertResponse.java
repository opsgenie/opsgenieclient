package com.ifountain.opsgenie.client.model;

/**
 * Represents the OpsGenie service response for a delete alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/4/12 3:38 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#deleteAlert(DeleteAlertRequest)
 */
public class DeleteAlertResponse implements Response{
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
