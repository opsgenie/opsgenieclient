package com.ifountain.opsgenie.client.http

import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.util.ClientConfiguration
import com.ifountain.opsgenie.client.util.ClientProxyConfiguration
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.commons.io.FileUtils
import org.apache.http.HttpHeaders
import org.apache.http.HttpRequest
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.conn.HttpHostConnectException
import org.apache.http.protocol.HttpContext
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.littleshoot.proxy.DefaultHttpProxyServer
import org.littleshoot.proxy.ProxyAuthorizationHandler

import javax.net.ssl.SSLPeerUnverifiedException

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 2:11 PM
 */
class OpsGenieHttpClientTest implements HttpTestRequestListener {
    static def host
    static def port
    static HttpTestServer httpServer;
    static def receivedRequests = [];
    def httpClient;
    static String url

    @BeforeClass
    public static void beforeClass() {
        CommonTestUtils.initializeFromFile("Test.properties");
        host = CommonTestUtils.getLocalhostIp()
        port = CommonTestUtils.getLocalPort();
        url = "http://${host}:${port}/dummy"
        println url

    }

    @Before
    public void setUp() {
        httpServer = new HttpTestServer(host, port)
        httpServer.addRequestListener(this)
        httpServer.open()
        httpClient = new OpsGenieHttpClient();
        FileUtils.deleteDirectory(new TestFile());
        receivedRequests.clear();
    }

    @After
    public void tearDown() {
        httpServer.close()
    }


    @Test
    public void testProxySettings() throws Exception {

        httpClient = new OpsGenieHttpClient();
        int proxyPort = port + 15;
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setClientProxyConfiguration(new ClientProxyConfiguration(host, proxyPort))
        httpClient = new OpsGenieHttpClient(clientConfiguration);
        DefaultHttpProxyServer proxyServer = new DefaultHttpProxyServer(proxyPort);
        proxyServer.setHost(host);
        try {
            httpServer.setResponseToReturn("success");
            try {
                //test will not connect if proxy is not started
                httpClient.post(url, "content")
                fail("Should throw exception since proxy server is down");
            }
            catch (HttpHostConnectException t) {
            }
            proxyServer.start();

            def response = httpClient.post(url, "content")
            assertEquals(1, receivedRequests.size())
            HttpTestRequest request = receivedRequests[0];

            assertEquals("content", request.getContent())
            assertEquals("/dummy", request.getUrl())
            assertEquals(HttpPost.METHOD_NAME, request.getMethod());

            def headers = request.getHeaders()
            assertEquals("7", headers.get(HttpHeaders.CONTENT_LENGTH))
            assertTrue(headers.containsKey(HttpHeaders.HOST))
            println headers.get(HttpHeaders.USER_AGENT)
            assertTrue(String.valueOf(headers.get(HttpHeaders.USER_AGENT)), headers.get(HttpHeaders.USER_AGENT).indexOf("opsgenie-sdk-java") > -1)
            assertEquals("text/plain; charset=UTF-8", headers.get(HttpHeaders.CONTENT_TYPE));

            assertEquals(HttpStatus.SC_OK, response.getStatusCode())
            assertEquals("success", new String(response.getContent()))
        }
        finally {
            proxyServer.stop();
        }
    }

