package com.ifountain.opsgenie.client.marid.alert

import com.ifountain.opsgenie.client.http.*
import com.ifountain.opsgenie.client.marid.MaridConfig
import com.ifountain.opsgenie.client.misc.SmartWait
import com.ifountain.opsgenie.client.pubnub.PubnubTestUtils
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.script.ScriptManager
import com.ifountain.opsgenie.client.script.util.ScriptProxy
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.JSON
import com.ifountain.opsgenie.client.test.util.MaridTestCase
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.test.util.logging.MockAppender
import com.ifountain.opsgenie.client.test.util.logging.TestLogUtils
import org.apache.commons.io.FileUtils
import org.apache.http.HttpStatus
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.littleshoot.proxy.DefaultHttpProxyServer
import org.littleshoot.proxy.ProxyAuthorizationHandler
import pubnub.api.Pubnub

import static org.junit.Assert.*

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/6/12 2:21 PM
 */
class PubnubAlertActionListenerTest extends MaridTestCase implements HttpTestRequestListener {
    def scriptsDir = new TestFile("scripts")
    static def scriptCalls = [];
    Pubnub channel;
    def channelName;
    MockAppender logAppender;
    static def receivedRequests = [];
    static HttpTestServer httpServer;
    static def host;
    static def port;
    public static Object scriptWaitLock = new Object()

    @BeforeClass
    public static void beforeClass() {
        MaridTestCase.beforeClass()
        host = CommonTestUtils.getLocalhostIp()
        port = CommonTestUtils.getLocalPort()
    }

    @Before
    public void setUp() {
        httpServer = new HttpTestServer(host, port)
        httpServer.addRequestListener(this)
        httpServer.open();
        MaridConfig.destroyInstance();
        MaridConfig.getInstance().setOpsgenieApiUrl("http://${host}:${port}");
        MaridConfig.getInstance().setApiKey("customer1")
        MaridConfig.getInstance().setConfiguration(new Properties([prop1: "prop1Value"]))
        MaridConfig.getInstance().setOpsGenieHttpClient(new OpsGenieHttpClient());
        MaridConfig.getInstance().setOpsGenieClient(new OpsGenieClientMock());
        scriptCalls.clear();
        channelName = "customer1AlertExecutor"
        receivedRequests.clear();
        FileUtils.deleteDirectory(scriptsDir);
        scriptsDir.mkdirs();
        ScriptManager.getInstance().initialize(scriptsDir.getPath());
        def credentials = PubnubTestUtils.getCredentials();
        channel = new Pubnub(credentials.getPublishKey(), credentials.getSubscribeKey(),
                credentials.getSecretKey(), MaridConfig.getInstance().getApiKey(), credentials.isSslOn())

        logAppender = TestLogUtils.addMockAppender(Logger.getLogger(PubnubAlertActionListener.class))
        PubnubChannelParameters alertActionExecutorChannelParameters = new PubnubChannelParameters();
        alertActionExecutorChannelParameters.setChannel(channelName);
        alertActionExecutorChannelParameters.setSubscribeKey(credentials.getSubscribeKey())
        alertActionExecutorChannelParameters.setCipherKey(MaridConfig.getInstance().getApiKey())
        alertActionExecutorChannelParameters.setSslOn(credentials.isSslOn())
        PubnubAlertActionListener.getInstance().initialize(alertActionExecutorChannelParameters)
        SmartWait.waitForClosure {
            assertTrue(PubnubAlertActionListener.getInstance().isSubscribed())
        }
        httpServer.setResponseToReturn("success");
    }

    @After
    public void tearDown() {
        Thread.sleep(300);
        PubnubAlertActionListener.destroyInstance();
        TestLogUtils.disableLogger(Logger.getLogger(PubnubAlertActionListener.class))
        MaridConfig.destroyInstance();
        httpServer.close();
        super.tearDown()
    }

