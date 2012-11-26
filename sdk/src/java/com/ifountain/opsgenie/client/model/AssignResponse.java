package com.ifountain.opsgenie.client.model;

/**
 * Represents the OpsGenie service response for a assign ownership request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:37 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#assign(AssignRequest)
 */
public class AssignResponse implements Response{
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
