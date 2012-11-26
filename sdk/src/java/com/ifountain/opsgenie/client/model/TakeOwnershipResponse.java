package com.ifountain.opsgenie.client.model;

/**
 * Represents the OpsGenie service response for a take ownership request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:32 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#takeOwnership(TakeOwnershipRequest)
 */
public class TakeOwnershipResponse implements Response {
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
