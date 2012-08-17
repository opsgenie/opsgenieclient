package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.util.Strings;
import com.ifountain.opsgenie.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @see com.ifountain.opsgenie.client.model.CreateAlertRequest
     * @see com.ifountain.opsgenie.client.model.CreateAlertResponse
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

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");

        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + createAlertRequest.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        Map resp = handleResponse(httpResponse);
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId((String) resp.get("alertId"));
        return response;
    }

    /**
     * Closes alerts at OpsGenie.
     *
     * @param closeAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.CloseAlertRequest
     * @see com.ifountain.opsgenie.client.model.CloseAlertResponse
     */
    @Override
    public CloseAlertResponse closeAlert(CloseAlertRequest closeAlertRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, closeAlertRequest.getCustomerKey());
        if (closeAlertRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, closeAlertRequest.getAlertId());
        if (closeAlertRequest.getAlias() != null)
            json.put(OpsGenieClientConstants.API.ALIAS, closeAlertRequest.getAlias());
        if (closeAlertRequest.getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, closeAlertRequest.getUser());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + closeAlertRequest.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        handleResponse(httpResponse);
        return new CloseAlertResponse();
    }

    /**
     * Retrieves specified alert details from OpsGenie.
     *
     * @param getAlertRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.opsgenie.client.model.GetAlertRequest
     * @see com.ifountain.opsgenie.client.model.GetAlertResponse
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
        response.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        response.setMessage((String) resp.get(OpsGenieClientConstants.API.MESSAGE));
        response.setAlias((String) resp.get(OpsGenieClientConstants.API.ALIAS));
        response.setDescription((String) resp.get(OpsGenieClientConstants.API.DESCRIPTION));
        response.setSource((String) resp.get(OpsGenieClientConstants.API.SOURCE));
        response.setEntity((String) resp.get(OpsGenieClientConstants.API.ENTITY));
        response.setStatus((String) resp.get(OpsGenieClientConstants.API.STATUS));
        response.setTags((List<String>) resp.get(OpsGenieClientConstants.API.TAGS));
        response.setActions((List<String>) resp.get(OpsGenieClientConstants.API.ACTIONS));
        response.setRecipients((List<String>) resp.get(OpsGenieClientConstants.API.RECIPIENTS));
        response.setDetails((Map<String, String>) resp.get(OpsGenieClientConstants.API.DETAILS));
        response.setCreatedAt(((Number) resp.get(OpsGenieClientConstants.API.CREATED_AT)).longValue());
        return response;
    }

    /**
     * Add notes to alerts in OpsGenie.
     *
     * @param addNoteRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.AddNoteRequest
     * @see com.ifountain.opsgenie.client.model.AddNoteResponse
     */
    @Override
    public AddNoteResponse addNote(AddNoteRequest addNoteRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addNoteRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.NOTE, addNoteRequest.getNote());
        if (addNoteRequest.getAlertId() != null)
            json.put(OpsGenieClientConstants.API.ALERT_ID, addNoteRequest.getAlertId());
        if (addNoteRequest.getAlias() != null) json.put(OpsGenieClientConstants.API.ALIAS, addNoteRequest.getAlias());
        if (addNoteRequest.getUser() != null) json.put(OpsGenieClientConstants.API.USER, addNoteRequest.getUser());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + addNoteRequest.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        handleResponse(httpResponse);
        return new AddNoteResponse();
    }

    /**
     * Attaches files to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.FileAttachRequest
     * @see com.ifountain.opsgenie.client.model.AttachResponse
     */
    @Override
    public AttachResponse attach(FileAttachRequest attachRequest) throws OpsGenieClientException, IOException {
        FileInputStream in = attachRequest.getFile() != null?new FileInputStream(attachRequest.getFile()):null;
        String fileName = attachRequest.getFile() != null?attachRequest.getFile().getName():null;
        return _attach(attachRequest, in, fileName);
    }

    /**
     * Attaches ${@link java.io.InputStream} content to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.InputStreamAttachRequest
     * @see com.ifountain.opsgenie.client.model.AttachResponse
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
     * @see com.ifountain.opsgenie.client.model.HeartbeatRequest
     * @see com.ifountain.opsgenie.client.model.HeartbeatResponse
     */
    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, heartbeatRequest.getCustomerKey());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + heartbeatRequest.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        Map resp = handleResponse(httpResponse);
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat(((Number) resp.get("heartbeat")).longValue());
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
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + attachRequest.getEndPoint(), entity);
        handleResponse(httpResponse);
        return new AttachResponse();
    }

    /*this is required to fix proxy authentication retry failure
    *caused by org.apache.http.client.NonRepeatableRequestException: Cannot retry request with a non-repeatable request entity
    */
    private byte[] convertInputStreamToByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int c = -1;
        while((c=in.read()) != -1){
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
