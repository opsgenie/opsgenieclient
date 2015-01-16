package com.ifountain.opsgenie.client.marid

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.http.HttpTestServer
import com.ifountain.opsgenie.client.marid.alert.PubnubAlertActionListener
import com.ifountain.opsgenie.client.misc.RsSmartWait
import com.ifountain.opsgenie.client.script.AsyncScriptManager
import com.ifountain.opsgenie.client.script.ScriptManager
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.MaridTestCase
import com.ifountain.opsgenie.client.pubnub.PubnubTestUtils
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.test.util.http.HttpUtils
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.commons.io.FileUtils
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.auth.AuthScope
import org.apache.http.auth.NTCredentials
import org.apache.http.client.methods.HttpGet
import org.apache.http.conn.HttpHostConnectException
import org.apache.http.conn.params.ConnRoutePNames
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import sun.security.tools.KeyTool
import static org.junit.Assert.*

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/2/12 9:39 AM
 */
class BootstrapTest extends MaridTestCase implements HttpTestRequestListener {
    def static testHttpServerHost;
    def static testHttpServerPort;
    def static int proxyPort = 0;
    def static HttpTestServer testHttpServer;
    static def receivedRequests = [];
    def static url;
    def keyPass = "123456"
    def keystorePath = TestFile.TESTOUTPUT_DIR + "/.keystore"
    Bootstrap bootstrap;
    DefaultHttpClient httpClient;
    File confFile;

    @BeforeClass
    public static void beforeClass() {
        MaridTestCase.beforeClass()
        testHttpServerHost = CommonTestUtils.getLocalhostIp();
        testHttpServerPort = CommonTestUtils.getLocalPort() + 10;
        proxyPort = CommonTestUtils.getLocalPort() + 15;
        url = "http://${testHttpServerHost}:${testHttpServerPort}/dummy";
    }

    @Before
    public void setUp() {
        testHttpServer = new HttpTestServer(testHttpServerHost, testHttpServerPort)
        testHttpServer.addRequestListener(this)
        testHttpServer.open()
        confFile = new TestFile("conf")
        File scriptsFile = new TestFile("scripts")
        System.setProperty(Bootstrap.MARID_CONF_DIR_SYSTEM_PROPERTY, confFile.getPath())
        System.setProperty(Bootstrap.MARID_SCRIPTS_DIR_SYSTEM_PROPERTY, scriptsFile.getPath())

        FileUtils.deleteDirectory(new TestFile())

        MaridConfig.destroyInstance()
        receivedRequests.clear();
        bootstrap = new Bootstrap();
        httpClient = new DefaultHttpClient();
        confFile = new File(TestFile.TESTOUTPUT_DIR + "/conf/marid.conf")
        confFile.getParentFile().mkdirs();
        //FileUtils.deleteQuietly(confFile)
    }

    @After
    public void tearDown() {
        bootstrap.close();
        httpClient.getConnectionManager().shutdown()
        MaridConfig.destroyInstance()

        System.clearProperty(Bootstrap.CONF_PATH_SYSTEM_PROPERTY)
        System.clearProperty(Bootstrap.MARID_CONF_DIR_SYSTEM_PROPERTY)
        System.clearProperty(Bootstrap.MARID_SCRIPTS_DIR_SYSTEM_PROPERTY)
        testHttpServer.close()
        System.setProperty("maridhome", ".")
        super.tearDown()
    }

    @Test
    public void testInitializeAndCloseWithOldConf() throws Exception {
        applyInitAndCloseTest()
    }

    @Test
    public void testInitializeAndCloseWithConfPathFromSystemProp() {
        FileUtils.deleteDirectory(new TestFile())

        confFile = new File(TestFile.TESTOUTPUT_DIR + "/integration/integration.conf")
        confFile.parentFile.mkdirs();

        System.setProperty(Bootstrap.CONF_PATH_SYSTEM_PROPERTY, confFile.getPath())

        applyInitAndCloseTest()
    }

