package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.ifountain.opsgenie.client.util.Strings;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 9:40 AM
 */
public class OpsGenieClient implements IOpsGenieClient {
    private OpsGenieHttpClient httpClient;
    private String rootUri = OpsGenieClientConstants.OPSGENIE_API_URI;

    public OpsGenieClient(ClientConfiguration config) {
        httpClient = new OpsGenieHttpClient(config);
    }

    public OpsGenieClient() {
        httpClient = new OpsGenieHttpClient();
    }

    @Override
    public CreateAlertResponse createAlert(CreateAlertRequest request) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, request.getCustomerKey());
        json.put(OpsGenieClientConstants.API.MESSAGE, request.getMessage());
        if (request.getRecipients() != null)
            json.put(OpsGenieClientConstants.API.RECIPIENTS, Strings.join(request.getRecipients(), ","));
        if (request.getAlias() != null) json.put(OpsGenieClientConstants.API.ALIAS, request.getAlias());
        if (request.getSource() != null) json.put(OpsGenieClientConstants.API.SOURCE, request.getSource());
        if (request.getEntity() != null) json.put(OpsGenieClientConstants.API.ENTITY, request.getEntity());
        if (request.getNote() != null) json.put(OpsGenieClientConstants.API.NOTE, request.getNote());
        if (request.getUser() != null) json.put(OpsGenieClientConstants.API.USER, request.getUser());
        if (request.getDescription() != null)
            json.put(OpsGenieClientConstants.API.DESCRIPTION, request.getDescription());
        if (request.getTags() != null && request.getTags().size() > 0)
            json.put(OpsGenieClientConstants.API.TAGS, Strings.join(request.getTags(), ","));
        if (request.getActions() != null && request.getActions().size() > 0)
            json.put(OpsGenieClientConstants.API.ACTIONS, Strings.join(request.getActions(), ","));
        if (request.getDetails() != null && request.getDetails().size() > 0)
            json.put(OpsGenieClientConstants.API.DETAILS, request.getDetails());

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");

        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + request.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        Map resp = handleResponse(httpResponse);
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId((String) resp.get("alertId"));
        return response;
    }

    @Override
    public CloseAlertResponse closeAlert(CloseAlertRequest request) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, request.getCustomerKey());
        if (request.getAlertId() != null) json.put(OpsGenieClientConstants.API.ALERT_ID, request.getAlertId());
        if (request.getAlias() != null) json.put(OpsGenieClientConstants.API.ALIAS, request.getAlias());
        if (request.getUser() != null) json.put(OpsGenieClientConstants.API.USER, request.getUser());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + request.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        handleResponse(httpResponse);
        return new CloseAlertResponse();
    }

    @Override
    public AddNoteResponse addNote(AddNoteRequest request) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, request.getCustomerKey());
        json.put(OpsGenieClientConstants.API.NOTE, request.getNote());
        if (request.getAlertId() != null) json.put(OpsGenieClientConstants.API.ALERT_ID, request.getAlertId());
        if (request.getAlias() != null) json.put(OpsGenieClientConstants.API.ALIAS, request.getAlias());
        if (request.getUser() != null) json.put(OpsGenieClientConstants.API.USER, request.getUser());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + request.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        handleResponse(httpResponse);
        return new AddNoteResponse();
    }

    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest request) throws OpsGenieClientException, IOException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, request.getCustomerKey());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + request.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        Map resp = handleResponse(httpResponse);
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat(((Number) resp.get("heartbeat")).longValue());
        return response;
    }

    @Override
    public AttachResponse attach(AttachRequest request) throws OpsGenieClientException, IOException {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (request.getFile() != null)
            entity.addPart(OpsGenieClientConstants.API.ATTACHMENT, new FileBody(request.getFile()));
        if (request.getCustomerKey() != null)
            entity.addPart(OpsGenieClientConstants.API.CUSTOMER_KEY, new StringBody(request.getCustomerKey(), "text/plain", Charset.forName("utf-8")));
        if (request.getAlertId() != null)
            entity.addPart(OpsGenieClientConstants.API.ALERT_ID, new StringBody(request.getAlertId(), "text/plain", Charset.forName("utf-8")));
        if (request.getAlias() != null)
            entity.addPart(OpsGenieClientConstants.API.ALIAS, new StringBody(request.getAlias(), "text/plain", Charset.forName("utf-8")));
        if (request.getIndexFile() != null)
            entity.addPart(OpsGenieClientConstants.API.INDEX_FILE, new StringBody(request.getIndexFile(), "text/plain", Charset.forName("utf-8")));
        if (request.getUser() != null)
            entity.addPart(OpsGenieClientConstants.API.USER, new StringBody(request.getUser(), "text/plain", Charset.forName("utf-8")));
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + request.getEndPoint(), entity);
        handleResponse(httpResponse);
        return new AttachResponse();
    }

    @Override
    public GetAlertResponse getAlert(GetAlertRequest request) throws OpsGenieClientException, IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.CUSTOMER_KEY, request.getCustomerKey());
        if (request.getAlertId() != null) parameters.put(OpsGenieClientConstants.API.ALERT_ID, request.getAlertId());
        if (request.getAlias() != null) parameters.put(OpsGenieClientConstants.API.ALIAS, request.getAlias());
        OpsGenieHttpResponse httpResponse;
        try {
            httpResponse = httpClient.get(rootUri + request.getEndPoint(), parameters);
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
    @Override
    public void setRootUri(String rootUri) {
        this.rootUri = rootUri;
    }


}
