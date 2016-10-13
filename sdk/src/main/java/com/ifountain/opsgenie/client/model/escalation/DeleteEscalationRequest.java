package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a delete escalation api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#deleteEscalation(DeleteEscalationRequest)
 */
public class DeleteEscalationRequest extends BaseRequest<DeleteEscalationResponse> {
    private String id;
    private String name;

    /**
     * Rest api uri of deleting escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    /**
     * Id of escalation to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of escalation to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of escalation to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteEscalationResponse createResponse() {
        return new DeleteEscalationResponse();
    }
}
