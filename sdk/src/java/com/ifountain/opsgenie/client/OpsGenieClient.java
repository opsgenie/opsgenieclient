package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.beans.Alert;
import com.ifountain.opsgenie.client.model.beans.Forwarding;
import com.ifountain.opsgenie.client.model.customer.HeartbeatRequest;
import com.ifountain.opsgenie.client.model.customer.HeartbeatResponse;
import com.ifountain.opsgenie.client.model.user.forward.*;
import com.ifountain.opsgenie.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.ifountain.opsgenie.client.util.Strings;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Provides the client for accessing the OpsGenie web service.
 * <p/>
 * <p><code>OpsGenieClient</code> class provides the implementation APIs for OpsGenie operations like creating, closing and getting alerts,
 * adding comments, attaching files, etc. All service calls made using this client are blocking, and will not return until the service call completes.</p>
 * <p/>
 * <h4>Creating an Alerts</h4>
 * <p>Construct a <code>CreateAlertRequest</code> object with preferred options and call <code>createAlert</code> method on client.</p>
 * <p><blockquote><pre>
 * OpsGenieClient client = new OpsGenieClient();
 * CreateAlertRequest request = new CreateAlertRequest();
 * request.setCustomerKey("ab5454992-fabb2-4ba2-ad44f-1af65ds8b5c079");
 * request.setMessage("appserver1 down");
 * request.setDescription("cpu usage is over 60%");
 * request.setSource("nagios");
 * request.setEntity("appserver1");
 * request.setActions(Arrays.asList("acknowledge", "restart"));
 * request.setTags(Arrays.asList("network", "operations"));
 * request.setRecipients(Arrays.asList("john.smith@acme.com"));
 * CreateAlertResponse response = client.createAlert(request);
 * String alertId = response.getAlertId();
 * </pre></blockquote></p>
 * <h4>Adding Notes</h4>
 * <p>Construct a <code>AddNoteRequest</code> object with preferred options and call <code>addNote</code> method on client.</p>
 * <p><blockquote><pre>
 * OpsGenieClient client = new OpsGenieClient();
 * AddNoteRequest request = new AddNoteRequest();
 * request.setAlertId("29c4d6f6-0919-40ec-8d37-4ab2ed2042c8");
 * request.setCustomerKey("ab5454992-fabb2-4ba2-ad44f-1af65ds8b5c079");
 * request.setUser("john.smith@acme.com");
 * request.setNote("We should find another solution.");
 * AddNoteResponse response = client.addNote(request);
 * assert response.isSuccess();
 * </pre></blockquote></p>
 * <h4>Attaching Files</h4>
 * <p>Construct a <code>FileAttachRequest</code> object with preferred options and call <code>attach</code> method on client.</p>
 * <p><blockquote><pre>
 * OpsGenieClient client = new OpsGenieClient();
 * FileAttachRequest request = new FileAttachRequest();
 * request.setAlertId("29c4d6f6-0919-40ec-8d37-4ab2ed2042c8");
 * request.setCustomerKey("ab5454992-fabb2-4ba2-ad44f-1af65ds8b5c079");
 * request.setUser("john.smith@acme.com");
 * request.setFile(new File("/home/john/performanceGraphs.zip"));
 * AttachResponse response = client.attach(request);
 * assert response.isSuccess();
 * </pre></blockquote></p>
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:40 AM
 * @see IOpsGenieClient
 */
public class OpsGenieClient implements IOpsGenieClient {
    /**
     * Http client object *
     */
    private OpsGenieHttpClient httpClient;

    /**
     * OpsGenie services endpoint uri. Default is https://api.opsgenie.com *
     */
    private String rootUri = OpsGenieClientConstants.OPSGENIE_API_URI;

    /**
     * Constructs a new client to invoke service methods on OpsGenie using the specified client configuration options.
     */
    public OpsGenieClient(ClientConfiguration config) {
        httpClient = new OpsGenieHttpClient(config);
    }

    /**
     * Constructs a new client to invoke service methods on OpsGenie.
     */
    public OpsGenieClient() {
        httpClient = new OpsGenieHttpClient();
    }


