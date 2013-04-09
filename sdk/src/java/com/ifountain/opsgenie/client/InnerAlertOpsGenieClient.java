package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.InputStreamAttachRequest;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.beans.Alert;
import com.ifountain.opsgenie.client.util.Strings;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.ifountain.opsgenie.client.JsonOpgenieHttpClient.*;

/**
 * Abstract Inner Client
 */
public class InnerAlertOpsGenieClient implements IAlertOpsGenieClient{
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerAlertOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Creates alerts at OpsGenie.
     *
     * @param createAlertRequest Object to construct request parameters.
     * @return <code>CreateAlertResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.CreateAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.CreateAlertResponse
     */
    @Override
    public CreateAlertResponse createAlert(CreateAlertRequest createAlertRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, createAlertRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.MESSAGE, createAlertRequest.getMessage());
        if (createAlertRequest.getRecipients() != null)
            json.put(OpsGenieClientConstants.API.RECIPIENTS, Strings.join(createAlertRequest.getRecipients(), ","));
        if (createAlertRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, createAlertRequest.getAlias());
        if (createAlertRequest.getSource() != null)
            json.put(OpsGenieClientConstants.API.SOURCE, createAlertRequest.getSource());
        if (createAlertRequest.getEntity() != null)
            json.put(OpsGenieClientConstants.API.ENTITY, createAlertRequest.getEntity());
        if (createAlertRequest.getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, createAlertRequest.getNote());
        if (createAlertRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, createAlertRequest.getUser());
        if (createAlertRequest.getDescription() != null)
            json.put(OpsGenieClientConstants.API.DESCRIPTION, createAlertRequest.getDescription());
        if (createAlertRequest.getTags() != null && createAlertRequest.getTags().size() > 0)
            json.put(OpsGenieClientConstants.API.TAGS, Strings.join(createAlertRequest.getTags(), ","));
        if (createAlertRequest.getActions() != null && createAlertRequest.getActions().size() > 0)
            json.put(OpsGenieClientConstants.API.ACTIONS, Strings.join(createAlertRequest.getActions(), ","));
        if (createAlertRequest.getDetails() != null && createAlertRequest.getDetails().size() > 0)
            json.put(OpsGenieClientConstants.API.DETAILS, createAlertRequest.getDetails());