    @Test
    public void testWithAuthenticationProxySettings() throws Exception {
        httpClient = new OpsGenieHttpClient();
        int proxyPort = port + 15;
        String username = "user1"
        String password = "password1"
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        ClientProxyConfiguration clientProxyConfiguration = new ClientProxyConfiguration(host, proxyPort)
        clientProxyConfiguration.setProxyUsername(username)
        clientProxyConfiguration.setProxyPassword(password)
        clientConfiguration.setClientProxyConfiguration(clientProxyConfiguration)
        DefaultHttpProxyServer proxyServer = new DefaultHttpProxyServer(proxyPort);
        proxyServer.setHost(host);
        boolean proxyUsed = false;
        proxyServer.addProxyAuthenticationHandler(new ProxyAuthorizationHandler() {
            @Override
            public boolean authenticate(String usernamep, String passwordp) {
                proxyUsed = true;
                return username == usernamep && passwordp == password;
            }
        });
        proxyServer.start();
        try {
            httpServer.setResponseToReturn("success");

            clientConfiguration.clientProxyConfiguration.setProxyPassword("wrongpass")
            httpClient = new OpsGenieHttpClient(clientConfiguration);
            OpsGenieHttpResponse resp = httpClient.post(url, "content")
            assertEquals(407, resp.getStatusCode());
            httpClient.close();
            clientConfiguration.clientProxyConfiguration.setProxyPassword(password)
            httpClient = new OpsGenieHttpClient(clientConfiguration);

            def response = httpClient.post(url, "content")
            assertEquals(1, receivedRequests.size())
            HttpTestRequest request = receivedRequests[0];

            assertEquals("content", request.getContent())
            assertEquals("/dummy", request.getUrl())
            assertEquals(HttpPost.METHOD_NAME, request.getMethod());

            def headers = request.getHeaders()
            assertEquals("7", headers.get(HttpHeaders.CONTENT_LENGTH))
            assertTrue(headers.containsKey(HttpHeaders.HOST))
            println headers.get(HttpHeaders.USER_AGENT)
            assertTrue(String.valueOf(headers.get(HttpHeaders.USER_AGENT)), headers.get(HttpHeaders.USER_AGENT).indexOf("opsgenie-sdk-java") > -1)
            assertEquals("text/plain; charset=UTF-8", headers.get(HttpHeaders.CONTENT_TYPE));

            assertEquals(HttpStatus.SC_OK, response.getStatusCode())
            assertEquals("success", new String(response.getContent()))
        }
        finally {
            proxyServer.stop();
        }
    }

    @Test
    public void testWithProxySettingsWithWrongProtocol() throws Exception {
        httpClient = new OpsGenieHttpClient();
        int proxyPort = port + 15;
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        ClientProxyConfiguration clientProxyConfiguration = new ClientProxyConfiguration(host, proxyPort)
        clientProxyConfiguration.setProxyProtocol("https")
        clientConfiguration.setClientProxyConfiguration(clientProxyConfiguration)
        DefaultHttpProxyServer proxyServer = new DefaultHttpProxyServer(proxyPort);
        proxyServer.setHost(host);
        proxyServer.start();
        try {
            httpServer.setResponseToReturn("success");
            httpClient = new OpsGenieHttpClient(clientConfiguration);
            try {
                httpClient.post(url, "content")
                fail("should fail since proxy protocol is not same as httpclient")
            }
            catch (SSLPeerUnverifiedException t) {

            }
        }
        finally {
            proxyServer.stop();
        }
    }


