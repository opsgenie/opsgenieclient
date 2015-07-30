package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.model.BaseRequest
import com.ifountain.opsgenie.client.model.InputStreamAttachRequest
import com.ifountain.opsgenie.client.model.alert.*
import com.ifountain.opsgenie.client.model.beans.Alert
import com.ifountain.opsgenie.client.model.beans.AlertLog
import com.ifountain.opsgenie.client.model.beans.AlertNote
import com.ifountain.opsgenie.client.model.beans.AlertRecipient
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyAlertTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testCreateAlertSuccessfully() throws Exception {
        _testCreateAlertSuccessfully(false);
        opsGenieClient.getExecutedRequests().clear();
        _testCreateAlertSuccessfully(false, true);
        opsGenieClient.getExecutedRequests().clear();
        _testCreateAlertSuccessfully(true);
    }

    @Test
    public void testCreateAlertWillThrowExceptionIfDetailsIsNotMap() throws Exception {
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId("alertId1");
        opsGenieClient.alert().setCreateAlertResponse(response)

        def params = [details: "invalidmap"]

        try {
            proxy.createAlert(params);
            fail("Should throw exception since details is invalid");
        }
        catch (Exception ex) {
            assertEquals("[${TestConstants.API.DETAILS}] paramater should be hash".toString(), ex.getMessage())
        }
    }

    public void _testCreateAlertSuccessfully(boolean useConfFiles, boolean useCustomerKey = false) throws Exception {
        String key = "customer1Key"
        CreateAlertResponse response = new CreateAlertResponse();
        response.setAlertId("alertId1");
        opsGenieClient.alert().setCreateAlertResponse(response)

        def params = [message    : "my message",
                      description: "my description",
                      source     : "source1", entity: "entity1", alias: "alias1",
                      note       : "alert note",
                      tags       : ["tag1", "tag2"], actions: ["action1", "action2"],
                      recipients : ["sezgin@ifountain.com", "berkay@ifountain.com"],
                      teams      : ["team1", "team2"],
                      details    : [param1: "value1", param2: "value2"]];
        if (!useConfFiles) {
            if (useCustomerKey) {
                params.customerKey = key;
            } else {
                params.apiKey = key;
            }
            params.user = "someuser";
        }

        Map resp = proxy.createAlert(params);
        assertEquals(response.getAlertId(), resp.alertId)
        assertEquals(1, opsGenieClient.getExecutedRequests().size());
        CreateAlertRequest request = opsGenieClient.getExecutedRequests()[0];
        assertEquals(params.actions, request.getActions())
        assertEquals(params.tags, request.getTags())
        assertEquals(params.recipients, request.getRecipients())
        assertEquals(params.teams, request.getTeams())
        assertEquals(params.details, request.getDetails())
        assertEquals(params.message, request.getMessage())
        assertEquals(params.description, request.getDescription())
        assertEquals(params.source, request.getSource())
        assertEquals(params.entity, request.getEntity())
        assertEquals(params.note, request.getNote())
        assertEquals(params.user, request.getUser())
        if (!useConfFiles) {
            assertEquals(key, request.getApiKey())
        } else {
            assertEquals(apiKey, request.getApiKey())
        }
    }

    @Test
    public void testCreateAlertWithException() throws Exception {
        opsGenieClient.setException(new Exception("No message has been given."));
        def args = ["createAlert", "--apiKey", "customer1"];

        try {
            proxy.createAlert([apiKey: "customer1"]);
            fail("Should throw exception");
        }
        catch (Exception ex) {
            assertEquals("No message has been given.", ex.getMessage())
        }

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CreateAlertRequest request = executedRequests[0] as CreateAlertRequest;
        assertEquals("customer1", request.getApiKey())
        assertNull(request.getMessage())
    }

    @Test
    public void testCloseAlert() throws Exception {
        _testCloseAlert(false);
        opsGenieClient.getExecutedRequests().clear()
        _testCloseAlert(true);


    }

    public void _testCloseAlert(boolean useConfig) throws Exception {
        opsGenieClient.alert().setCloseAlertResponse(new CloseAlertResponse());
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", user: "someuser", source: "source1"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }

        Map response = proxy.closeAlert(params)

        assertTrue(response.success)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CloseAlertRequest request = executedRequests[0] as CloseAlertRequest;
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())

        }
        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("source1", request.getSource());

    }

    @Test
    public void testCloseAlertReturningException() throws Exception {
        _testeReturningException("closeAlert", [apiKey: "customer1", alias: "alias1"], new Exception("Alert does not exist."))
    }


    @Test
    public void testRenotify() throws Exception {
        _testRenotify(false);
        opsGenieClient.getExecutedRequests().clear()
        _testRenotify(true);

    }

    public void _testRenotify(boolean useConfig) throws Exception {
        opsGenieClient.alert().setRenotifyResponse(new RenotifyResponse());
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", user: "someuser", recipients: ["user1@xyz.com", "group1"], source: "source1"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }

        Map response = proxy.renotify(params)

        assertTrue(response.success)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        RenotifyRequest request = executedRequests[0] as RenotifyRequest;
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())

        }
        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("someuser", request.getUser());
        assertEquals("source1", request.getSource());
        assertEquals(2, request.getRecipients().size());
        assertTrue(request.getRecipients()*.recipient.containsAll(params.recipients));

    }

    @Test
    public void testRenotifyReturningException() throws Exception {
        _testeReturningException("renotify", [apiKey: "customer1", alias: "alias1"], new Exception("Alert does not exist."))
    }

    @Test
    public void testAddNote() throws Exception {
        _testAddNote(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddNote(true);

    }

    public void _testAddNote(boolean useConfig) throws Exception {
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", note: "mynote", user: "someuser", source: "source1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        opsGenieClient.alert().setAddNoteResponse(new AddNoteResponse());
        Map response = proxy.addNote(params)
        assertTrue(response.success)
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteRequest request = executedRequests[0] as AddNoteRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        assertEquals("source1", request.getSource());
        assertEquals("someuser", request.getUser());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddNoteReturningException() throws Exception {
        _testeReturningException("addNote", [apiKey: "customer1", alias: "alias1"], new Exception("Alert does not exist."))
    }

    @Test
    public void testAddTags() throws Exception {
        _testAddTags(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddTags(true);

    }

    public void _testAddTags(boolean useConfig) throws Exception {
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", note: "mynote", user: "someuser", source: "source1", tags: ["tag1","tag2"]];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        opsGenieClient.alert().setAddTagsResponse(new AddTagsResponse());
        Map response = proxy.addTags(params)
        assertTrue(response.success)
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddTagsRequest request = executedRequests[0] as AddTagsRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        assertEquals("source1", request.getSource());
        assertEquals("someuser", request.getUser());
        assertEquals(["tag1","tag2"], request.getTags());

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddTagsReturningException() throws Exception {
        _testeReturningException("addTags", [apiKey: "customer1", alias: "alias1"], new Exception("Alert does not exist."))
    }

    @Test
    public void testExecuteAction() throws Exception {
        _testExecuteAction(false);
        opsGenieClient.getExecutedRequests().clear()
        _testExecuteAction(true);

    }

    public void _testExecuteAction(boolean useConfig) throws Exception {
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", action: "action", user: "user1@xyz.com", note: "note1", source: "source1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        opsGenieClient.alert().setExecuteAlertActionResponse(new ExecuteAlertActionResponse());
        Map response = proxy.executeAlertAction(params)
        assertTrue(response.success)
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ExecuteAlertActionRequest request = executedRequests[0] as ExecuteAlertActionRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("note1", request.getNote());
        assertEquals("user1@xyz.com", request.getUser());
        assertEquals("source1", request.getSource());
        assertEquals("action", request.getAction());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testExecuteActionReturningException() throws Exception {
        _testeReturningException("executeAlertAction", [apiKey: "customer1"], new Exception("Alert does not exist."))
    }

    @Test
    public void testFileAttach() throws Exception {
        _testFileAttach(false)
        opsGenieClient.getExecutedRequests().clear()
        _testFileAttach(true);
    }

    public void _testFileAttach(boolean useConfig) throws Exception {
        opsGenieClient.alert().setAttachResponse(new AttachResponse());
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", indexFile: "index.html", attachment: "dummy.txt", user: "someuser", source: "source1", note: "comment"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        Map response = proxy.attach(params)
        assertTrue(response.success)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        FileAttachRequest request = executedRequests[0] as FileAttachRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("source1", request.getSource());
        assertEquals("alertId1", request.getAlertId());
        assertEquals("index.html", request.getIndexFile());
        assertEquals("comment", request.getNote());
        assertEquals(new File("dummy.txt"), request.getFile());
        assertEquals("someuser", request.getUser());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testInputStreamAttach() throws Exception {
        _testInputStreamAttach(false)
        opsGenieClient.getExecutedRequests().clear()
        _testInputStreamAttach(true);
    }

    public void _testInputStreamAttach(boolean useConfig) throws Exception {
        opsGenieClient.alert().setAttachResponse(new AttachResponse());
        def stream = new ByteArrayInputStream()
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", indexFile: "index.html", stream: stream, fileName: "dummy.txt", user: "someuser", source: "source1", note: "comment"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        Map response = proxy.attach(params)
        assertTrue(response.success)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        InputStreamAttachRequest request = executedRequests[0] as InputStreamAttachRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("source1", request.getSource());
        assertEquals("alertId1", request.getAlertId());
        assertEquals("index.html", request.getIndexFile());
        assertEquals(stream, request.getInputStream());
        assertEquals("dummy.txt", request.getFileName());
        assertEquals("someuser", request.getUser());
        assertEquals("comment", request.getNote());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAttachReturningException() throws Exception {
        _testeReturningException("attach", [apiKey: "customer1", attachment: "dummy.txt"], new Exception("Alert does not exist."))
    }

    @Test
    public void testGetAlert() throws Exception {
        _testGetAlert(false)
        opsGenieClient.getExecutedRequests().clear();
        _testGetAlert(true)
    }

    public void _testGetAlert(boolean useConfig) throws Exception {
        def expectedResponse = new GetAlertResponse(alert: new Alert(message: "message1", actions: ["action1"], alias: "alias1", createdAt: 5,
                description: "desc1", entity: "entity1", details: [param1: "param1Value"], id: "alert1", source: "ip1", status: "open",
                recipients: ["user1"], tags: ["tag1"], tinyId: "tinyid1", teams: ["team1"]))
        opsGenieClient.alert().setGetAlertResponse(expectedResponse);
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        Map response = proxy.getAlert(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetAlertRequest request = executedRequests[0] as GetAlertRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertEquals(expectedResponse.alert.message, response.message)
        assertEquals(expectedResponse.alert.actions, response.actions)
        assertEquals(expectedResponse.alert.alias, response.alias)
        assertEquals(expectedResponse.alert.createdAt, response.createdAt)
        assertEquals(expectedResponse.alert.description, response.description)
        assertEquals(expectedResponse.alert.entity, response.entity)
        assertEquals(expectedResponse.alert.details, response.details)
        assertEquals(expectedResponse.alert.id, response.alertId)
        assertEquals(expectedResponse.alert.source, response.source)
        assertEquals(expectedResponse.alert.status.name(), response.status)
        assertEquals(expectedResponse.alert.recipients, response.recipients)
        assertEquals(expectedResponse.alert.teams, response.teams)
        assertEquals(expectedResponse.alert.tags, response.tags)
        assertEquals(expectedResponse.alert.tinyId, response.tinyId)
    }

    @Test
    public void testListAlertLogs() throws Exception {
        _testListAlertLogs(false)
        opsGenieClient.getExecutedRequests().clear();
        _testListAlertLogs(true)
    }

    public void _testListAlertLogs(boolean useConfig) throws Exception {
        def expectedResponse = new ListAlertLogsResponse(alertLogs: [
                new AlertLog(log: "log1", createdAt: 1000, logType: "type1", owner: "user1@xyz.com"),
                new AlertLog(log: "log2", createdAt: 3000, logType: "type2", owner: "user2@xyz.com")
        ], lastKey: "lastKeyVal2"
        )
        opsGenieClient.alert().setListAlertLogsResponse(expectedResponse);
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", order: "asc", limit: 10, lastKey: "lastKeyVal1"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def logsWrapper = proxy.listAlertLogs(params)
        assertEquals("lastKeyVal2", logsWrapper[TestConstants.API.LAST_KEY])
        def logs = logsWrapper.logs

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListAlertLogsRequest request = executedRequests[0] as ListAlertLogsRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("lastKeyVal1", request.getLastKey());
        assertEquals(ListAlertLogsRequest.SortOrder.asc, request.getSortOrder());
        assertEquals(10, request.getLimit());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertEquals(2, logs.size())
        def log = logs.find { it.owner == "user1@xyz.com" }
        assertEquals("log1", log.log)
        assertEquals("type1", log.logType)
        assertEquals(1000, log.createdAt)

        log = logs.find { it.owner == "user2@xyz.com" }
        assertEquals("log2", log.log)
        assertEquals("type2", log.logType)
        assertEquals(3000, log.createdAt)
    }

    @Test
    public void testListAlertRecipients() throws Exception {
        _testListAlertRecipients(false)
        opsGenieClient.getExecutedRequests().clear();
        _testListAlertRecipients(true)
    }

    public void _testListAlertRecipients(boolean useConfig) throws Exception {
        def expectedResponse = new ListAlertRecipientsResponse(users: [
                new AlertRecipient(username: "user1@xyz.com", method: "email", state: "pending", stateChangedAt: 1000),
                new AlertRecipient(username: "user2@xyz.com", method: "sms", state: "sent", stateChangedAt: 2000),
        ],
                groups: [
                        group1: [
                                new AlertRecipient(username: "user3@xyz.com", method: "email", state: "pending", stateChangedAt: 1000)
                        ]
                ]
        )
        opsGenieClient.alert().setListAlertRecipientsResponse(expectedResponse);
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def recipients = proxy.listAlertRecipients(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListAlertRecipientsRequest request = executedRequests[0] as ListAlertRecipientsRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        def users = recipients[TestConstants.API.USERS]
        assertEquals(2, users.size())
        def user = users.find { it.username == "user1@xyz.com" }
        assertEquals("email", user.method)
        assertEquals("pending", user.state)
        assertEquals(1000, user.stateChangedAt)

        user = users.find { it.username == "user2@xyz.com" }
        assertEquals("sms", user.method)
        assertEquals("sent", user.state)
        assertEquals(2000, user.stateChangedAt)

        def groupRecipients = recipients[TestConstants.API.GROUPS]
        assertEquals(1, groupRecipients.size())

        def group1Recipients = groupRecipients.group1
        assertEquals(1, group1Recipients.size())

        user = group1Recipients.find { it.username == "user3@xyz.com" }
        assertEquals("email", user.method)
        assertEquals("pending", user.state)
        assertEquals(1000, user.stateChangedAt)

    }

    @Test
    public void testGetAlertReturningException() throws Exception {
        _testeReturningException("getAlert", [apiKey: "customer1"], new Exception("Alert does not exist."))
    }

    @Test
    public void testListAlerts() throws Exception {
        _testListAlerts(false)
        opsGenieClient.getExecutedRequests().clear();
        _testListAlerts(true)
    }

    public void _testListAlerts(boolean useConfig) throws Exception {
        def expectedResponse = new ListAlertsResponse(alerts: [new Alert(message: "message1", actions: ["action1"], alias: "alias1", createdAt: 5,
                description: "desc1", entity: "entity1", details: [param1: "param1Value"], id: "alert1", source: "ip1", status: "open",
                recipients: ["user1"], tags: ["tag1"], tinyId: "tinyid1")])
        opsGenieClient.alert().setListAlertsResponse(expectedResponse);
        def params = [limit: 2, status: "open", createdAfter: 3, createdBefore: 4, updatedAfter: 5, updatedBefore: 6, sortBy: "createdAt", order: "asc", tags: "tag1, tag3", tagsOperator: "or"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        List<Map> response = proxy.listAlerts(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListAlertsRequest request = executedRequests[0] as ListAlertsRequest;

        assertEquals(2, request.getLimit())
        assertEquals(3, request.getCreatedAfter())
        assertEquals(4, request.getCreatedBefore())
        assertEquals(5, request.getUpdatedAfter())
        assertEquals(6, request.getUpdatedBefore())
        assertEquals(ListAlertsRequest.SortBy.createdAt, request.getSortBy())
        assertEquals(ListAlertsRequest.SortOrder.asc, request.getSortOrder())
        assertNotNull(request.getTags())
        assertEquals(AlertsRequest.Operator.or, request.getTagsOperator())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertEquals(expectedResponse.alerts[0].message, response[0].message)
        assertEquals(expectedResponse.alerts[0].actions, response[0].actions)
        assertEquals(expectedResponse.alerts[0].alias, response[0].alias)
        assertEquals(expectedResponse.alerts[0].createdAt, response[0].createdAt)
        assertEquals(expectedResponse.alerts[0].description, response[0].description)
        assertEquals(expectedResponse.alerts[0].entity, response[0].entity)
        assertEquals(expectedResponse.alerts[0].details, response[0].details)
        assertEquals(expectedResponse.alerts[0].id, response[0].id)
        assertEquals(expectedResponse.alerts[0].source, response[0].source)
        assertEquals(expectedResponse.alerts[0].status.name(), response[0].status)
        assertEquals(expectedResponse.alerts[0].recipients, response[0].recipients)
        assertEquals(expectedResponse.alerts[0].tags, response[0].tags)
        assertEquals(expectedResponse.alerts[0].tinyId, response[0].tinyId)
        assertEquals(expectedResponse.alerts[0].tags, response[0].tags)
    }

    @Test
    public void testCountAlerts() throws Exception {
        _testCountAlerts(false)
        opsGenieClient.getExecutedRequests().clear();
        _testCountAlerts(true)
    }

    public void _testCountAlerts(boolean useConfig) throws Exception {
        def expectedResponse = new CountAlertsResponse(count: 5)
        opsGenieClient.alert().setCountAlertsResponse(expectedResponse);
        def params = [ status: "open", createdAfter: 3, createdBefore: 4, updatedAfter: 5, updatedBefore: 6, sortBy: "createdAt", order: "asc", tags: "tag1, tag3", tagsOperator: "or"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        Map response = proxy.countAlerts(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CountAlertsRequest request = executedRequests[0] as CountAlertsRequest;

        assertEquals(3, request.getCreatedAfter())
        assertEquals(4, request.getCreatedBefore())
        assertEquals(5, request.getUpdatedAfter())
        assertEquals(6, request.getUpdatedBefore())
        assertNotNull(request.getTags())
        assertEquals(AlertsRequest.Operator.or, request.getTagsOperator())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertEquals(expectedResponse.count, response.count)
    }

    @Test
    public void testListAlertsReturningException() throws Exception {
        _testeReturningException("listAlerts", [apiKey: "customer1"], new Exception("Alert does not exist."))
    }


    public void _testeReturningException(String methodName, Map params, Exception exception) throws Exception {
        opsGenieClient.setException(exception);
        try {
            proxy."${methodName}"(params)
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals(exception.getMessage(), e.getMessage())
        }

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        BaseRequest request = executedRequests[0];
        assertEquals("customer1", request.getApiKey())
    }

    @Test
    public void testListAlertNotes() throws Exception {
        _testListAlertNotes(false)
        opsGenieClient.getExecutedRequests().clear();
        _testListAlertNotes(true)
    }

    public void _testListAlertNotes(boolean useConfig) throws Exception {
        def expectedResponse = new ListAlertNotesResponse(alertNotes: [
                new AlertNote(note: "note1", createdAt: 1000, owner: "user1@xyz.com"),
                new AlertNote(note: "note2", createdAt: 3000, owner: "user2@xyz.com")
        ], lastKey: "lastKeyVal2"
        )
        opsGenieClient.alert().setListAlertNotesResponse(expectedResponse);
        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1", order: "asc", limit: 10, lastKey: "lastKeyVal1"]
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def notesWrapper = proxy.listAlertNotes(params)
        assertEquals("lastKeyVal2", notesWrapper[TestConstants.API.LAST_KEY])
        def notes = notesWrapper.notes

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListAlertNotesRequest request = executedRequests[0] as ListAlertNotesRequest;

        assertEquals("alias1", request.getAlias())
        assertEquals("tinyId1", request.getTinyId())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("lastKeyVal1", request.getLastKey());
        assertEquals(ListAlertNotesRequest.SortOrder.asc, request.getSortOrder());
        assertEquals(10, request.getLimit());
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertEquals(2, notes.size())
        def note = notes.find { it.owner == "user1@xyz.com" }
        assertEquals("note1", note.note)
        assertEquals(1000, note.createdAt)

        note = notes.find { it.owner == "user2@xyz.com" }
        assertEquals("note2", note.note)
        assertEquals(3000, note.createdAt)
    }
}
