package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.swagger.ApiException;
import com.ifountain.opsgenie.client.swagger.api.AlertApi;
import com.ifountain.opsgenie.client.swagger.model.AcknowledgeAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.AddAlertDetailsRequest;
import com.ifountain.opsgenie.client.swagger.model.AddAlertNoteRequest;
import com.ifountain.opsgenie.client.swagger.model.AddAlertTagsRequest;
import com.ifountain.opsgenie.client.swagger.model.AddAlertTeamRequest;
import com.ifountain.opsgenie.client.swagger.model.AddSavedSearchRequest;
import com.ifountain.opsgenie.client.swagger.model.AddSavedSearchResponse;
import com.ifountain.opsgenie.client.swagger.model.AssignAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.CloseAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.CreateAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.DeleteAlertDetailsRequest;
import com.ifountain.opsgenie.client.swagger.model.DeleteAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.DeleteAlertTagsRequest;
import com.ifountain.opsgenie.client.swagger.model.EscalateAlertToNextRequest;
import com.ifountain.opsgenie.client.swagger.model.ExecuteCustomAlertActionRequest;
import com.ifountain.opsgenie.client.swagger.model.GetAlertResponse;
import com.ifountain.opsgenie.client.swagger.model.GetRequestStatusResponse;
import com.ifountain.opsgenie.client.swagger.model.GetSavedSearchResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertLogsRequest;
import com.ifountain.opsgenie.client.swagger.model.ListAlertLogsResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertNotesRequest;
import com.ifountain.opsgenie.client.swagger.model.ListAlertNotesResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertRecipientsResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertsRequest;
import com.ifountain.opsgenie.client.swagger.model.ListAlertsResponse;
import com.ifountain.opsgenie.client.swagger.model.ListSavedSearchResponse;
import com.ifountain.opsgenie.client.swagger.model.SnoozeAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.SuccessResponse;
import com.ifountain.opsgenie.client.swagger.model.UnAcknowledgeAlertRequest;

/**
 * @author Celal Emre CICEK
 * @version 04/07/2017
 */

public class InnerAlertV2OpsGenieClientMock extends AlertApi {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private SuccessResponse genericSuccessResponse;
    private SuccessResponseMock genericSuccessResponseMock;
    private AddSavedSearchResponse addSavedSearchResponse;
    private GetAlertResponse getAlertResponse;
    private GetRequestStatusResponse getRequestStatusResponse;
    private GetSavedSearchResponse getSavedSearchResponse;
    private ListAlertsResponse listAlertsResponse;
    private ListAlertLogsResponse listAlertLogsResponse;
    private ListAlertNotesResponse listAlertNotesResponse;
    private ListAlertRecipientsResponse listAlertRecipientsResponse;
    private ListSavedSearchResponse listSavedSearchResponse;

