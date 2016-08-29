package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.InputStreamAttachRequest;
import com.ifountain.opsgenie.client.model.alert.*;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;

/**
 * Abstract Inner Client
 */
public class InnerAlertOpsGenieClient implements IAlertOpsGenieClient{
    private JsonOpsgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerAlertOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IAlertOpsGenieClient#createAlert(com.ifountain.opsgenie.client.model.alert.CreateAlertRequest)
     */
    @Override
    public CreateAlertResponse createAlert(CreateAlertRequest createAlertRequest) throws IOException, OpsGenieClientException, ParseException {
        return (CreateAlertResponse) httpClient.doPostRequest(createAlertRequest);
    }

    /**
     * @see IAlertOpsGenieClient#closeAlert(com.ifountain.opsgenie.client.model.alert.CloseAlertRequest)
     */
    @Override
    public CloseAlertResponse closeAlert(CloseAlertRequest closeAlertRequest) throws OpsGenieClientException, IOException, ParseException {
        return (CloseAlertResponse) httpClient.doPostRequest(closeAlertRequest);
    }

    /**
     * @see IAlertOpsGenieClient#deleteAlert(com.ifountain.opsgenie.client.model.alert.DeleteAlertRequest)
     */
    @Override
    public DeleteAlertResponse deleteAlert(DeleteAlertRequest deleteAlertRequest) throws OpsGenieClientException, IOException, ParseException {
        return (DeleteAlertResponse) httpClient.doDeleteRequest(deleteAlertRequest);
    }

    /**
     * @see IAlertOpsGenieClient#getAlert(com.ifountain.opsgenie.client.model.alert.GetAlertRequest)
     */
    @Override
    public GetAlertResponse getAlert(GetAlertRequest getAlertRequest) throws OpsGenieClientException, IOException, ParseException {
        return (GetAlertResponse) httpClient.doGetRequest(getAlertRequest);
    }

    /**
     * @see IAlertOpsGenieClient#listAlerts(com.ifountain.opsgenie.client.model.alert.ListAlertsRequest)
     */
    @Override
    public ListAlertsResponse listAlerts(ListAlertsRequest listAlertsRequest) throws OpsGenieClientException, IOException, ParseException {
        return (ListAlertsResponse) httpClient.doGetRequest(listAlertsRequest);
    }

    /**
     * @see IAlertOpsGenieClient#countAlerts(com.ifountain.opsgenie.client.model.alert.CountAlertsRequest)
     */
    @Override
    public CountAlertsResponse countAlerts(CountAlertsRequest countAlertsRequest) throws OpsGenieClientException, IOException, ParseException{
        return (CountAlertsResponse) httpClient.doGetRequest(countAlertsRequest);
    }

    /**
     * @see IAlertOpsGenieClient#addNote(com.ifountain.opsgenie.client.model.alert.AddNoteRequest)
     */
    @Override
    public AddNoteResponse addNote(AddNoteRequest addNoteRequest) throws OpsGenieClientException, IOException, ParseException {
        return (AddNoteResponse) httpClient.doPostRequest(addNoteRequest);
    }

    /**
     * @see IAlertOpsGenieClient#acknowledge(com.ifountain.opsgenie.client.model.alert.AcknowledgeRequest)
     */
    @Override
    public AcknowledgeResponse acknowledge(AcknowledgeRequest acknowledgeRequest) throws OpsGenieClientException, IOException, ParseException {
        return (AcknowledgeResponse) httpClient.doPostRequest(acknowledgeRequest);
    }

    /**
     * @see IAlertOpsGenieClient#renotify(com.ifountain.opsgenie.client.model.alert.RenotifyRequest)
     */
    @Override
    public RenotifyResponse renotify(RenotifyRequest renotifyRequest) throws OpsGenieClientException, IOException, ParseException {
        return (RenotifyResponse) httpClient.doPostRequest(renotifyRequest);
    }

