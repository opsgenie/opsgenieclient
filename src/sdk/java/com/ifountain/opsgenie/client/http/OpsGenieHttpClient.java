package com.ifountain.opsgenie.client.http;

import com.ifountain.opsgenie.client.util.ClientConfiguration;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 9:30 AM
 */
public class OpsGenieHttpClient {
    private HttpClient httpClient;
    private ClientConfiguration config;

    public OpsGenieHttpClient() {
        this(new ClientConfiguration());
    }

    public OpsGenieHttpClient(ClientConfiguration config) {
        this.config = config;
        createHttpClient();
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
        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager();
        connectionManager.setDefaultMaxPerRoute(config.getMaxConnections());
        connectionManager.setMaxTotal(config.getMaxConnections());
        httpClient = new DefaultHttpClient(connectionManager, httpClientParams);
        Scheme sch = new Scheme("https", 443, new TrustingSocketFactory());
        httpClient.getConnectionManager().getSchemeRegistry().register(sch);
        ((DefaultHttpClient) httpClient).setHttpRequestRetryHandler(config.getRetryHandler());
    }


    private static class TrustingSocketFactory implements SchemeSocketFactory, LayeredSchemeSocketFactory {
        private SSLContext sslcontext;

        private TrustingSocketFactory() {
            this.sslcontext = null;
        }

        private static SSLContext createSSLContext() throws IOException {
            try {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, new TrustManager[]{new TrustingX509TrustManager()}, null);
                return context;
            } catch (Exception e) {
                throw new IOException(e.getMessage());
            }
        }

        private SSLContext getSSLContext() throws IOException {
            if (this.sslcontext == null) this.sslcontext = createSSLContext();
            return this.sslcontext;
        }

        public Socket createSocket(HttpParams params) throws IOException {
            return getSSLContext().getSocketFactory().createSocket();
        }

        public Socket connectSocket(Socket sock, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params)
                throws IOException {
            int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
            int soTimeout = HttpConnectionParams.getSoTimeout(params);

            SSLSocket sslsock = (SSLSocket) ((sock != null) ? sock : createSocket(params));
            if (localAddress != null) sslsock.bind(localAddress);

            sslsock.connect(remoteAddress, connTimeout);
            sslsock.setSoTimeout(soTimeout);
            return sslsock;
        }

        public boolean isSecure(Socket sock) throws IllegalArgumentException {
            return true;
        }

        public Socket createLayeredSocket(Socket arg0, String arg1, int arg2, boolean arg3) throws IOException {
            return getSSLContext().getSocketFactory().createSocket();
        }
    }

    private static class TrustingX509TrustManager implements X509TrustManager {
        private static final X509Certificate[] X509_CERTIFICATES = new X509Certificate[0];

        public X509Certificate[] getAcceptedIssuers() {
            return X509_CERTIFICATES;
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    }
}
