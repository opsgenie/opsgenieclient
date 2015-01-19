package com.ifountain.opsgenie.client.lamp.scripts

import com.ifountain.opsgenie.client.IOpsGenieClient
import com.ifountain.opsgenie.client.OpsGenieClientException
import com.ifountain.opsgenie.client.cli.OpsGenieCommandLine
import com.ifountain.opsgenie.client.model.alert.AcknowledgeRequest
import com.ifountain.opsgenie.client.model.alert.AcknowledgeResponse
import com.ifountain.opsgenie.client.model.alert.CloseAlertRequest
import com.ifountain.opsgenie.client.model.alert.CloseAlertResponse
import com.ifountain.opsgenie.client.model.alert.DeleteAlertRequest
import com.ifountain.opsgenie.client.model.alert.DeleteAlertResponse
import com.ifountain.opsgenie.client.model.alert.ListAlertsRequest
import com.ifountain.opsgenie.client.model.alert.ListAlertsResponse
import com.ifountain.opsgenie.client.model.beans.Alert
import com.ifountain.opsgenie.client.test.util.InnerAlertOpsGenieClientMock
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.util.ClientConfiguration
import org.apache.commons.io.FileUtils
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.text.ParseException
import java.util.concurrent.TimeUnit

import static org.junit.Assert.*

class ProcessOldAlertsScriptTest {

    OpsGenieCommandLine cli;
    OpsGenieClientMock opsGenieClient;
    static List messages;
    File testScript;
    String commandLineConfPath = ";"

    @Before
    public void setUp() {

        commandLineConfPath = System.getProperty(OpsGenieCommandLine.CONF_PATH_SYSTEM_PROPERTY);

        messages = new ArrayList();
        opsGenieClient = new OpsGenieClientMock();
        cli = new OpsGenieCommandLine() {
            @Override
            protected IOpsGenieClient createOpsGenieClient(ClientConfiguration clientConfiguration) {
                opsGenieClient.setClientConfiguration(clientConfiguration);
                return opsGenieClient;
            }
        };
        FileUtils.deleteDirectory(new TestFile());
        setupScript();
    }

    @After
    public void tearDown() {
        if (commandLineConfPath != null) {
            System.setProperty(OpsGenieCommandLine.CONF_PATH_SYSTEM_PROPERTY, commandLineConfPath);
        }
    }

    @Test
    public void testProcessOldAlertScriptClosesByDefault() throws Exception {
        def listCallCount = 0;

        def alert1 = new Alert(id: "al1", status: Alert.Status.open, createdAt: 100);
        def alert2 = new Alert(id: "al2", status: Alert.Status.open, createdAt: 10);

        opsGenieClient.alertOpsGenieClientMock = new InnerAlertOpsGenieClientMock(opsGenieClient.requestProcessor) {
            @Override
            ListAlertsResponse listAlerts(ListAlertsRequest listAlertsRequest) throws OpsGenieClientException, IOException, ParseException {
                listCallCount++;
                super.listAlerts(listAlertsRequest);
                if (listCallCount == 1) {
                    return new ListAlertsResponse(alerts: [alert1, alert2]);
                } else {
                    return new ListAlertsResponse(alerts: []);
                }
            }
        }

        opsGenieClient.alert().setCloseAlertResponse(new CloseAlertResponse());

        def args = ["executeScript", "--name", testScript.getName(), ""];
        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(4, executedRequests.size())
        ListAlertsRequest listRequest1 = executedRequests[0];
        assertEquals("key1", listRequest1.getApiKey())
        assertEquals("open", String.valueOf(listRequest1.getStatus()));
        assertEquals(100, listRequest1.getLimit());
        def timeDiff = System.currentTimeMillis() - TimeUnit.NANOSECONDS.toMillis(listRequest1.getCreatedBefore());
        assertEquals(2, TimeUnit.MILLISECONDS.toDays(timeDiff));

        CloseAlertRequest closeRequest1 = executedRequests[1];
        assertEquals("key1", closeRequest1.getApiKey())
        assertEquals(alert1.getId(), closeRequest1.getId())

        CloseAlertRequest closeRequest2 = executedRequests[2];
        assertEquals("key1", closeRequest2.getApiKey())
        assertEquals(alert2.getId(), closeRequest2.getId())

        ListAlertsRequest listRequest2 = executedRequests[3];
        assertEquals("key1", listRequest2.getApiKey())
        assertEquals("open", String.valueOf(listRequest2.getStatus()));
        assertEquals(100, listRequest2.getLimit());
        assertEquals(alert2.getCreatedAt(), listRequest2.getCreatedBefore());
    }

