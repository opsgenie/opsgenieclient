package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.InputStreamAttachRequest
import com.ifountain.opsgenie.client.model.alert.*
import com.ifountain.opsgenie.client.model.beans.AlertRecipient
import com.ifountain.opsgenie.client.model.beans.RenotifyRecipient
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.FileUpload
import org.apache.commons.fileupload.RequestContext
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.http.HttpHeaders
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 11:09 AM
 */
class AlertOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Before
    public void setUp() {
        super.setUp()
    }

    @Test
    public void testCreateAlertSuccessfully() throws Exception {

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"alertId\":\"alertid1\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        CreateAlertRequest request = new CreateAlertRequest();
        request.setApiKey("customer1");
        request.setMessage("message1");
        request.setDescription("description1");
        request.setSource("source1")
        request.setEntity("entity1")
        request.setAlias("alert1")
        request.setNote("create note")
        request.setUser("someuser")
        request.setActions(["action1", "action2"])
        request.setTags(["tag1", "tag2"])
        request.setRecipients(["sezgin@ifountain.com", "berkay@ifountain.com"])
        request.setTeams(["team1", "team2"])
        request.setDetails([param1: "value1", param2: "value2"])

        def response = OpsGenieClientTestCase.opsgenieClient.alert().createAlert(request)
        assertEquals("alertid1", response.getAlertId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("message1", jsonContent[TestConstants.API.MESSAGE])
        assertEquals("description1", jsonContent[TestConstants.API.DESCRIPTION])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
        assertEquals("entity1", jsonContent[TestConstants.API.ENTITY])
        assertEquals("alert1", jsonContent[TestConstants.API.ALIAS])
        assertEquals("create note", jsonContent[TestConstants.API.NOTE])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])

        def tags = jsonContent[TestConstants.API.TAGS]
        assertEquals(2, tags.size())
        assertTrue(tags.contains("tag1"));
        assertTrue(tags.contains("tag2"));

        def actions = jsonContent[TestConstants.API.ACTIONS]
        assertEquals(2, actions.size())
        assertTrue(actions.contains("action1"));
        assertTrue(actions.contains("action2"));

        def recipients = jsonContent[TestConstants.API.RECIPIENTS]
        assertEquals(2, recipients.size())
        assertTrue(recipients.contains("sezgin@ifountain.com"));
        assertTrue(recipients.contains("berkay@ifountain.com"));

        def teams = jsonContent[TestConstants.API.TEAMS]
        assertEquals(2, teams.size())
        assertTrue(teams.contains("team1"));
        assertTrue(teams.contains("team2"));

        def details = jsonContent[TestConstants.API.DETAILS]
        assertEquals(2, details.size())
        assertEquals("value1", details.param1)
        assertEquals("value2", details.param2)
    }


    @Test
    public void testCreateAlertThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "createAlert", new CreateAlertRequest())
    }

    @Test
    public void testCloseAlertSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        CloseAlertRequest request = new CloseAlertRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().closeAlert(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/close", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
    }

    @Test
    public void testCloseAlertThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "closeAlert", new CloseAlertRequest())
    }

    @Test
    public void testDeleteAlertSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteAlertRequest request = new DeleteAlertRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().deleteAlert(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert", requestSent.getUrl())

        def requestParams = requestSent.getParameters();
        assertEquals("customer1", requestParams[TestConstants.API.API_KEY])
        assertEquals("alert1", requestParams[TestConstants.API.ID])
        assertEquals("alias", requestParams[TestConstants.API.ALIAS])
        assertEquals("tinyId", requestParams[TestConstants.API.TINY_ID])
        assertEquals("someuser", requestParams[TestConstants.API.USER])
    }

    @Test
    public void testDeleteAlertThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "deleteAlert", new DeleteAlertRequest())
    }

    @Test
    public void testAcknowledgeSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AcknowledgeRequest request = new AcknowledgeRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().acknowledge(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/acknowledge", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
    }

    @Test
    public void testAcknowledgeThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "acknowledge", new AcknowledgeRequest())
    }

    @Test
    public void testRenotifySuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        RenotifyRequest request = new RenotifyRequest();
        request.setId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().renotify(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/renotify", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
        assertFalse(jsonContent.containsKey(TestConstants.API.RECIPIENTS))

        //test renotify with recipients

        request = new RenotifyRequest();
        request.setId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setApiKey("customer1")
        request.setRecipients([new RenotifyRecipient(recipient: "user1@xyz.com"),
                               new RenotifyRecipient(recipient: "group1", type: RenotifyRecipient.Type.group)]);

        response = OpsGenieClientTestCase.opsgenieClient.alert().renotify(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/renotify", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
        def expectedRecipients = request.recipients*.recipient
        assertEquals(expectedRecipients.size(), jsonContent[TestConstants.API.RECIPIENTS].size())
        assertTrue(expectedRecipients.containsAll(jsonContent[TestConstants.API.RECIPIENTS]))
    }

    @Test
    public void testRenotifyThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "renotify", new RenotifyRequest())
    }

    @Test
    public void testTakeOwnershipSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        TakeOwnershipRequest request = new TakeOwnershipRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().takeOwnership(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/takeOwnership", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
    }

    @Test
    public void testTakeOwnershipThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "takeOwnership", new TakeOwnershipRequest())
    }

    @Test
    public void testAssignSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AssignRequest request = new AssignRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setOwner("someowner")
        request.setNote("comment")
        request.setSource("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().assign(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/assign", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("someowner", jsonContent[TestConstants.API.OWNER])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
    }

    @Test
    public void testAssignThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "assign", new AssignRequest())
    }

    @Test
    public void testAddRecipientSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddRecipientRequest request = new AddRecipientRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setRecipient("somegroup")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().addRecipient(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/recipient", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("somegroup", jsonContent[TestConstants.API.RECIPIENT])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
    }

    @Test
    public void testAddRecipientThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "addRecipient", new AddRecipientRequest())
    }

    @Test
    public void testAddTeamSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddAlertTeamRequest request = new AddAlertTeamRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setTeam("someteam")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().addTeam(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/team", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("someteam", jsonContent[TestConstants.API.TEAM])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
    }

    @Test
    public void testAddTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "addTeam", new AddAlertTeamRequest())
    }

    @Test
    public void testAddTagsSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddTagsRequest request = new AddTagsRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setTags(["tag1","tag2"])
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().addTags(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/tags", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])

        def tags = jsonContent[TestConstants.API.TAGS]
        assertEquals(2, tags.size())
        assertTrue(tags.contains("tag1"));
        assertTrue(tags.contains("tag2"));
    }

    @Test
    public void testAddTagsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "addTags", new AddTagsRequest())
    }

    @Test
    public void testAddNoteSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddNoteRequest request = new AddNoteRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setApiKey("customer1")
        request.setNote("note1")
        request.setSource("source1")
        request.setUser("someuser")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().addNote(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/note", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("note1", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
    }

    @Test
    public void testAddNoteThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "addNote", new AddNoteRequest())
    }

    @Test
    public void testExecuteAlertActionSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"result\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        ExecuteAlertActionRequest request = new ExecuteAlertActionRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setApiKey("customer1")
        request.setAction("action1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().executeAlertAction(request)
        assertEquals("successful", response.getResult())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/executeAction", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("alert1", jsonContent[TestConstants.API.ID])
        assertEquals("alias", jsonContent[TestConstants.API.ALIAS])
        assertEquals("tinyId", jsonContent[TestConstants.API.TINY_ID])
        assertEquals("someuser", jsonContent[TestConstants.API.USER])
        assertEquals("action1", jsonContent[TestConstants.API.ACTION])
        assertEquals("comment", jsonContent[TestConstants.API.NOTE])
        assertEquals("source1", jsonContent[TestConstants.API.SOURCE])
    }

    @Test
    public void testExecuteAlertActionThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "executeAlertAction", new ExecuteAlertActionRequest())
    }

    @Test
    public void testFileAttachSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        File file = new File(TestFile.TESTOUTPUT_DIR + "/dummy.txt")
        file.setText("dummy content");

        FileAttachRequest request = new FileAttachRequest();
        request.setApiKey("customer1")
        request.setAlertId("alert1")
        request.setAlias("alias1")
        request.setTinyId("tinyId")
        request.setIndexFile("index.html")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setFile(file)

        def response = OpsGenieClientTestCase.opsgenieClient.alert().attach(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/attach", requestSent.getUrl())
        assertTrue(requestSent.getHeader(HttpHeaders.CONTENT_TYPE).startsWith(FileUpload.MULTIPART))

        FileUpload uploader = new FileUpload(new DiskFileItemFactory());
        List<FileItem> fileItems = uploader.parseRequest(new RequestContext() {
            @Override
            String getCharacterEncoding() {
                return requestSent.getHeader(HttpHeaders.CONTENT_ENCODING);
            }

            @Override
            String getContentType() {
                return requestSent.getHeader(HttpHeaders.CONTENT_TYPE);
            }

            @Override
            int getContentLength() {
                return Integer.parseInt(requestSent.getHeader(HttpHeaders.CONTENT_LENGTH))
            }

            @Override
            InputStream getInputStream() {
                return new ByteArrayInputStream(requestSent.getContentAsByte());
            }
        })
        assertEquals(8, fileItems.size());
        def idItem = fileItems.find { it.fieldName == TestConstants.API.ID }
        assertNotNull(idItem)
        assertEquals("alert1", idItem.getString())
        def aliasItem = fileItems.find { it.fieldName == TestConstants.API.ALIAS }
        assertNotNull(aliasItem)
        assertEquals("alias1", aliasItem.getString())
        def tinyIdItem = fileItems.find { it.fieldName == TestConstants.API.TINY_ID }
        assertNotNull(tinyIdItem)
        assertEquals("tinyId", tinyIdItem.getString())
        def apiKeyItem = fileItems.find { it.fieldName == TestConstants.API.API_KEY }
        assertNotNull(apiKeyItem)
        assertEquals("customer1", apiKeyItem.getString())
        def indexFileItem = fileItems.find { it.fieldName == TestConstants.API.INDEX_FILE }
        assertNotNull(indexFileItem)
        assertEquals("index.html", indexFileItem.getString())
        def userItem = fileItems.find { it.fieldName == TestConstants.API.USER }
        assertNotNull(userItem)
        assertEquals("someuser", userItem.getString())

        def noteItem = fileItems.find { it.fieldName == TestConstants.API.NOTE }
        assertNotNull(noteItem)
        assertEquals("comment", noteItem.getString())

        def attachItem = fileItems.find { it.fieldName == TestConstants.API.ATTACHMENT }
        assertNotNull(attachItem)
        assertEquals("dummy content", attachItem.getInputStream().getText())
        assertEquals(request.getFile().getName(), attachItem.getName())

        //test inputstream closed
        response = OpsGenieClientTestCase.opsgenieClient.alert().attach(request)
        assertTrue(response.isSuccess())
    }

    @Test
    public void testFileAttachThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        File file = new File(TestFile.TESTOUTPUT_DIR + "/dummy.txt")
        file.setText("dummy content");
        FileAttachRequest request = new FileAttachRequest();
        request.setFile(file)
        request.setApiKey("customer")
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "attach", new FileAttachRequest())
    }

    @Test
    public void testInputStreamAttachSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"successful\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        boolean closed = false;
        ByteArrayInputStream bin = new ByteArrayInputStream("dummy content".getBytes()) {
            @Override
            void close() {
                closed = true;
                super.close()
            }
        }

        InputStreamAttachRequest request = new InputStreamAttachRequest();
        request.setApiKey("customer1")
        request.setAlertId("alert1")
        request.setAlias("alias1")
        request.setTinyId("tinyId")
        request.setIndexFile("index.html")
        request.setUser("someuser")
        request.setNote("comment")
        request.setSource("source1")
        request.setInputStream(bin)
        request.setFileName("tmp/dummy.txt")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().attach(request)
        assertTrue(response.isSuccess())
        assertEquals(1, response.getTook())
        assertTrue(closed);

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/attach", requestSent.getUrl())
        assertTrue(requestSent.getHeader(HttpHeaders.CONTENT_TYPE).startsWith(FileUpload.MULTIPART))

        FileUpload uploader = new FileUpload(new DiskFileItemFactory());
        List<FileItem> fileItems = uploader.parseRequest(new RequestContext() {
            @Override
            String getCharacterEncoding() {
                return requestSent.getHeader(HttpHeaders.CONTENT_ENCODING);
            }

            @Override
            String getContentType() {
                return requestSent.getHeader(HttpHeaders.CONTENT_TYPE);
            }

            @Override
            int getContentLength() {
                return Integer.parseInt(requestSent.getHeader(HttpHeaders.CONTENT_LENGTH))
            }

            @Override
            InputStream getInputStream() {
                return new ByteArrayInputStream(requestSent.getContentAsByte());
            }
        })
        assertEquals(8, fileItems.size());
        def idItem = fileItems.find { it.fieldName == TestConstants.API.ID }
        assertNotNull(idItem)
        assertEquals("alert1", idItem.getString())
        def aliasItem = fileItems.find { it.fieldName == TestConstants.API.ALIAS }
        assertNotNull(aliasItem)
        assertEquals("alias1", aliasItem.getString())
        def tinyIdItem = fileItems.find { it.fieldName == TestConstants.API.TINY_ID }
        assertNotNull(tinyIdItem)
        assertEquals("tinyId", tinyIdItem.getString())
        def apiKeyItem = fileItems.find { it.fieldName == TestConstants.API.API_KEY }
        assertNotNull(apiKeyItem)
        assertEquals("customer1", apiKeyItem.getString())
        def indexFileItem = fileItems.find { it.fieldName == TestConstants.API.INDEX_FILE }
        assertNotNull(indexFileItem)
        assertEquals("index.html", indexFileItem.getString())
        def userItem = fileItems.find { it.fieldName == TestConstants.API.USER }
        assertNotNull(userItem)
        assertEquals("someuser", userItem.getString())
        def noteItem = fileItems.find { it.fieldName == TestConstants.API.NOTE }
        assertNotNull(noteItem)
        assertEquals("comment", noteItem.getString())

        def attachItem = fileItems.find { it.fieldName == TestConstants.API.ATTACHMENT }
        assertNotNull(attachItem)
        assertEquals("dummy content", attachItem.getInputStream().getText())
        assertEquals(new File(request.getFileName()).getName(), attachItem.getName())
    }

    @Test
    public void testInputStreamAttachThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        def errorResponse = [error: "No attachment", code: 2]
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(errorResponse), HttpStatus.SC_BAD_REQUEST, "application/json; charset=utf-8"))

        ByteArrayInputStream bin = new ByteArrayInputStream("dummy content".getBytes());
        InputStreamAttachRequest request = new InputStreamAttachRequest();
        request.setApiKey("customer1")
        request.setAlertId("alert1")
        request.setAlias("alias1")
        request.setIndexFile("index.html")
        request.setUser("someuser")
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "attach", request)
    }

    @Test
    public void testGetAlertSuccessfully() throws Exception {
        def jsonResponse = [
                id        : "id1", message: "message1", systemData: [integrationType: "email"], description: "descr1", source: "source1",
                entity    : "entity1", alias: "alias1", createdAt: 1234, updatedAt: 4567, status: "open", owner: "user1",
                isSeen    : true, acknowledged: false, count: 2, tinyId: "tiny1",
                tags      : ["tag1", "tag2"], actions: ["action1", "action2"],
                recipients: ["sezgin@ifountain.com", "berkay@ifountain.com"],
                teams     : ["team1", "team2"],
                details   : [param1: "value1", param2: "value2"],
                took      : 1
        ]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(jsonResponse), HttpStatus.SC_OK, "application/json; charset=utf-8"))
        GetAlertRequest request = new GetAlertRequest();
        request.setAlertId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().getAlert(request)
        assertEquals("id1", response.getId())
        assertEquals("message1", response.getMessage())
        assertEquals("descr1", response.getDescription())
        assertEquals("tiny1", response.getAlert().getTinyId())
        assertEquals([integrationType: "email"], response.getAlert().getSystemData())
        assertEquals("source1", response.getSource())
        assertEquals("user1", response.getOwner())
        assertTrue(response.isSeen())
        assertFalse(response.isAcknowledged())
        assertEquals(1234, response.getCreatedAt())
        assertEquals(4567, response.getAlert().getUpdatedAt())
        assertEquals(2, response.getCount())

        def tags = response.getTags()
        assertEquals(2, tags.size())
        assertTrue(tags.contains("tag1"))
        assertTrue(tags.contains("tag2"))

        def actions = response.getActions()
        assertEquals(2, actions.size())
        assertTrue(actions.contains("action1"))
        assertTrue(actions.contains("action2"))

        def recipients = response.getRecipients()
        assertEquals(2, recipients.size())
        assertTrue(recipients.contains("sezgin@ifountain.com"))
        assertTrue(recipients.contains("berkay@ifountain.com"))

        def teams = response.getAlert().getTeams();
        assertEquals(2, teams.size())
        assertTrue(teams.contains("team1"))
        assertTrue(teams.contains("team2"))

        def details = response.getDetails();
        assertEquals(2, details.size())
        assertEquals("value1", details.param1)
        assertEquals("value2", details.param2)

        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert", requestSent.getUrl())

        def parameters = requestSent.getParameters();
        assertEquals("customer1", parameters[TestConstants.API.API_KEY])
        assertEquals("alert1", parameters[TestConstants.API.ID])
        assertEquals("alias", parameters[TestConstants.API.ALIAS])
        assertEquals("tinyId", parameters[TestConstants.API.TINY_ID])
    }

    @Test
    public void testListAlertLogsSuccessfully() throws Exception {
        def jsonResponse = [
                logs   : [
                        [owner: "user1@xyz.com", log: "log message1", logType: "comment", createdAt: 1000],
                        [owner: "user2@xyz.com", log: "log message2", logType: "attachment", createdAt: 2000],
                ],
                lastKey: "lastKeyVal",
                took   : 1
        ]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(jsonResponse), HttpStatus.SC_OK, "application/json; charset=utf-8"))
        ListAlertLogsRequest request = new ListAlertLogsRequest();
        request.setId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setApiKey("customer1")
        request.setLastKey("lastKey")
        request.setSortOrder(ListAlertLogsRequest.SortOrder.asc)
        request.setLimit(10)

        def response = OpsGenieClientTestCase.opsgenieClient.alert().listAlertLogs(request)

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/log", requestSent.getUrl())

        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID])
        assertEquals(request.getAlias(), requestSent.getParameters()[TestConstants.API.ALIAS])
        assertEquals(request.getTinyId(), requestSent.getParameters()[TestConstants.API.TINY_ID])
        assertEquals(request.getLastKey(), requestSent.getParameters()[TestConstants.API.LAST_KEY])
        assertEquals(request.getSortOrder().name(), requestSent.getParameters()[TestConstants.API.ORDER])
        assertEquals("" + request.getLimit(), requestSent.getParameters()[TestConstants.API.LIMIT])

        assertEquals("lastKeyVal", response.getLastKey())
        assertEquals(2, response.getAlertLogs().size())

        def log = response.getAlertLogs().find { it.logType == "comment" }
        assertEquals("log message1", log.log)
        assertEquals(1000, log.createdAt)
        assertEquals("user1@xyz.com", log.owner)

        log = response.getAlertLogs().find { it.logType == "attachment" }
        assertEquals("log message2", log.log)
        assertEquals(2000, log.createdAt)
        assertEquals("user2@xyz.com", log.owner)
    }

    @Test
    public void testListAlertNotesSuccessfully() throws Exception {
        def jsonResponse = [
                notes  : [
                        [owner: "user1@xyz.com", note: "log message1", createdAt: 1000],
                        [owner: "user2@xyz.com", note: "log message2", createdAt: 2000],
                ],
                lastKey: "lastKeyVal",
                took   : 1
        ]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(jsonResponse), HttpStatus.SC_OK, "application/json; charset=utf-8"))
        ListAlertNotesRequest request = new ListAlertNotesRequest();
        request.setId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setApiKey("customer1")
        request.setLastKey("lastKey")
        request.setSortOrder(ListAlertNotesRequest.SortOrder.asc)
        request.setLimit(10)

        def response = OpsGenieClientTestCase.opsgenieClient.alert().listAlertNotes(request)

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/note", requestSent.getUrl())

        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID])
        assertEquals(request.getAlias(), requestSent.getParameters()[TestConstants.API.ALIAS])
        assertEquals(request.getTinyId(), requestSent.getParameters()[TestConstants.API.TINY_ID])
        assertEquals(request.getLastKey(), requestSent.getParameters()[TestConstants.API.LAST_KEY])
        assertEquals(request.getSortOrder().name(), requestSent.getParameters()[TestConstants.API.ORDER])
        assertEquals("" + request.getLimit(), requestSent.getParameters()[TestConstants.API.LIMIT])

        assertEquals("lastKeyVal", response.getLastKey())
        assertEquals(2, response.getAlertNotes().size())

        def note = response.getAlertNotes().find { it.note == "log message1" }
        assertEquals(1000, note.createdAt)
        assertEquals("user1@xyz.com", note.owner)

        note = response.getAlertNotes().find { it.note == "log message2" }
        assertEquals("log message2", note.note)
        assertEquals(2000, note.createdAt)
        assertEquals("user2@xyz.com", note.owner)
    }

    @Test
    public void testListAlertRecipientsSuccessfully() throws Exception {
        def jsonResponse = [
                users : [
                        [username: "user1@xyz.com", state: "pending", method: "mobile", stateChangedAt: 1000],
                        [username: "user2@xyz.com", state: "sent", method: "sms", stateChangedAt: 2000]
                ],
                groups: [
                        group1: [
                                [username: "user3@xyz.com", state: "pending", method: "mobile", stateChangedAt: 1000],
                        ],
                        group2: [
                                [username: "user4@xyz.com", state: "action", method: "email", stateChangedAt: 3000],
                        ]
                ],
                took  : 1
        ]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(jsonResponse), HttpStatus.SC_OK, "application/json; charset=utf-8"))
        ListAlertRecipientsRequest request = new ListAlertRecipientsRequest();
        request.setId("alert1")
        request.setAlias("alias")
        request.setTinyId("tinyId")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.alert().listAlertRecipients(request)
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/recipient", requestSent.getUrl())

        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID])
        assertEquals(request.getAlias(), requestSent.getParameters()[TestConstants.API.ALIAS])
        assertEquals(request.getTinyId(), requestSent.getParameters()[TestConstants.API.TINY_ID])

        assertEquals(2, response.getUsers().size())

        AlertRecipient recipient = response.getUsers().find { it.username == "user1@xyz.com" }
        assertEquals("mobile", recipient.getMethod())
        assertEquals("pending", recipient.getState())
        assertEquals(1000, recipient.getStateChangedAt())

        recipient = response.getUsers().find { it.username == "user2@xyz.com" }
        assertEquals("sms", recipient.getMethod())
        assertEquals("sent", recipient.getState())
        assertEquals(2000, recipient.getStateChangedAt())

        def groups = response.groups
        assertEquals(2, groups.size())
        List<AlertRecipient> group1Recipients = groups.group1
        assertEquals(1, group1Recipients.size())

        recipient = group1Recipients.find { it.username == "user3@xyz.com" }
        assertEquals("mobile", recipient.getMethod())
        assertEquals("pending", recipient.getState())
        assertEquals(1000, recipient.getStateChangedAt())

        List<AlertRecipient> group2Recipients = groups.group2
        assertEquals(1, group2Recipients.size())

        recipient = group2Recipients.find { it.username == "user4@xyz.com" }
        assertEquals("email", recipient.getMethod())
        assertEquals("action", recipient.getState())
        assertEquals(3000, recipient.getStateChangedAt())

    }

    @Test
    public void testGetAlertThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        def errorResponse = [error: "Could not authenticate.", code: 2]
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(errorResponse), HttpStatus.SC_BAD_REQUEST, "application/json; charset=utf-8"))

        GetAlertRequest getAlertRequest = new GetAlertRequest(id: "alert1")
        try {
            OpsGenieClientTestCase.opsgenieClient.alert().getAlert(getAlertRequest)
            fail("should throw exception")
        }
        catch (OpsGenieClientException e) {
            assertEquals(2, e.getCode())
            assertEquals("Could not authenticate.", e.getMessage())
        }

        //unexpected responses will be thrown as ioexception
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("No handler found.".getBytes(), HttpStatus.SC_BAD_REQUEST, "text/plain; charset=utf-8"))
        try {
            OpsGenieClientTestCase.opsgenieClient.alert().getAlert(getAlertRequest)
            fail("should throw exception")
        }
        catch (IOException e) {
            assertEquals("No handler found.", e.getMessage())
        }
    }


    @Test
    public void testListAlertsSuccessfully() throws Exception {
        def alert1Content = [
                id    : "id1", message: "message1", alias: "alias1", createdAt: 1234, updatedAt: 4567,
                status: "open",
                isSeen: true, acknowledged: false, tinyId: "tiny1", tags: ["tag1", "tag2"]
        ]

        def alert2Content = [
                id    : "id2", message: "message2", alias: "alias2", createdAt: 4567, updatedAt: 7890,
                status: "closed",
                isSeen: false, acknowledged: true, tinyId: "tiny2", tags: ["tag2", "tag3"]
        ]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes([alerts: [alert1Content, alert2Content]]), HttpStatus.SC_OK, "application/json; charset=utf-8"))
        ListAlertsRequest request = new ListAlertsRequest();
        request.setCreatedAfter(1)
        request.setCreatedBefore(2)
        request.setUpdatedAfter(3)
        request.setUpdatedBefore(4)
        request.setLimit(10)
        request.setSortBy(ListAlertsRequest.SortBy.createdAt)
        request.setSortOrder(ListAlertsRequest.SortOrder.asc)
        request.setStatus(AlertsRequest.Status.unacked)
        request.setApiKey("customer1")
        request.setTags(["tag1", "tag2"])
        request.setTagsOperator(AlertsRequest.Operator.or)

        def response = OpsGenieClientTestCase.opsgenieClient.alert().listAlerts(request)
        assertEquals(2, response.alerts.size())
        def alert = response.alerts.find { it.id == alert1Content.id }
        assertEquals("id1", alert.getId())
        assertEquals("message1", alert.getMessage())
        assertEquals("tiny1", alert.getTinyId())
        assertTrue(alert.isSeen())
        assertFalse(alert.isAcknowledged())
        assertEquals(1234, alert.getCreatedAt())
        assertEquals(4567, alert.getUpdatedAt())
        assertEquals(["tag1", "tag2"], alert.getTags())

        alert = response.alerts.find { it.id == alert2Content.id }
        assertEquals("id2", alert.getId())
        assertEquals("message2", alert.getMessage())
        assertEquals("tiny2", alert.getTinyId())
        assertFalse(alert.isSeen())
        assertTrue(alert.isAcknowledged())
        assertEquals(4567, alert.getCreatedAt())
        assertEquals(7890, alert.getUpdatedAt())
        assertEquals(["tag2", "tag3"], alert.getTags())


        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert", requestSent.getUrl())

        def parameters = requestSent.getParameters();
        assertEquals("customer1", parameters[TestConstants.API.API_KEY])
        assertEquals("" + request.getCreatedAfter(), parameters[TestConstants.API.CREATED_AFTER])
        assertEquals("" + request.getCreatedBefore(), parameters[TestConstants.API.CREATED_BEFORE])
        assertEquals("" + request.getUpdatedAfter(), parameters[TestConstants.API.UPDATED_AFTER])
        assertEquals("" + request.getUpdatedBefore(), parameters[TestConstants.API.UPDATED_BEFORE])
        assertEquals(request.sortBy.name(), parameters[TestConstants.API.SORT_BY])
        assertEquals(request.sortOrder.name(), parameters[TestConstants.API.ORDER])
        assertEquals(request.status.name(), parameters[TestConstants.API.STATUS])
        assertEquals("" + request.limit, parameters[TestConstants.API.LIMIT])
        assertEquals(request.getTagsOperator().name(), parameters[TestConstants.API.TAGS_OPERATOR])
    }

    @Test
    public void testCountAlertsSuccessfully() throws Exception {

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes([count: 3]), HttpStatus.SC_OK, "application/json; charset=utf-8"))
        CountAlertsRequest request = new CountAlertsRequest();
        request.setCreatedAfter(1)
        request.setCreatedBefore(2)
        request.setUpdatedAfter(3)
        request.setUpdatedBefore(4)
        request.setStatus(AlertsRequest.Status.unacked)
        request.setApiKey("customer1")
        request.setTags(["tag1", "tag2"])
        request.setTagsOperator(AlertsRequest.Operator.or)

        def response = OpsGenieClientTestCase.opsgenieClient.alert().countAlerts(request)
        assertEquals(3, response.count)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/count", requestSent.getUrl())

        def parameters = requestSent.getParameters();
        assertEquals("customer1", parameters[TestConstants.API.API_KEY])
        assertEquals("" + request.getCreatedAfter(), parameters[TestConstants.API.CREATED_AFTER])
        assertEquals("" + request.getCreatedBefore(), parameters[TestConstants.API.CREATED_BEFORE])
        assertEquals("" + request.getUpdatedAfter(), parameters[TestConstants.API.UPDATED_AFTER])
        assertEquals("" + request.getUpdatedBefore(), parameters[TestConstants.API.UPDATED_BEFORE])
        assertEquals(request.status.name(), parameters[TestConstants.API.STATUS])
        assertEquals(request.getTagsOperator().name(), parameters[TestConstants.API.TAGS_OPERATOR])
    }

    @Test
    public void testListAlertThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alert(), "listAlerts", new ListAlertsRequest())
    }
}
