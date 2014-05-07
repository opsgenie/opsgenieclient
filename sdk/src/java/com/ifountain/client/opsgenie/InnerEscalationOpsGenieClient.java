package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.http.JsonHttpClient;
import com.ifountain.client.opsgenie.model.escalation.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Escalation Client
 */
public class InnerEscalationOpsGenieClient implements IEscalationOpsGenieClient{
    private JsonHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerEscalationOpsGenieClient(JsonHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IEscalationOpsGenieClient#addEscalation(com.ifountain.client.opsgenie.model.escalation.AddEscalationRequest)
     */
    @Override
    public AddEscalationResponse addEscalation(AddEscalationRequest addEscalationRequest) throws IOException, ClientException, ParseException {
        return (AddEscalationResponse) httpClient.doPostRequest(addEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#updateEscalation(com.ifountain.client.opsgenie.model.escalation.UpdateEscalationRequest)
     */
    @Override
    public UpdateEscalationResponse updateEscalation(UpdateEscalationRequest updateEscalationRequest) throws IOException, ClientException, ParseException {
        return (UpdateEscalationResponse) httpClient.doPostRequest(updateEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#deleteEscalation(com.ifountain.client.opsgenie.model.escalation.DeleteEscalationRequest)
     */
    @Override
    public DeleteEscalationResponse deleteEscalation(DeleteEscalationRequest deleteEscalationRequest) throws IOException, ClientException, ParseException {
        return (DeleteEscalationResponse) httpClient.doDeleteRequest(deleteEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#getEscalation(com.ifountain.client.opsgenie.model.escalation.GetEscalationRequest)
     */
    @Override
    public GetEscalationResponse getEscalation(GetEscalationRequest getEscalationRequest) throws IOException, ClientException, ParseException {
        return (GetEscalationResponse) httpClient.doGetRequest(getEscalationRequest);
    }

    /**
     * @see IEscalationOpsGenieClient#listEscalations(com.ifountain.client.opsgenie.model.escalation.ListEscalationsRequest)
     */
    @Override
    public ListEscalationsResponse listEscalations(ListEscalationsRequest listEscalationsRequest) throws IOException, ClientException, ParseException {
        return (ListEscalationsResponse) httpClient.doGetRequest(listEscalationsRequest);
    }

}