    @Test
    public void testProcessOldAlertScriptAck() {

        def listCallCount = 0;

        def alert1 = new Alert(id: "al1", status: Alert.Status.open, createdAt: 100);

        opsGenieClient.alertOpsGenieClientMock = new InnerAlertOpsGenieClientMock(opsGenieClient.requestProcessor) {
            @Override
            ListAlertsResponse listAlerts(ListAlertsRequest listAlertsRequest) throws OpsGenieClientException, IOException, ParseException {
                listCallCount++;
                super.listAlerts(listAlertsRequest);
                if (listCallCount == 1) {
                    return new ListAlertsResponse(alerts: [alert1]);
                } else {
                    return new ListAlertsResponse(alerts: []);
                }
            }
        }

        opsGenieClient.alert().setAcknowledgeResponse(new AcknowledgeResponse());

        def args = ["executeScript", "--name", testScript.getName(), "-Daction=ack", "-Dday=5"];
        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(3, executedRequests.size())
        ListAlertsRequest listRequest1 = executedRequests[0];
        assertEquals("key1", listRequest1.getApiKey())
        assertEquals("open", String.valueOf(listRequest1.getStatus()));
        assertEquals(100, listRequest1.getLimit());
        def timeDiff = System.currentTimeMillis() - TimeUnit.NANOSECONDS.toMillis(listRequest1.getCreatedBefore());
        assertEquals(5, TimeUnit.MILLISECONDS.toDays(timeDiff));

        AcknowledgeRequest ackRequest1 = executedRequests[1];
        assertEquals("key1", ackRequest1.getApiKey())
        assertEquals(alert1.getId(), ackRequest1.getId())

        ListAlertsRequest listRequest2 = executedRequests[2];
        assertEquals("key1", listRequest2.getApiKey())
        assertEquals("open", String.valueOf(listRequest2.getStatus()));
        assertEquals(100, listRequest2.getLimit());
        assertEquals(alert1.getCreatedAt(), listRequest2.getCreatedBefore());

    }

    @Test
    public void testProcessOldAlertScriptDelete() {

        def listCallCount = 0;

        def alert1 = new Alert(id: "al1", status: Alert.Status.open, createdAt: 100);

        opsGenieClient.alertOpsGenieClientMock = new InnerAlertOpsGenieClientMock(opsGenieClient.requestProcessor) {
            @Override
            ListAlertsResponse listAlerts(ListAlertsRequest listAlertsRequest) throws OpsGenieClientException, IOException, ParseException {
                listCallCount++;
                super.listAlerts(listAlertsRequest);
                if (listCallCount == 1) {
                    return new ListAlertsResponse(alerts: [alert1]);
                } else {
                    return new ListAlertsResponse(alerts: []);
                }
            }
        }

        opsGenieClient.alert().setDeleteAlertResponse(new DeleteAlertResponse());

        def args = ["executeScript", "--name", testScript.getName(), "-Daction=delete", "-Dstatus=closed"];
        assertTrue(cli.run(args as String[]))

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(3, executedRequests.size())
        ListAlertsRequest listRequest1 = executedRequests[0];
        assertEquals("key1", listRequest1.getApiKey())
        assertEquals("closed", String.valueOf(listRequest1.getStatus()));
        assertEquals(100, listRequest1.getLimit());

        DeleteAlertRequest ackRequest1 = executedRequests[1];
        assertEquals("key1", ackRequest1.getApiKey())
        assertEquals(alert1.getId(), ackRequest1.getId())

        ListAlertsRequest listRequest2 = executedRequests[2];
        assertEquals("key1", listRequest2.getApiKey())
        assertEquals("closed", String.valueOf(listRequest2.getStatus()));
        assertEquals(100, listRequest2.getLimit());
        assertEquals(alert1.getCreatedAt(), listRequest2.getCreatedBefore());

    }

    public void setupScript() {
        def confFile = new TestFile("conf/lamp.conf")
        File scriptDir = new File(TestFile.TESTOUTPUT_DIR + "/scripts")
        System.setProperty(OpsGenieCommandLine.LAMP_CONF_DIR_SYSTEM_PROPERTY, confFile.getParentFile().getPath());
        System.setProperty(OpsGenieCommandLine.LAMP_SCRIPTS_DIR_SYSTEM_PROPERTY, scriptDir.getPath());
        confFile.parentFile.mkdirs();
        confFile.setText("""
           apiKey=key1
           user=user1
        """)

        System.setProperty(OpsGenieCommandLine.CONF_PATH_SYSTEM_PROPERTY, confFile.getAbsolutePath());
        testScript = new File(scriptDir, "processOldAlerts.groovy")
        testScript.parentFile.mkdirs();
        testScript.setText(new File("lamp/scripts/processOldAlerts.groovy").getText())
    }

}