    public InnerAlertV2OpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public SuccessResponse acknowledgeAlert(String identifier, String identifierType, AcknowledgeAlertRequest body) throws ApiException {
        requestProcessor.processRequestV2(body);
        genericSuccessResponseMock.setIdentifier(identifier);
        genericSuccessResponseMock.setIdentifierType(identifierType);
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse addDetails(String identifier, AddAlertDetailsRequest body, String identifierType) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse addNote(String identifier, AddAlertNoteRequest body, String identifierType) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public AddSavedSearchResponse addSavedSearches(AddSavedSearchRequest body) throws ApiException {
        requestProcessor.processRequestV2(body);
        return addSavedSearchResponse;
    }

    @Override
    public SuccessResponse addTags(String identifier, AddAlertTagsRequest body, String identifierType) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse addTeam(String identifier, AddAlertTeamRequest body, String identifierType) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse assignAlert(String identifier, AssignAlertRequest body, String identifierType) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse closeAlert(String identifier, String identifierType, CloseAlertRequest body) throws ApiException {
        requestProcessor.processRequestV2(body);
        genericSuccessResponseMock.setIdentifier(identifier);
        genericSuccessResponseMock.setIdentifierType(identifierType);
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse createAlert(CreateAlertRequest body) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse deleteAlert(DeleteAlertRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse deleteDetails(DeleteAlertDetailsRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse deleteSavedSearch(String identifier, String identifierType) throws ApiException {
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse deleteTags(DeleteAlertTagsRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse escalateAlert(String identifier, EscalateAlertToNextRequest body, String identifierType) throws ApiException {
        requestProcessor.processRequestV2(body);
        genericSuccessResponseMock.setIdentifier(identifier);
        genericSuccessResponseMock.setIdentifierType(identifierType);
        return genericSuccessResponseMock;
    }

    @Override
    public SuccessResponse executeCustomAction(String identifier, String actionName, String identifierType, ExecuteCustomAlertActionRequest body) throws ApiException {
        requestProcessor.processRequestV2(body);
        genericSuccessResponseMock.setIdentifier(identifier);
        genericSuccessResponseMock.setIdentifierType(identifierType);
        return genericSuccessResponseMock;
    }

    @Override
    public GetAlertResponse getAlert(String identifier, String identifierType) throws ApiException {
        return getAlertResponse;
    }

    @Override
    public GetRequestStatusResponse getRequestStatus(String requestId) throws ApiException {
        return getRequestStatusResponse;
    }

    @Override
    public GetSavedSearchResponse getSavedSearch(String identifier, String identifierType) throws ApiException {
        return getSavedSearchResponse;
    }

    @Override
    public ListAlertsResponse listAlerts(ListAlertsRequest params) throws ApiException {
        requestProcessor.processRequestV2(params);
        return listAlertsResponse;
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
    public ListAlertRecipientsResponse listRecipients(String identifier, String identifierType) throws ApiException {
        return listAlertRecipientsResponse;
    }

    @Override
    public ListSavedSearchResponse listSavedSearches() throws ApiException {
        return listSavedSearchResponse;
    }

    @Override
    public SuccessResponse snoozeAlert(String identifier, SnoozeAlertRequest body, String identifierType) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    @Override
    public SuccessResponse unAcknowledgeAlert(String identifier, String identifierType, UnAcknowledgeAlertRequest body) throws ApiException {
        requestProcessor.processRequestV2(body);
        return genericSuccessResponse;
    }

    public void setGenericSuccessResponse(SuccessResponse genericSuccessResponse) {
        this.genericSuccessResponse = genericSuccessResponse;
    }

    public void setGenericSuccessResponseMock(SuccessResponseMock genericSuccessResponseMock) {
        this.genericSuccessResponseMock = genericSuccessResponseMock;
    }

    public void setAddSavedSearchResponse(AddSavedSearchResponse addSavedSearchResponse) {
        this.addSavedSearchResponse = addSavedSearchResponse;
    }

    public void setGetAlertResponse(GetAlertResponse getAlertResponse) {
        this.getAlertResponse = getAlertResponse;
    }

    public void setGetRequestStatusResponse(GetRequestStatusResponse getRequestStatusResponse) {
        this.getRequestStatusResponse = getRequestStatusResponse;
    }

    public void setGetSavedSearchResponse(GetSavedSearchResponse getSavedSearchResponse) {
        this.getSavedSearchResponse = getSavedSearchResponse;
    }

    public void setListAlertsResponse(ListAlertsResponse listAlertsResponse) {
        this.listAlertsResponse = listAlertsResponse;
    }

    public void setListAlertLogsResponse(ListAlertLogsResponse listAlertLogsResponse) {
        this.listAlertLogsResponse = listAlertLogsResponse;
    }

    public void setListAlertNotesResponse(ListAlertNotesResponse listAlertNotesResponse) {
        this.listAlertNotesResponse = listAlertNotesResponse;
    }

    public void setListAlertRecipientsResponse(ListAlertRecipientsResponse listAlertRecipientsResponse) {
        this.listAlertRecipientsResponse = listAlertRecipientsResponse;
    }

    public void setListSavedSearchResponse(ListSavedSearchResponse listSavedSearchResponse) {
        this.listSavedSearchResponse = listSavedSearchResponse;
    }
}
