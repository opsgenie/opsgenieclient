package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.beans.Escalation;
import com.ifountain.opsgenie.client.model.beans.EscalationRule;
import com.ifountain.opsgenie.client.model.escalation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ifountain.opsgenie.client.JsonOpgenieHttpClient.OpsGenieJsonResponse;

/**
 * Inner Escalation Client
 */
public class InnerEscalationOpsGenieClient implements IEscalationOpsGenieClient{
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerEscalationOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IEscalationOpsGenieClient#addEscalation(com.ifountain.opsgenie.client.model.escalation.AddEscalationRequest)
     */
    @Override
    public AddEscalationResponse addEscalation(AddEscalationRequest addEscalationRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddEscalationResponse) httpClient.doPostRequest(addEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#updateEscalation(com.ifountain.opsgenie.client.model.escalation.UpdateEscalationRequest)
     */
    @Override
    public UpdateEscalationResponse updateEscalation(UpdateEscalationRequest updateEscalationRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateEscalationResponse) httpClient.doPostRequest(updateEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#deleteEscalation(com.ifountain.opsgenie.client.model.escalation.DeleteEscalationRequest)
     */
    @Override
    public DeleteEscalationResponse deleteEscalation(DeleteEscalationRequest deleteEscalationRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteEscalationResponse) httpClient.doDeleteRequest(deleteEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#getEscalation(com.ifountain.opsgenie.client.model.escalation.GetEscalationRequest)
     */
    @Override
    public GetEscalationResponse getEscalation(GetEscalationRequest getEscalationRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetEscalationResponse) httpClient.doGetRequest(getEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#listEscalations(com.ifountain.opsgenie.client.model.escalation.ListEscalationRequest)
     */
    @Override
    public ListEscalationResponse listEscalations(ListEscalationRequest listEscalationRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListEscalationResponse) httpClient.doGetRequest(listEscalationRequest);
    }

}
