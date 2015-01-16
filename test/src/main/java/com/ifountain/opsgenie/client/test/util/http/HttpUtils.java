/**
 * Created on Apr 12, 2006
 *
 * Author Sezgin Kucukkaraaslan
 */
package com.ifountain.opsgenie.client.test.util.http;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EncodingUtils;
import org.jboss.netty.handler.codec.http.HttpHeaders;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {

    private DefaultHttpClient httpClient;
    private int timeout;
    private long lastRenewTime;
    private DefaultHttpClient oldHttpClient;
    private long renewDuration = 0;
    private static int INITIAL_BUFFER_SIZE = 4 * 1024;//4K
    private final Object HTTP_CLIENT_RENEW_LOCK = new Object();
    public static final int MAX_TOTAL_CONNECTIONS = 20; //default of apache PoolingClientConnectionManager
    public static final int DEFAULT_MAX_CONNECTION_PER_ROUTE = 2; //default of apache PoolingClientConnectionManager

    private int maxTotalConnections = MAX_TOTAL_CONNECTIONS;
    private int defaultMaxConnectionPerRoute = DEFAULT_MAX_CONNECTION_PER_ROUTE;


    public HttpUtils(int timeout) {
        init(timeout);
    }

    public HttpUtils(int timeout, int maxTotalConnections, int defaultMaxConnectionPerRoute) {
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxConnectionPerRoute = defaultMaxConnectionPerRoute;
        init(timeout);
    }


    public HttpUtils(int timeout, long renewDuration){
        if(renewDuration != 0 && renewDuration < TimeUnit.MINUTES.toMillis(10)){
            throw new RuntimeException("renew duration must be longer than 10 minutes");
        }
        init(timeout);
        this.renewDuration = renewDuration;
        lastRenewTime = SystemTime.currentTimeMillis();
    }

    private void init(int timeout){
        httpClient = createHttpClient(timeout);
        this.timeout = timeout;
    }

    public long getRenewDuration() {
        return renewDuration;
    }

    protected DefaultHttpClient createHttpClient(int timeout) {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = createSocketFactory();
            schemeRegistry.register(new Scheme("https", 443, sf));
        } catch (Exception ignored) {
        }

        PoolingClientConnectionManager conManager= new PoolingClientConnectionManager(schemeRegistry);
        conManager.setMaxTotal(maxTotalConnections);
        conManager.setDefaultMaxPerRoute(defaultMaxConnectionPerRoute);
        DefaultHttpClient client = new DefaultHttpClient(conManager);
        HttpParams params = client.getParams();
        HttpConnectionParams.setSoTimeout(params, timeout);
        HttpConnectionParams.setConnectionTimeout(params, timeout);
        addPreemptiveAuthInterceptor(client);
        return client;
    }

    public void destroy() {
        synchronized (HTTP_CLIENT_RENEW_LOCK) {
            httpClient.getConnectionManager().shutdown();
            httpClient = null;
            if (oldHttpClient != null)
                oldHttpClient.getConnectionManager().shutdown();
            oldHttpClient = null;
        }
    }

    public long getTimeout() {
        return HttpConnectionParams.getConnectionTimeout(httpClient.getParams());
    }

    //should only be used from tests
    protected HttpClient getClient() {
        return httpClient;
    }

    //should only be used from tests
    protected void setClientForTesting(DefaultHttpClient client){
        httpClient = client;
    }

    //should only be used from tests
    protected PoolingClientConnectionManager getConnectionManager() {
        return (PoolingClientConnectionManager) httpClient.getConnectionManager();
    }

    public String doPostRequest(String urlStr, Map params) throws HttpStatusException, IOException, URISyntaxException {
        return getHttpString(preparePostMethod(urlStr, params));
    }

    public String doPostRequest(String urlStr, String requestBody) throws HttpStatusException, IOException, URISyntaxException {
        return getHttpString(preparePostMethod(urlStr, requestBody));
    }

    public String doPostRequest(String urlStr, Map params, String requestBody) throws HttpStatusException, IOException, URISyntaxException {
        return getHttpString(preparePostMethod(urlStr, params, requestBody));
    }

    public HttpPost preparePostMethod(String urlStr, Map params) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(encodeUrlPath(urlStr));
        List<NameValuePair> formparams = getNameValuePairsFromMap(params);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        post.setEntity(entity);
        return post;
    }

    public static void setContentType(HttpUriRequest request, String contentType){
        request.addHeader(HttpHeaders.Names.CONTENT_TYPE, contentType);
    }

    public HttpPost preparePostMethod(String urlStr, String requestBody) throws UnsupportedEncodingException {
        return preparePostMethodWithBody(new HttpPost(encodeUrlPath(urlStr)), requestBody);
    }

    public HttpPost preparePostMethod(String urlStr, Map params, String requestBody) throws UnsupportedEncodingException, URISyntaxException {
        return preparePostMethodWithBody(new HttpPost(prepareUriWithParams(urlStr, params)), requestBody);
    }

    protected HttpPost preparePostMethodWithBody(HttpPost post, String requestBody) throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(requestBody, "UTF-8");
        entity.setChunked(true);
        post.setEntity(entity);
        return post;
    }

    protected HttpPut preparePutMethodWithBody(HttpPut put, String requestBody) throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(requestBody, "UTF-8");
        entity.setChunked(true);
        put.setEntity(entity);
        return put;
    }

    public HttpPut preparePutMethod(String urlStr, String requestBody) throws UnsupportedEncodingException {
        return preparePutMethodWithBody(new HttpPut(encodeUrlPath(urlStr)), requestBody);
    }

    public String doGetRequest(String urlStr, Map params) throws HttpStatusException, IOException, URISyntaxException {
        HttpGet get = prepareGetMethod(urlStr, params);
        return executeGetMethod(get);
    }

    public String doDeleteRequest(String urlStr, Map params) throws IOException, URISyntaxException {
        HttpDelete delete = prepareDeleteMethod(urlStr, params);
        return getHttpString(delete);
    }

    public BufferedImage getImage(String urlStr, Map params) throws HttpStatusException, IOException, URISyntaxException {
        HttpGet get = prepareGetMethod(urlStr, params);
        return executeGetImage(get);
    }

    public byte[] getBytes(String urlStr, Map params) throws HttpStatusException, IOException, URISyntaxException {
        HttpGet get = prepareGetMethod(urlStr, params);
        return executeHttpMethod(get).content;
    }

    public String doGetWithBasicAuth(String urlStr, String userName, String password, Map params) throws HttpStatusException, IOException, URISyntaxException {
        HttpGet get = prepareGetMethod(urlStr, params);
        return executeGetMethodWithBasicAuth(get, urlStr, userName, password);
    }

    public String doDeleteWithBasicAuth(String urlStr, String userName, String password, Map params) throws HttpStatusException, IOException, URISyntaxException {
        HttpDelete delete = prepareDeleteMethod(urlStr, params);
        return getHttpStringWithBasicAuth(delete, urlStr, userName, password);
    }

    public String doPostWithBasicAuth(String urlStr, String userName, String password, Map params) throws HttpStatusException, IOException, URISyntaxException {
        HttpPost post = preparePostMethod(urlStr, params);
        return executePostMethodWithBasicAuth(post, urlStr, userName, password);
    }

    public String doPostWithBasicAuth(String urlStr, String userName, String password, String requestBody) throws HttpStatusException, IOException, URISyntaxException {
        HttpPost post = preparePostMethod(urlStr, requestBody);
        return executePostMethodWithBasicAuth(post, urlStr, userName, password);
    }

    public String doPostWithBasicAuth(String urlStr, String userName, String password, String requestBody, boolean chunked, String contentTypeHeader) throws HttpStatusException, IOException, URISyntaxException {
        HttpPost post = preparePostMethod(urlStr, requestBody);
        ((StringEntity) post.getEntity()).setChunked(chunked);
        post.setHeader("Content-Type", contentTypeHeader);
        return executePostMethodWithBasicAuth(post, urlStr, userName, password);
    }

    public BufferedImage executeGetImage(HttpGet get) throws HttpStatusException, IOException, URISyntaxException {
        byte[] rowData = executeHttpMethod(get).content;
        if (rowData != null) {
            InputStream in = new ByteArrayInputStream(rowData);
            return javax.imageio.ImageIO.read(in);
        }
        return null;
    }

    public String executeGetMethod(HttpGet get) throws HttpStatusException, IOException, URISyntaxException {
        return getHttpString(get);
    }

    public String executeGetMethodWithBasicAuth(HttpGet get, String urlStr, String userName, String password) throws HttpStatusException, IOException, URISyntaxException {
        return getHttpStringWithBasicAuth(get, urlStr, userName, password);
    }

    public String executePostMethod(HttpPost post) throws HttpStatusException, IOException, URISyntaxException {
        return getHttpString(post);
    }

    public String executePostMethodWithBasicAuth(HttpPost post, String urlStr, String userName, String password) throws HttpStatusException, IOException, URISyntaxException {
        return getHttpStringWithBasicAuth(post, urlStr, userName, password);
    }

    public String executePutMethodWithBasicAuth(HttpPut put, String urlStr, String userName, String password) throws IOException, URISyntaxException {
        return getHttpStringWithBasicAuth(put, urlStr, userName, password);
    }

    public HttpGet prepareGetMethod(String urlStr, Map params) throws UnsupportedEncodingException, URISyntaxException {
        HttpGet get = new HttpGet(prepareUriWithParams(urlStr, params));
        return get;
    }

    protected URI prepareUriWithParams(String urlStr, Map params) throws UnsupportedEncodingException, URISyntaxException {
        URI uri = new URI(encodeUrlPath(urlStr));
        List<NameValuePair> optionsInQuery = URLEncodedUtils.parse(uri, null);
        List<NameValuePair> queryParams = getNameValuePairsFromMap(params);

        for (NameValuePair nvp : optionsInQuery) {
            if (!params.containsKey(nvp.getName())) {
                queryParams.add(nvp);
            }
        }

        URI newUri = URIUtils.createURI(uri.getScheme(), uri.getHost(), uri.getPort(), encodeUrlPath(uri.getPath()), URLEncodedUtils.format(queryParams, "UTF-8"), uri.getFragment());
        return newUri;
    }

    public static String encodeUrlPath(String path){
        return path.replaceAll(" ","%20");
    }

    public HttpDelete prepareDeleteMethod(String urlStr, Map params) throws UnsupportedEncodingException, URISyntaxException {
        HttpDelete delete = new HttpDelete(prepareUriWithParams(urlStr, params));
        return delete;
    }

    private List<NameValuePair> getNameValuePairsFromMap(Map<String, Object> params) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> o : params.entrySet()) {
            if(o.getValue() != null){
                if(o.getValue() instanceof Collection){
                    Collection col = (Collection) o.getValue();
                    for(Object content:col){
                        formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(content)));
                    }
                }
                else if(o.getValue().getClass().isArray()){
                    int length = Array.getLength(o.getValue());
                    for(int i=0; i < length; i++){
                        Object content = Array.get(o.getValue(), i);
                        formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(content)));
                    }
                }
                else{
                    formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(o.getValue())));
                }
            }

        }
        return formparams;
    }

    private String getHttpString(HttpRequestBase method) throws HttpStatusException, IOException, URISyntaxException {
        return _getHttpString(executeHttpMethod(method));
    }

    private String getHttpStringWithBasicAuth(HttpRequestBase method, String urlStr, String userName, String password) throws HttpStatusException, IOException, URISyntaxException {
        return _getHttpString(_executeHttpMethod(method, new AuthCredentials(urlStr, userName, password)));
    }

    private String _getHttpString(Response response) throws HttpStatusException, IOException, URISyntaxException {
        byte[] rawData = response.getContent();
        if (rawData != null) {
            return EncodingUtils.getString(rawData, "UTF-8");
        }
        return null;
    }

    private void addPreemptiveAuthInterceptor(DefaultHttpClient client) {
        HttpRequestInterceptor preemptiveAuth = new HttpRequestInterceptor() {
            public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
                AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
                CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute(ClientContext.CREDS_PROVIDER);
                HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);

                // If not auth scheme has been initialized yet
                if (authState.getAuthScheme() == null) {
                    AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
                    // Obtain credentials matching the target host
                    Credentials creds = credsProvider.getCredentials(authScope);
                    // If found, generate BasicScheme preemptively
                    if (creds != null) {
                        authState.update(new BasicScheme(), creds);
                    }
                }
            }

        };
        client.addRequestInterceptor(preemptiveAuth, 0);
    }


    public Response _executeHttpMethod(final HttpRequestBase method, AuthCredentials credentials) throws IOException, URISyntaxException {
        if(renewDuration > 0) {
            long currentTime = SystemTime.currentTimeMillis();
            long nextRenewTime = lastRenewTime + renewDuration;
            if (currentTime >= nextRenewTime) {
                synchronized (HTTP_CLIENT_RENEW_LOCK) {
                    /*
                    With a very rare chance this shutdown might be destroing an old client which is still in use,
                    This is hardly possible because renew is done min every 10 minutes
                    If this approach creates problems , we should put old connections to a list and destroy them if they are not in use by checking the connections in pool etc
                     */
                    if (oldHttpClient != null)
                        oldHttpClient.getConnectionManager().shutdown();

                    oldHttpClient = httpClient;
                    httpClient = createHttpClient(timeout);
                    lastRenewTime = currentTime;
                }
            }
        }

        // Execute the method.
        ResponseHandler<Response> handler = new ResponseHandler<Response>() {
            public Response handleResponse(
                    HttpResponse response) throws ClientProtocolException, IOException {
                try {
                    // Execute the method.
                    int statusCode = response.getStatusLine().getStatusCode();
                    byte[] content = null;
                    String contentTypeStr = null;
                    if(response.getEntity() != null){
                        InputStream instream = response.getEntity().getContent();
                        if (instream != null) {
                            long contentLength = response.getEntity().getContentLength();
                            if (contentLength > Integer.MAX_VALUE) { //guard below cast from overflow
                                throw new IOException("Content too large to be buffered: " + contentLength + " bytes");
                            }
                            ByteArrayOutputStream outstream = new ByteArrayOutputStream(contentLength > 0 ? (int) contentLength : INITIAL_BUFFER_SIZE);
                            byte[] buffer = new byte[4096];
                            int len;
                            while ((len = instream.read(buffer)) > 0) {
                                outstream.write(buffer, 0, len);
                            }
                            outstream.close();
                            content = outstream.toByteArray();
                        }
                        if (statusCode > 399) {
                            throw new HttpStatusException("ERROR: HttpServer returned the following status code <" + statusCode + "> for the url:\n " + method.getURI(), statusCode, content);
                        }
                        Header contentType = response.getEntity().getContentType();
                        contentTypeStr = contentType != null ? contentType.getValue() : null;
                    }
                    return new Response(statusCode, contentTypeStr, content, response.getAllHeaders());

                } finally {
                    // Release the connection. When keepalive is true(which is true by default), connection
                    //must be released to reuse it.
                    method.abort();
                }
            }
        };
        // save reference to the current httpClient so it won't be affected by change of httpClient in other thread
        DefaultHttpClient client = httpClient;
        if (credentials != null) {
            credentials.setCredentialsToClient(client);
        }

        return client.execute(method, handler);
    }

    public Response executeHttpMethod(final HttpRequestBase method) throws IOException, URISyntaxException {
        return _executeHttpMethod(method, null);
    }

    private class AuthCredentials{
        protected AuthScope authScope;
        protected Credentials credentials;

        AuthCredentials(String urlStr, String userName, String password) throws URISyntaxException {
            URI uri = new URI(encodeUrlPath(urlStr));
            this.authScope = new AuthScope(uri.getHost(), uri.getPort());
            this.credentials = new UsernamePasswordCredentials(userName, password);
        }

        public void setCredentialsToClient(DefaultHttpClient client){
            client.getCredentialsProvider().setCredentials(authScope, credentials);
        }
    }

    public class Response{
        int statusCode;
        String contentType;
        byte[]content;
        Header[] headers;

        public Header[] getHeaders() {
            return headers;
        }

        public void setHeaders(Header[] headers) {
            this.headers = headers;
        }

        public Response(int statusCode, String contentType, byte[] content, Header[] headers) {
            this.statusCode = statusCode;
            this.contentType = contentType;
            this.content = content;
            this.headers = headers;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }
    }

    private SSLSocketFactory createSocketFactory() throws Exception{
        SSLContext sslContext = SSLContext.getInstance("TLS");
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
}