    @Test
    public void testActionScriptExecution() throws Exception {
        def scriptFile = new File(scriptsDir, "restart.groovy");
        scriptFile.setText("""
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ALERT});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ACTION});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_SOURCE});

        """)

        JSONObject json = new JSONObject();
        JSONObject alertJson = new JSONObject();
        alertJson.put("alertId", "alert1")
        alertJson.put("entity", "router1")
        alertJson.put("username", "user1")
        json.put("alert", alertJson.toString())
        json.put("source", JSON.toJson([type: "api"]))
        def action = "restart"
        json.put("action", action)

        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            assertEquals(6, scriptCalls.size());
            assertEquals(1, receivedRequests.size());
        }

        def alert = scriptCalls[0]
        assertEquals("alert1", alert.alertId)
        assertEquals("router1", alert.entity)
        assertEquals("user1", alert.username)

        assertEquals(action, scriptCalls[1])
        assertTrue(scriptCalls[2] instanceof ScriptProxy)
        assertSame(MaridConfig.getInstance().getOpsGenieClient(), scriptCalls[2].opsGenieClient)
        assertEquals(MaridConfig.getInstance().getConfiguration(), scriptCalls[3])
        assertSame(Logger.getLogger("script." + scriptFile.getName()), scriptCalls[4])
        assertEquals([type: "api"], scriptCalls[5])

        HttpTestRequest request = receivedRequests[0]
        assertEquals("/v1/json/marid/actionExecutionResult", request.getUrl())
        def requestParams = request.getParameters();
        assertEquals("true", requestParams.success)
        assertEquals("alert1", requestParams.alertId)
        assertEquals("restart", requestParams.alertAction)
        assertEquals(MaridConfig.getInstance().getApiKey(), requestParams.apiKey)