    /**
     * @see IAlertOpsGenieClient#takeOwnership(com.ifountain.opsgenie.client.model.alert.TakeOwnershipRequest)
     */
    @Override
    public TakeOwnershipResponse takeOwnership(TakeOwnershipRequest takeOwnershipRequest) throws OpsGenieClientException, IOException, ParseException {
        return (TakeOwnershipResponse) httpClient.doPostRequest(takeOwnershipRequest);
    }

    /**
     * @see IAlertOpsGenieClient#assign(com.ifountain.opsgenie.client.model.alert.AssignRequest)
     */
    @Override
    public AssignResponse assign(AssignRequest assignRequest) throws OpsGenieClientException, IOException, ParseException {
        return (AssignResponse) httpClient.doPostRequest(assignRequest);
    }

    /**
     * @see IAlertOpsGenieClient#addTeam(com.ifountain.opsgenie.client.model.alert.AddAlertTeamRequest)
     */
    @Override
    public AddAlertTeamResponse addTeam(AddAlertTeamRequest addAlertTeamRequest) throws OpsGenieClientException, IOException, ParseException {
        return (AddAlertTeamResponse) httpClient.doPostRequest(addAlertTeamRequest);
    }

    /**
     * @see IAlertOpsGenieClient#addRecipient(com.ifountain.opsgenie.client.model.alert.AddRecipientRequest)
     */
    @Override
    public AddRecipientResponse addRecipient(AddRecipientRequest addRecipientRequest) throws OpsGenieClientException, IOException, ParseException {
        return (AddRecipientResponse) httpClient.doPostRequest(addRecipientRequest);
    }

    /**
     * @see IAlertOpsGenieClient#addTags(com.ifountain.opsgenie.client.model.alert.AddTagsRequest)
     */
    @Override
    public AddTagsResponse addTags(AddTagsRequest addTagsRequest) throws OpsGenieClientException, IOException, ParseException {
        return (AddTagsResponse) httpClient.doPostRequest(addTagsRequest);
    }

    /**
     * @see IAlertOpsGenieClient#removeTags(com.ifountain.opsgenie.client.model.alert.RemoveTagsRequest)
     */
    @Override
    public RemoveTagsResponse removeTags(RemoveTagsRequest removeTagsRequest) throws OpsGenieClientException, IOException, ParseException {
        return (RemoveTagsResponse) httpClient.doDeleteRequest(removeTagsRequest);
    }


    /**
     * @see IAlertOpsGenieClient#attach(com.ifountain.opsgenie.client.model.alert.FileAttachRequest)
     */
    @Override
    public AttachResponse attach(FileAttachRequest attachRequest) throws OpsGenieClientException, IOException, ParseException {
        FileInputStream in = attachRequest.getFile() != null ? new FileInputStream(attachRequest.getFile()) : null;
        String fileName = attachRequest.getFile() != null ? attachRequest.getFile().getName() : null;
        return _attach(attachRequest, in, fileName);
    }

    /**
     * @see IAlertOpsGenieClient#attach(com.ifountain.opsgenie.client.model.InputStreamAttachRequest)
     */
    @Override
    public AttachResponse attach(InputStreamAttachRequest attachRequest) throws OpsGenieClientException, IOException, ParseException {
        return _attach(attachRequest, attachRequest.getInputStream(), attachRequest.getFileName());
    }

    /**
     * @see IAlertOpsGenieClient#listAlertLogs(com.ifountain.opsgenie.client.model.alert.ListAlertLogsRequest)
     */
    @Override
    public ListAlertLogsResponse listAlertLogs(ListAlertLogsRequest listAlertLogsRequest) throws ParseException, OpsGenieClientException, IOException {
        return (ListAlertLogsResponse) httpClient.doGetRequest(listAlertLogsRequest);
    }

