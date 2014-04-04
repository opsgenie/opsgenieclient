package com.ifountain.opsgenie.client.http;

import com.ifountain.opsgenie.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.util.ClientProxyConfiguration;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:30 AM
 */
public class OpsGenieHttpClient {
    private DefaultHttpClient httpClient;
    private ClientConfiguration config;
    private OpsgenieHttpClientRetryMechanism opsgenieHttpClientRetryMechanism;

    public OpsGenieHttpClient() {
        this(new ClientConfiguration());
    }

    public OpsGenieHttpClient(ClientConfiguration config) {
        this.config = config;
        opsgenieHttpClientRetryMechanism = new OpsgenieHttpClientRetryMechanism(config.getRetryHandler());
        createHttpClient();
    }

    public void close(){
        if(httpClient != null){
            httpClient.getConnectionManager().shutdown();
            httpClient = null;
        }
    }

    protected ClientConfiguration getConfig() {
        return config;
    }

    public OpsGenieHttpResponse post(String uri, Map<String, Object> parameters) throws IOException {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for(Map.Entry<String, Object> entry:parameters.entrySet()){
            formParams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
        return post(uri, entity);
    }

    public OpsGenieHttpResponse post(String uri, String content) throws IOException {
        return post(uri, content, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse post(String uri, byte[] content) throws IOException {
        return post(uri, content, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse post(String uri, HttpEntity entity) throws IOException {
        return post(uri, entity, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse post(String uri, String content, Map<String, String> headers) throws IOException {
        HttpPost postMethod = new HttpPost(uri);
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);
        postMethod.setEntity(entity);
        configureHeaders(postMethod, headers);
        return executeHttpMethod(postMethod);
    }

    public OpsGenieHttpResponse post(String uri, byte[] content, Map<String, String> headers) throws IOException {
        HttpPost postMethod = new HttpPost(uri);
        ByteArrayEntity entity = new ByteArrayEntity(content);
        entity.setChunked(true);
        postMethod.setEntity(entity);
        configureHeaders(postMethod, headers);
        return executeHttpMethod(postMethod);
    }

    public OpsGenieHttpResponse post(String uri, HttpEntity entity, Map<String, String> headers) throws IOException {
        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(entity);
        configureHeaders(postMethod, headers);
        return executeHttpMethod(postMethod);
    }

    public OpsGenieHttpResponse get(String uri, Map<String, Object> parameters) throws URISyntaxException, IOException {
        return get(uri, parameters, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse get(String uri, Map<String, Object> parameters, Map<String, String> headers) throws IOException, URISyntaxException {
        URI uriObj = new URI(uri);
        List<NameValuePair> optionsInQuery = URLEncodedUtils.parse(uriObj, null);
        List<NameValuePair> queryParams = getNameValuePairsFromMap(parameters);

        for (NameValuePair nvp : optionsInQuery) {
            if (!parameters.containsKey(nvp.getName())) {
                queryParams.add(nvp);
            }
        }

        URI newUri = URIUtils.createURI(uriObj.getScheme(), uriObj.getHost(), uriObj.getPort(), uriObj.getPath(), URLEncodedUtils.format(queryParams, "UTF-8"), uriObj.getFragment());
        HttpGet get = new HttpGet(newUri);
        configureHeaders(get, headers);
        return executeHttpMethod(get);
    }

    public OpsGenieHttpResponse delete(String uri, Map<String, Object> parameters) throws URISyntaxException, IOException {
        return delete(uri, parameters, new HashMap<String, String>());
    }

    public OpsGenieHttpResponse delete(String uri, Map<String, Object> parameters, Map<String, String> headers) throws IOException, URISyntaxException {
        URI uriObj = new URI(uri);
        List<NameValuePair> optionsInQuery = URLEncodedUtils.parse(uriObj, null);
        List<NameValuePair> queryParams = getNameValuePairsFromMap(parameters);

        for (NameValuePair nvp : optionsInQuery) {
            if (!parameters.containsKey(nvp.getName())) {
                queryParams.add(nvp);
            }
        }

        URI newUri = URIUtils.createURI(uriObj.getScheme(), uriObj.getHost(), uriObj.getPort(), uriObj.getPath(), URLEncodedUtils.format(queryParams, "UTF-8"), uriObj.getFragment());
        HttpDelete delete = new HttpDelete(newUri);
        configureHeaders(delete, headers);
        return executeHttpMethod(delete);
    }

    private List<NameValuePair> getNameValuePairsFromMap(Map<String, Object> params) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> o : params.entrySet()) {
            if (o.getValue() != null) {
                if (o.getValue() instanceof Collection) {
                    Collection col = (Collection) o.getValue();
                    for (Object content : col) {
                        formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(content)));
                    }
                } else if (o.getValue().getClass().isArray()) {
                    int length = Array.getLength(o.getValue());
                    for (int i = 0; i < length; i++) {
                        Object content = Array.get(o.getValue(), i);
                        formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(content)));
                    }
                } else {
                    formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(o.getValue())));
                }
            }
        }
        return formparams;
    }