        //script file name convention
        json = new JSONObject();
        alertJson = new JSONObject();
        alertJson.put("alertId", "alert2")
        alertJson.put("username", "user2")
        json.put("alert", alertJson.toString())
        action = "re STA RT  ";
        json.put("action", action)
        scriptCalls.clear();
        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            assertEquals(5, scriptCalls.size());
            assertEquals(2, receivedRequests.size());
        }

        alert = scriptCalls[0]
        assertEquals("alert2", alert.alertId)
        assertEquals("user2", alert.username)
        assertEquals(action.trim(), scriptCalls[1])
        assertTrue(scriptCalls[2] instanceof ScriptProxy)
        assertSame(MaridConfig.getInstance().getOpsGenieClient(), scriptCalls[2].opsGenieClient)
        assertEquals(MaridConfig.getInstance().getConfiguration(), scriptCalls[3])
        assertSame(Logger.getLogger("script." + scriptFile.getName()), scriptCalls[4])

        request = receivedRequests[0]
        assertEquals("/v1/json/marid/actionExecutionResult", request.getUrl())
        requestParams = request.getParameters();
        assertEquals("true", requestParams.success)
        assertEquals("alert1", requestParams.alertId)
        assertEquals("restart", requestParams.alertAction)
        assertEquals(MaridConfig.getInstance().getApiKey(), requestParams.apiKey)
    }

    @Test
    public void testScriptExecutionWithMappedAction() throws Exception {
        def scriptFile = new File(scriptsDir, "restart.groovy");
        scriptFile.setText("""
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_MAPPED_ACTION});
        """)

        JSONObject json = new JSONObject();
        JSONObject params = new JSONObject();
        params.put("mappedActionV2", ["name": "restart"])
        params.put("action", "Create")
        params.put("entity", "router1")
        params.put("alertId", "alert1")
        json.put("params", params.toString())
        json.put("source", JSON.toJson([type: "api"]))

        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            assertEquals(1, scriptCalls.size());
            assertEquals(1, receivedRequests.size());
        }

        assertEquals("restart", scriptCalls[0])

        HttpTestRequest request = receivedRequests[0]
        assertEquals("/v1/json/marid/actionExecutionResult", request.getUrl())
        def requestParams = request.getParameters();
        assertEquals("true", requestParams.success)
        assertEquals("alert1", requestParams.alertId)
        assertEquals("restart", requestParams.mappedAction)
        assertEquals(MaridConfig.getInstance().getApiKey(), requestParams.apiKey)
    }

    @Test
    public void testActionScriptExecutionWillNotBlockOtherScriptExecutions() throws Exception {
        try {
            def scriptFile1 = new File(scriptsDir, "script1.groovy");
            scriptFile1.setText("""
            ${this.class.getName()}.scriptCall("script1");
        """)

            def scriptFile2 = new File(scriptsDir, "script2.groovy");
            scriptFile2.setText("""
            synchronized(${PubnubAlertActionListenerTest.class.getName()}.scriptWaitLock){
                ${this.class.getName()}.scriptCall("script2");
                ${PubnubAlertActionListenerTest.class.getName()}.scriptWaitLock.wait()
                ${this.class.getName()}.scriptCall("script2_end");
            }
        """)


            JSONObject json = new JSONObject();
            JSONObject alertJson = new JSONObject();
            alertJson.put("alertId", "alert1")
            alertJson.put("entity", "router1")
            alertJson.put("username", "user1")
            json.put("alert", alertJson.toString())
            def action = "script2"
            json.put("action", action)

            channel.publish(channelName, json)

            SmartWait.waitForClosure {
                assertEquals(1, scriptCalls.size());
                assertTrue(scriptCalls.contains("script2"));
            }

            Thread.sleep(500)

            json = new JSONObject();
            alertJson = new JSONObject();
            alertJson.put("alertId", "alert1")
            alertJson.put("entity", "router1")
            alertJson.put("username", "user1")
            json.put("alert", alertJson.toString())
            action = "script1"
            json.put("action", action)

            channel.publish(channelName, json)

            SmartWait.waitForClosure {
                assertEquals(2, scriptCalls.size());
                assertTrue(scriptCalls.contains("script1"));
                assertTrue(scriptCalls.contains("script2"));
            }

            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }

            SmartWait.waitForClosure {
                assertEquals(3, scriptCalls.size());
                assertTrue(scriptCalls.contains("script1"));
                assertTrue(scriptCalls.contains("script2"));
                assertTrue(scriptCalls.contains("script2_end"));
            }
        } finally {
            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }
        }


    }


    @Test
    public void testDestroyWillWaitGracefullTerminationAndThenWillTerminate() throws Exception {
        def pubnubShutdownWaitTime = 3000
        PubnubAlertActionListener.getInstance().setShutdownWaitTime(pubnubShutdownWaitTime);

        try {
            def scriptFile2 = new File(scriptsDir, "script2.groovy");
            scriptFile2.setText("""
            synchronized(${PubnubAlertActionListenerTest.class.getName()}.scriptWaitLock){
                ${this.class.getName()}.scriptCall("script2");
                try{
                ${PubnubAlertActionListenerTest.class.getName()}.scriptWaitLock.wait()
                }
                catch(Throwable t){
                ${this.class.getName()}.scriptCall("script2_exception");
                }

            }
        """)


            JSONObject json = new JSONObject();
            JSONObject alertJson = new JSONObject();
            alertJson.put("alertId", "alert1")
            alertJson.put("entity", "router1")
            alertJson.put("username", "user1")
            json.put("alert", alertJson.toString())
            def action = "script2"
            json.put("action", action)

            channel.publish(channelName, json)

            SmartWait.waitForClosure {
                assertEquals(1, scriptCalls.size());
                assertTrue(scriptCalls.contains("script2"));
            }

            Thread.sleep(500)


            long t = System.currentTimeMillis()
            PubnubAlertActionListener.destroyInstance();

            def shutdownTime = System.currentTimeMillis() - t
            assertTrue("shutdownTime:" + shutdownTime + " should be greater than " + pubnubShutdownWaitTime, shutdownTime >= pubnubShutdownWaitTime)

            SmartWait.waitForClosure {
                assertEquals(2, scriptCalls.size());
                assertTrue(scriptCalls.contains("script2"));
                assertTrue(scriptCalls.contains("script2_exception"));
            }
        } finally {
            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }
        }


    }

    @Test
    public void testActionScriptExecutionWithProxy() throws Exception {
        int proxyPort = port + 15;
        DefaultHttpProxyServer proxyServer = new DefaultHttpProxyServer(proxyPort);
        proxyServer.setHost(host);
        PubnubAlertActionListener.destroyInstance();
        try {
            def credentials = PubnubTestUtils.getCredentials();
            PubnubChannelParameters alertActionExecutorChannelParameters = new PubnubChannelParameters();
            alertActionExecutorChannelParameters.setChannel(channelName);
            alertActionExecutorChannelParameters.setSubscribeKey(credentials.getSubscribeKey())
            alertActionExecutorChannelParameters.setCipherKey(MaridConfig.getInstance().getApiKey())
            alertActionExecutorChannelParameters.setSslOn(credentials.isSslOn())
            alertActionExecutorChannelParameters.setProxyEnabled(true)
            alertActionExecutorChannelParameters.setProxyHost(host)
            alertActionExecutorChannelParameters.setProxyPort(proxyPort)
            //test cannot connect if proxy is not started
            PubnubAlertActionListener.getInstance().initialize(alertActionExecutorChannelParameters)
            SmartWait.waitForClosure {
                assertTrue(PubnubAlertActionListener.getInstance().lastErrorMessage.indexOf("Lost Network Connection") >= 0)
            }
            PubnubAlertActionListener.destroyInstance();

            //test successfull connection via proxy
            proxyServer.start();
            PubnubAlertActionListener.getInstance().initialize(alertActionExecutorChannelParameters)
            SmartWait.waitForClosure {
                assertTrue(PubnubAlertActionListener.getInstance().isSubscribed())
            }


            def scriptFile = new File(scriptsDir, "restart.groovy");
            scriptFile.setText("""
               ${this.class.getName()}.scriptCall("ack");
            """)

            JSONObject json = new JSONObject();
            JSONObject alertJson = new JSONObject();
            alertJson.put("alertId", "alert1")
            alertJson.put("entity", "router1")
            alertJson.put("username", "user1")
            json.put("alert", alertJson.toString())
            def action = "restart"
            json.put("action", action)

            channel.publish(channelName, json)
            SmartWait.waitForClosure {
                assertEquals(1, scriptCalls.size());
                assertEquals(1, receivedRequests.size());
            }
            assertEquals("ack", scriptCalls[0])
        }
        finally {
            proxyServer.stop();
        }
    }

    @Test
    public void testActionScriptExecutionWithAuthProxy() throws Exception {
        int proxyPort = port + 15;
        DefaultHttpProxyServer proxyServer = new DefaultHttpProxyServer(proxyPort);
        proxyServer.setHost(host);
        String username = "user1"
        String password = "password1"
        boolean proxyUsed = false;
        proxyServer.addProxyAuthenticationHandler(new ProxyAuthorizationHandler() {
            @Override
            public boolean authenticate(String usernamep, String passwordp) {
                proxyUsed = true;
                return username == usernamep && passwordp == password;
            }
        });
        proxyServer.start();
        PubnubAlertActionListener.destroyInstance();
        try {
            def credentials = PubnubTestUtils.getCredentials();
            PubnubChannelParameters alertActionExecutorChannelParameters = new PubnubChannelParameters();
            alertActionExecutorChannelParameters.setChannel(channelName);
            alertActionExecutorChannelParameters.setSubscribeKey(credentials.getSubscribeKey())
            alertActionExecutorChannelParameters.setCipherKey(MaridConfig.getInstance().getApiKey())
            alertActionExecutorChannelParameters.setSslOn(credentials.isSslOn())
            alertActionExecutorChannelParameters.setProxyEnabled(true)
            alertActionExecutorChannelParameters.setProxyHost(host)
            alertActionExecutorChannelParameters.setProxyPort(proxyPort)
            alertActionExecutorChannelParameters.setProxyUsername(username)
            alertActionExecutorChannelParameters.setProxyPassword(password)
            PubnubAlertActionListener.getInstance().initialize(alertActionExecutorChannelParameters)
            SmartWait.waitForClosure {
                assertTrue(PubnubAlertActionListener.getInstance().isSubscribed())
            }
            assertTrue(proxyUsed);


            def scriptFile = new File(scriptsDir, "restart.groovy");
            scriptFile.setText("""
               ${this.class.getName()}.scriptCall("ack");
            """)

            JSONObject json = new JSONObject();
            JSONObject alertJson = new JSONObject();
            alertJson.put("alertId", "alert1")
            alertJson.put("entity", "router1")
            alertJson.put("username", "user1")
            json.put("alert", alertJson.toString())
            def action = "restart"
            json.put("action", action)

            channel.publish(channelName, json)
            SmartWait.waitForClosure {
                assertEquals(1, scriptCalls.size());
                assertEquals(1, receivedRequests.size());
            }
            assertEquals("ack", scriptCalls[0])
        }
        finally {
            proxyServer.stop();
        }
    }

    @Test
    public void testActionScriptExecutionWithHttpsProxy() throws Exception {
        int proxyPort = port + 15;
        DefaultHttpProxyServer proxyServer = new DefaultHttpProxyServer(proxyPort);
        proxyServer.setHost(host);
        proxyServer.start();
        PubnubAlertActionListener.destroyInstance();
        try {
            def credentials = PubnubTestUtils.getCredentials();
            PubnubChannelParameters alertActionExecutorChannelParameters = new PubnubChannelParameters();
            alertActionExecutorChannelParameters.setChannel(channelName);
            alertActionExecutorChannelParameters.setSubscribeKey(credentials.getSubscribeKey())
            alertActionExecutorChannelParameters.setCipherKey(MaridConfig.getInstance().getApiKey())
            alertActionExecutorChannelParameters.setSslOn(credentials.isSslOn())
            alertActionExecutorChannelParameters.setProxyEnabled(true)
            alertActionExecutorChannelParameters.setProxyHost(host)
            alertActionExecutorChannelParameters.setProxyPort(proxyPort)
            alertActionExecutorChannelParameters.setProxyProtocol("https")
            PubnubAlertActionListener.getInstance().initialize(alertActionExecutorChannelParameters)
            SmartWait.waitForClosure {
                assertTrue(PubnubAlertActionListener.getInstance().lastErrorMessage, PubnubAlertActionListener.getInstance().lastErrorMessage.indexOf("Lost Network Connection") >= 0)
            }
        }
        finally {
            proxyServer.stop();
        }
    }

    @Test
    public void testLogAddedIfMessageDoesNotContainAction() throws Exception {
        JSONObject json = new JSONObject()
        json.put("alert", "{}")
        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.ERROR.toString())
            assertTrue(logMessages.size() > 0)
            assertTrue(logMessages.toString(), logMessages[logMessages.size() - 1].indexOf("No action specified") > -1);
        }
    }

    @Test
    public void testLogAddedAndOpsGenieIsInformedIfSpecifiedScriptCannotBeFound() throws Exception {
        JSONObject json = new JSONObject()
        json.put("action", "restart")
        json.put("alert", "{\"alertId\":\"alert1\", \"username\":\"user1\"}")
        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.WARN.toString())
            assertTrue(logMessages.size() > 0)
            assertTrue(logMessages.toString(), logMessages[logMessages.size() - 1].indexOf("No script file found for action [restart]") > -1);
        }

        SmartWait.waitForClosure {
            assertEquals(1, receivedRequests.size());
        }

        HttpTestRequest request = receivedRequests[0]
        assertEquals("/v1/json/marid/actionExecutionResult", request.getUrl())
        def requestParams = request.getParameters()
        assertEquals("false", requestParams.success)
        assertEquals("alert1", requestParams.alertId)
        assertEquals("restart", requestParams.alertAction)
        assertEquals("No script file found for action [restart]", requestParams.failureMessage)
        assertEquals(MaridConfig.getInstance().getApiKey(), requestParams.apiKey)
    }

    @Test
    public void testLogAddedIfAlertJsonCannotBeFound() throws Exception {
        def scriptFile = new File(scriptsDir, "restart.groovy");
        scriptFile.setText("""
           ${this.class.getName()}.scriptCall(alert);
        """)

        JSONObject json = new JSONObject()
        json.put("action", "restart")
        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.ERROR.toString())
            assertTrue(logMessages.size() > 0)
            assertTrue(logMessages.toString(), logMessages[logMessages.size() - 1].indexOf("No alert specified") > -1);
        }
    }

    @Test
    public void testLogAddedAndOpsGenieIsInformedIfScriptExecutionThrowsError() throws Exception {
        def scriptFile = new File(scriptsDir, "restart.groovy");
        scriptFile.setText("""
           throw new Exception('script exception')
        """)

        JSONObject json = new JSONObject()
        json.put("action", "restart")
        json.put("alert", "{\"alertId\":\"alert1\", \"username\":\"user1\"}")
        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.WARN.toString())
            assertTrue(logMessages.size() > 0)
            assertTrue(logMessages.toString(), logMessages[logMessages.size() - 1].indexOf("Could not process message") > -1);
            assertTrue(logMessages.toString(), logMessages[logMessages.size() - 1].indexOf("script exception") > -1);
        }

        SmartWait.waitForClosure {
            assertEquals(1, receivedRequests.size());
        }

        HttpTestRequest request = receivedRequests[0]
        assertEquals("/v1/json/marid/actionExecutionResult", request.getUrl())
        def requestParams = request.getParameters()
        assertEquals("false", requestParams.success)
        assertEquals("alert1", requestParams.alertId)
        assertEquals("restart", requestParams.alertAction)
        assertTrue(requestParams.failureMessage.indexOf("script exception") > -1)
        assertEquals(MaridConfig.getInstance().getApiKey(), requestParams.apiKey)
    }

    @Test
    public void testLogAddedIfAlertContentCannotBeParsed() throws Exception {
        def scriptFile = new File(scriptsDir, "restart.groovy");
        scriptFile.setText("""
           ${this.class.getName()}.scriptCall(alert);
        """)

        JSONObject json = new JSONObject()
        json.put("action", "restart")
        json.put("alert", "invalidAlertContent")
        channel.publish(channelName, json)

        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.ERROR.toString())
            assertTrue(logMessages.size() > 0)
            assertTrue(logMessages.toString(), logMessages[logMessages.size() - 1].indexOf("Could not parse alert content") > -1);
        }
    }

    @Test
    public void testLogAddedIfOpsGenieDoesNotReturnOK() throws Exception {
        httpServer.setResponseToReturn(new HttpTestResponse("failure".getBytes(), HttpStatus.SC_BAD_REQUEST))
        def scriptFile = new File(scriptsDir, "restart.groovy");
        scriptFile.setText("""
           ${this.class.getName()}.scriptCall(alert);
        """)

        JSONObject json = new JSONObject()
        json.put("action", "restart")
        json.put("alert", "{\"alertId\":\"alert1\", \"username\":\"user1\"}")
        channel.publish(channelName, json)
        SmartWait.waitForClosure {
            assertEquals(1, receivedRequests.size());
        }
        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.WARN.toString())
            assertTrue(logMessages.size() > 0)
            assertTrue(logMessages.toString(), logMessages[logMessages.size() - 1].indexOf("Could not send action result to OpsGenie. HttpStatus:") > -1);
        }
    }

    public static void scriptCall(Object call) {
        scriptCalls.add(call);
    }

    @Override
    void requestRecieved(HttpTestRequest request) {
        receivedRequests.add(request)
    }
}