        OpsGenieJsonResponse resp = httpClient.doPostRequest(createAlertRequest, json);
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId((String) resp.json().get("alertId"));
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    /**
     * Closes alerts at OpsGenie.
     *
     * @param closeAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.CloseAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.CloseAlertResponse
     */
    @Override
    public CloseAlertResponse closeAlert(CloseAlertRequest closeAlertRequest) throws OpsGenieClientException, IOException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, closeAlertRequest.getCustomerKey());
        if (closeAlertRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, closeAlertRequest.getAlertId());
        if (closeAlertRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, closeAlertRequest.getAlias());
        if (closeAlertRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, closeAlertRequest.getUser());
        if (closeAlertRequest.getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, closeAlertRequest.getNote());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(closeAlertRequest, json);
        CloseAlertResponse response = new CloseAlertResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    /**
     * Deletes alerts at OpsGenie.
     *
     * @param deleteAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.DeleteAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.DeleteAlertResponse
     */
    @Override
    public DeleteAlertResponse deleteAlert(DeleteAlertRequest deleteAlertRequest) throws OpsGenieClientException, IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.CUSTOMER_KEY, deleteAlertRequest.getCustomerKey());
        if (deleteAlertRequest.getAlertId() != null)
            parameters.put(OpsGenieClientConstants.API.ALERT_ID, deleteAlertRequest.getAlertId());
        if (deleteAlertRequest.getAlias() != null)
            parameters.put(OpsGenieClientConstants.API.ALIAS, deleteAlertRequest.getAlias());
        if (deleteAlertRequest.getUser() != null)
            parameters.put(OpsGenieClientConstants.API.USER, deleteAlertRequest.getUser());
        OpsGenieJsonResponse resp = httpClient.doDeleteRequest(deleteAlertRequest, parameters);
        DeleteAlertResponse response = new DeleteAlertResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    /**
     * Retrieves specified alert details from OpsGenie.
     *
     * @param getAlertRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.opsgenie.client.model.alert.GetAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.GetAlertResponse
     */
    @Override
    public GetAlertResponse getAlert(GetAlertRequest getAlertRequest) throws OpsGenieClientException, IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.CUSTOMER_KEY, getAlertRequest.getCustomerKey());
        if (getAlertRequest.getAlertId() != null)
            parameters.put(OpsGenieClientConstants.API.ALERT_ID, getAlertRequest.getAlertId());
        if (getAlertRequest.getAlias() != null)
            parameters.put(OpsGenieClientConstants.API.ALIAS, getAlertRequest.getAlias());
        OpsGenieJsonResponse resp = httpClient.doGetRequest(getAlertRequest, parameters);
        GetAlertResponse response = new GetAlertResponse();
        response.setJson(new String(resp.getContent(), "utf-8"));
        Alert alert = new Alert();
        alert.setId((String) resp.json().get(OpsGenieClientConstants.API.ID));
        alert.setMessage((String) resp.json().get(OpsGenieClientConstants.API.MESSAGE));
        alert.setAlias((String) resp.json().get(OpsGenieClientConstants.API.ALIAS));
        alert.setDescription((String) resp.json().get(OpsGenieClientConstants.API.DESCRIPTION));
        alert.setSource((String) resp.json().get(OpsGenieClientConstants.API.SOURCE));
        alert.setEntity((String) resp.json().get(OpsGenieClientConstants.API.ENTITY));
        alert.setStatus((String) resp.json().get(OpsGenieClientConstants.API.STATUS));
        alert.setOwner((String) resp.json().get(OpsGenieClientConstants.API.OWNER));
        alert.setTinyId((String) resp.json().get(OpsGenieClientConstants.API.TINY_ID));
        alert.setSeen((Boolean) resp.json().get(OpsGenieClientConstants.API.IS_SEEN));
        alert.setAcknowledged((Boolean) resp.json().get(OpsGenieClientConstants.API.ACKNOWLEDGED));
        alert.setTags((List<String>) resp.json().get(OpsGenieClientConstants.API.TAGS));
        alert.setActions((List<String>) resp.json().get(OpsGenieClientConstants.API.ACTIONS));
        alert.setRecipients((List<String>) resp.json().get(OpsGenieClientConstants.API.RECIPIENTS));
        alert.setDetails((Map<String, String>) resp.json().get(OpsGenieClientConstants.API.DETAILS));
        alert.setCreatedAt(((Number) resp.json().get(OpsGenieClientConstants.API.CREATED_AT)).longValue());
        alert.setCount(((Number) resp.json().get(OpsGenieClientConstants.API.COUNT)).intValue());
        response.setTook(((Number) resp.json().get("took")).longValue());
        response.setAlert(alert);
        return response;
    }

    /**
     * Add notes to alerts in OpsGenie.
     *
     * @param addNoteRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddNoteRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddNoteResponse
     */
    @Override
    public AddNoteResponse addNote(AddNoteRequest addNoteRequest) throws OpsGenieClientException, IOException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addNoteRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.NOTE, addNoteRequest.getNote());
        if (addNoteRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, addNoteRequest.getAlertId());
        if (addNoteRequest.getAlias() != null) json.put(OpsGenieClientConstants.API.ALIAS, addNoteRequest.getAlias());
        if (addNoteRequest.getUser() != null) json.put(OpsGenieClientConstants.API.USER, addNoteRequest.getUser());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(addNoteRequest, json);
        AddNoteResponse response = new AddNoteResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    /**
     * Acknowledges alerts in OpsGenie.
     *
     * @param acknowledgeRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AcknowledgeRequest
     * @see com.ifountain.opsgenie.client.model.alert.AcknowledgeResponse
     */
    @Override
    public AcknowledgeResponse acknowledge(AcknowledgeRequest acknowledgeRequest) throws OpsGenieClientException, IOException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, acknowledgeRequest.getCustomerKey());
        if (acknowledgeRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, acknowledgeRequest.getAlertId());
        if (acknowledgeRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, acknowledgeRequest.getAlias());
        if (acknowledgeRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, acknowledgeRequest.getUser());
        if (acknowledgeRequest.getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, acknowledgeRequest.getNote());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(acknowledgeRequest, json);
        AcknowledgeResponse response = new AcknowledgeResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    /**
     * Takes the ownership of an alert.
     *
     * @param takeOwnershipRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.TakeOwnershipRequest
     * @see com.ifountain.opsgenie.client.model.alert.TakeOwnershipResponse
     */
    @Override
    public TakeOwnershipResponse takeOwnership(TakeOwnershipRequest takeOwnershipRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, takeOwnershipRequest.getCustomerKey());
        if (takeOwnershipRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, takeOwnershipRequest.getAlertId());
        if (takeOwnershipRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, takeOwnershipRequest.getAlias());
        if (takeOwnershipRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, takeOwnershipRequest.getUser());
        if (takeOwnershipRequest.getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, takeOwnershipRequest.getNote());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(takeOwnershipRequest, json);
        TakeOwnershipResponse response = new TakeOwnershipResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    /**
     * Assigns the ownership of an alert to the specified user.
     *
     * @param assignRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AssignRequest
     * @see com.ifountain.opsgenie.client.model.alert.AssignResponse
     */
    @Override
    public AssignResponse assign(AssignRequest assignRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, assignRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.OWNER, assignRequest.getOwner());
        if (assignRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, assignRequest.getAlertId());
        if (assignRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, assignRequest.getAlias());
        if (assignRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, assignRequest.getUser());
        if (assignRequest.getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, assignRequest.getNote());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(assignRequest, json);
        AssignResponse response = new AssignResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    /**
     * Adds a new recipient to an alert.
     *
     * @param addRecipientRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientResponse
     */
    @Override
    public AddRecipientResponse addRecipient(AddRecipientRequest addRecipientRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addRecipientRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.RECIPIENT, addRecipientRequest.getRecipient());
        if (addRecipientRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, addRecipientRequest.getAlertId());
        if (addRecipientRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, addRecipientRequest.getAlias());
        if (addRecipientRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, addRecipientRequest.getUser());
        if (addRecipientRequest.getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, addRecipientRequest.getNote());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(addRecipientRequest, json);
        AddRecipientResponse response = new AddRecipientResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }



    /**
     * Attaches files to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.FileAttachRequest
     * @see com.ifountain.opsgenie.client.model.alert.AttachResponse
     */
    @Override
    public AttachResponse attach(FileAttachRequest attachRequest) throws OpsGenieClientException, IOException {
        FileInputStream in = attachRequest.getFile() != null ? new FileInputStream(attachRequest.getFile()) : null;
        String fileName = attachRequest.getFile() != null ? attachRequest.getFile().getName() : null;
        return _attach(attachRequest, in, fileName);
    }

    /**
     * Attaches ${@link java.io.InputStream} content to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.InputStreamAttachRequest
     * @see com.ifountain.opsgenie.client.model.alert.AttachResponse
     */
    @Override
    public AttachResponse attach(InputStreamAttachRequest attachRequest) throws OpsGenieClientException, IOException {
        return _attach(attachRequest, attachRequest.getInputStream(), attachRequest.getFileName());
    }


    /**
     * Executes actions on alerts in OpsGenie.
     *
     * @param executeAlertActionRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionRequest
     * @see com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionResponse
     */
    @Override
    public ExecuteAlertActionResponse executeAlertAction(ExecuteAlertActionRequest executeAlertActionRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, executeAlertActionRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.ACTION, executeAlertActionRequest.getAction());
        if (executeAlertActionRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, executeAlertActionRequest.getAlertId());
        if (executeAlertActionRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, executeAlertActionRequest.getAlias());
        if (executeAlertActionRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, executeAlertActionRequest.getUser());
        if (executeAlertActionRequest.getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, executeAlertActionRequest.getNote());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(executeAlertActionRequest, json);
        ExecuteAlertActionResponse response = new ExecuteAlertActionResponse();
        response.setResult((String) resp.json().get("result"));
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
    }

    private AttachResponse _attach(AttachRequest attachRequest, InputStream inputStream, String fileName) throws IOException, OpsGenieClientException {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (inputStream != null && fileName != null)
            entity.addPart(OpsGenieClientConstants.API.ATTACHMENT, new ByteArrayBody(convertInputStreamToByteArray(inputStream), new File(fileName).getName()));
        if (attachRequest.getCustomerKey() != null)
            entity.addPart(OpsGenieClientConstants.API.CUSTOMER_KEY, new StringBody(attachRequest.getCustomerKey(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getAlertId() != null)
            entity.addPart(OpsGenieClientConstants.API.ALERT_ID, new StringBody(attachRequest.getAlertId(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getAlias() != null)
            entity.addPart(OpsGenieClientConstants.API.ALIAS, new StringBody(attachRequest.getAlias(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getIndexFile() != null)
            entity.addPart(OpsGenieClientConstants.API.INDEX_FILE, new StringBody(attachRequest.getIndexFile(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getUser() != null)
            entity.addPart(OpsGenieClientConstants.API.USER, new StringBody(attachRequest.getUser(), "text/plain", Charset.forName("utf-8")));
        if (attachRequest.getNote() != null)
            entity.addPart(OpsGenieClientConstants.API.NOTE, new StringBody(attachRequest.getNote(), "text/plain", Charset.forName("utf-8")));
        OpsGenieJsonResponse resp = httpClient.doPostRequest(attachRequest, entity);
        AttachResponse response = new AttachResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        return response;
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