    private OpsGenieHttpResponse executeHttpMethod(final HttpRequestBase method) throws IOException {
        RetryAction action = new RetryAction() {
            @Override
            public OpsGenieHttpResponse execute() throws IOException {
                method.reset();
                return httpClient.execute(method, new ResponseHandler<OpsGenieHttpResponse>() {
                    @Override
                    public OpsGenieHttpResponse handleResponse(HttpResponse httpResponse) throws IOException {
                        try {
                            byte[] content = EntityUtils.toByteArray(httpResponse.getEntity());
                            OpsGenieHttpResponse response = new OpsGenieHttpResponse();
                            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
                            response.setContent(content);
                            Header[] allHeaders = httpResponse.getAllHeaders();
                            for (Header header : allHeaders) {
                                response.addHeader(header.getName(), header.getValue());
                            }
                            return response;
                        } finally {
                            method.abort();
                        }
                    }
                });
            }
        };
        return opsgenieHttpClientRetryMechanism.execute(action);

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
        if(config.getMaxConnections() > 1){
            ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager();
            threadSafeClientConnManager.setDefaultMaxPerRoute(config.getMaxConnections());
            threadSafeClientConnManager.setMaxTotal(config.getMaxConnections());
            connectionManager = threadSafeClientConnManager;
        }
        else{
            connectionManager = new SingleClientConnManager();
        }
        httpClient = new DefaultHttpClient(connectionManager, httpClientParams);
        if(config.getCredentials() != null){
            httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, config.getCredentials());
        }
        try {
            SSLSocketFactory sf = createSocketFactory();
            List<Integer> httpsPorts = config.getHttpsPorts();
            if(httpsPorts == null){
                httpsPorts = Arrays.asList(443);
            }
            for(int httpsPort:httpsPorts){
                httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", httpsPort, sf));
            }

        } catch (Exception ignored) {
        }

        if(config.getRetryHandler() != null){
            httpClient.setHttpRequestRetryHandler(config.getRetryHandler());
        }
        if (config.getClientProxyConfiguration() != null) {
            String proxyHost = config.getClientProxyConfiguration().getProxyHost();
            int proxyPort = config.getClientProxyConfiguration().getProxyPort();

            HttpHost proxyHttpHost;
            if(config.getClientProxyConfiguration().getProxyProtocol() == null){
                proxyHttpHost = new HttpHost(proxyHost, proxyPort);
            }
            else{
                proxyHttpHost = new HttpHost(proxyHost, proxyPort, config.getClientProxyConfiguration().getProxyProtocol());
            }
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
            String proxyUsername = config.getClientProxyConfiguration().getProxyUsername();
            String proxyPassword = config.getClientProxyConfiguration().getProxyPassword();
            String proxyDomain = config.getClientProxyConfiguration().getProxyDomain();
            String proxyWorkstation = config.getClientProxyConfiguration().getProxyWorkstation();
            if ((proxyUsername != null) && (proxyPassword != null)) {
                if(config.getClientProxyConfiguration().getAuthType() == ClientProxyConfiguration.AuthType.NT){
                    httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new NTCredentials(proxyUsername, proxyPassword, proxyWorkstation, proxyDomain));
                }
                else if(config.getClientProxyConfiguration().getAuthType() == ClientProxyConfiguration.AuthType.BASIC){
                    httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
                }
            }
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

    private class OpsgenieHttpClientRetryMechanism {
        protected Logger logger = Logger.getLogger(OpsgenieHttpClientRetryMechanism.class);
        private OpsgenieRequestRetryHandler opsgenieRequestRetryHandler;
        public OpsgenieHttpClientRetryMechanism(OpsgenieRequestRetryHandler opsgenieRequestRetryHandler) {
            this.opsgenieRequestRetryHandler = opsgenieRequestRetryHandler;
        }

        public  OpsGenieHttpResponse execute(RetryAction retryAction) throws IOException {
            int retryCount = 1;
            while (true) {
                OpsGenieHttpResponse opsGenieHttpResponse = retryAction.execute();
                if(opsgenieRequestRetryHandler != null && opsgenieRequestRetryHandler.retryRequest(opsGenieHttpResponse, retryCount)){
                    logger.info("Retrying response. RetryCount " + retryCount);
                    try {
                        Thread.sleep(pauseExp(retryCount));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    retryCount++;
                }
                else {
                    return opsGenieHttpResponse;
                }
            }
        }


        private long pauseExp(int retryCount){
            return (long) (retryCount * 300);
        }
    }
    private interface RetryAction{
        public OpsGenieHttpResponse execute() throws IOException;
    }



}
