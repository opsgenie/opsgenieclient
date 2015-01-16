package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.IEscalationOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.escalation.*;

import java.io.IOException;

public class InnerEscalationOpsGenieClientMock implements IEscalationOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private AddEscalationResponse addEscalationResponse;
    private UpdateEscalationResponse updateEscalationResponse;
    private DeleteEscalationResponse deleteEscalationResponse;
    private GetEscalationResponse getEscalationResponse;
    private ListEscalationsResponse listEscalationsResponse;
    public InnerEscalationOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public AddEscalationResponse addEscalation(AddEscalationRequest addEscalationRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(addEscalationRequest);
        return addEscalationResponse;
    }

    @Override
    public UpdateEscalationResponse updateEscalation(UpdateEscalationRequest updateEscalationRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(updateEscalationRequest);
        return updateEscalationResponse;
    }

    @Override
    public ListEscalationsResponse listEscalations(ListEscalationsRequest listEscalationsRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(listEscalationsRequest);
        return listEscalationsResponse;
    }

    @Override
    public DeleteEscalationResponse deleteEscalation(DeleteEscalationRequest deleteEscalationRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(deleteEscalationRequest);
        return deleteEscalationResponse;
    }

    @Override
    public GetEscalationResponse getEscalation(GetEscalationRequest getEscalationRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(getEscalationRequest);
        return getEscalationResponse;
    }


    public void setAddEscalationResponse(AddEscalationResponse addEscalationResponse) {
        this.addEscalationResponse = addEscalationResponse;
    }

    public void setUpdateEscalationResponse(UpdateEscalationResponse updateEscalationResponse) {
        this.updateEscalationResponse = updateEscalationResponse;
    }

    public void setDeleteEscalationResponse(DeleteEscalationResponse deleteEscalationResponse) {
        this.deleteEscalationResponse = deleteEscalationResponse;
    }

    public void setGetEscalationResponse(GetEscalationResponse getEscalationResponse) {
        this.getEscalationResponse = getEscalationResponse;
    }

    public void setListEscalationsResponse(ListEscalationsResponse listEscalationsResponse) {
        this.listEscalationsResponse = listEscalationsResponse;
    }

}
