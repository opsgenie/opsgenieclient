package com.ifountain.opsgenie.client.marid.http

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestServer
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.util.ClientProxyConfiguration
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.FileUpload
import org.apache.commons.fileupload.RequestContext
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.http.HttpHeaders
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.auth.AuthScope
import org.apache.http.auth.NTCredentials
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.client.utils.URIUtils
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.conn.params.ConnRoutePNames
import org.apache.http.entity.StringEntity
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.InputStreamBody
import org.apache.http.entity.mime.content.StringBody
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.jboss.netty.handler.codec.http.HttpMethod
import com.ifountain.opsgenie.client.test.util.MaridTestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

/**
 * @author Sezgin Kucukkaraaslan
 * @version 7/30/12 10:17 AM
 */
class HttpProxyTest extends MaridTestCase implements HttpTestRequestListener {
    static def host
    static def port;
    static def proxyPort;
    static def proxyHost;
    static def httpServer;
    static def receivedRequests = [];
    DefaultHttpClient httpClient;
    DefaultHttpClient httpClientWithProxy;
    HttpProxy proxy;
    static def url;
    HttpProxyConfig httpProxyConfig;

    @Override
    public static void beforeClass() {
        MaridTestCase.beforeClass()
        host = CommonTestUtils.getLocalhostIp()
        port = CommonTestUtils.getLocalPort()
        proxyPort = CommonTestUtils.getLocalPort() + 1
        proxyHost = CommonTestUtils.getLocalhostIp()
        url = "http://${host}:${port}/dummy"
    }