    @Test
    public void testInitializeAndCloseWithPrimarilyUsePathFromSysProp() {
        confFile.setText("""
            apiKey=12321
            maridKey=key14
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            http.proxy.server.enabled=true
            http.proxy.server.port=${proxyPort}
            http.proxy.server.host=${CommonTestUtils.getLocalhostIp()}
            http.proxy.server.username=sezgin
            http.proxy.server.password=sezgin
            opsgenie.api.url=http://${testHttpServerHost}:${testHttpServerPort}/api
            http.server.enabled=true
            http.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.enabled=true
            https.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.keystore=${keystorePath}
            https.server.keyPassword=${keyPass}
            extra.config.param=extraval
         """)

        confFile = new File(TestFile.TESTOUTPUT_DIR + "/integration/integration.conf")
        confFile.parentFile.mkdirs();

        System.setProperty(Bootstrap.CONF_PATH_SYSTEM_PROPERTY, confFile.getPath())

        applyInitAndCloseTest()
    }

    @Test
    public void testInitializeAndCloseWithUsingHomeDirForConf() {
        FileUtils.deleteDirectory(confFile.parentFile)

        confFile = new File("conf/marid.conf")
        confFile.parentFile.mkdirs();

        applyInitAndCloseTest()

        FileUtils.deleteDirectory(confFile.parentFile)
    }

