package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make an execute aler action api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#executeAlertAction(ExecuteAlertActionRequest)
 */
public class ExecuteAlertActionRequest extends AddNoteRequest {
    private String action;

    /**
     * Rest api uri of execute alert action operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/executeAction";
    }

    /**
     * The action to be executed.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action to be executed.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ExecuteAlertActionResponse createResponse() {
        return new ExecuteAlertActionResponse();
    }
}