    @Before
    public void setUp() {
        httpServer = new HttpTestServer(host, port)
        httpServer.addRequestListener(this)
        httpServer.open()
        httpProxyConfig = new HttpProxyConfig(proxyPort)
        httpProxyConfig.setHost(host);
        receivedRequests.clear()
        httpClient = new DefaultHttpClient();
        httpClientWithProxy = new DefaultHttpClient();
        HttpHost proxyHost = new HttpHost(proxyHost, proxyPort);
        httpClientWithProxy.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);

    }

    @After
    public void tearDown() {
        httpClient.getConnectionManager().shutdown();
        httpClientWithProxy.getConnectionManager().shutdown();
        if (proxy) proxy.stop();
        httpServer.close()
        super.tearDown()
    }

    @Test
    public void testGetRequestThroughProxy() throws Exception {
        def responseToReturn = "success";
        httpServer.setResponseToReturn(responseToReturn);

        proxy = new HttpProxy(httpProxyConfig);
        proxy.start();

        Thread.sleep(300)
        def params = [param1: "value1", param2: "value2"]

        def originalResponse = doGet(httpClient, params)
        def proxyResponse = doGet(httpClientWithProxy, params)

        assertEquals(responseToReturn, originalResponse)
        assertEquals(originalResponse, proxyResponse)

        assertEquals(2, receivedRequests.size());
        HttpTestRequest originalRequest = receivedRequests[0]
        assertEquals("/dummy", originalRequest.getUrl())
        assertEquals(HttpMethod.GET.getName(), originalRequest.getMethod())
        def requestParams = originalRequest.getParameters();
        assertEquals(2, requestParams.size())
        assertEquals("value1", requestParams.param1)
        assertEquals("value2", requestParams.param2)

        HttpTestRequest proxyRequest = receivedRequests[1]
        assertEquals("/dummy", proxyRequest.getUrl())
        assertEquals(HttpMethod.GET.getName(), proxyRequest.getMethod())
        requestParams = proxyRequest.getParameters();
        assertEquals(2, requestParams.size())
        assertEquals("value1", requestParams.param1)
        assertEquals("value2", requestParams.param2)
    }

    @Test
    public void testGetRequestThroughChainProxy() throws Exception {
        def responseToReturn = "success";
        httpServer.setResponseToReturn(responseToReturn);

        HttpProxyConfig externalProxyConf = new HttpProxyConfig(proxyPort + 10);
        externalProxyConf.setHost(proxyHost);
        HttpProxy externalProxy = new HttpProxy(externalProxyConf);

        httpProxyConfig.setClientProxyConfiguration(new ClientProxyConfiguration(externalProxyConf.getHost(), externalProxyConf.getPort()))
        proxy = new HttpProxy(httpProxyConfig);
        proxy.start();

        Thread.sleep(300)
        def params = [param1: "value1", param2: "value2"]

        try {
            doGet(httpClientWithProxy, params)
            fail("should throw ex since external proxy not started")
        }
        catch (Throwable t) {
        }

        try {
            externalProxy.start()

            def originalResponse = doGet(httpClient, params)
            def proxyResponse = doGet(httpClientWithProxy, params)

            assertEquals(responseToReturn, originalResponse)
            assertEquals(originalResponse, proxyResponse)

            assertEquals(2, receivedRequests.size());
            HttpTestRequest originalRequest = receivedRequests[0]
            assertEquals("/dummy", originalRequest.getUrl())
            assertEquals(HttpMethod.GET.getName(), originalRequest.getMethod())
            def requestParams = originalRequest.getParameters();
            assertEquals(2, requestParams.size())
            assertEquals("value1", requestParams.param1)
            assertEquals("value2", requestParams.param2)

            HttpTestRequest proxyRequest = receivedRequests[1]
            assertEquals("/dummy", proxyRequest.getUrl())
            assertEquals(HttpMethod.GET.getName(), proxyRequest.getMethod())
            requestParams = proxyRequest.getParameters();
            assertEquals(2, requestParams.size())
            assertEquals("value1", requestParams.param1)
            assertEquals("value2", requestParams.param2)
        } finally {
            externalProxy.stop();
        }
    }

    @Test
    public void testGetRequestThroughChainProxyWithAuthentication() throws Exception {
        def responseToReturn = "success";
        httpServer.setResponseToReturn(responseToReturn);

        HttpProxyConfig externalProxyConf = new HttpProxyConfig(proxyPort + 10);
        externalProxyConf.setHost(proxyHost);
        externalProxyConf.setUsername("user1")
        externalProxyConf.setPassword("pass1")
        HttpProxy externalProxy = new HttpProxy(externalProxyConf);
        externalProxy.start()
        try {
            //test with wrong password
            ClientProxyConfiguration clientProxyConfiguration = new ClientProxyConfiguration(externalProxyConf.getHost(), externalProxyConf.getPort())
            clientProxyConfiguration.setProxyUsername("user1")
            clientProxyConfiguration.setProxyPassword("wrongpass")
            httpProxyConfig.setClientProxyConfiguration(clientProxyConfiguration)
            proxy = new HttpProxy(httpProxyConfig);
            proxy.start();

            Thread.sleep(300)
            def params = [param1: "value1", param2: "value2"]

            try {
                doGet(httpClientWithProxy, params)
                fail("should throw ex since external proxy not started")
            }
            catch (Throwable t) {
            }

            //test with correct
            proxy.stop()
            clientProxyConfiguration.setProxyUsername(externalProxyConf.getUsername())
            clientProxyConfiguration.setProxyPassword(externalProxyConf.getPassword())
            httpProxyConfig.setClientProxyConfiguration(clientProxyConfiguration)
            proxy = new HttpProxy(httpProxyConfig);
            proxy.start();



            def originalResponse = doGet(httpClient, params)
            def proxyResponse = doGet(httpClientWithProxy, params)

            assertEquals(responseToReturn, originalResponse)
            assertEquals(originalResponse, proxyResponse)

            assertEquals(2, receivedRequests.size());
            HttpTestRequest originalRequest = receivedRequests[0]
            assertEquals("/dummy", originalRequest.getUrl())
            assertEquals(HttpMethod.GET.getName(), originalRequest.getMethod())
            def requestParams = originalRequest.getParameters();
            assertEquals(2, requestParams.size())
            assertEquals("value1", requestParams.param1)
            assertEquals("value2", requestParams.param2)

            HttpTestRequest proxyRequest = receivedRequests[1]
            assertEquals("/dummy", proxyRequest.getUrl())
            assertEquals(HttpMethod.GET.getName(), proxyRequest.getMethod())
            requestParams = proxyRequest.getParameters();
            assertEquals(2, requestParams.size())
            assertEquals("value1", requestParams.param1)
            assertEquals("value2", requestParams.param2)
        } finally {
            externalProxy.stop();
        }
    }

    @Test
    public void testPostRequestThroughProxy() throws Exception {
        def responseToReturn = "success";
        httpServer.setResponseToReturn(responseToReturn);

        proxy = new HttpProxy(httpProxyConfig);
        proxy.start();

        Thread.sleep(300)

        def content = "post content";
        def originalResponse = doPost(httpClient, content)
        def proxyResponse = doPost(httpClientWithProxy, content)

        assertEquals(responseToReturn, originalResponse)
        assertEquals(originalResponse, proxyResponse)

        assertEquals(2, receivedRequests.size());
        HttpTestRequest originalRequest = receivedRequests[0]
        assertEquals("/dummy", originalRequest.getUrl())
        assertEquals(HttpMethod.POST.getName(), originalRequest.getMethod())
        def requestContent = originalRequest.getContent();
        assertEquals(content, requestContent)

        HttpTestRequest proxyRequest = receivedRequests[1]
        assertEquals("/dummy", proxyRequest.getUrl())
        assertEquals(HttpMethod.POST.getName(), proxyRequest.getMethod())
        requestContent = proxyRequest.getContent();
        assertEquals(content, requestContent)
    }

    @Test
    public void testPutRequestThroughProxy() throws Exception {
        def responseToReturn = "success";
        httpServer.setResponseToReturn(responseToReturn);

        proxy = new HttpProxy(httpProxyConfig);
        proxy.start();

        Thread.sleep(300)
        def content = "PUT content";
        def originalResponse = doPut(httpClient, content)
        def proxyResponse = doPut(httpClientWithProxy, content)

        assertEquals(responseToReturn, originalResponse)
        assertEquals(originalResponse, proxyResponse)

        assertEquals(2, receivedRequests.size());
        HttpTestRequest originalRequest = receivedRequests[0]
        assertEquals("/dummy", originalRequest.getUrl())
        assertEquals(HttpMethod.PUT.getName(), originalRequest.getMethod())
        def requestContent = originalRequest.getContent();
        assertEquals(content, requestContent)

        HttpTestRequest proxyRequest = receivedRequests[1]
        assertEquals("/dummy", proxyRequest.getUrl())
        assertEquals(HttpMethod.PUT.getName(), proxyRequest.getMethod())
        requestContent = proxyRequest.getContent();
        assertEquals(content, requestContent)
    }

    @Test
    public void testFileUploadThroughProxy() throws Exception {
        def responseToReturn = "success";
        httpServer.setResponseToReturn(responseToReturn);

        proxy = new HttpProxy(httpProxyConfig);
        proxy.start();

        Thread.sleep(300)

        File file = new File(TestFile.TESTOUTPUT_DIR + "/dummy.txt")
        file.setText("dummy content");

        def params = [param1: "value1", param2: "value2"]
        def originalResponse = uploadFile(httpClient, file, params)
        def proxyResponse = uploadFile(httpClientWithProxy, file, params)

        assertEquals(responseToReturn, originalResponse)
        assertEquals(originalResponse, proxyResponse)

        assertEquals(2, receivedRequests.size());
        HttpTestRequest originalRequest = receivedRequests[0]

        //parse originial request
        FileUpload uploader = new FileUpload(new DiskFileItemFactory());
        List<FileItem> fileItems = uploader.parseRequest(new RequestContext() {
            @Override
            String getCharacterEncoding() {
                return originalRequest.getHeader(HttpHeaders.CONTENT_ENCODING);
            }

            @Override
            String getContentType() {
                return originalRequest.getHeader(HttpHeaders.CONTENT_TYPE);
            }

            @Override
            int getContentLength() {
                return Integer.parseInt(originalRequest.getHeader(HttpHeaders.CONTENT_LENGTH))
            }

            @Override
            InputStream getInputStream() {
                return new ByteArrayInputStream(originalRequest.getContentAsByte());
            }
        })
        assertEquals(3, fileItems.size());
        def fileItem = fileItems.find { it.fieldName == "file" }
        assertNotNull(fileItem)
        assertEquals("dummy content", fileItem.getInputStream().getText())
        assertEquals(file.getName(), fileItem.getName())

        def param1Item = fileItems.find { it.fieldName == "param1" }
        assertNotNull(param1Item);
        assertEquals("value1", param1Item.getString())

        def param2Item = fileItems.find { it.fieldName == "param2" }
        assertNotNull(param2Item);
        assertEquals("value2", param2Item.getString())

        //parse proxy request
        HttpTestRequest proxyRequest = receivedRequests[0]
        uploader = new FileUpload(new DiskFileItemFactory());
        fileItems = uploader.parseRequest(new RequestContext() {
            @Override
            String getCharacterEncoding() {
                return proxyRequest.getHeader(HttpHeaders.CONTENT_ENCODING);
            }

            @Override
            String getContentType() {
                return proxyRequest.getHeader(HttpHeaders.CONTENT_TYPE);
            }

            @Override
            int getContentLength() {
                return Integer.parseInt(proxyRequest.getHeader(HttpHeaders.CONTENT_LENGTH))
            }

            @Override
            InputStream getInputStream() {
                return new ByteArrayInputStream(proxyRequest.getContentAsByte());
            }
        })
        assertEquals(3, fileItems.size());
        fileItem = fileItems.find { it.fieldName == "file" }
        assertNotNull(fileItem)
        assertEquals("dummy content", fileItem.getInputStream().getText())
        assertEquals(file.getName(), fileItem.getName())

        param1Item = fileItems.find { it.fieldName == "param1" }
        assertNotNull(param1Item);
        assertEquals("value1", param1Item.getString())

        param2Item = fileItems.find { it.fieldName == "param2" }
        assertNotNull(param2Item);
        assertEquals("value2", param2Item.getString())
    }

    @Test
    public void testProxyAuthentication() throws Exception {
        def proxyUsername = "proxyUser"
        def proxyPassword = "proxyUserPassword"
        httpClientWithProxy.getCredentialsProvider().setCredentials(new AuthScope(CommonTestUtils.getLocalhostIp(), proxyPort), new NTCredentials(proxyUsername, proxyPassword, null, null));

        def responseToReturn = "success";
        httpServer.setResponseToReturn(responseToReturn);

        httpProxyConfig.setUsername(proxyUsername)
        httpProxyConfig.setPassword(proxyPassword)
        proxy = new HttpProxy(httpProxyConfig)
        proxy.start();

        Thread.sleep(300)

        def proxyResponse = doGet(httpClientWithProxy, [:])
        assertEquals(responseToReturn, proxyResponse)

        //invalid credentials;
        httpClientWithProxy.getCredentialsProvider().setCredentials(new AuthScope(CommonTestUtils.getLocalhostIp(), proxyPort), new NTCredentials(proxyUsername, "invalidPass", null, null));
        proxyResponse = doGet(httpClientWithProxy, [:])
        assertTrue(proxyResponse.indexOf("407 Proxy Authentication Required") > -1)
    }

    private String doGet(client, params) {
        URI uriObj = new URI(url);
        def queryParams = [];
        params.each { key, value ->
            queryParams << new BasicNameValuePair(key, String.valueOf(value));
        }
        URI newUri = URIUtils.createURI(uriObj.getScheme(), uriObj.getHost(), uriObj.getPort(), uriObj.getPath(), URLEncodedUtils.format(queryParams, "UTF-8"), uriObj.getFragment());
        HttpGet get = new HttpGet(newUri);
        HttpResponse response = client.execute(get);
        return EntityUtils.toString(response.getEntity());
    }

    private String doPost(client, content) {
        HttpPost postMethod = new HttpPost(url);
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);
        postMethod.setEntity(entity);
        HttpResponse response = client.execute(postMethod);
        return EntityUtils.toString(response.getEntity());
    }

    private String doPut(client, content) {
        HttpPut put = new HttpPut(url)
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);
        put.setEntity(entity);
        HttpResponse response = client.execute(put);
        return EntityUtils.toString(response.getEntity());
    }

    private String uploadFile(client, file, params) {
        HttpPost postMethod = new HttpPost(url);
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        entity.addPart("file", new InputStreamBody(new FileInputStream(file), file.getName()));
        params.each { key, value ->
            entity.addPart(key, new StringBody(value))
        }
        postMethod.setEntity(entity);
        HttpResponse response = client.execute(postMethod);
        return EntityUtils.toString(response.getEntity());
    }

    @Override
    void requestRecieved(HttpTestRequest request) {
        receivedRequests.add(request)
    }
}