    private void applyInitAndCloseTest() {
        generateKeyStore();
        def httpPort = CommonTestUtils.getLocalPort();
        def httpsPort = httpPort + 1
        def shutdownWaitTime = 5000
        def scriptExecutorThreadCount = 3
        def scriptExecutorQueueCount = 3
        confFile.setText("""
            apiKey=123
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            http.proxy.server.enabled=true
            http.proxy.server.port=${proxyPort}
            http.proxy.server.host=${CommonTestUtils.getLocalhostIp()}
            http.proxy.server.username=sezgin
            http.proxy.server.password=sezgin
            opsgenie.api.url=http://${testHttpServerHost}:${testHttpServerPort}/api
            http.server.enabled=true
            http.server.port=${httpPort}
            http.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.enabled=true
            https.server.port=${httpsPort}
            https.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.keystore=${keystorePath}
            https.server.keyPassword=${keyPass}
            extra.config.param=extraval
            async.script.shutdown.wait.time=${shutdownWaitTime}
            async.script.executor.thread.count=${scriptExecutorThreadCount}
            async.script.executor.queue.count=${scriptExecutorQueueCount}
         """)
        def pubnubCredentials = PubnubTestUtils.getCredentials();
        def content = JsonUtils.toJson([
                "execute.alert.actions": "true",
                "pubnub.channel"       : "customer1",
                "pubnub.subscribekey"  : pubnubCredentials.getSubscribeKey(),
                "pubnub.ssl.enabled"   : pubnubCredentials.isSslOn(),
        ])
        testHttpServer.setResponseToReturn(content);
        bootstrap.initialize();

        Thread.sleep(300)
        //scripting
        assertTrue(ScriptManager.getInstance().isInitialized())
        assertTrue(AsyncScriptManager.getInstance().isInitialized())
        assertEquals(shutdownWaitTime, AsyncScriptManager.getInstance().getShutdownWaitTime())

        //alert action executor
        RsSmartWait.waitForClosure {
            assertTrue(PubnubAlertActionListener.getInstance().isSubscribed())
        }

        //proxy server
        testHttpServer.setResponseToReturn("success")

        HttpHost proxyHost = new HttpHost(CommonTestUtils.getLocalhostIp(), proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(CommonTestUtils.getLocalhostIp(), proxyPort), new NTCredentials("sezgin", "sezgin", null, null));

        HttpGet get = new HttpGet(new URI(this.url));
        HttpResponse response = httpClient.execute(get);
        def httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("success", httpResponse)

        //test httpserver
        DefaultHttpClient httpServerClient = new DefaultHttpClient();
        def httpServerUrl = "http://${CommonTestUtils.getLocalhostIp()}:${httpPort}/unknown"
        def httpsServerUrl = "https://${CommonTestUtils.getLocalhostIp()}:${httpsPort}/unknown"
        get = new HttpGet(new URI(httpServerUrl));
        response = httpServerClient.execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        def httpUtils = new HttpUtils(30000);

        get = new HttpGet(new URI(httpsServerUrl));
        response = httpUtils.getClient().execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        //test MaridConfigConstruction
        assertEquals("key1", MaridConfig.getInstance().getMaridKey())
        assertEquals("123", MaridConfig.getInstance().getApiKey())
        assertEquals("http://${testHttpServerHost}:${testHttpServerPort}/api".toString(), MaridConfig.getInstance().getOpsgenieApiUrl())
        //test opegnie client config
        assertEquals(26, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(25000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(20000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)
        assertEquals(MaridConfig.getInstance().getOpsgenieApiUrl(), MaridConfig.getInstance().opsGenieClient.jsonHttpClient.rootUri)
        assertEquals("extraval", MaridConfig.getInstance().getConfiguration().getProperty("extra.config.param"))

        bootstrap.close();

        try {
            httpClient.execute(get);
            fail("should throw exception")
        }
        catch (HttpHostConnectException ignored) {
        }

        //alert action executor
        RsSmartWait.waitForClosure {
            assertFalse(PubnubAlertActionListener.getInstance().isSubscribed())
        }
        //scripting
        assertFalse(ScriptManager.getInstance().isInitialized())
        assertFalse(AsyncScriptManager.getInstance().isInitialized())

        get = new HttpGet(new URI(httpServerUrl));
        try {
            httpServerClient.execute(get);
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals("Connection to http://${CommonTestUtils.getLocalhostIp()}:${httpPort} refused".toString(), e.getMessage())
        }

        get = new HttpGet(new URI(httpsServerUrl));
        try {
            httpServerClient.execute(get);
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals("Connection to https://${CommonTestUtils.getLocalhostIp()}:${httpsPort} refused".toString(), e.getMessage())
        }
    }

    //TODO:Will be removed when customer key support is deprecated
    @Test
    public void testInitializeAndCloseWithCustomerKey() throws Exception {
        generateKeyStore();
        def httpPort = CommonTestUtils.getLocalPort();
        def httpsPort = httpPort + 1
        confFile.setText("""
            customerKey=123
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            http.proxy.server.enabled=true
            http.proxy.server.port=${proxyPort}
            http.proxy.server.host=${CommonTestUtils.getLocalhostIp()}
            http.proxy.server.username=sezgin
            http.proxy.server.password=sezgin
            opsgenie.api.url=http://${testHttpServerHost}:${testHttpServerPort}/api
            http.server.enabled=true
            http.server.port=${httpPort}
            http.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.enabled=true
            https.server.port=${httpsPort}
            https.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.keystore=${keystorePath}
            https.server.keyPassword=${keyPass}
            extra.config.param=extraval
         """)
        def pubnubCredentials = PubnubTestUtils.getCredentials();
        def content = JsonUtils.toJson([
                "execute.alert.actions": "true",
                "pubnub.channel"       : "customer1",
                "pubnub.subscribekey"  : pubnubCredentials.getSubscribeKey(),
                "pubnub.ssl.enabled"   : pubnubCredentials.isSslOn(),
        ])
        testHttpServer.setResponseToReturn(content);
        bootstrap.initialize();

        Thread.sleep(300)
        //scripting
        assertTrue(ScriptManager.getInstance().isInitialized())
        assertTrue(AsyncScriptManager.getInstance().isInitialized())

        //alert action executor
        RsSmartWait.waitForClosure {
            assertTrue(PubnubAlertActionListener.getInstance().isSubscribed())
        }

        //proxy server
        testHttpServer.setResponseToReturn("success")

        HttpHost proxyHost = new HttpHost(CommonTestUtils.getLocalhostIp(), proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(CommonTestUtils.getLocalhostIp(), proxyPort), new NTCredentials("sezgin", "sezgin", null, null));

        HttpGet get = new HttpGet(new URI(this.url));
        HttpResponse response = httpClient.execute(get);
        def httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("success", httpResponse)

        //test httpserver
        DefaultHttpClient httpServerClient = new DefaultHttpClient();
        def httpServerUrl = "http://${CommonTestUtils.getLocalhostIp()}:${httpPort}/unknown"
        def httpsServerUrl = "https://${CommonTestUtils.getLocalhostIp()}:${httpsPort}/unknown"
        get = new HttpGet(new URI(httpServerUrl));
        response = httpServerClient.execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        def httpUtils = new HttpUtils(30000);

        get = new HttpGet(new URI(httpsServerUrl));
        response = httpUtils.getClient().execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        //test MaridConfigConstruction
        assertEquals("key1", MaridConfig.getInstance().getMaridKey())
        assertEquals("123", MaridConfig.getInstance().getApiKey())
        assertEquals("http://${testHttpServerHost}:${testHttpServerPort}/api".toString(), MaridConfig.getInstance().getOpsgenieApiUrl())
        //test opegnie client config
        assertEquals(26, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(25000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(20000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)
        assertEquals(MaridConfig.getInstance().getOpsgenieApiUrl(), MaridConfig.getInstance().opsGenieClient.jsonHttpClient.rootUri)
        assertEquals("extraval", MaridConfig.getInstance().getConfiguration().getProperty("extra.config.param"))


        bootstrap.close();

        try {
            httpClient.execute(get);
            fail("should throw exception")
        }
        catch (HttpHostConnectException ignored) {
        }

        //alert action executor
        RsSmartWait.waitForClosure {
            assertFalse(PubnubAlertActionListener.getInstance().isSubscribed())
        }
        //scripting
        assertFalse(ScriptManager.getInstance().isInitialized())
        assertFalse(AsyncScriptManager.getInstance().isInitialized())

        get = new HttpGet(new URI(httpServerUrl));
        try {
            httpServerClient.execute(get);
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals("Connection to http://${CommonTestUtils.getLocalhostIp()}:${httpPort} refused".toString(), e.getMessage())
        }

        get = new HttpGet(new URI(httpsServerUrl));
        try {
            httpServerClient.execute(get);
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals("Connection to https://${CommonTestUtils.getLocalhostIp()}:${httpsPort} refused".toString(), e.getMessage())
        }
    }

    @Test
    public void testInitializeAndCloseWithoutHttpServers() throws Exception {
        generateKeyStore();
        def httpPort = CommonTestUtils.getLocalPort();
        def httpsPort = httpPort + 1
        confFile.setText("""
            apiKey=123
            maridKey=key1
            http.proxy.server.enabled=true
            http.proxy.server.port=${proxyPort}
            http.proxy.server.host=${CommonTestUtils.getLocalhostIp()}
            http.proxy.server.username=sezgin
            http.proxy.server.password=sezgin
            opsgenie.api.url=http://${testHttpServerHost}:${testHttpServerPort}
            http.server.enabled=false
            https.server.enabled=false
         """)
        def pubnubCredentials = PubnubTestUtils.getCredentials();
        def content = JsonUtils.toJson([
                "execute.alert.actions": "true",
                "pubnub.channel"       : "customer1",
                "pubnub.subscribekey"  : pubnubCredentials.getSubscribeKey(),
        ])
        testHttpServer.setResponseToReturn(content);
        bootstrap.initialize();

        Thread.sleep(300)
        //scripting
        assertTrue(ScriptManager.getInstance().isInitialized())
        assertTrue(AsyncScriptManager.getInstance().isInitialized())

        //alert action executor
        RsSmartWait.waitForClosure {
            assertTrue(PubnubAlertActionListener.getInstance().isSubscribed())
        }

        //proxy server
        testHttpServer.setResponseToReturn("success")

        HttpHost proxyHost = new HttpHost(CommonTestUtils.getLocalhostIp(), proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(CommonTestUtils.getLocalhostIp(), proxyPort), new NTCredentials("sezgin", "sezgin", null, null));

        HttpGet get = new HttpGet(new URI(this.url));
        HttpResponse response = httpClient.execute(get);
        def httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("success", httpResponse)

        //test httpserver
        DefaultHttpClient httpServerClient = new DefaultHttpClient();
        def httpServerUrl = "http://${CommonTestUtils.getLocalhostIp()}:${httpPort}/unknown"
        def httpsServerUrl = "https://${CommonTestUtils.getLocalhostIp()}:${httpsPort}/unknown"
        get = new HttpGet(new URI(httpServerUrl));
        try {
            httpServerClient.execute(get);
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals("Connection to http://${CommonTestUtils.getLocalhostIp()}:${httpPort} refused".toString(), e.getMessage())
        }

        get = new HttpGet(new URI(httpsServerUrl));
        try {
            httpServerClient.execute(get);
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals("Connection to https://${CommonTestUtils.getLocalhostIp()}:${httpsPort} refused".toString(), e.getMessage())
        }

        //test MaridConfigConstruction
        assertEquals("key1", MaridConfig.getInstance().getMaridKey())
        assertEquals("123", MaridConfig.getInstance().getApiKey())
        //test opegnie client config
        assertEquals(50, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(30000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(30000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)



        bootstrap.close();

        try {
            httpClient.execute(get);
            fail("should throw exception")
        }
        catch (HttpHostConnectException ignored) {
        }

        //alert action executor
        RsSmartWait.waitForClosure {
            assertFalse(PubnubAlertActionListener.getInstance().isSubscribed())
        }
        //scripting
        assertFalse(ScriptManager.getInstance().isInitialized())
        assertFalse(AsyncScriptManager.getInstance().isInitialized())
    }

    private generateKeyStore() {
        new File(keystorePath).delete();
        String[] args = ["-genkey", "-alias", "localhost", "-dname",
                         "CN=localhost,OU=Test,O=Test,C=US", "-keyalg", "RSA",
                         "-validity", "365", "-storepass", "key", "-keystore",
                         "${keystorePath}", "-storepass", "${keyPass}",
                         "-keypass", "${keyPass}"] as String[];
        KeyTool.main(args)
    }

    @Test
    public void testIfCannotReachOpsGenieServerWillNotStartPubnub() throws Exception {
        generateKeyStore();
        def httpPort = CommonTestUtils.getLocalPort();
        def httpsPort = httpPort + 1
        confFile.setText("""
            customerKey=123
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            http.proxy.server.enabled=true
            http.proxy.server.port=${proxyPort}
            http.proxy.server.host=${CommonTestUtils.getLocalhostIp()}
            http.proxy.server.username=sezgin
            http.proxy.server.password=sezgin
            opsgenie.api.url=http://unknownhost
            http.server.enabled=true
            http.server.port=${httpPort}
            http.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.enabled=true
            https.server.port=${httpsPort}
            https.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.keystore=${keystorePath}
            https.server.keyPassword=${keyPass}
            extra.config.param=extraval
         """)
        bootstrap.initialize();

        Thread.sleep(300)
        //scripting
        assertTrue(ScriptManager.getInstance().isInitialized())
        assertTrue(AsyncScriptManager.getInstance().isInitialized())

        //proxy server
        testHttpServer.setResponseToReturn("success")

        HttpHost proxyHost = new HttpHost(CommonTestUtils.getLocalhostIp(), proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(CommonTestUtils.getLocalhostIp(), proxyPort), new NTCredentials("sezgin", "sezgin", null, null));

        HttpGet get = new HttpGet(new URI(this.url));
        HttpResponse response = httpClient.execute(get);
        def httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("success", httpResponse)

        //test httpserver
        DefaultHttpClient httpServerClient = new DefaultHttpClient();
        def httpServerUrl = "http://${CommonTestUtils.getLocalhostIp()}:${httpPort}/unknown"
        def httpsServerUrl = "https://${CommonTestUtils.getLocalhostIp()}:${httpsPort}/unknown"
        get = new HttpGet(new URI(httpServerUrl));
        response = httpServerClient.execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        def httpUtils = new HttpUtils(30000);

        get = new HttpGet(new URI(httpsServerUrl));
        response = httpUtils.getClient().execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        //test MaridConfigConstruction
        assertEquals("key1", MaridConfig.getInstance().getMaridKey())
        assertEquals("123", MaridConfig.getInstance().getApiKey())
        assertEquals("http://unknownhost", MaridConfig.getInstance().getOpsgenieApiUrl())
        //test opsgenie client config
        assertEquals(26, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(25000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(20000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)
        assertEquals(MaridConfig.getInstance().getOpsgenieApiUrl(), MaridConfig.getInstance().opsGenieClient.jsonHttpClient.rootUri)
        assertEquals("extraval", MaridConfig.getInstance().getConfiguration().getProperty("extra.config.param"))

        //will not start pubnub
        assertFalse(PubnubAlertActionListener.getInstance().isSubscribed())
    }

    @Test
    public void testIfServerReturnsErrorWillStartInWebhookModeWithOkStatusCodeAndInvalidContent() throws Exception {
        HttpTestResponse testResponse = new HttpTestResponse("success")
        _testIfServerReturnsErrorWillStartInWebhookMode(testResponse)
    }

    @Test
    public void testIfServerReturnsErrorWillStartInWebhookModeWithBadRequest() throws Exception {
        HttpTestResponse testResponse = new HttpTestResponse("error".getBytes(), HttpStatus.SC_BAD_REQUEST)
        _testIfServerReturnsErrorWillStartInWebhookMode(testResponse)
    }

    public void _testIfServerReturnsErrorWillStartInWebhookMode(HttpTestResponse testResponse) throws Exception {
        //proxy server
        testHttpServer.setResponseToReturn(testResponse)
        generateKeyStore();
        def httpPort = CommonTestUtils.getLocalPort();
        def httpsPort = httpPort + 1
        confFile.setText("""
            apiKey=invalidcustomer
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            http.proxy.server.enabled=true
            http.proxy.server.port=${proxyPort}
            http.proxy.server.host=${CommonTestUtils.getLocalhostIp()}
            http.proxy.server.username=sezgin
            http.proxy.server.password=sezgin
            opsgenie.api.url=http://${testHttpServerHost}:${testHttpServerPort}
            http.server.enabled=true
            http.server.port=${httpPort}
            http.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.enabled=true
            https.server.port=${httpsPort}
            https.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.keystore=${keystorePath}
            https.server.keyPassword=${keyPass}
            extra.config.param=extraval
         """)
        bootstrap.initialize();

        Thread.sleep(300)
        //scripting
        assertTrue(ScriptManager.getInstance().isInitialized())
        assertTrue(AsyncScriptManager.getInstance().isInitialized())

        HttpHost proxyHost = new HttpHost(CommonTestUtils.getLocalhostIp(), proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(CommonTestUtils.getLocalhostIp(), proxyPort), new NTCredentials("sezgin", "sezgin", null, null));

        HttpGet get = new HttpGet(new URI(this.url));
        HttpResponse response = httpClient.execute(get);
        def httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals(new String(testResponse.getContent()), httpResponse)

        //test httpserver
        DefaultHttpClient httpServerClient = new DefaultHttpClient();
        def httpServerUrl = "http://${CommonTestUtils.getLocalhostIp()}:${httpPort}/unknown"
        def httpsServerUrl = "https://${CommonTestUtils.getLocalhostIp()}:${httpsPort}/unknown"
        get = new HttpGet(new URI(httpServerUrl));
        response = httpServerClient.execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        def httpUtils = new HttpUtils(30000);

        get = new HttpGet(new URI(httpsServerUrl));
        response = httpUtils.getClient().execute(get);
        httpResponse = EntityUtils.toString(response.getEntity());
        assertEquals("No handler found for url [GET-/unknown]", httpResponse)

        //test MaridConfigConstruction
        assertEquals("key1", MaridConfig.getInstance().getMaridKey())
        assertEquals("invalidcustomer", MaridConfig.getInstance().getApiKey())
        assertEquals("http://${testHttpServerHost}:${testHttpServerPort}".toString(), MaridConfig.getInstance().getOpsgenieApiUrl())
        //test opsgenie client config
        assertEquals(26, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(25000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(20000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)
        assertEquals(MaridConfig.getInstance().getOpsgenieApiUrl(), MaridConfig.getInstance().opsGenieClient.jsonHttpClient.rootUri)
        assertEquals("extraval", MaridConfig.getInstance().getConfiguration().getProperty("extra.config.param"))

        //will not start pubnub
        assertFalse(PubnubAlertActionListener.getInstance().isSubscribed())
    }

    @Test
    public void testLoggerInitWithOldLogProp() {
        def logConfFile = new File(confFile.getParentFile(), "log.properties")
        logConfFile.parentFile.mkdirs();

        applyLoggerConfigurationTest(logConfFile)
    }

    @Test
    public void testLoggerInitWithPathFromSysProp() {
        def logConfFile = new File(TestFile.TESTOUTPUT_DIR + "/integration/customLog.properties")
        logConfFile.parentFile.mkdirs();
        System.setProperty(Bootstrap.LOG_PATH_SYSTEM_PROPERTY, logConfFile.getPath())

        applyLoggerConfigurationTest(logConfFile)
    }

    @Test
    public void testLoggerInitWithPrimarilyUsePathFromSysProp() {
        def logConfFile = new File(confFile.getParentFile(), "log.properties")
        logConfFile.parentFile.mkdirs();
        logConfFile.setText(""""
        log4j.rootLogger=INFO
        """)

        logConfFile = new File(TestFile.TESTOUTPUT_DIR + "/integration/customLog.properties")
        logConfFile.parentFile.mkdirs();
        System.setProperty(Bootstrap.LOG_PATH_SYSTEM_PROPERTY, logConfFile.getPath())

        applyLoggerConfigurationTest(logConfFile)
    }

    @Test
    public void testLoggerInitWithUsingHomeDir() {
        def logConfFile = new File("conf/log.properties")
        logConfFile.parentFile.mkdirs();

        applyLoggerConfigurationTest(logConfFile)

        FileUtils.deleteDirectory(logConfFile.parentFile)
    }

    private void applyLoggerConfigurationTest(File logConfFile) {
        Logger.getRootLogger().setLevel(Level.ERROR);
        logConfFile.setText(""""
        log4j.rootLogger=DEBUG
        """)

        generateKeyStore();
        def httpPort = CommonTestUtils.getLocalPort();
        def httpsPort = httpPort + 1
        confFile.setText("""
            apiKey=123
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            http.proxy.server.enabled=true
            http.proxy.server.port=${proxyPort}
            http.proxy.server.host=${CommonTestUtils.getLocalhostIp()}
            http.proxy.server.username=sezgin
            http.proxy.server.password=sezgin
            opsgenie.api.url=http://${testHttpServerHost}:${testHttpServerPort}/api
            http.server.enabled=true
            http.server.port=${httpPort}
            http.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.enabled=true
            https.server.port=${httpsPort}
            https.server.host=${CommonTestUtils.getLocalhostIp()}
            https.server.keystore=${keystorePath}
            https.server.keyPassword=${keyPass}
            extra.config.param=extraval
         """)
        def pubnubCredentials = PubnubTestUtils.getCredentials();
        def content = JsonUtils.toJson([
                "execute.alert.actions": "true",
                "pubnub.channel"       : "customer1",
                "pubnub.subscribekey"  : pubnubCredentials.getSubscribeKey(),
                "pubnub.ssl.enabled"   : pubnubCredentials.isSslOn(),
        ])
        testHttpServer.setResponseToReturn(content);
        bootstrap.initialize();

        assertEquals(Level.DEBUG, Logger.getRootLogger().getLevel())

        bootstrap.close();

        def httpServerUrl = "http://${CommonTestUtils.getLocalhostIp()}:${httpPort}/unknown"
        def get = new HttpGet(new URI(httpServerUrl));
        try {
            httpClient.execute(get);
            fail("should throw exception")
        }
        catch (HttpHostConnectException ignored) {
        }
    }

    @Override
    void requestRecieved(HttpTestRequest request) {

    }
}
