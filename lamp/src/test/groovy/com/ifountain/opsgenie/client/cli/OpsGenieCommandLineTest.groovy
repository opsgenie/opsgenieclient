package com.ifountain.opsgenie.client.cli

import com.ifountain.opsgenie.client.IOpsGenieClient
import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.model.alert.*
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyResponse
import com.ifountain.opsgenie.client.model.customer.HeartbeatRequest
import com.ifountain.opsgenie.client.model.customer.HeartbeatResponse
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationResponse
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.script.ScriptManagerTest
import com.ifountain.opsgenie.client.script.util.MockBsfEngine
import com.ifountain.opsgenie.client.script.util.ScriptProxy
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.util.ClientConfiguration
import com.ifountain.opsgenie.client.util.ClientProxyConfiguration
import org.apache.commons.io.FileUtils
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class OpsGenieCommandLineTest {
    OpsGenieCommandLine cli;
    OpsGenieClientMock opsGenieClient;
    def originalOut, originalErr;
    ByteArrayOutputStream sysout;
    ByteArrayOutputStream syserr;
    static List messages;
    File confFile
    File scriptDir;

    @Before
    public void setUp() {
        messages = new ArrayList();
        originalOut = System.out;
        originalErr = System.err;
        sysout = new ByteArrayOutputStream();
        syserr = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysout))
        System.setErr(new PrintStream(syserr))
        opsGenieClient = new OpsGenieClientMock();
        cli = new OpsGenieCommandLine() {
            @Override
            protected IOpsGenieClient createOpsGenieClient(ClientConfiguration clientConfiguration) {
                opsGenieClient.setClientConfiguration(clientConfiguration);
                return opsGenieClient;
            }
        };
        FileUtils.deleteDirectory(new TestFile());

        ScriptManagerTest.messages = [];
        confFile = new File(TestFile.TESTOUTPUT_DIR + "/conf/lamp.conf")
        confFile.parentFile.mkdirs();
        scriptDir = new File(TestFile.TESTOUTPUT_DIR + "/scripts")
        System.setProperty(OpsGenieCommandLine.LAMP_CONF_DIR_SYSTEM_PROPERTY, confFile.getParentFile().getPath())
        System.setProperty(OpsGenieCommandLine.LAMP_SCRIPTS_DIR_SYSTEM_PROPERTY, scriptDir.getPath())
    }

    @Override
    protected void tearDown() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @Test
    public void testNoOption() throws Exception {
        assertFalse(cli.run([] as String[]))
        def output = sysout.toString()
        assertTrue(output.startsWith("No command has been specified."))
        assertTrue(output.contains("Usage"));
    }

    @Test
    public void testCreateAlertSuccessfully() throws Exception {
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId("alertId1");
        opsGenieClient.alert().setCreateAlertResponse(response)

        def args = ["createAlert", "--message", "my", "message", "with", "space",
                    "--apiKey", "customer1",
                    "--description", "my", "description", "with", "space",
                    "--source", "source1", "--entity", "entity1", "--alias", "alias1",
                    "--note", "alert note", "--user", "someuser",
                    "--tags", "tag1,spaced", "tag2", "--actions", "action1,spaced", "action2",
                    "--recipients", "sezgin@ifountain.com,berkay@ifountain.com",
                    "--teams", "team1,team2",
                    "-Dparam1=value1", "-Dparam2=value2"];

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CreateAlertRequest request = executedRequests[0] as CreateAlertRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("my message with space", request.getMessage())
        assertEquals("my description with space", request.getDescription())
        assertEquals("source1", request.getSource())
        assertEquals("entity1", request.getEntity())
        assertEquals("alias1", request.getAlias())
        assertEquals("someuser", request.getUser())
        assertEquals("alert note", request.getNote())

        def tags = request.getTags();
        assertEquals(2, tags.size())
        assertTrue(tags.contains("tag1"))
        assertTrue(tags.contains("spaced tag2"))

        def actions = request.getActions()
        assertEquals(2, actions.size())
        assertTrue(actions.contains("action1"))
        assertTrue(actions.contains("spaced action2"))

        def recipients = request.getRecipients()
        assertEquals(2, recipients.size())
        assertTrue(recipients.contains("sezgin@ifountain.com"))
        assertTrue(recipients.contains("berkay@ifountain.com"))

        def teams = request.getTeams()
        assertEquals(2, teams.size())
        assertTrue(teams.contains("team1"))
        assertTrue(teams.contains("team2"))

        def details = request.getDetails();
        assertEquals(2, details.size())
        assertEquals("value1", details.param1)
        assertEquals("value2", details.param2)

        assertTrue(sysout.toString().startsWith("alertId=alertId1"))
    }

    //TODO: Will be removed after customer key
    @Test
    public void testCreateAlertSuccessfullyWithCustomerKey() throws Exception {
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId("alertId1");
        opsGenieClient.alert().setCreateAlertResponse(response)

        def args = ["createAlert", "--message", "my", "message", "with", "space",
                    "--customerKey", "customer1",
                    "--description", "my", "description", "with", "space",
                    "--source", "source1", "--entity", "entity1", "--alias", "alias1",
                    "--note", "alert note", "--user", "someuser",
                    "--tags", "tag1,spaced", "tag2", "--actions", "action1,spaced", "action2",
                    "--recipients", "sezgin@ifountain.com,berkay@ifountain.com",
                    "-Dparam1=value1", "-Dparam2=value2"];

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CreateAlertRequest request = executedRequests[0] as CreateAlertRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("my message with space", request.getMessage())
        assertEquals("my description with space", request.getDescription())
        assertEquals("source1", request.getSource())
        assertEquals("entity1", request.getEntity())
        assertEquals("alias1", request.getAlias())
        assertEquals("someuser", request.getUser())
        assertEquals("alert note", request.getNote())

        def tags = request.getTags();
        assertEquals(2, tags.size())
        assertTrue(tags.contains("tag1"))
        assertTrue(tags.contains("spaced tag2"))

        def actions = request.getActions()
        assertEquals(2, actions.size())
        assertTrue(actions.contains("action1"))
        assertTrue(actions.contains("spaced action2"))

        def recipients = request.getRecipients()
        assertEquals(2, recipients.size())
        assertTrue(recipients.contains("sezgin@ifountain.com"))
        assertTrue(recipients.contains("berkay@ifountain.com"))

        def details = request.getDetails();
        assertEquals(2, details.size())
        assertEquals("value1", details.param1)
        assertEquals("value2", details.param2)

        assertTrue(sysout.toString().startsWith("alertId=alertId1"))
    }

    @Test
    public void testLoggerInitWithOldLogProp() throws Exception {
        def logConfFile = new File(confFile.getParentFile(), "log.properties")
        logConfFile.parentFile.mkdirs();

        applyLoggerConfigurationTest(logConfFile)
    }

    @Test
    public void testLoggerInitWithPathFromSysProp() {
        FileUtils.deleteDirectory(new TestFile())

        def logConfFile = new File(TestFile.TESTOUTPUT_DIR + "/integration/customLog.properties")
        logConfFile.parentFile.mkdirs();
        System.setProperty(OpsGenieCommandLine.LOG_PATH_SYSTEM_PROPERTY, logConfFile.getPath())

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
        System.setProperty(OpsGenieCommandLine.LOG_PATH_SYSTEM_PROPERTY, logConfFile.getPath())

        applyLoggerConfigurationTest(logConfFile)
    }

    @Test
    public void testLoggerInitWithUsingHomeDir() {
        FileUtils.deleteDirectory(new TestFile())

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
        assertFalse(cli.run([] as String[]))
        assertEquals(Level.DEBUG, Logger.getRootLogger().getLevel())
    }

    @Test
    public void testExecuteScriptWithNewEngine() throws Exception {
        String extension1 = "ext" + ((int) (Math.random() * 1000000));
        String extension2 = "ext" + ((int) (Math.random() * 1000000));
        confFile.setText("""
           apiKey=key1
           user=user1
           script.engines=custengine
           script.engine.custengine.class=${MockBsfEngine.class.name}
           script.engine.custengine.extensions=${extension1},${extension2}

        """)
        File trialGroovy = new File(scriptDir, "trial." + extension1)
        trialGroovy.parentFile.mkdirs();
        trialGroovy.setText("""""")
        def args = ["executeScript", "--name", trialGroovy.getName(), "-Dparam1=value1"];
        assertTrue(cli.run(args as String[]))
        assertEquals("Successfully executed " + trialGroovy.getName(), sysout.toString().trim())
        assertEquals(1, ScriptManagerTest.messages.size())
        assertEquals("run2", ScriptManagerTest.messages[0])

        ScriptManagerTest.messages.clear();
        sysout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysout))
        trialGroovy = new File(scriptDir, "trial." + extension2)
        trialGroovy.setText("""
            ${this.getClass().getName()}.addMessage(params.param1);
        """)
        args = ["executeScript", "--name", trialGroovy.getName(), "-Dparam1=value2"];
        assertTrue(cli.run(args as String[]))
        assertEquals("Successfully executed " + trialGroovy.getName().trim(), sysout.toString().trim())
        assertEquals(1, ScriptManagerTest.messages.size())
        assertEquals("run2", ScriptManagerTest.messages[0])
    }

    @Test
    public void testExecuteScriptWithNewEngineThrowsExceptionIfClassPropertyIsMissing() throws Exception {
        confFile.setText("""
           apiKey=key1
           user=user1
           script.engines=custengine
           script.engine.custengine.extensions=ext1

        """)
        File trialGroovy = new File(scriptDir, "trial.ext1")
        def args = ["executeScript", "--name", trialGroovy.getName(), "-Dparam1=value1"];
        assertFalse(cli.run(args as String[]))
        assertTrue(sysout.toString().indexOf("script.engine.custengine.class") >= 0)

        cli = new OpsGenieCommandLine();
        confFile.parentFile.mkdirs();
        confFile.setText("""
           apiKey=key1
           user=user1
           script.engines=custengine
           script.engine.custengine.class=ext1

        """)
        args = ["executeScript", "--name", trialGroovy.getName(), "-Dparam1=value1"];
        assertFalse(cli.run(args as String[]))
        assertTrue(sysout.toString().indexOf("script.engine.custengine.extensions") >= 0)
    }


    @Test
    public void testExecuteScriptSuccessfully() throws Exception {
        confFile.setText("""
           apiKey=key1
           user=user1
           prop1=prop1value
        """)
        File scriptDir = new File(TestFile.TESTOUTPUT_DIR + "/scripts")
        File trialGroovy = new File(scriptDir, "trial.groovy")
        trialGroovy.parentFile.mkdirs()
        trialGroovy.setText("""
            ${this.getClass().getName()}.addMessage(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS});
            ${this.getClass().getName()}.addMessage(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF});
            ${this.getClass().getName()}.addMessage(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER});
            ${this.getClass().getName()}.addMessage(${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT
        });
        """)
        def args = ["executeScript", "--name", trialGroovy.getName(), "-Dparam1=value1"];
        assertTrue(cli.run(args as String[]))
        assertEquals("Successfully executed " + trialGroovy.getName().trim(), sysout.toString().trim())
        assertEquals(4, messages.size())
        assertEquals(1, messages[0].size())
        assertEquals("value1", messages[0].param1)
        assertEquals(3, messages[1].size())
        assertEquals("key1", messages[1].get(TestConstants.API.API_KEY))
        assertEquals("user1", messages[1].get(TestConstants.API.USER))
        assertEquals("prop1value", messages[1].get("prop1"))
        assertSame(Logger.getLogger("script." + trialGroovy.getName()), messages[2])
        assertTrue(messages[3] instanceof ScriptProxy)

        messages.clear();
        sysout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysout))
        //test with apiKey from arguments
        args = ["executeScript", "--name", trialGroovy.getName(), "-Dparam1=value1", "--apiKey", "updatedKey"];
        assertTrue(cli.run(args as String[]))
        assertEquals("Successfully executed " + trialGroovy.getName().trim(), sysout.toString().trim())
        assertEquals(4, messages.size())
        assertEquals(1, messages[0].size())
        assertEquals("value1", messages[0].param1)
        assertEquals(3, messages[1].size())
        assertEquals("updatedKey", messages[1].get(TestConstants.API.API_KEY))
        assertEquals("user1", messages[1].get(TestConstants.API.USER))
        assertEquals("prop1value", messages[1].get("prop1"))
        assertSame(Logger.getLogger("script." + trialGroovy.getName()), messages[2])
        assertTrue(messages[3] instanceof ScriptProxy)
    }

    @Test
    public void testExecuteScriptSuccessfullyWithCustomerKeyAndUserParameters() throws Exception {
        confFile.setText("""
           apiKey=key1
           user=user1
        """)
        File scriptDir = new File(TestFile.TESTOUTPUT_DIR + "/scripts")
        File trialGroovy = new File(scriptDir, "trial.groovy")
        trialGroovy.parentFile.mkdirs()
        trialGroovy.setText("""
            ${this.getClass().getName()}.addMessage(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS});
            ${this.getClass().getName()}.addMessage(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF});
            ${this.getClass().getName()}.addMessage(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER});
            ${this.getClass().getName()}.addMessage(${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT
        });
        """)
        def args = ["executeScript", "--name", trialGroovy.getName(), "--apiKey", "key2", "--user", "user2", "-Dparam1=value1"];
        assertTrue(cli.run(args as String[]))
        assertEquals("Successfully executed " + trialGroovy.getName(), sysout.toString().trim())
        assertEquals(4, messages.size())
        assertEquals(1, messages[0].size())
        assertEquals("value1", messages[0].param1)
        assertEquals(2, messages[1].size())
        assertEquals("key2", messages[1].get(TestConstants.API.API_KEY))
        assertEquals("user2", messages[1].get(TestConstants.API.USER))
        assertSame(Logger.getLogger("script." + trialGroovy.getName()), messages[2])
        assertTrue(messages[3] instanceof ScriptProxy)
    }

    @Test
    public void testExecuteScriptWithException() throws Exception {
        def exceptionMessage = "exception message1"
        File trialGroovy = new File(scriptDir, "trial.groovy")
        trialGroovy.parentFile.mkdirs();
        trialGroovy.setText("""
            throw new Exception("${exceptionMessage}")
        """)
        def args = ["executeScript", "--name", trialGroovy.getName(), "-Dparam1=value1"];
        assertFalse(cli.run(args as String[]))
        assertTrue(String.valueOf(sysout.toString()), sysout.toString().indexOf(exceptionMessage) >= 0)
    }

    @Test
    public void testCreateAlertReturningException() throws Exception {
        opsGenieClient.setException(new Exception("No message has been given."));
        def args = ["createAlert", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CreateAlertRequest request = executedRequests[0] as CreateAlertRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getMessage())

        assertTrue(sysout.toString().startsWith("No message has been given"))
    }

    @Test
    public void testCloseAlert() throws Exception {
        opsGenieClient.alert().setCloseAlertResponse(new CloseAlertResponse());
        def args = ["closeAlert", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CloseAlertRequest request = executedRequests[0] as CloseAlertRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testCloseAlertReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["closeAlert", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CloseAlertRequest request = executedRequests[0] as CloseAlertRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testDeleteAlert() throws Exception {
        opsGenieClient.alert().setDeleteAlertResponse(new DeleteAlertResponse());
        def args = ["deleteAlert", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteAlertRequest request = executedRequests[0] as DeleteAlertRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("alias1", request.getAlias())
        assertEquals("someuser", request.getUser());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testDeleteAlertReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["deleteAlert", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteAlertRequest request = executedRequests[0] as DeleteAlertRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testAcknowledge() throws Exception {
        opsGenieClient.alert().setAcknowledgeResponse(new AcknowledgeResponse());
        def args = ["acknowledge", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AcknowledgeRequest request = executedRequests[0] as AcknowledgeRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testAcknowledgeReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["acknowledge", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AcknowledgeRequest request = executedRequests[0] as AcknowledgeRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testRenotify() throws Exception {
        opsGenieClient.alert().setRenotifyResponse(new RenotifyResponse());
        def args = ["renotify", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--note", "comment", "--recipients", "user1@xyz.com", ", group1", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        RenotifyRequest request = executedRequests[0] as RenotifyRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
        assertEquals(2, request.getRecipients().size());
        assertTrue(request.getRecipients()*.recipient.containsAll(["user1@xyz.com", "group1"]));

        //test without recipients
        opsGenieClient.alert().setRenotifyResponse(new RenotifyResponse());
        args = ["renotify", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(2, executedRequests.size())
        request = executedRequests[1] as RenotifyRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
        assertNull(request.getRecipients());
    }

    @Test
    public void testRenotifyReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["renotify", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        RenotifyRequest request = executedRequests[0] as RenotifyRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testTakeOwnership() throws Exception {
        opsGenieClient.alert().setTakeOwnershipResponse(new TakeOwnershipResponse());
        def args = ["takeOwnership", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        TakeOwnershipRequest request = executedRequests[0] as TakeOwnershipRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testTakeOwnershipReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["takeOwnership", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        TakeOwnershipRequest request = executedRequests[0] as TakeOwnershipRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testAssign() throws Exception {
        opsGenieClient.alert().setAssignResponse(new AssignResponse());
        def args = ["assign", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--owner", "someowner", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AssignRequest request = executedRequests[0] as AssignRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("someowner", request.getOwner());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testAssignReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["assign", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AssignRequest request = executedRequests[0] as AssignRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testAddRecipient() throws Exception {
        opsGenieClient.alert().setAddRecipientResponse(new AddRecipientResponse());
        def args = ["addRecipient", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--recipient", "somegroup", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddRecipientRequest request = executedRequests[0] as AddRecipientRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("somegroup", request.getRecipient());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testAddRecipientReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["addRecipient", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddRecipientRequest request = executedRequests[0] as AddRecipientRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testAddTeam() throws Exception {
        opsGenieClient.alert().setAddAlertTeamResponse(new AddAlertTeamResponse());
        def args = ["addTeam", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--user", "someuser", "--team", "someteam", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddAlertTeamRequest request = executedRequests[0] as AddAlertTeamRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("someteam", request.getTeam());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testAddTeamReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["addTeam", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddAlertTeamRequest request = executedRequests[0] as AddAlertTeamRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testAddNote() throws Exception {
        opsGenieClient.alert().setAddNoteResponse(new AddNoteResponse());
        def args = ["addNote", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--note", "mynote", "--user", "someuser", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteRequest request = executedRequests[0] as AddNoteRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        assertEquals("someuser", request.getUser());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testAddNoteReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["addNote", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteRequest request = executedRequests[0] as AddNoteRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testExecuteAction() throws Exception {
        opsGenieClient.alert().setExecuteAlertActionResponse(new ExecuteAlertActionResponse());
        def args = ["executeAction", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1", "--action", "action1", "--user", "someuser", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ExecuteAlertActionRequest request = executedRequests[0] as ExecuteAlertActionRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("action1", request.getAction());
        assertEquals("someuser", request.getUser());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testExecuteActionReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["executeAction", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ExecuteAlertActionRequest request = executedRequests[0] as ExecuteAlertActionRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testAttach() throws Exception {
        opsGenieClient.alert().setAttachResponse(new AttachResponse());
        def args = ["attachFile", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1",
                    "--indexFile", "index.html", "--attachment", "dummy.txt", "--user", "someuser", "--note", "comment", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AttachRequest request = executedRequests[0] as AttachRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("index.html", request.getIndexFile());
        assertEquals("someuser", request.getUser());
        assertEquals(new File("dummy.txt"), request.getFile());
        assertEquals("comment", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testAttachReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["attachFile", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AttachRequest request = executedRequests[0] as AttachRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getAlertId())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testHearbeat() throws Exception {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat(123);
        opsGenieClient.setHeartbeatResponse(response);
        def args = ["heartbeat", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        HeartbeatRequest request = executedRequests[0] as HeartbeatRequest;

        assertEquals("customer1", request.getApiKey())

        assertTrue(sysout.toString().startsWith("heartbeat=123"))
    }

    @Test
    public void testHearbeatWithName() throws Exception {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat(123);
        opsGenieClient.setHeartbeatResponse(response);
        def args = ["heartbeat", "--apiKey", "customer1", "--name", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        HeartbeatRequest request = executedRequests[0] as HeartbeatRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("source1", request.getName())

        assertTrue(sysout.toString().startsWith("heartbeat=123"))
    }


    @Test
    public void testHearbeatWithSource() throws Exception {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat(123);
        opsGenieClient.setHeartbeatResponse(response);
        def args = ["heartbeat", "--apiKey", "customer1", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        HeartbeatRequest request = executedRequests[0] as HeartbeatRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("source1", request.getName())

        assertTrue(sysout.toString().startsWith("heartbeat=123"))
    }

    @Test
    public void testHeartbeatReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Customer does not exist."));
        def args = ["heartbeat", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        HeartbeatRequest request = executedRequests[0] as HeartbeatRequest;
        assertEquals("customer1", request.getApiKey())

        assertTrue(sysout.toString().startsWith("Customer does not exist."))
    }

    @Test
    public void testEnableIntegration() throws Exception {
        EnableIntegrationResponse response = new EnableIntegrationResponse();
        response.setSuccess(true);
        opsGenieClient.integration().setEnableIntegrationResponse(response);
        def args = ["enable", "--type", "integration", "--name", "integration1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableIntegrationRequest request = executedRequests[0] as EnableIntegrationRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("integration1", request.getName())
        assertNull(request.getId())
        assertTrue(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Integration enabled"))
    }

    @Test
    public void testEnableIntegrationWithId() throws Exception {
        EnableIntegrationResponse response = new EnableIntegrationResponse();
        response.setSuccess(true);
        opsGenieClient.integration().setEnableIntegrationResponse(response);
        def args = ["enable", "--type", "integration", "--id", "integration1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableIntegrationRequest request = executedRequests[0] as EnableIntegrationRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("integration1", request.getId())
        assertNull(request.getName())
        assertTrue(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Integration enabled"))
    }

    @Test
    public void testEnableIntegrationWithInvalidType() throws Exception {
        EnableIntegrationResponse response = new EnableIntegrationResponse();
        response.setSuccess(true);
        opsGenieClient.integration().setEnableIntegrationResponse(response);
        def args = ["enable", "--type", "invalid", "--id", "integration1", "--apiKey", "customer1"]

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(0, executedRequests.size())

        assertTrue(sysout.toString().contains("Invalid type [invalid]. Should be one of [integration, policy]"))
    }


    @Test
    public void testDisableIntegration() throws Exception {
        EnableIntegrationResponse response = new EnableIntegrationResponse();
        response.setSuccess(true);
        opsGenieClient.integration().setEnableIntegrationResponse(response);
        def args = ["disable", "--type", "integration", "--name", "integration1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableIntegrationRequest request = executedRequests[0] as EnableIntegrationRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("integration1", request.getName())
        assertNull(request.getId())
        assertFalse(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Integration disabled"))
    }

    @Test
    public void testDisableIntegrationWithId() throws Exception {
        EnableIntegrationResponse response = new EnableIntegrationResponse();
        response.setSuccess(true);
        opsGenieClient.integration().setEnableIntegrationResponse(response);
        def args = ["disable", "--type", "integration", "--id", "integration1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableIntegrationRequest request = executedRequests[0] as EnableIntegrationRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("integration1", request.getId())
        assertNull(request.getName())
        assertFalse(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Integration disabled"))
    }


    @Test
    public void testEnablePolicy() throws Exception {
        EnableAlertPolicyResponse response = new EnableAlertPolicyResponse();
        response.setSuccess(true);
        opsGenieClient.alertPolicy().setEnableAlertPolicyResponse(response);
        def args = ["enable", "--type", "policy", "--name", "policy1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableAlertPolicyRequest request = executedRequests[0] as EnableAlertPolicyRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("policy1", request.getName())
        assertNull(request.getId())
        assertTrue(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Policy enabled"))
    }

    @Test
    public void testEnablePolicynWithId() throws Exception {
        EnableAlertPolicyResponse response = new EnableAlertPolicyResponse();
        response.setSuccess(true);
        opsGenieClient.alertPolicy().setEnableAlertPolicyResponse(response);
        def args = ["enable", "--type", "policy", "--id", "policy1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableAlertPolicyRequest request = executedRequests[0] as EnableAlertPolicyRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("policy1", request.getId())
        assertNull(request.getName())
        assertTrue(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Policy enabled"))
    }


    @Test
    public void testDisablePolicy() throws Exception {
        EnableAlertPolicyResponse response = new EnableAlertPolicyResponse();
        response.setSuccess(true);
        opsGenieClient.alertPolicy().setEnableAlertPolicyResponse(response);
        def args = ["disable", "--type", "policy", "--name", "policy1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableAlertPolicyRequest request = executedRequests[0] as EnableAlertPolicyRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("policy1", request.getName())
        assertNull(request.getId())
        assertFalse(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Policy disabled"))
    }

    @Test
    public void testDisablePolicyWithId() throws Exception {
        EnableAlertPolicyResponse response = new EnableAlertPolicyResponse();
        response.setSuccess(true);
        opsGenieClient.alertPolicy().setEnableAlertPolicyResponse(response);
        def args = ["disable", "--type", "policy", "--id", "policy1", "--apiKey", "customer1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableAlertPolicyRequest request = executedRequests[0] as EnableAlertPolicyRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("policy1", request.getId())
        assertNull(request.getName())
        assertFalse(request.isEnabled())

        assertTrue(sysout.toString().startsWith("Policy disabled"))
    }

    @Test
    public void testGetAlert() throws Exception {
        opsGenieClient.alert().setGetAlertResponse(new GetAlertResponse(json: "{\"message\":\"alert message\"}"));
        def args = ["getAlert", "--apiKey", "customer1", "--alias", "alias1", "--alertId", "alertId1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetAlertRequest request = executedRequests[0] as GetAlertRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());

        assertTrue(sysout.toString().startsWith("{\"message\":\"alert message\"}"))
    }

    @Test
    public void testGetAlertReturningException() throws Exception {
        opsGenieClient.setException(new Exception("Alert does not exist."));
        def args = ["getAlert", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetAlertRequest request = executedRequests[0] as GetAlertRequest;
        assertEquals("customer1", request.getApiKey())

        assertTrue(sysout.toString().startsWith("Alert does not exist."))
    }

    @Test
    public void testHelp() throws Exception {
        def args = ["--help"]
        assertTrue(cli.run(args as String[]));
        assertTrue(sysout.toString().contains("Usage"));
    }

    @Test
    public void testUserAndCustomerKeyFromConfFile() throws Exception {
        confFile.setText("""
            apiKey=customer1
            user=defaultUser
        """)
        opsGenieClient.alert().setAddNoteResponse(new AddNoteResponse());
        def args = ["addNote", "--note", "mynote", "--alertId", "alertId1", "--alias", "alias1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteRequest request = executedRequests[0] as AddNoteRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        assertEquals("defaultUser", request.getUser());

        //option overriding

        args = ["addNote", "--apiKey", "customer2", "--user", "otheruser", "--note", "mynote", "--alertId", "alertId1", "--alias", "alias1", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(2, executedRequests.size())
        request = executedRequests[1] as AddNoteRequest;

        assertEquals("customer2", request.getApiKey())
        assertEquals("otheruser", request.getUser())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        assertEquals("source1", request.getSource());
    }

    //TODO: Will be removed whne customerkey is deprecated
    @Test
    public void testUserAndApiKeyFromConfFileWithCustomerKey() throws Exception {
        confFile.setText("""
            customerKey=customer1
            user=defaultUser
        """)
        opsGenieClient.alert().setAddNoteResponse(new AddNoteResponse());
        def args = ["addNote", "--note", "mynote", "--alertId", "alertId1", "--alias", "alias1"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteRequest request = executedRequests[0] as AddNoteRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        assertEquals("defaultUser", request.getUser());

        //option overriding

        args = ["addNote", "--apiKey", "customer2", "--user", "otheruser", "--note", "mynote", "--alertId", "alertId1", "--alias", "alias1", "--source", "source1"]

        assertTrue(cli.run(args as String[]))

        executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(2, executedRequests.size())
        request = executedRequests[1] as AddNoteRequest;

        assertEquals("customer2", request.getApiKey())
        assertEquals("otheruser", request.getUser())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testClientConfigurationFromOldConfFile() throws Exception {
        applyConfigurationTest()
    }

    @Test
    public void testClientConfigurationFromConfFileWithPathFromSysProp() throws Exception {
        FileUtils.deleteDirectory(new TestFile())

        confFile = new File(TestFile.TESTOUTPUT_DIR + "/integration/integration.conf")
        confFile.parentFile.mkdirs();
        System.setProperty(OpsGenieCommandLine.CONF_PATH_SYSTEM_PROPERTY, confFile.getPath())

        applyConfigurationTest();
    }

    @Test
    public void testClientConfigurationFromConfFileWithPrimarilyUsePathFromSysProp() {
        confFile.setText("""
           opsgenie.api.url=localhost
           proxyHost=host6
           proxyPort=8000
           authMethod=BASIC
           proxyUsername=user1
           proxyPassword=pass1
           proxyDomain=domain1
           proxyProtocol=https
           proxyWorkstation=workstation1
           socketTimeout=31
           connectionTimeout=32
           maxConnectionCount=7
           socketReceiveBufferSizeHint=2000
           socketSendBufferSizeHint=4000
        """)

        confFile = new File(TestFile.TESTOUTPUT_DIR + "/integration/integration.conf")
        confFile.parentFile.mkdirs();
        System.setProperty(OpsGenieCommandLine.CONF_PATH_SYSTEM_PROPERTY, confFile.getPath())

        applyConfigurationTest();
    }

    @Test
    public void testClientConfigurationFromConfFileWithUsingHomeDir() {
        FileUtils.deleteDirectory(new TestFile())

        confFile = new File("conf/lamp.conf")
        confFile.parentFile.mkdirs();

        applyConfigurationTest()

        FileUtils.deleteDirectory(confFile.parentFile)
    }

    private void applyConfigurationTest() {
        confFile.setText("""
           opsgenie.api.url=localhost
           proxyHost=host1
           proxyPort=5000
           authMethod=BASIC
           proxyUsername=user1
           proxyPassword=pass1
           proxyDomain=domain1
           proxyProtocol=https
           proxyWorkstation=workstation1
           socketTimeout=31
           connectionTimeout=32
           maxConnectionCount=7
           socketReceiveBufferSizeHint=2000
           socketSendBufferSizeHint=4000
        """)
        opsGenieClient.alert().setAddNoteResponse(new AddNoteResponse());
        def args = ["addNote", "--note", "mynote", "--alertId", "alertId1", "--alias", "alias1"]

        assertTrue(cli.run(args as String[]))
        assertEquals("localhost", opsGenieClient.getRootUri())

        ClientConfiguration clientConfiguration = opsGenieClient.getClientConfiguration()
        assertNotNull(clientConfiguration);

        assertEquals("host1", clientConfiguration.getClientProxyConfiguration().getProxyHost())
        assertEquals(5000, clientConfiguration.getClientProxyConfiguration().getProxyPort())
        assertEquals("user1", clientConfiguration.getClientProxyConfiguration().getProxyUsername())
        assertEquals("pass1", clientConfiguration.getClientProxyConfiguration().getProxyPassword())
        assertEquals("domain1", clientConfiguration.getClientProxyConfiguration().getProxyDomain())
        assertEquals("https", clientConfiguration.getClientProxyConfiguration().getProxyProtocol())
        assertEquals("workstation1", clientConfiguration.getClientProxyConfiguration().getProxyWorkstation())
        assertEquals(ClientProxyConfiguration.AuthType.BASIC, clientConfiguration.getClientProxyConfiguration().getAuthType())
        assertEquals(31000, clientConfiguration.getSocketTimeout())
        assertEquals(32000, clientConfiguration.getConnectionTimeout())
        assertEquals(7, clientConfiguration.getMaxConnections())
        assertEquals(2000, clientConfiguration.getSocketReceiveBufferSizeHint())
        assertEquals(4000, clientConfiguration.getSocketSendBufferSizeHint())
    }

    @Test
    public void testJCommanderFileOptionIsDisabled() throws Exception {
        opsGenieClient.alert().setAddNoteResponse(new AddNoteResponse());
        def args = ["addNote", "--apiKey", "customer1", "--alertId", "alertId1", "--note", "@mynote"]

        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteRequest request = executedRequests[0] as AddNoteRequest;

        assertEquals("customer1", request.getApiKey())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("@mynote", request.getNote());
    }

    @Test
    public void testIOExceptionFormatting() throws Exception {
        opsGenieClient.setIOException(new UnknownHostException("unknown host."));
        def args = ["getAlert", "--apiKey", "customer1"];

        assertFalse(cli.run(args as String[]))
        assertTrue(sysout.toString(), sysout.toString().startsWith("UnknownHostException[unknown host.]"))

    }

    public static addMessage(Object message) {
        messages.add(message)
    }
}
