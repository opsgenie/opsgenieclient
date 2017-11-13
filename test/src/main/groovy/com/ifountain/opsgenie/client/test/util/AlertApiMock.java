package com.ifountain.opsgenie.client.test.util;

import com.opsgenie.oas.sdk.ApiClient;
import com.opsgenie.oas.sdk.ApiException;
import com.opsgenie.oas.sdk.api.AlertApi;
import com.opsgenie.oas.sdk.model.*;

import java.util.List;

/**
 * Created by bcelenk on 11/7/17.
 */
public class AlertApiMock extends AlertApi {
    private OpsGenieClientMockRequestProcessor requestProcessor = new OpsGenieClientMockRequestProcessor();
    private SuccessResponse genericSuccessResponse;
    private SuccessResponseMock genericSuccessResponseMock;
    private CreateSavedSearchResponse createSavedSearchResponse;
    private GetAlertResponse getAlertResponse;
    private GetAlertAttachmentResponse getAlertAttachmentResponse;
    private GetRequestStatusResponse getRequestStatusResponse;
    private GetSavedSearchResponse getSavedSearchResponse;
    private ListAlertsResponse listAlertsResponse;
    private ListAlertAttachmentsResponse listAlertAttachmentsResponse;
    private ListAlertLogsResponse listAlertLogsResponse;
    private ListAlertNotesResponse listAlertNotesResponse;
    private ListAlertRecipientsResponse listAlertRecipientsResponse;
    private ListSavedSearchesResponse listSavedSearchesResponse;