    @Test
    public void testRetryExecution() throws Exception {
        int maxRetryCountForException = 10;
        int maxRetryCountForResponse = 3;
        int exceptionretryCallCount = 0;
        int responseRetryCallCount = 0;
        ClientConfiguration conf = new ClientConfiguration()
        conf.setRetryHandler(new OpsgenieRequestRetryHandler() {
            @Override
            boolean retryRequest(HttpRequest request, OpsGenieHttpResponse response, int executionCount) {
                if (executionCount >= maxRetryCountForResponse) {
                    return false;
                }
                responseRetryCallCount++;
                return response.getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR;
            }

            @Override
            boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= maxRetryCountForException) {
                    return false;
                }
                exceptionretryCallCount++;
                return true;
            }
        })
        httpClient = new OpsGenieHttpClient(conf);
        try {
            httpClient.post("http://localhost:29999", "content")
            fail("Should throw ex");
        }
        catch (IOException ex) {
        }
        assertEquals(0, responseRetryCallCount)
        assertEquals(maxRetryCountForException - 1, exceptionretryCallCount)

        //test response
        responseRetryCallCount = 0
        exceptionretryCallCount = 0
        HttpTestResponse expectedResp = new HttpTestResponse("testing".getBytes());
        expectedResp.status = HttpStatus.SC_INTERNAL_SERVER_ERROR
        httpServer.setResponseToReturn(expectedResp);

        httpClient = new OpsGenieHttpClient(conf);
        def resp = httpClient.post(url, "content")
        assertEquals(new String(expectedResp.getContent()), resp.getContentAsString())
        assertEquals(maxRetryCountForResponse - 1, responseRetryCallCount)
        assertEquals(0, exceptionretryCallCount)
    }

    @Test
    public void testResponseWithNoContent() throws Exception{
        httpServer.setResponseToReturn(new HttpTestResponse())

        def response = httpClient.post(url, "")
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];

        assertEquals(null, response.content)
        assertEquals(HttpStatus.SC_NO_CONTENT, response.statusCode)
    }

    @Test
    public void testPostWithStringContent() throws Exception {
        httpServer.setResponseToReturn("success");

        def response = httpClient.post(url, "content")
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];

        assertEquals("content", request.getContent())
        assertEquals("/dummy", request.getUrl())
        assertEquals(HttpPost.METHOD_NAME, request.getMethod());

        def headers = request.getHeaders()
        assertEquals("7", headers.get(HttpHeaders.CONTENT_LENGTH))
        assertTrue(headers.containsKey(HttpHeaders.HOST))
        println headers.get(HttpHeaders.USER_AGENT)
        assertTrue(String.valueOf(headers.get(HttpHeaders.USER_AGENT)), headers.get(HttpHeaders.USER_AGENT).indexOf("opsgenie-sdk-java") > -1)
        assertEquals("text/plain; charset=UTF-8", headers.get(HttpHeaders.CONTENT_TYPE));

        assertEquals(HttpStatus.SC_OK, response.getStatusCode())
        assertEquals("success", new String(response.getContent()))
    }

    @Test
    public void testPostWithParameterContent() throws Exception {
        httpServer.setResponseToReturn("success");

        def response = httpClient.post(url, [prop1: "prop1value"])
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];


        def content = "prop1=prop1value"
        assertEquals(content, request.getContent())
        assertEquals("/dummy", request.getUrl())
        assertEquals(HttpPost.METHOD_NAME, request.getMethod());

        def headers = request.getHeaders()
        assertEquals("" + content.length(), headers.get(HttpHeaders.CONTENT_LENGTH))
        assertTrue(headers.containsKey(HttpHeaders.HOST))
        println headers.get(HttpHeaders.USER_AGENT)
        assertTrue(String.valueOf(headers.get(HttpHeaders.USER_AGENT)), headers.get(HttpHeaders.USER_AGENT).indexOf("opsgenie-sdk-java") > -1)
        assertEquals("application/x-www-form-urlencoded; charset=UTF-8", headers.get(HttpHeaders.CONTENT_TYPE));

        assertEquals(HttpStatus.SC_OK, response.getStatusCode())
        assertEquals("success", new String(response.getContent()))
    }

    @Test
    public void testPostWithByteContent() throws Exception {
        httpServer.setResponseToReturn("success");

        def response = httpClient.post(url, "content".getBytes())
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];

        assertEquals("content", request.getContent())
        assertEquals("/dummy", request.getUrl())
        assertEquals(HttpPost.METHOD_NAME, request.getMethod());

        def headers = request.getHeaders()
        assertEquals("7", headers.get(HttpHeaders.CONTENT_LENGTH))
        assertTrue(headers.containsKey(HttpHeaders.HOST))
        assertTrue(headers.get(HttpHeaders.USER_AGENT).indexOf("opsgenie-sdk-java") > -1)
        assertEquals("application/x-www-form-urlencoded; charset=UTF-8".toLowerCase(), headers.get(HttpHeaders.CONTENT_TYPE));

        assertEquals(HttpStatus.SC_OK, response.getStatusCode())
        assertEquals("success", new String(response.getContent()))
    }

    @Test
    public void testPostWithParametersAndStringContentAndHeaders() {
        httpServer.setResponseToReturn("success");

        def response = httpClient.post(url, "content", ["header1": "head1"], ["param1": "par1"])
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];

        assertEquals(1, request.parameters.size())
        assertEquals("par1", request.parameters.param1)

        assertEquals("content", request.getContent())
        assertEquals("/dummy", request.getUrl())
        assertEquals(HttpPost.METHOD_NAME, request.getMethod());

        def headers = request.getHeaders()
        assertEquals("7", headers.get(HttpHeaders.CONTENT_LENGTH))
        assertTrue(headers.containsKey(HttpHeaders.HOST))
        println headers.get(HttpHeaders.USER_AGENT)
        assertTrue(String.valueOf(headers.get(HttpHeaders.USER_AGENT)), headers.get(HttpHeaders.USER_AGENT).indexOf("opsgenie-sdk-java") > -1)
        assertEquals("text/plain; charset=UTF-8", headers.get(HttpHeaders.CONTENT_TYPE));
        assertEquals("head1", headers.header1)

        assertEquals(HttpStatus.SC_OK, response.getStatusCode())
        assertEquals("success", new String(response.getContent()))
    }

    @Test
    public void testGet() throws Exception {
        httpServer.setResponseToReturn("success");

        def response = httpClient.get(url + "?param1=value1&param2=value2", [param1: "updatedValue1", param3: "value3"])
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];
        assertEquals(HttpGet.METHOD_NAME, request.getMethod());
        assertEquals("/dummy%3Fparam1=value1&param2=value2", request.getUrl())
        assertEquals(0, request.getContentAsByte().length)

        def params = request.getParameters();
        assertEquals(2, params.size())
        assertEquals("updatedValue1", params.param1)
        assertEquals("value3", params.param3)

        assertEquals(HttpStatus.SC_OK, response.getStatusCode())
        assertEquals("success", new String(response.getContent()))
    }

    @Test
    public void testDelete() throws Exception {
        httpServer.setResponseToReturn("success");

        def response = httpClient.delete(url + "?param1=value1&param2=value2", [param1: "updatedValue1", param3: "value3"])
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];
        assertEquals(HttpDelete.METHOD_NAME, request.getMethod());
        assertEquals("/dummy%3Fparam1=value1&param2=value2", request.getUrl())
        assertEquals(0, request.getContentAsByte().length)

        def params = request.getParameters();
        assertEquals(2, params.size())
        assertEquals("updatedValue1", params.param1)
        assertEquals("value3", params.param3)

        assertEquals(HttpStatus.SC_OK, response.getStatusCode())
        assertEquals("success", new String(response.getContent()))
    }

    @Test
    public void testPut() throws Exception {
        httpServer.setResponseToReturn("success");

        def response = httpClient.put(url, ["contentParam1": "22"], [param1: "updatedValue1", param3: "value3"])
        assertEquals(1, receivedRequests.size())
        HttpTestRequest request = receivedRequests[0];
        assertEquals(HttpPut.METHOD_NAME, request.getMethod());
        assertEquals("/dummy", request.getUrl())

        def contentMap = JsonUtils.parse(request.content)
        assertEquals("22", contentMap.contentParam1)

        def params = request.getParameters();
        assertEquals(2, params.size())
        assertEquals("updatedValue1", params.param1)
        assertEquals("value3", params.param3)

        assertEquals(HttpStatus.SC_OK, response.getStatusCode())
        assertEquals("success", new String(response.getContent()))
    }

    @Override
    void requestRecieved(HttpTestRequest request) {
        receivedRequests.add(request)
    }
}
