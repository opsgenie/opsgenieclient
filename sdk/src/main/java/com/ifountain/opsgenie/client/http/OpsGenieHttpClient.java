package com.ifountain.opsgenie.client.http;

import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.util.ClientProxyConfiguration;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.ifountain.opsgenie.client.util.UriUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:30 AM
 */
public class OpsGenieHttpClient {
    private DefaultHttpClient httpClient;
    private ClientConfiguration config;

    public OpsGenieHttpClient() {
        this(new ClientConfiguration());
    }

    public OpsGenieHttpClient(ClientConfiguration config) {
        this.config = config;
        createHttpClient();
    }

    public void close() {
        if (httpClient != null) {
            httpClient.getConnectionManager().shutdown();
            httpClient = null;
        }
    }

    protected ClientConfiguration getConfig() {
        return config;
    }

    public OpsGenieHttpResponse post(String uri, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            formParams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");

        return post(uri, entity);
    }

    public OpsGenieHttpResponse post(String uri, String content) throws IOException, OpsGenieClientException {
        return post(uri, content, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse post(String uri, byte[] content) throws IOException, OpsGenieClientException {
        return post(uri, content, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse post(String uri, HttpEntity entity) throws IOException, OpsGenieClientException {
        return post(uri, entity, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse post(String uri, String content, Map<String, String> headers) throws IOException, OpsGenieClientException {
        HttpPost postMethod = preparePostMethod(uri, content, headers);

        return executeHttpMethod(postMethod);
    }

    public OpsGenieHttpResponse post(String uri, byte[] content, Map<String, String> headers) throws IOException, OpsGenieClientException {
        HttpPost postMethod = preparePostMethod(uri, content, headers);

        return executeHttpMethod(postMethod);
    }

    public OpsGenieHttpResponse post(String uri, HttpEntity entity, Map<String, String> headers) throws IOException, OpsGenieClientException {
        HttpPost postMethod = preparePostMethod(uri, entity, headers);

        return executeHttpMethod(postMethod);
    }

    public OpsGenieHttpResponse post(String uri, HttpEntity entity, Map<String, String> headers, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        HttpPost postMethod = preparePostMethod(UriUtils.generateUriWithParams(uri, parameters), entity, headers);

        return executeHttpMethod(postMethod);
    }

    public OpsGenieHttpResponse post(String uri, String content, Map<String, String> headers, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        HttpPost postMethod = preparePostMethod(uri, content, headers, parameters);

        return executeHttpMethod(postMethod);
    }

    public HttpPost preparePostMethod(String uri, String content, Map<String, String> headers, Map<String, Object> parameters) throws UnsupportedEncodingException, OpsGenieClientException {
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);

        return preparePostMethod(UriUtils.generateUriWithParams(uri, parameters), entity, headers);
    }

    private HttpPost preparePostMethod(String uri, String content, Map<String, String> headers) throws UnsupportedEncodingException, OpsGenieClientException {
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);

        return preparePostMethod(uri, entity, headers);
    }

    private HttpPost preparePostMethod(String uri, byte[] content, Map<String, String> headers) throws OpsGenieClientException {
        ByteArrayEntity entity = new ByteArrayEntity(content);
        entity.setChunked(true);

        return preparePostMethod(uri, entity, headers);
    }

    private HttpPost preparePostMethod(String uri, HttpEntity entity, Map<String, String> headers) throws OpsGenieClientException {
        HttpPost postMethod = new HttpPost(UriUtils.generateUri(uri));
        postMethod.setEntity(entity);
        configureHeaders(postMethod, headers);

        return postMethod;
    }

    private HttpPost preparePostMethod(URI uri, HttpEntity entity, Map<String, String> headers) {
        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(entity);
        configureHeaders(postMethod, headers);

        return postMethod;
    }

    public OpsGenieHttpResponse put(String uri, Map<String, Object> contentMap, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        HttpPut putMethod = preparePutMethod(uri, contentMap, parameters);

        return executeHttpMethod(putMethod);
    }

    public OpsGenieHttpResponse put(String uri, String content, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        HttpPut putMethod = preparePutMethod(uri, content, parameters);

        return executeHttpMethod(putMethod);
    }


    public HttpPut preparePutMethod(String uri, Map<String, Object> contentMap, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        HttpPut putMethod = new HttpPut(UriUtils.generateUriWithParams(uri, parameters));
        StringEntity entity = new StringEntity(JsonUtils.toJson(contentMap), "UTF-8");
        entity.setChunked(true);
        putMethod.setEntity(entity);

        return putMethod;
    }

    public HttpPut preparePutMethod(String uri, String content, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        HttpPut putMethod = new HttpPut(UriUtils.generateUriWithParams(uri, parameters));
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);
        putMethod.setEntity(entity);

        return putMethod;
    }

    public OpsGenieHttpResponse patch(String uri, String content, Map<String, Object> parameters, Map<String, String> headers) throws IOException, OpsGenieClientException {
        HttpPatch patchMethod = preparePatchMethod(uri, content, parameters, headers);

        return executeHttpMethod(patchMethod);
    }

    public HttpPatch preparePatchMethod(String uri, String content, Map<String, Object> parameters, Map<String, String> headers) throws IOException, OpsGenieClientException {
        HttpPatch patchMethod = new HttpPatch(UriUtils.generateUriWithParams(uri, parameters));
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);
        patchMethod.setEntity(entity);
        configureHeaders(patchMethod, headers);

        return patchMethod;
    }

    public OpsGenieHttpResponse get(String uri, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        return get(uri, parameters, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse get(String uri, Map<String, Object> parameters, Map<String, String> headers) throws IOException, OpsGenieClientException {
        HttpGet get = new HttpGet(UriUtils.generateUriWithParams(uri, parameters));
        configureHeaders(get, headers);

        return executeHttpMethod(get);
    }

    public OpsGenieHttpResponse delete(String uri, Map<String, Object> parameters) throws IOException, OpsGenieClientException {
        HttpDelete delete = new HttpDelete(UriUtils.generateUriWithParams(uri, parameters));
        configureHeaders(delete, new HashMap<String, String>());

        return executeHttpMethod(delete);
    }

    public OpsGenieHttpResponse delete(String uri, Map<String, Object> parameters, Map<String, String> headers) throws IOException, OpsGenieClientException {
        HttpDelete delete = new HttpDelete(UriUtils.generateUriWithParams(uri, parameters));
        configureHeaders(delete, headers);

        return executeHttpMethod(delete);
    }

    public OpsGenieHttpResponse executeHttpMethod(final HttpRequestBase method) throws IOException {
        return new OpsgenieHttpClientRetryMechanism(method).execute();

    }

    private void configureHeaders(HttpRequestBase httpRequest, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if ((entry.getKey()).equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)) continue;
            if ((entry.getKey()).equalsIgnoreCase(HttpHeaders.HOST)) continue;
            httpRequest.addHeader(entry.getKey(), entry.getValue());
        }
        if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
            HttpEntityEnclosingRequestBase entityEnclosingRequest = (HttpEntityEnclosingRequestBase) httpRequest;
            if (entityEnclosingRequest.getEntity().getContentType() == null && (httpRequest.getHeaders(HttpHeaders.CONTENT_TYPE) == null || httpRequest.getHeaders(HttpHeaders.CONTENT_TYPE).length == 0)) {
                httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8".toLowerCase());
            }
        } else if (httpRequest.getHeaders(HttpHeaders.CONTENT_TYPE) == null || httpRequest.getHeaders(HttpHeaders.CONTENT_TYPE).length == 0) {
            httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8".toLowerCase());
        }
    }

    private void createHttpClient() {
        String userAgent = config.getUserAgent();

        HttpParams httpClientParams = new BasicHttpParams();
        HttpProtocolParams.setUserAgent(httpClientParams, userAgent);
        HttpConnectionParams.setConnectionTimeout(httpClientParams, config.getConnectionTimeout());
        HttpConnectionParams.setSoTimeout(httpClientParams, config.getSocketTimeout());
        HttpConnectionParams.setStaleCheckingEnabled(httpClientParams, true);
        HttpConnectionParams.setTcpNoDelay(httpClientParams, true);

        int socketSendBufferSizeHint = config.getSocketSendBufferSizeHint();
        int socketReceiveBufferSizeHint = config.getSocketReceiveBufferSizeHint();
        if ((socketSendBufferSizeHint > 0) || (socketReceiveBufferSizeHint > 0)) {
            HttpConnectionParams.setSocketBufferSize(httpClientParams, Math.max(socketSendBufferSizeHint, socketReceiveBufferSizeHint));
        }
        ClientConnectionManager connectionManager;
        if (config.getMaxConnections() > 1) {
            PoolingClientConnectionManager poolingClientConnectionManager = new PoolingClientConnectionManager();
            poolingClientConnectionManager.setDefaultMaxPerRoute(config.getMaxConnections());
            poolingClientConnectionManager.setMaxTotal(config.getMaxConnections());
            connectionManager = poolingClientConnectionManager;
        } else {
            connectionManager = new BasicClientConnectionManager();
        }
        httpClient = new DefaultHttpClient(connectionManager, httpClientParams);
        if (config.getCredentials() != null) {
            httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, config.getCredentials());
        }
        try {
            SSLSocketFactory sf = createSocketFactory();
            List<Integer> httpsPorts = config.getHttpsPorts();
            if (httpsPorts == null) {
                httpsPorts = Arrays.asList(443);
            }
            for (int httpsPort : httpsPorts) {
                httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", httpsPort, sf));
            }

        } catch (Exception ignored) {
        }

        if (config.getRetryHandler() != null) {
            httpClient.setHttpRequestRetryHandler(config.getRetryHandler());
        }
        if (config.getClientProxyConfiguration() != null) {
            String proxyHost = config.getClientProxyConfiguration().getProxyHost();
            int proxyPort = config.getClientProxyConfiguration().getProxyPort();

            HttpHost proxyHttpHost;
            if (config.getClientProxyConfiguration().getProxyProtocol() == null) {
                proxyHttpHost = new HttpHost(proxyHost, proxyPort);
            } else {
                proxyHttpHost = new HttpHost(proxyHost, proxyPort, config.getClientProxyConfiguration().getProxyProtocol());
            }
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
            String proxyUsername = config.getClientProxyConfiguration().getProxyUsername();
            String proxyPassword = config.getClientProxyConfiguration().getProxyPassword();
            String proxyDomain = config.getClientProxyConfiguration().getProxyDomain();
            String proxyWorkstation = config.getClientProxyConfiguration().getProxyWorkstation();
            if ((proxyUsername != null) && (proxyPassword != null)) {
                if (config.getClientProxyConfiguration().getAuthType() == ClientProxyConfiguration.AuthType.NT) {
                    httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new NTCredentials(proxyUsername, proxyPassword, proxyWorkstation, proxyDomain));
                } else if (config.getClientProxyConfiguration().getAuthType() == ClientProxyConfiguration.AuthType.BASIC) {
                    httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
                }
            }
        }
    }

    private SSLSocketFactory createSocketFactory() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

        TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sslContext.init(null, new TrustManager[]{tm}, null);
        return new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    private class OpsgenieHttpClientRetryMechanism {
        HttpRequestBase request;

        public OpsgenieHttpClientRetryMechanism(HttpRequestBase request) {
            this.request = request;
        }

        public OpsGenieHttpResponse execute() throws IOException {
            int retryCount = 1;
            while (true) {
                request.reset();
                OpsGenieHttpResponse opsGenieHttpResponse = httpClient.execute(request, new ResponseHandler<OpsGenieHttpResponse>() {
                    @Override
                    public OpsGenieHttpResponse handleResponse(HttpResponse httpResponse) throws IOException {
                        try {
                            OpsGenieHttpResponse response = new OpsGenieHttpResponse();
                            byte[] content;
                            if (httpResponse.getEntity() != null) {
                                content = EntityUtils.toByteArray(httpResponse.getEntity());
                                Header contentType = httpResponse.getEntity().getContentType();
                                String contentTypeStr = contentType != null ? contentType.getValue() : null;
                                response.setContentType(contentTypeStr);
                                response.setContent(content);
                            }
                            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
                            Header[] allHeaders = httpResponse.getAllHeaders();

                            for (Header header : allHeaders) {
                                response.addHeader(header.getName(), header.getValue());
                            }
                            return response;
                        } finally {
                            request.abort();
                        }
                    }
                });
                if (config.getRetryHandler() != null && config.getRetryHandler().retryRequest(request, opsGenieHttpResponse, retryCount)) {
                    retryCount++;
                } else {
                    return opsGenieHttpResponse;
                }
            }
        }
    }
}
