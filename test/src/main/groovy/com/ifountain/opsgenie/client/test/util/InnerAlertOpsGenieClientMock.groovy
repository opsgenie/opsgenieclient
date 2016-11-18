package com.ifountain.opsgenie.client.test.util

import com.ifountain.opsgenie.client.IAlertOpsGenieClient
import com.ifountain.opsgenie.client.OpsGenieClientException
import com.ifountain.opsgenie.client.model.InputStreamAttachRequest
import com.ifountain.opsgenie.client.model.alert.*

import java.text.ParseException

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:11 AM
 */
public class InnerAlertOpsGenieClientMock implements IAlertOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private CreateAlertResponse createAlertResponse;
    private CloseAlertResponse closeAlertResponse;
    private DeleteAlertResponse deleteAlertResponse;
    private AddNoteResponse addNoteResponse;
    private AcknowledgeResponse acknowledgeResponse;
    private RenotifyResponse renotifyResponse;
    private TakeOwnershipResponse takeOwnershipResponse;
    private AssignResponse assignResponse;
    private AddRecipientResponse addRecipientResponse;
    private AddAlertTeamResponse addAlertTeamResponse;
    private AddTagsResponse addTagsResponse;
    private RemoveTagsResponse removeTagsResponse;
    private AttachResponse attachResponse;
    private ExecuteAlertActionResponse executeAlertActionResponse;
    private GetAlertResponse getAlertResponse;
    private ListAlertsResponse listAlertsResponse;
    private CountAlertsResponse countAlertsResponse;
    private ListAlertLogsResponse listAlertLogsResponse;
    private ListAlertRecipientsResponse listAlertRecipientsResponse;
    private ListAlertNotesResponse listAlertNotesResponse;
    private SnoozeResponse snoozeResponse;
    private AddDetailsResponse addDetailsResponse;
    private RemoveDetailsResponse removeDetailsResponse;
    private UnAcknowledgeResponse unAcknowledgeResponse;
    private EscalateToNextResponse escalateToNextResponse;

    public InnerAlertOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public CreateAlertResponse createAlert(CreateAlertRequest request) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(request);
        return createAlertResponse;
    }

    @Override
    public CloseAlertResponse closeAlert(CloseAlertRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return closeAlertResponse;
    }

    @Override
    public DeleteAlertResponse deleteAlert(DeleteAlertRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return deleteAlertResponse;
    }

    @Override
    public AddNoteResponse addNote(AddNoteRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return addNoteResponse;
    }

    @Override
    public AcknowledgeResponse acknowledge(AcknowledgeRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return acknowledgeResponse;
    }

    @Override
    public RenotifyResponse renotify(RenotifyRequest renotifyRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(renotifyRequest);
        return renotifyResponse;
    }

    @Override
    public TakeOwnershipResponse takeOwnership(TakeOwnershipRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return takeOwnershipResponse;
    }

    @Override
    public AssignResponse assign(AssignRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return assignResponse;
    }

    @Override
    public AddAlertTeamResponse addTeam(AddAlertTeamRequest request) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(request);
        return addAlertTeamResponse;
    }

    @Override
    public AddRecipientResponse addRecipient(AddRecipientRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return addRecipientResponse;
    }

    @Override
    AddTagsResponse addTags(AddTagsRequest request) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(request)
        return addTagsResponse
    }

    @Override
    RemoveTagsResponse removeTags(RemoveTagsRequest request) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(request)
        return removeTagsResponse
    }

    @Override
    public AttachResponse attach(FileAttachRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return attachResponse;
    }

    @Override
    public AttachResponse attach(InputStreamAttachRequest attachRequest) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(attachRequest);
        return attachResponse;
    }

    @Override
    public ExecuteAlertActionResponse executeAlertAction(ExecuteAlertActionRequest executeAlertActionRequest) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(executeAlertActionRequest);
        return executeAlertActionResponse;
    }

    @Override
    public GetAlertResponse getAlert(GetAlertRequest request) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(request);
        return getAlertResponse;
    }

    @Override
    public ListAlertLogsResponse listAlertLogs(ListAlertLogsRequest listAlertLogsRequest) throws ParseException, OpsGenieClientException, IOException {
        requestProcessor.processRequest(listAlertLogsRequest);
        return listAlertLogsResponse;
    }

    @Override
    public ListAlertRecipientsResponse listAlertRecipients(ListAlertRecipientsRequest listAlertRecipientsRequest) throws ParseException, OpsGenieClientException, IOException {
        requestProcessor.processRequest(listAlertRecipientsRequest);
        return listAlertRecipientsResponse;
    }

    @Override
    public ListAlertsResponse listAlerts(ListAlertsRequest listAlertsRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(listAlertsRequest);
        return listAlertsResponse;
    }

    @Override
    public CountAlertsResponse countAlerts(CountAlertsRequest countAlertsRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(countAlertsRequest);
        return countAlertsResponse;
    }

    @Override
    public ListAlertNotesResponse listAlertNotes(ListAlertNotesRequest listAlertNotesRequest) throws ParseException, OpsGenieClientException, IOException {
        requestProcessor.processRequest(listAlertNotesRequest);
        return listAlertNotesResponse;
    }

    @Override
    AddDetailsResponse addDetails(AddDetailsRequest addDetailsRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(addDetailsRequest);
        return addDetailsResponse;
    }

    @Override
    RemoveDetailsResponse removeDetails(RemoveDetailsRequest removeDetailsRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(removeDetailsRequest);
        return removeDetailsResponse;
    }

    @Override
    SnoozeResponse snooze(SnoozeRequest snoozeRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(snoozeRequest);
        return snoozeResponse;
    }

    @Override
    UnAcknowledgeResponse unAcknowledge(UnAcknowledgeRequest unAcknowledgeRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(unAcknowledgeRequest);
        return unAcknowledgeResponse;
    }

    @Override
    EscalateToNextResponse escalateToNext(EscalateToNextReqeust escalateToNextReqeust) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(escalateToNextReqeust);
        return escalateToNextResponse;
    }

    public void setCreateAlertResponse(CreateAlertResponse createAlertResponse) {
        this.createAlertResponse = createAlertResponse;
    }

    public void setCloseAlertResponse(CloseAlertResponse closeAlertResponse) {
        this.closeAlertResponse = closeAlertResponse;
    }

    public void setDeleteAlertResponse(DeleteAlertResponse deleteAlertResponse) {
        this.deleteAlertResponse = deleteAlertResponse;
    }

    public void setAddNoteResponse(AddNoteResponse addNoteResponse) {
        this.addNoteResponse = addNoteResponse;
    }

    public void setAttachResponse(AttachResponse attachResponse) {
        this.attachResponse = attachResponse;
    }

    public void setExecuteAlertActionResponse(ExecuteAlertActionResponse executeAlertActionResponse) {
        this.executeAlertActionResponse = executeAlertActionResponse;
    }

    public void setGetAlertResponse(GetAlertResponse getAlertResponse) {
        this.getAlertResponse = getAlertResponse;
    }

    public void setAcknowledgeResponse(AcknowledgeResponse acknowledgeResponse) {
        this.acknowledgeResponse = acknowledgeResponse;
    }

    public void setTakeOwnershipResponse(TakeOwnershipResponse takeOwnershipResponse) {
        this.takeOwnershipResponse = takeOwnershipResponse;
    }

    public void setAssignResponse(AssignResponse assignResponse) {
        this.assignResponse = assignResponse;
    }

    public void setAddRecipientResponse(AddRecipientResponse addRecipientResponse) {
        this.addRecipientResponse = addRecipientResponse;
    }

    public void setListAlertsResponse(ListAlertsResponse listAlertsResponse) {
        this.listAlertsResponse = listAlertsResponse;
    }

    public void setCountAlertsResponse(CountAlertsResponse countAlertsResponse) {
        this.countAlertsResponse = countAlertsResponse;
    }

    public void setListAlertRecipientsResponse(ListAlertRecipientsResponse listAlertRecipientsResponse) {
        this.listAlertRecipientsResponse = listAlertRecipientsResponse;
    }

    public void setRenotifyResponse(RenotifyResponse renotifyResponse) {
        this.renotifyResponse = renotifyResponse;
    }

    public void setListAlertLogsResponse(ListAlertLogsResponse listAlertLogsResponse) {
        this.listAlertLogsResponse = listAlertLogsResponse;
    }

    public void setListAlertNotesResponse(ListAlertNotesResponse listAlertNotesResponse) {
        this.listAlertNotesResponse = listAlertNotesResponse;
    }

    public void setAddAlertTeamResponse(AddAlertTeamResponse addAlertTeamResponse) {
        this.addAlertTeamResponse = addAlertTeamResponse;
    }

    public void setAddTagsResponse(AddTagsResponse addTagsResponse) {
        this.addTagsResponse = addTagsResponse;
    }

    public void setRemoveTagsResponse(RemoveTagsResponse removeTagsResponse) {
        this.removeTagsResponse = removeTagsResponse;
    }

    public void setSnoozeResponse(SnoozeResponse snoozeResponse) {
        this.snoozeResponse = snoozeResponse;
    }

    public void setAddDetailsResponse(AddDetailsResponse addDetailsResponse) {
        this.addDetailsResponse = addDetailsResponse;
    }

    public void setRemoveDetailsResponse(RemoveDetailsResponse removeDetailsResponse) {
        this.removeDetailsResponse = removeDetailsResponse;
    }

    public void setUnAcknowledgeResponse(UnAcknowledgeResponse unAcknowledgeResponse) {
        this.unAcknowledgeResponse = unAcknowledgeResponse;
    }

    public void setEscalateToNextResponse(EscalateToNextResponse escalateToNextResponse) {
        this.escalateToNextResponse = escalateToNextResponse;
    }
}