    @Override
    public SuccessResponse acknowledgeAlert(AcknowledgeAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse addAttachment(AddAttachmentToAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return null;
    }

    @Override
    public SuccessResponse addDetails(AddDetailsToAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse addNote(AddNoteToAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse addTags(AddTagsToAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse addTeam(AddTeamToAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse assignAlert(AssignAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse closeAlert(CloseAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse createAlert(CreateAlertPayload body) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public CreateSavedSearchResponse createSavedSearches(CreateSavedSearchPayload body) throws ApiException {
        requestProcessor.processRequestV2(body);
        return createSavedSearchResponse;
    }

    @Override
    public SuccessResponse deleteAlert(DeleteAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse deleteSavedSearch(DeleteSavedSearchRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse escalateAlert(EscalateAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse executeCustomAlertAction(ExecuteCustomAlertActionRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public GetAlertResponse getAlert(GetAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return getAlertResponse;
    }

    @Override
    public GetAlertAttachmentResponse getAttachment(GetAlertAttachmentRequest params) throws ApiException {
        return super.getAttachment(params);
    }

    @Override
    public GetRequestStatusResponse getRequestStatus(String requestId) throws ApiException {
        return getRequestStatusResponse;
    }

    @Override
    public GetSavedSearchResponse getSavedSearch(GetSavedSearchRequest params) throws ApiException {
        return getSavedSearchResponse;
    }

    @Override
    public ListAlertsResponse listAlerts(ListAlertsRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return listAlertsResponse;
    }

    @Override
    public ListAlertAttachmentsResponse listAttachments(ListAlertAttachmentsRequest params) throws ApiException {
        return super.listAttachments(params);
    }

    @Override
    public ListAlertLogsResponse listLogs(ListAlertLogsRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return listAlertLogsResponse;
    }

    @Override
    public ListAlertNotesResponse listNotes(ListAlertNotesRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return listAlertNotesResponse;
    }

    @Override
    public ListAlertRecipientsResponse listRecipients(ListAlertRecipientsRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return listAlertRecipientsResponse;
    }

    @Override
    public ListSavedSearchesResponse listSavedSearches() throws ApiException {
        return listSavedSearchesResponse;
    }

    @Override
    public SuccessResponse removeAttachment(RemoveAttachmentFromAlertRequest params) throws ApiException {
        return super.removeAttachment(params);
    }

    @Override
    public SuccessResponse removeDetails(RemoveDetailsFromAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse removeTags(RemoveTagsFromAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        genericSuccessResponseMock.setIdentifier(params.getIdentifier());
        genericSuccessResponseMock.setIdentifierType(params.getIdentifierType().getValue());
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse snoozeAlert(SnoozeAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse unAcknowledgeAlert(UnAcknowledgeAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    public SuccessResponseMock getGenericSuccessResponseMock() {
        return genericSuccessResponseMock;
    }

    public void setGenericSuccessResponse(SuccessResponse genericSuccessResponse) {
        this.genericSuccessResponse = genericSuccessResponse;
    }

    public void setGenericSuccessResponseMock(SuccessResponseMock genericSuccessResponseMock) {
        this.genericSuccessResponseMock = genericSuccessResponseMock;
    }

    public List<Object> getExecutedRequests() {
        return requestProcessor.getExecutedRequestsV2();
    }

    public CreateSavedSearchResponse getCreateSavedSearchResponse() {
        return createSavedSearchResponse;
    }

    public void setCreateSavedSearchResponse(CreateSavedSearchResponse createSavedSearchResponse) {
        this.createSavedSearchResponse = createSavedSearchResponse;
    }

    public GetAlertResponse getGetAlertResponse() {
        return getAlertResponse;
    }

    public void setGetAlertResponse(GetAlertResponse getAlertResponse) {
        this.getAlertResponse = getAlertResponse;
    }

    public GetAlertAttachmentResponse getGetAlertAttachmentResponse() {
        return getAlertAttachmentResponse;
    }

    public void setGetAlertAttachmentResponse(GetAlertAttachmentResponse getAlertAttachmentResponse) {
        this.getAlertAttachmentResponse = getAlertAttachmentResponse;
    }

    public GetRequestStatusResponse getGetRequestStatusResponse() {
        return getRequestStatusResponse;
    }

    public void setGetRequestStatusResponse(GetRequestStatusResponse getRequestStatusResponse) {
        this.getRequestStatusResponse = getRequestStatusResponse;
    }

    public GetSavedSearchResponse getGetSavedSearchResponse() {
        return getSavedSearchResponse;
    }

    public void setGetSavedSearchResponse(GetSavedSearchResponse getSavedSearchResponse) {
        this.getSavedSearchResponse = getSavedSearchResponse;
    }

    public ListAlertsResponse getListAlertsResponse() {
        return listAlertsResponse;
    }

    public void setListAlertsResponse(ListAlertsResponse listAlertsResponse) {
        this.listAlertsResponse = listAlertsResponse;
    }

    public ListAlertAttachmentsResponse getListAlertAttachmentsResponse() {
        return listAlertAttachmentsResponse;
    }

    public void setListAlertAttachmentsResponse(ListAlertAttachmentsResponse listAlertAttachmentsResponse) {
        this.listAlertAttachmentsResponse = listAlertAttachmentsResponse;
    }

    public ListAlertLogsResponse getListAlertLogsResponse() {
        return listAlertLogsResponse;
    }

    public void setListAlertLogsResponse(ListAlertLogsResponse listAlertLogsResponse) {
        this.listAlertLogsResponse = listAlertLogsResponse;
    }

    public ListAlertNotesResponse getListAlertNotesResponse() {
        return listAlertNotesResponse;
    }

    public void setListAlertNotesResponse(ListAlertNotesResponse listAlertNotesResponse) {
        this.listAlertNotesResponse = listAlertNotesResponse;
    }

    public ListAlertRecipientsResponse getListAlertRecipientsResponse() {
        return listAlertRecipientsResponse;
    }

    public void setListAlertRecipientsResponse(ListAlertRecipientsResponse listAlertRecipientsResponse) {
        this.listAlertRecipientsResponse = listAlertRecipientsResponse;
    }

    public ListSavedSearchesResponse getListSavedSearchesResponse() {
        return listSavedSearchesResponse;
    }

    public void setListSavedSearchesResponse(ListSavedSearchesResponse listSavedSearchesResponse) {
        this.listSavedSearchesResponse = listSavedSearchesResponse;
    }
}
