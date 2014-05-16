package com.ifountain.client.http;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class JsonHttpClient {
    private Log log = LogFactory.getLog(JsonHttpClient.class);
    /**
     * Http client object *
     */
    private HttpClient httpClient;

    /**
     * Service endpoint uri.
     */
    private String rootUri;

    private String apiKey;

    /**
     * Constructs a new inner client to invoke service methods using the specified client configuration options.
     */
    public JsonHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected JsonResponse handleResponse(HttpResponse response) throws IOException, ClientException {
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return new JsonResponse(response.getContent());
        } else {
            String contentType = response.getHeaders().get(HttpHeaders.CONTENT_TYPE);
            if (contentType != null && contentType.toLowerCase().startsWith("application/json")) {
                Map error = JsonUtils.parse(response.getContent());
                throw new ClientException((String) error.get("error"), (Integer) error.get("code"));
            } else {
                throw new IOException(new String(response.getContent(), "UTF-8"));
            }
        }
    }

    public BaseResponse doPostRequest(BaseRequest request) throws IOException, ClientException, ParseException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        String uri = rootUri + request.getEndPoint();
        if(request.getApiKey() == null){
            request.setApiKey(getApiKey());
        }
        Map parameters = request.serialize();
        log.info("Executing request to ["+uri+"] with Parameters:"+parameters);
        HttpResponse httpResponse = httpClient.post(uri, JsonUtils.toJsonAsBytes(parameters), headers);
        JsonResponse jsonResponse = handleResponse(httpResponse);
        return populateResponse(request.createResponse(), jsonResponse);
    }
    public BaseResponse doPostRequest(BaseRequest request, MultipartEntity entity) throws IOException, ClientException, ParseException {
        String uri = rootUri + request.getEndPoint();
        if(request.getApiKey() == null){
            entity.addPart(ClientConstants.API.API_KEY, new StringBody(getApiKey(), "text/plain", Charset.forName("utf-8")));
        }
        log.info("Executing request to ["+uri+"] with multipart data");
        HttpResponse httpResponse = httpClient.post(uri, entity);
        JsonResponse jsonResponse = handleResponse(httpResponse);
        return populateResponse(request.createResponse(), jsonResponse);
    }

    public BaseResponse doDeleteRequest(BaseRequest request) throws ClientException, IOException, ParseException {
        try {
            String uri = rootUri + request.getEndPoint();
            if(request.getApiKey() == null){
                request.setApiKey(getApiKey());
            }
            Map parameters = request.serialize();
            log.info("Executing request to ["+uri+"] with Parameters:"+parameters);
            HttpResponse httpResponse = httpClient.delete(uri, parameters);
            JsonResponse jsonResponse = handleResponse(httpResponse);
            return populateResponse(request.createResponse(), jsonResponse);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }
    public BaseResponse doGetRequest(BaseRequest request) throws ClientException, IOException, ParseException {
        try {
            String uri = rootUri + request.getEndPoint();
            if(request.getApiKey() == null){
                request.setApiKey(getApiKey());
            }
            Map parameters = request.serialize();
            log.info("Executing request to ["+uri+"] with Parameters:"+parameters);
            HttpResponse httpResponse = httpClient.get(uri, parameters);
            JsonResponse jsonResponse = handleResponse(httpResponse);
            return populateResponse(request.createResponse(), jsonResponse);

        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    private BaseResponse populateResponse(BaseResponse response, JsonResponse jsonResponse) throws ParseException {
        response.deserialize(jsonResponse.getJson());
        response.setJson(new String(jsonResponse.getContent()));
        return response;
    }

    public String getRootUri() {
        return rootUri;
    }

    public void setRootUri(String rootUri) {
        this.rootUri = rootUri;
    }

    private HttpClient getHttpClient() {
        return httpClient;
    }

    private void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void close(){
        this.httpClient.close();
    }

    protected static class JsonResponse {
        byte[] content;
        Map json;

        public JsonResponse(byte[] content) throws IOException {
            this.content = content;
            json = JsonUtils.parse(content);
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public Map getJson() {
            return json;
        }

        public void setJson(Map json) {
            this.json = json;
        }

        public Map json(){
            return json;
        }
    }
}