    /**
     * @see IAlertOpsGenieClient#listAlertRecipients(com.ifountain.opsgenie.client.model.alert.ListAlertRecipientsRequest)
     */
    @Override
    public ListAlertRecipientsResponse listAlertRecipients(ListAlertRecipientsRequest listAlertRecipientsRequest) throws ParseException, OpsGenieClientException, IOException {
        return (ListAlertRecipientsResponse) httpClient.doGetRequest(listAlertRecipientsRequest);
    }

    /**
     * @see IAlertOpsGenieClient#executeAlertAction(com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionRequest)
     */
    @Override
    public ExecuteAlertActionResponse executeAlertAction(ExecuteAlertActionRequest executeAlertActionRequest) throws OpsGenieClientException, IOException, ParseException {
        return (ExecuteAlertActionResponse) httpClient.doPostRequest(executeAlertActionRequest);
    }

    /**
     * @see IAlertOpsGenieClient#listAlertNotes(com.ifountain.opsgenie.client.model.alert.ListAlertNotesRequest)
     */
    @Override
    public ListAlertNotesResponse listAlertNotes(ListAlertNotesRequest listAlertNotesRequest) throws ParseException, OpsGenieClientException, IOException {
        return (ListAlertNotesResponse) httpClient.doGetRequest(listAlertNotesRequest);
    }
    
    /**
     * @see IAlertOpsGenieClient#addDetails(AddDetailsRequest))
     */
	@Override
	public AddDetailsResponse addDetails(AddDetailsRequest addDetailsRequest) throws OpsGenieClientException, IOException, ParseException {
		 return (AddDetailsResponse) httpClient.doPostRequest(addDetailsRequest);
	}
    
    /**
     * @see IAlertOpsGenieClient#removeDetails(RemoveDetailsRequest))
     */
	@Override
	public RemoveDetailsResponse removeDetails(RemoveDetailsRequest removeDetailsRequest) throws OpsGenieClientException, IOException, ParseException {
		 return (RemoveDetailsResponse) httpClient.doDeleteRequest(removeDetailsRequest);
	}
	
    private AttachResponse _attach(AttachRequest attachRequest, InputStream inputStream, String fileName) throws IOException, OpsGenieClientException, ParseException {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (inputStream != null && fileName != null)
            entity.addPart(OpsGenieClientConstants.API.ATTACHMENT, new ByteArrayBody(convertInputStreamToByteArray(inputStream), new File(fileName).getName()));
        if (attachRequest.getApiKey() != null)
            entity.addPart(OpsGenieClientConstants.API.API_KEY, new StringBody(attachRequest.getApiKey(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getId() != null)
            entity.addPart(OpsGenieClientConstants.API.ID, new StringBody(attachRequest.getId(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getAlias() != null)
            entity.addPart(OpsGenieClientConstants.API.ALIAS, new StringBody(attachRequest.getAlias(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getTinyId() != null)
            entity.addPart(OpsGenieClientConstants.API.TINY_ID, new StringBody(attachRequest.getTinyId(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getIndexFile() != null)
            entity.addPart(OpsGenieClientConstants.API.INDEX_FILE, new StringBody(attachRequest.getIndexFile(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getUser() != null)
            entity.addPart(OpsGenieClientConstants.API.USER, new StringBody(attachRequest.getUser(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getNote() != null)
            entity.addPart(OpsGenieClientConstants.API.NOTE, new StringBody(attachRequest.getNote(), "text/plain", Charset.forName("utf-8")));
        return (AttachResponse) httpClient.doPostRequest(attachRequest, entity);
    }

    /*this is required to fix proxy authentication retry failure
    *caused by org.apache.http.client.NonRepeatableRequestException: Cannot retry request with a non-repeatable request entity
    */
    protected byte[] convertInputStreamToByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int c = -1;
        while ((c = in.read()) != -1) {
            outputStream.write(c);
        }
        outputStream.flush();
        in.close();
        return outputStream.toByteArray();
    }


    

}
