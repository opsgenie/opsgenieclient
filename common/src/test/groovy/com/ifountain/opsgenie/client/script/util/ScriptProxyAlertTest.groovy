package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.test.util.AlertApiMock
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.SuccessResponseMock
import com.ifountain.opsgenie.client.util.JsonUtils
import com.opsgenie.oas.sdk.ApiClient
import com.opsgenie.oas.sdk.model.*
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyAlertTest {
    ApiClient apiClient;
    AlertApiMock alertApiMock = new AlertApiMock()
    String apiKey = "key1"
    ScriptProxy scriptProxy;

    @Before
    public void setUp() {
        apiClient = new ApiClient()
        apiClient.setApiKey(apiKey)
        alertApiMock.setApiClient(apiClient)
        scriptProxy = new ScriptProxy(apiClient)
        scriptProxy.setAlertApi(alertApiMock)
    }

    @Test
    public void testCreateAlertSuccessfully() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();
        alertApiMock.setGenericSuccessResponse(successResponse);

        def params = [message    : "my message",
                      description: "my description",
                      source     : "source1", entity: "entity1", alias: "alias1",
                      note       : "alert note",
                      tags       : ["tag1", "tag2"], actions: ["action1", "action2"],
                      teams      : [["name" : "team1"],["name" : "team2"]],
                      details    : [param1: "value1", param2: "value2"]];

        Map resp = scriptProxy.createAlert(params);

        CommonTestUtils.assertGenericResponseWithoutData(resp)

        assertEquals(1, alertApiMock.getExecutedRequests().size())

        CreateAlertPayload payload = alertApiMock.getExecutedRequests()[0] as CreateAlertPayload
        TeamRecipient team1 = new TeamRecipient();
        team1.setName("team1");

        TeamRecipient team2 = new TeamRecipient();
        team2.setName("team2");

        def expectedTeamsList = [
                team1,
                team2
        ];

        assertEquals(params.actions, payload.getActions())
        assertEquals(params.tags, payload.getTags())
        assertEquals(expectedTeamsList, payload.getTeams())
        assertEquals(params.details, payload.getDetails())
        assertEquals(params.message, payload.getMessage())
        assertEquals(params.description, payload.getDescription())
        assertEquals(params.source, payload.getSource())
        assertEquals(params.entity, payload.getEntity())
        assertEquals(params.note, payload.getNote())
        assertEquals(params.user, payload.getUser())
    }

    @Test
    public void testCreateAlertWillThrowExceptionIfDetailsIsNotMap() throws Exception {
        def params = [details: "invalidmap"]

        try {
            scriptProxy.createAlert(params);
            fail("Should throw exception since details is invalid");
        }
        catch (Exception ex) {
            assertEquals("[${TestConstants.API.DETAILS}] paramater should be hash".toString(), ex.getMessage())
        }
    }

    @Test
    public void testCloseAlert() throws Exception {
        SuccessResponseMock successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        alertApiMock.setGenericSuccessResponseMock(successResponse);

        def params = [alertId: "alertId1", user: "someuser", source: "source1", "note": "note1"];
        Map response = scriptProxy.closeAlert(params);

        assertEquals("alertId1", response.identifier);
        assertEquals("id", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        assertEquals(1, alertApiMock.getExecutedRequests().size());

        def executedRequests = alertApiMock.getExecutedRequests();

        CloseAlertRequest request = executedRequests[0] as CloseAlertRequest;
        assertEquals("someuser", request.getBody().getUser());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("note1", request.getBody().getNote());
    }

    @Test
    public void testAcknowledgeAlert() throws Exception {
        SuccessResponseMock successResponse = CommonTestUtils.createGenericSuccessResponseMock();

        alertApiMock.setGenericSuccessResponseMock(successResponse);

        def params = [alias: "alias1", user: "someuser", source: "source1", note: "note"];
        Map response = scriptProxy.acknowledge(params);

        assertEquals("alias1", response.identifier);
        assertEquals("alias", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        assertEquals(1, alertApiMock.getExecutedRequests().size());

        def executedRequests = alertApiMock.getExecutedRequests();

        AcknowledgeAlertRequest request = executedRequests[0] as AcknowledgeAlertRequest;

        assertEquals("someuser", request.getBody().getUser());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("note", request.getBody().getNote());
    }

    @Test
    public void testEscalateAlertWithNewStructure() throws Exception {
        SuccessResponseMock successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        alertApiMock.setGenericSuccessResponseMock(successResponse);

        def params = [tinyId: "tinyId1", user: "someuser", source: "source1", note: "note",
                      escalation: [id: "escalation1"]];
        Map response = scriptProxy.escalateToNext(params);

        assertEquals("tinyId1", response.identifier);
        assertEquals("tiny", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        assertEquals(1, alertApiMock.getExecutedRequests().size());

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size());

        EscalateAlertRequest request = executedRequests[0] as EscalateAlertRequest;
        assertEquals("someuser", request.getBody().getUser());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("note", request.getBody().getNote());

        EscalationRecipient expectedEscalation = new EscalationRecipient();
        expectedEscalation.setId("escalation1");

        assertEquals(expectedEscalation, request.getBody().getEscalation());
    }

    @Test
    public void testEscalateAlertWithOldStructure() throws Exception {
        SuccessResponseMock successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        alertApiMock.setGenericSuccessResponseMock(successResponse);

        def params = [tinyId: "tinyId1", user: "someuser", source: "source1", note: "note",
                      escalationName: "escalationName1"];
        Map response = scriptProxy.escalateToNext(params);

        assertEquals("tinyId1", response.identifier);
        assertEquals("tiny", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        assertEquals(1, alertApiMock.getExecutedRequests().size());

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size());

        EscalateAlertRequest request = executedRequests[0] as EscalateAlertRequest;
        assertEquals("someuser", request.getBody().getUser());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("note", request.getBody().getNote());

        EscalationRecipient expectedEscalation = new EscalationRecipient();
        expectedEscalation.setName("escalationName1");

        assertEquals(expectedEscalation, request.getBody().getEscalation());
    }

    @Test
    public void testAddNote() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [alias: "alias1", note: "mynote", user: "someuser", source: "source1"];

        alertApiMock.setGenericSuccessResponseMock(successResponse);
        Map response = scriptProxy.addNote(params);
        assertEquals("alias1", response.identifier);
        assertEquals("alias", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteToAlertRequest request = executedRequests[0] as AddNoteToAlertRequest;

        assertEquals("mynote", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("someuser", request.getBody().getUser());
    }

    @Test
    public void testAddTags() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [tinyId: "tinyId1", note: "mynote", user: "someuser", source: "source1", tags: ["tag1","tag2"]];

        alertApiMock.setGenericSuccessResponseMock(successResponse);
        Map response = scriptProxy.addTags(params)
        assertEquals("tinyId1", response.identifier);
        assertEquals("tiny", response.identifierType);
        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddTagsToAlertRequest request = executedRequests[0] as AddTagsToAlertRequest;

        assertEquals("mynote", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("someuser", request.getBody().getUser());
        assertEquals(["tag1","tag2"], request.getBody().getTags());

    }

    @Test
    public void testRemoveTags() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [alertId: "alertId1", note: "mynote", user: "someuser", source: "source1", tags: ["tag1","tag2"]];

        alertApiMock.setGenericSuccessResponseMock(successResponse);
        Map response = scriptProxy.removeTags(params);
        assertEquals("alertId1", response.identifier);
        assertEquals("id", response.identifierType);
        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        RemoveTagsFromAlertRequest request = executedRequests[0] as RemoveTagsFromAlertRequest;

        assertEquals("mynote", request.getNote());
        assertEquals("source1", request.getSource());
        assertEquals("someuser", request.getUser());
        assertEquals(["tag1","tag2"], request.getTags());

    }

    @Test
    public void testExecuteAction() throws Exception {
        SuccessResponseMock successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [id: "alertId1", action: "action", user: "user1@xyz.com", note: "note1", source: "source1"];

        alertApiMock.setGenericSuccessResponseMock(successResponse);

        Map response = scriptProxy.executeAlertAction(params);

        assertEquals("alertId1", response.identifier);
        assertEquals("id", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ExecuteCustomAlertActionRequest request = executedRequests[0] as ExecuteCustomAlertActionRequest;

        assertEquals("note1", request.getBody().getNote());
        assertEquals("user1@xyz.com", request.getBody().getUser());
        assertEquals("source1", request.getBody().getSource());
    }


    @Test
    public void testFileAttach() throws Exception {
        def params = [alertId: "alertId1", indexFile: "index.html", attachment: "dummy.txt", user: "someuser", source: "source1", note: "comment"]

        scriptProxy.attach(params)
        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())

        AddAttachmentToAlertRequest request = executedRequests[0] as AddAttachmentToAlertRequest;

        assertEquals("alertId1", request.getIdentifier());
        assertEquals("id", request.getAlertIdentifierType().getValue());
        assertEquals("index.html", request.getIndexFile());
        assertEquals(new File("dummy.txt"), request.getFile());
        assertEquals("someuser", request.getUser());
    }

    @Test
    public void testInputStreamAttach() throws Exception {
        String content = "example content";
        byte[] contentBytes = content.getBytes();

        def stream = new ByteArrayInputStream(contentBytes)

        def params = [tinyId: "tinyId1", indexFile: "index.html", stream: stream, fileName: "dummy.txt", user: "someuser", source: "source1", note: "comment"]

        scriptProxy.attach(params)


        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddAttachmentToAlertRequest request = executedRequests[0] as AddAttachmentToAlertRequest;
        assertEquals("tinyId1", request.getIdentifier())
        assertEquals("tiny", request.getAlertIdentifierType().getValue())
        assertEquals("index.html", request.getIndexFile());
        assertEquals("someuser", request.getUser());
        assertEquals(contentBytes.toString(), IOUtils.toByteArray(new FileInputStream(request.getFile())).toString());
    }

    @Test
    public void testGetAlert() throws Exception {
        GetAlertResponse expectedResponse = new GetAlertResponse();

        Alert expectedAlertData = new Alert();

        expectedAlertData.setMessage("message1");
        expectedAlertData.setActions(Collections.singletonList("action1"));
        expectedAlertData.setAlias("alias1");
        expectedAlertData.setCreatedAt(new DateTime(new Date("07/07/2017 17:17")));
        expectedAlertData.setDescription("desc");
        expectedAlertData.setEntity("entity");
        expectedAlertData.setDetails(new HashMap<String, String>() {{
            put("param1", "param1Value");
        }});
        expectedAlertData.setId("alert1");
        expectedAlertData.setSource("ip1");
        expectedAlertData.setStatus("open");
        expectedAlertData.setTags(Collections.singletonList("tag1"));
        expectedAlertData.setTinyId("tinyId1");
        expectedAlertData.setTeams(Collections.singletonList((new TeamMeta()).id("teamId1")));

        expectedResponse.setData(expectedAlertData);
        expectedResponse.setTook(0.169f);
        expectedResponse.setRequestId("request-id");

        alertApiMock.setGetAlertResponse(expectedResponse);

        def params = [alias: "alias1", tinyId: "tinyId1", alertId: "alertId1"];
        Map response = scriptProxy.getAlert(params);

        assertEquals(expectedResponse.getData().getMessage(), response.message);
        assertEquals(expectedResponse.getData().getActions(), response.actions);
        assertEquals(expectedResponse.getData().getAlias(), response.alias);
        assertEquals(expectedResponse.getData().getCreatedAt().toString(), response.createdAt);
        assertEquals(expectedResponse.getData().getDescription(), response.description);
        assertEquals(expectedResponse.getData().getEntity(), response.entity);
        assertEquals(expectedResponse.getData().getDetails(), response.details);
        assertEquals(expectedResponse.getData().getId(), response.alertId);
        assertEquals(expectedResponse.getData().getSource(), response.source);
        assertEquals(expectedResponse.getRequestId(), response.requestId);
        assertEquals(expectedResponse.getData().getStatus(), response.status);
        assertEquals([["id" : "teamId1", "name" : null]], response.teams);
        assertEquals(expectedResponse.getData().getTags(), response.tags);
        assertEquals(expectedResponse.getData().getTinyId(), response.tinyId);
    }

    @Test
    public void testListAlertLogs() throws Exception {
        ListAlertLogsResponse expectedResponse = new ListAlertLogsResponse();

        expectedResponse.setTook(0.169f);
        expectedResponse.setRequestId("request-id");

        AlertPaging paging = new AlertPaging();
        paging.setFirst("paging_first");
        paging.setNext("paging_next");

        expectedResponse.setPaging(paging);

        List<AlertLog> alertLogs = [
                new AlertLog()
                        .log("log1")
                        .owner("owner1")
                        .type("type1")
                        .createdAt(new DateTime(new Date("7/7/2017 17:17")))
                        .offset("offset1"),
                new AlertLog()
                        .log("log2")
                        .owner("owner2")
                        .type("type2")
                        .createdAt(new DateTime(new Date("17/17/2017 17:17")))
                        .offset("offset2")
        ];

        expectedResponse.setData(alertLogs);

        alertApiMock.setListAlertLogsResponse(expectedResponse);

        def params = [
                alias: "alias1",
                order: "asc",
                limit: 10,
                direction: "next",
                offset: "offset_param"
        ]

        def response = scriptProxy.listAlertLogs(params);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size());
        ListAlertLogsRequest request = executedRequests[0] as ListAlertLogsRequest;

        assertEquals("offset_param", request.getOffset());
        assertEquals(ListAlertLogsRequest.OrderEnum.ASC, request.getOrder());
        assertEquals(10, request.getLimit());
        assertEquals(ListAlertLogsRequest.DirectionEnum.NEXT, request.getDirection());

        List<AlertLog> logsFromResponse = response.logs;

        assertEquals(2, logsFromResponse.size());

        def log = logsFromResponse.find { it.owner == "owner1" }
        assertEquals("log1", log.log);
        assertEquals("type1", log.type);
        assertEquals(new DateTime(new Date("7/7/2017 17:17")).toString(), log.createdAt);
        assertEquals("offset1", log.offset);

        log = logsFromResponse.find { it.owner == "owner2" }
        assertEquals("log2", log.log);
        assertEquals("type2", log.type);
        assertEquals(new DateTime(new Date("17/17/2017 17:17")).toString(), log.createdAt);
        assertEquals("offset2", log.offset);

        assertEquals("request-id", response.requestId);
        assertEquals(0.169f, (float) response.took, 0.001f);
        assertEquals([
                "first": "paging_first",
                "next" : "paging_next"
        ], response.paging);
    }

    @Test
    public void testListAlertRecipients() throws Exception {
        ListAlertRecipientsResponse expectedResponse = new ListAlertRecipientsResponse();

        expectedResponse.setTook(0.169f);
        expectedResponse.setRequestId("request-id");

        List<AlertRecipient> expectedRecipients = [
                new AlertRecipient()
                        .state("action")
                        .method("Acknowledge")
                        .createdAt(new DateTime(new Date("7/7/2017 17:17")))
                        .updatedAt(new DateTime(new Date("7/7/2017 18:18")))
                        .user(new AlertUserMeta().username("username1").id("userId1")),
                new AlertRecipient()
                        .state("notactive")
                        .method("")
                        .createdAt(new DateTime(new Date("8/8/2017 18:18")))
                        .updatedAt(new DateTime(new Date("8/8/2017 19:19")))
                        .user(new AlertUserMeta().username("username2").id("userId2"))
        ];

        expectedResponse.setData(expectedRecipients);

        alertApiMock.setListAlertRecipientsResponse(expectedResponse);

        def params = [alias: "alias1"];

        def response = scriptProxy.listAlertRecipients(params);

        assertEquals(0.169f, (float) response.took, 0.001f);
        assertEquals(expectedResponse.getRequestId(), response.requestId);
        assertEquals([
                [
                        "user": [
                                "id": "userId1",
                                "username": "username1"
                        ],
                        "state": "action",
                        "method": "Acknowledge",
                        "createdAt": new DateTime(new Date("7/7/2017 17:17")).toString(),
                        "updatedAt": new DateTime(new Date("7/7/2017 18:18")).toString()
                ],
                [
                        "user": [
                                "id": "userId2",
                                "username": "username2"
                        ],
                        "state": "notactive",
                        "method": "",
                        "createdAt": new DateTime(new Date("8/8/2017 18:18")).toString(),
                        "updatedAt": new DateTime(new Date("8/8/2017 19:19")).toString()
                ]
        ], response.users);
    }

    @Test
    public void testListAlerts() throws Exception {
        ListAlertsResponse expectedResponse = new ListAlertsResponse();

        List<BaseAlert> expectedAlerts = [
                new BaseAlert()
                        .id("alertId1")
                        .tinyId("tinyId1")
                        .alias("alias1")
                        .message("message1")
                        .status("closed")
                        .acknowledged(false)
                        .isSeen(true)
                        .tags([
                                "tag1",
                                "tag2"
                        ])
                        .snoozed(true)
                        .snoozedUntil(new DateTime(new Date("8/8/2017 18:18")))
                        .count(79)
                        .lastOccurredAt(new DateTime(new Date("8/8/2017 18:00")))
                        .createdAt(new DateTime(new Date("8/8/2017 10:18")))
                        .updatedAt(new DateTime(new Date("8/8/2017 11:18")))
                        .source("Isengard")
                        .owner("morpheus@opsgenie.com")
                        .priority("P4")
                        .teams([
                                new TeamMeta().id("teamId1"),
                                new TeamMeta().id("teamId2")
                        ])
                        .integration(new AlertIntegration()
                                            .id("integration-id1")
                                            .name("integration1")
                                            .type("API")
                        )
                        .report(new AlertReport()
                                            .ackTime(123)
                                            .closeTime(456)
                                            .acknowledgedBy("user1@opsgenie.com")
                                            .closedBy("user2@opsgenie.com")
                        ),
                new BaseAlert()
                        .id("alertId2")
                        .tinyId("tinyId2")
                        .alias("alias2")
                        .message("message2")
                        .status("open")
                        .acknowledged(true)
                        .isSeen(false)
                        .tags([
                                "tag1",
                                "tag2"
                        ])
                        .snoozed(false)
                        .count(1)
                        .lastOccurredAt(new DateTime(new Date("9/8/2017 18:00")))
                        .createdAt(new DateTime(new Date("9/8/2017 10:18")))
                        .updatedAt(new DateTime(new Date("9/8/2017 11:18")))
                        .source("Zion")
                        .owner("neo@opsgenie.com")
                        .priority("P5")
                        .teams([
                                new TeamMeta().id("teamId3"),
                                new TeamMeta().id("teamId4")
                        ])
                        .integration(new AlertIntegration()
                        .id("integration-id2")
                        .name("integration2")
                        .type("CloudWatch")
                        )
        ];

        expectedResponse.setData(expectedAlerts);

        alertApiMock.setListAlertsResponse(expectedResponse);

        def params = [limit: 10, order: "asc", sort: "createdAt", offset: 2,
                      query: "message: (lorem OR ipsum)", searchIdentifier: "alertId1", searchIdentifierType: "id"]

        List<Map> response = scriptProxy.listAlerts(params)

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListAlertsRequest request = executedRequests[0] as ListAlertsRequest;

        assertEquals(10, request.getLimit());
        assertEquals(ListAlertsRequest.OrderEnum.ASC, request.getOrder());
        assertEquals(ListAlertsRequest.SortEnum.CREATEDAT, request.getSort());
        assertEquals(2, request.getOffset());
        assertEquals("message: (lorem OR ipsum)", request.getQuery());
        assertEquals("alertId1", request.getSearchIdentifier());
        assertEquals(ListAlertsRequest.SearchIdentifierTypeEnum.ID, request.getSearchIdentifierType());

        assertEquals(2, response.size());

        assertEquals(expectedResponse.getData()[0].message, response[0].message);
        assertEquals(expectedResponse.getData()[0].alias, response[0].alias);
        assertEquals(expectedResponse.getData()[0].createdAt.toString(), response[0].createdAt);
        assertEquals(expectedResponse.getData()[0].id, response[0].id);
        assertEquals(expectedResponse.getData()[0].source, response[0].source);
        assertEquals(expectedResponse.getData()[0].status, response[0].status);
        assertEquals(expectedResponse.getData()[0].tags, response[0].tags);
        assertEquals(expectedResponse.getData()[0].tinyId, response[0].tinyId);
        assertEquals(expectedResponse.getData()[0].tags, response[0].tags);
        assertEquals([["id": "teamId1", "name" : null], ["id": "teamId2", "name" : null]], response[0].teams);
        assertEquals(expectedResponse.getData()[0].acknowledged, response[0].acknowledged);
        assertEquals(expectedResponse.getData()[0].isSeen, response[0].isSeen);
        assertEquals(expectedResponse.getData()[0].snoozed, response[0].snoozed);
        assertEquals(expectedResponse.getData()[0].snoozedUntil.toString(), response[0].snoozedUntil);
        assertEquals(expectedResponse.getData()[0].count, response[0].count);
        assertEquals(expectedResponse.getData()[0].lastOccurredAt.toString(), response[0].lastOccurredAt);
        assertEquals(expectedResponse.getData()[0].updatedAt.toString(), response[0].updatedAt);
        assertEquals(expectedResponse.getData()[0].owner, response[0].owner);
        assertEquals(expectedResponse.getData()[0].priority, response[0].priority);
        assertEquals(["id": "integration-id1", "name": "integration1", "type": "API"], response[0].integration);

        Map expectedReport = ["ackTime": 123, "closeTime": 456, "acknowledgedBy": "user1@opsgenie.com",
                              "closedBy": "user2@opsgenie.com"];

        assertTrue(expectedReport.equals(response[0].report));
    }

    @Test
    public void testListAlertNotes() throws Exception {
        ListAlertNotesResponse expectedResponse = new ListAlertNotesResponse();

        expectedResponse.setTook(0.169f);
        expectedResponse.setRequestId("request-id");


        AlertPaging paging = new AlertPaging();
        paging.setFirst("paging_first");
        paging.setNext("paging_next");

        expectedResponse.setPaging(paging);

        expectedResponse.setData([
                new AlertNote()
                        .note("note1")
                        .owner("owner1")
                        .createdAt(new DateTime(new Date("9/8/2017 18:00")))
                        .offset("offset1"),
                new AlertNote()
                        .note("note2")
                        .owner("owner2")
                        .createdAt(new DateTime(new Date("10/8/2017 18:00")))
                        .offset("offset2")
        ]);

        alertApiMock.setListAlertNotesResponse(expectedResponse);
        def params = [alertId: "alertId1", order: "asc", limit: 10, direction: "prev", offset: "offset1"]

        def response = scriptProxy.listAlertNotes(params);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListAlertNotesRequest request = executedRequests[0] as ListAlertNotesRequest;

        assertEquals("alertId1", request.getIdentifier());
        assertEquals(ListAlertNotesRequest.IdentifierTypeEnum.ID, request.getIdentifierType());
        assertEquals(ListAlertNotesRequest.OrderEnum.ASC, request.getOrder());
        assertEquals(10, request.getLimit());
        assertEquals(ListAlertNotesRequest.DirectionEnum.PREV, request.getDirection());
        assertEquals("offset1", request.getOffset());

        assertEquals(expectedResponse.getRequestId(), response.requestId);
        assertEquals(expectedResponse.getTook(), (float) response.took, 0.001f);
        assertEquals([
                "first": "paging_first",
                "next": "paging_next"
        ], response.paging);

        List<AlertNote> notes = response.notes;

        assertEquals(2, notes.size())
        def note = notes.find { it.owner == "owner1" }
        assertEquals("note1", note.note)
        assertEquals(new DateTime(new Date("9/8/2017 18:00")).toString(), note.createdAt)
        assertEquals("offset1", note.offset);

        note = notes.find { it.owner == "owner2" }
        assertEquals("note2", note.note)
        assertEquals(new DateTime(new Date("10/8/2017 18:00")).toString(), note.createdAt)
        assertEquals("offset2", note.offset);
    }

    @Test
    public void testAddTeamOldParameters() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();
        def params = [alias: "alias1", note: "mynote", user: "someuser", source: "source1", team: "teamName1"];

        alertApiMock.setGenericSuccessResponse(successResponse);
        Map response = scriptProxy.addTeam(params);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddTeamToAlertRequest request = executedRequests[0] as AddTeamToAlertRequest;

        assertEquals(new TeamRecipient().name("teamName1"), request.getBody().getTeam());
        assertEquals("mynote", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("someuser", request.getBody().getUser());
    }

    @Test
    public void testAddTeamNewParamters() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();
        def params = [alias: "alias1", note: "mynote", user: "someuser", source: "source1", team: ["id": "teamId1"]];

        alertApiMock.setGenericSuccessResponse(successResponse);
        Map response = scriptProxy.addTeam(params);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddTeamToAlertRequest request = executedRequests[0] as AddTeamToAlertRequest;

        assertEquals(new TeamRecipient().id("teamId1"), request.getBody().getTeam());
        assertEquals("mynote", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("someuser", request.getBody().getUser());
    }

    @Test
    public void testAssignOldParameters() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [alias: "alias1", note: "mynote", user: "someuser", source: "source1", owner: "owner@opsgenie.com"];

        alertApiMock.setGenericSuccessResponseMock(successResponse);
        Map response = scriptProxy.assign(params);
        assertEquals("alias1", response.identifier);
        assertEquals("alias", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AssignAlertRequest request = executedRequests[0] as AssignAlertRequest;

        assertEquals(new UserRecipient().username("owner@opsgenie.com"), request.getBody().getOwner());
        assertEquals("mynote", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("someuser", request.getBody().getUser());
    }

    @Test
    public void testAssignNewParameters() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [alias: "alias1", note: "mynote", user: "someuser", source: "source1", owner: ["username": "owner@opsgenie.com"]];

        alertApiMock.setGenericSuccessResponseMock(successResponse);
        Map response = scriptProxy.assign(params);
        assertEquals("alias1", response.identifier);
        assertEquals("alias", response.identifierType);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AssignAlertRequest request = executedRequests[0] as AssignAlertRequest;

        assertEquals(new UserRecipient().username("owner@opsgenie.com"), request.getBody().getOwner());
        assertEquals("mynote", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
        assertEquals("someuser", request.getBody().getUser());
    }

    @Test
    public void testDeleteAlert() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [alias: "alias1", user: "someuser", source: "source1"];

        alertApiMock.setGenericSuccessResponseMock(successResponse);
        Map response = scriptProxy.deleteAlert(params);
        assertEquals("alias1", response.identifier);
        assertEquals("alias", response.identifierType);
        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteAlertRequest request = executedRequests[0] as DeleteAlertRequest;

        assertEquals("source1", request.getSource());
        assertEquals("someuser", request.getUser());
    }

    @Test
    public void testAddSavedSearch() throws Exception {
        CreateSavedSearchResponse expectedResponse = new CreateSavedSearchResponse();

        expectedResponse.setTook(0.169f);
        expectedResponse.setRequestId("request-id");
        expectedResponse.setData(new SavedSearchMeta()
                .id("savedSearchId1")
                .name("savedSearchName")
        );

        def params = [name: "savedSearchName", query: "query1", owner: ["username": "owner1"], description: "desc", teams: ["name": "team1"]];

        alertApiMock.setCreateSavedSearchResponse(expectedResponse);
        Map response = scriptProxy.addSavedSearch(params);

        assertEquals(expectedResponse.getTook(), (float) response.took, 0.001f);
        assertEquals(expectedResponse.getRequestId(), response.requestId);
        assertEquals(JsonUtils.toMap(expectedResponse.getData()), response.data);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        CreateSavedSearchPayload request = executedRequests[0] as CreateSavedSearchPayload;

        assertEquals("savedSearchName", request.getName());
        assertEquals("query1", request.getQuery());
        assertEquals(new UserRecipient().username("owner1"), request.getOwner());
        assertEquals("desc", request.getDescription());
        assertEquals([new TeamRecipient().name("team1")], request.getTeams());
    }

    @Test
    public void testDeleteSavedSearch() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponseMock();
        def params = [name: "savedSearch1"];

        alertApiMock.setGenericSuccessResponseMock(successResponse);
        Map response = scriptProxy.deleteSavedSearch(params);
        assertEquals("savedSearch1", response.identifier);
        assertEquals("name", response.identifierType);
        CommonTestUtils.assertGenericResponseWithoutData(response);
    }

    @Test
    public void testGetRequestStatus() throws Exception {
        GetRequestStatusResponse expectedResponse = new GetRequestStatusResponse();
        expectedResponse.setRequestId("request-id");
        expectedResponse.setTook(0.169f);
        expectedResponse.setData(new AlertRequestStatus()
                .status("open")
                .alias("alias1")
                .action("Create")
                .alertId("alertId1")
                .integrationId("intId1")
                .isSuccess(true)
                .processedAt(new DateTime(new Date("10/07/2017 17:17")))
        );

        def params = [requestId: "request-id"];

        alertApiMock.setGetRequestStatusResponse(expectedResponse);

        Map response = scriptProxy.getRequestStatus(params);

        assertEquals(expectedResponse.getTook(), (float) response.took, 0.001f);
        assertEquals(expectedResponse.getRequestId(), response.requestId);
        assertEquals(JsonUtils.toMap(expectedResponse.getData()), response.data);
    }

    @Test
    public void testGetSavedSearch() throws Exception {
        GetSavedSearchResponse expectedResponse = new GetSavedSearchResponse();

        expectedResponse.setRequestId("request-id");
        expectedResponse.setTook(0.169f);
        expectedResponse.setData(new SavedSearch()
                .name("savedSearchName")
                .id("teamId")
                .teams([new SavedSearchEntity()
                        .id("savedSearchEntityId")
                ])
                .description("desc")
                .createdAt(new DateTime(new Date("10/7/2017 17:17")))
                .owner(new SavedSearchEntity()
                    .id("ownerId")
                )
                .query("query1")
                .updatedAt(new DateTime(new Date("11/7/2017 17:17")))
        );

        def params = [id: "saved-search-id"];

        alertApiMock.setGetSavedSearchResponse(expectedResponse);
        Map response = scriptProxy.getSavedSearch(params);

        assertEquals(expectedResponse.getTook(), (float) response.took, 0.001f);
        assertEquals(expectedResponse.getRequestId(), response.requestId);
        assertEquals(JsonUtils.toMap(expectedResponse.getData()), response.data);
    }

    @Test
    public void testListSavedSearch() throws Exception {
        ListSavedSearchesResponse expectedResponse = new ListSavedSearchesResponse();

        expectedResponse.setRequestId("request-id");
        expectedResponse.setTook(0.169f);

        List<SavedSearchMeta> savedSearchesList = new ArrayList<SavedSearchMeta>();
        savedSearchesList.add(new SavedSearchMeta()
                .name("savedSearchName1")
                .id("savedSearchId1"));
        savedSearchesList.add(new SavedSearchMeta()
                .name("savedSearchName2")
                .id("savedSearchId2"));

        expectedResponse.setData(savedSearchesList);

        alertApiMock.setListSavedSearchesResponse(expectedResponse);
        Map response = scriptProxy.listSavedSearch();

        assertEquals(expectedResponse.getTook(), (float) response.took, 0.001f);
        assertEquals(expectedResponse.getRequestId(), response.requestId);

        List<Map> savedSearchMapList = new ArrayList<Map>();
        expectedResponse.getData().each {savedSearchMapList.add(JsonUtils.toMap(it))};

        assertEquals(savedSearchMapList, response.data);
    }
}