    /**
     * Constructs a new client to invoke service methods on OpsGenie using the specified client.
     */
    public OpsGenieClient(OpsGenieHttpClient httpClient) {
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


        Map resp = doJsonPostRequest(createAlertRequest, json);
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId((String) resp.get("alertId"));
        response.setTook(((Number) resp.get("took")).longValue());
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
        Map resp = doJsonPostRequest(closeAlertRequest, json);
        CloseAlertResponse response = new CloseAlertResponse();
        response.setTook(((Number) resp.get("took")).longValue());
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
        OpsGenieHttpResponse httpResponse;
        try {
            httpResponse = httpClient.delete(rootUri + deleteAlertRequest.getEndPoint(), parameters);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
        Map resp = handleResponse(httpResponse);
        DeleteAlertResponse response = new DeleteAlertResponse();
        response.setTook(((Number) resp.get("took")).longValue());
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
        OpsGenieHttpResponse httpResponse;
        try {
            httpResponse = httpClient.get(rootUri + getAlertRequest.getEndPoint(), parameters);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
        Map resp = handleResponse(httpResponse);
        GetAlertResponse response = new GetAlertResponse();
        response.setJson(new String(httpResponse.getContent(), "utf-8"));
        Alert alert = new Alert();
        alert.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        alert.setMessage((String) resp.get(OpsGenieClientConstants.API.MESSAGE));
        alert.setAlias((String) resp.get(OpsGenieClientConstants.API.ALIAS));
        alert.setDescription((String) resp.get(OpsGenieClientConstants.API.DESCRIPTION));
        alert.setSource((String) resp.get(OpsGenieClientConstants.API.SOURCE));
        alert.setEntity((String) resp.get(OpsGenieClientConstants.API.ENTITY));
        alert.setStatus((String) resp.get(OpsGenieClientConstants.API.STATUS));
        alert.setOwner((String) resp.get(OpsGenieClientConstants.API.OWNER));
        alert.setSeen((Boolean) resp.get(OpsGenieClientConstants.API.IS_SEEN));
        alert.setAcknowledged((Boolean) resp.get(OpsGenieClientConstants.API.ACKNOWLEDGED));
        alert.setTags((List<String>) resp.get(OpsGenieClientConstants.API.TAGS));
        alert.setActions((List<String>) resp.get(OpsGenieClientConstants.API.ACTIONS));
        alert.setRecipients((List<String>) resp.get(OpsGenieClientConstants.API.RECIPIENTS));
        alert.setDetails((Map<String, String>) resp.get(OpsGenieClientConstants.API.DETAILS));
        alert.setCreatedAt(((Number) resp.get(OpsGenieClientConstants.API.CREATED_AT)).longValue());
        alert.setCount(((Number) resp.get(OpsGenieClientConstants.API.COUNT)).intValue());
        response.setTook(((Number) resp.get("took")).longValue());
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
        Map resp = doJsonPostRequest(addNoteRequest, json);
        AddNoteResponse response = new AddNoteResponse();
        response.setTook(((Number) resp.get("took")).longValue());
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
        Map resp = doJsonPostRequest(acknowledgeRequest, json);
        AcknowledgeResponse response = new AcknowledgeResponse();
        response.setTook(((Number) resp.get("took")).longValue());
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
        Map resp = doJsonPostRequest(takeOwnershipRequest, json);
        TakeOwnershipResponse response = new TakeOwnershipResponse();
        response.setTook(((Number) resp.get("took")).longValue());
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
        Map resp = doJsonPostRequest(assignRequest, json);
        AssignResponse response = new AssignResponse();
        response.setTook(((Number) resp.get("took")).longValue());
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
        Map resp = doJsonPostRequest(addRecipientRequest, json);
        AddRecipientResponse response = new AddRecipientResponse();
        response.setTook(((Number) resp.get("took")).longValue());
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
     * Sends heartbeat messages to OpsGenie. If heartbeat monitoring is enabled and OpsGenie does not get a heartbeat message within 10 minutes,
     * OpsGenie creates an alert to notify the specified people.
     *
     * @param heartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.HeartbeatRequest
     * @see com.ifountain.opsgenie.client.model.customer.HeartbeatResponse
     */
    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, heartbeatRequest.getCustomerKey());
        Map resp = doJsonPostRequest(heartbeatRequest, json);
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat(((Number) resp.get("heartbeat")).longValue());
        response.setTook(((Number) resp.get("took")).longValue());
        return response;
    }


    @Override
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, OpsGenieClientException {
        Map<String, String> json = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if(addForwardingRequest.getTimeZone() != null){
            sdf.setTimeZone(addForwardingRequest.getTimeZone());
            json.put(OpsGenieClientConstants.API.TIMEZONE, addForwardingRequest.getTimeZone().getID());
        }
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addForwardingRequest.getCustomerKey());
        if(addForwardingRequest.getEndDate() != null){
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(addForwardingRequest.getEndDate()));
        }
        if(addForwardingRequest.getStartDate() != null){
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(addForwardingRequest.getStartDate()));
        }
        json.put(OpsGenieClientConstants.API.FROM_USER, addForwardingRequest.getFromUser());
        json.put(OpsGenieClientConstants.API.TO_USER, addForwardingRequest.getToUser());
        json.put(OpsGenieClientConstants.API.ALIAS, addForwardingRequest.getAlias());
        Map resp = doJsonPostRequest(addForwardingRequest, json);
        AddForwardingResponse response = new AddForwardingResponse();
        response.setId((String)resp.get("id"));
        response.setTook(((Number) resp.get("took")).longValue());
        return response;
    }

    @Override
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, OpsGenieClientException {
        Map<String, String> json = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if(updateForwardingRequest.getTimeZone() != null){
            sdf.setTimeZone(updateForwardingRequest.getTimeZone());
            json.put(OpsGenieClientConstants.API.TIMEZONE, updateForwardingRequest.getTimeZone().getID());
        }
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, updateForwardingRequest.getCustomerKey());
        if(updateForwardingRequest.getEndDate() != null){
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(updateForwardingRequest.getEndDate()));
        }
        if(updateForwardingRequest.getStartDate() != null){
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(updateForwardingRequest.getStartDate()));
        }
        json.put(OpsGenieClientConstants.API.FROM_USER, updateForwardingRequest.getFromUser());
        json.put(OpsGenieClientConstants.API.TO_USER, updateForwardingRequest.getToUser());
        json.put(OpsGenieClientConstants.API.ALIAS, updateForwardingRequest.getAlias());
        json.put(OpsGenieClientConstants.API.ID, updateForwardingRequest.getId());
        Map resp = doJsonPostRequest(updateForwardingRequest, json);
        UpdateForwardingResponse response = new UpdateForwardingResponse();
        response.setId((String)resp.get("id"));
        response.setTook(((Number) resp.get("took")).longValue());
        return response;
    }

    @Override
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.ID, deleteForwardingRequest.getId());
        try {
            OpsGenieHttpResponse httpResponse = httpClient.delete(rootUri + deleteForwardingRequest.getEndPoint(), json);
            Map resp = handleResponse(httpResponse);
            DeleteForwardingResponse response = new DeleteForwardingResponse();
            response.setTook(((Number) resp.get("took")).longValue());
            return response;
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }

    }

    @Override
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.ID, getForwardingRequest.getId());
        parameters.put(OpsGenieClientConstants.API.ALIAS, getForwardingRequest.getAlias());

        try {
            OpsGenieHttpResponse httpResponse = httpClient.get(rootUri + getForwardingRequest.getEndPoint(), parameters);
            Map resp = handleResponse(httpResponse);
            SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
            if(resp.containsKey(OpsGenieClientConstants.API.TIMEZONE)){
                sdf.setTimeZone(TimeZone.getTimeZone((String) resp.get(OpsGenieClientConstants.API.TIMEZONE)));
            }

            GetForwardingResponse response = new GetForwardingResponse();
            response.setJson(new String(httpResponse.getContent(), "utf-8"));
            Forwarding forwarding = createForwardingFromParameters(resp, sdf);
            response.setTook(((Number) resp.get("took")).longValue());
            response.setForwarding(forwarding);
            return response;
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    private Forwarding createForwardingFromParameters(Map resp, SimpleDateFormat sdf) throws ParseException {
        Forwarding forwarding = new Forwarding();
        forwarding.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        forwarding.setAlias((String) resp.get(OpsGenieClientConstants.API.ALIAS));
        forwarding.setFromUser((String) resp.get(OpsGenieClientConstants.API.FROM_USER));
        forwarding.setToUser((String) resp.get(OpsGenieClientConstants.API.TO_USER));
        forwarding.setTimeZone(sdf.getTimeZone());
        forwarding.setStartDate(sdf.parse((String) resp.get(OpsGenieClientConstants.API.START_DATE)));
        forwarding.setEndDate(sdf.parse((String) resp.get(OpsGenieClientConstants.API.END_DATE)));
        return forwarding;
    }

    @Override
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.USER, listForwardingsRequest.getUser());

        try {
            OpsGenieHttpResponse httpResponse = httpClient.get(rootUri + listForwardingsRequest.getEndPoint(), parameters);
            Map resp = handleResponse(httpResponse);
            ListForwardingsResponse response = new ListForwardingsResponse();
            List<Forwarding> forwardings = new ArrayList<Forwarding>();
            if(resp.containsKey("forwardings")){
                List<Map> forwardingMaps = (List<Map>) resp.get("forwardings");
                for(Map forwardingMap:forwardingMaps){
                    SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
                    if(forwardingMap.containsKey(OpsGenieClientConstants.API.TIMEZONE)){
                        sdf.setTimeZone(TimeZone.getTimeZone((String) forwardingMap.get(OpsGenieClientConstants.API.TIMEZONE)));
                    }
                    Forwarding forwarding = createForwardingFromParameters(forwardingMap, sdf);
                    forwardings.add(forwarding);
                }
            }
            response.setJson(new String(httpResponse.getContent(), "utf-8"));
            response.setTook(((Number) resp.get("took")).longValue());
            response.setForwardings(forwardings);
            return response;
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
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
        Map resp = doJsonPostRequest(executeAlertActionRequest, json);
        ExecuteAlertActionResponse response = new ExecuteAlertActionResponse();
        response.setResult((String) resp.get("result"));
        response.setTook(((Number) resp.get("took")).longValue());
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
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + attachRequest.getEndPoint(), entity);
        Map resp = handleResponse(httpResponse);
        AttachResponse response = new AttachResponse();
        response.setTook(((Number) resp.get("took")).longValue());
        return response;
    }

    /*this is required to fix proxy authentication retry failure
    *caused by org.apache.http.client.NonRepeatableRequestException: Cannot retry request with a non-repeatable request entity
    */
    private byte[] convertInputStreamToByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int c = -1;
        while ((c = in.read()) != -1) {
            outputStream.write(c);
        }
        outputStream.flush();
        in.close();
        return outputStream.toByteArray();
    }

    private Map handleResponse(OpsGenieHttpResponse response) throws IOException, OpsGenieClientException {
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return JsonUtils.parse(response.getContent());
        } else {
            String contentType = response.getHeaders().get(HttpHeaders.CONTENT_TYPE);
            if (contentType != null && contentType.toLowerCase().startsWith("application/json")) {
                Map error = JsonUtils.parse(response.getContent());
                throw new OpsGenieClientException((String) error.get("error"), (Integer) error.get("code"));
            } else {
                throw new IOException(new String(response.getContent(), "UTF-8"));
            }
        }
    }

    private Map doJsonPostRequest(BaseRequest updateForwardingRequest, Map json) throws IOException, OpsGenieClientException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + updateForwardingRequest.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        return handleResponse(httpResponse);
    }

    /**
     * Set root endpoint uri that the client uses to send http requests. Default is https://api.opsgenie.com. Mostly used for testing.
     *
     * @param rootUri Uri to set.
     */
    @Override
    public void setRootUri(String rootUri) {
        this.rootUri = rootUri;
    }

    /**
     * Closes client
     */
    @Override
    public void close() {
        httpClient.close();
    }
}
