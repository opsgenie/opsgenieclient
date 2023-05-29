package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.BaseUserObj
import com.ifountain.opsgenie.client.model.beans.Role
import com.ifountain.opsgenie.client.model.beans.Team
import com.ifountain.opsgenie.client.model.beans.Team.TeamMember
import com.ifountain.opsgenie.client.model.team.*
import com.ifountain.opsgenie.client.model.user.forward.DeleteForwardingRequest
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.apache.http.HttpHeaders
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.json.JSONObject
import org.junit.Test

import static org.junit.Assert.*

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 2:24 PM
 */
class TeamOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Test
    public void testAddTeamSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"teamId1\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddTeamRequest request = new AddTeamRequest();
        request.setApiKey("customer1");
        request.setName("team1");
        request.setMembers([new TeamMember("user1@xyz.com", TeamMember.Role.admin), new TeamMember("user2@xyz.com")]);

        def response = OpsGenieClientTestCase.opsgenieClient.team().addTeam(request)
        assertEquals("teamId1", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])

        def members = jsonContent[TestConstants.API.MEMBERS]
        assertEquals(2, members.size());
        def member1 = members.find { it.user == "user1@xyz.com" }
        assertNotNull(member1)
        assertEquals(TeamMember.Role.admin.toString(), member1[TestConstants.API.ROLE])

        def member2 = members.find { it.user == "user2@xyz.com" }
        assertNotNull(member2)
        assertEquals(TeamMember.Role.user.toString(), member2[TestConstants.API.ROLE])
    }


    @Test
    public void testAddTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.team(), "addTeam", new AddTeamRequest())
    }

    @Test
    public void testUpdateTeamSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"team1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateTeamRequest request = new UpdateTeamRequest();
        request.setApiKey("customer1");
        request.setId("team1Id");
        request.setName("teamNameUpdated");
        request.setMembers([new TeamMember("user1@xyz.com", TeamMember.Role.admin), new TeamMember("user2@xyz.com")]);

        def response = OpsGenieClientTestCase.opsgenieClient.team().updateTeam(request)
        assertEquals("team1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])

        def members = jsonContent[TestConstants.API.MEMBERS]
        assertEquals(2, members.size());
        def member1 = members.find { it.user == "user1@xyz.com" }
        assertNotNull(member1)
        assertEquals(TeamMember.Role.admin.toString(), member1[TestConstants.API.ROLE])

        def member2 = members.find { it.user == "user2@xyz.com" }
        assertNotNull(member2)
        assertEquals(TeamMember.Role.user.toString(), member2[TestConstants.API.ROLE])
    }

    @Test
    public void testUpdateTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.team(), "updateTeam", new UpdateTeamRequest())
    }

    @Test
    public void testDeleteTeamSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteTeamRequest request = new DeleteTeamRequest();
        request.setApiKey("customer1");
        request.setId("team1Id");
        request.setName("team1");

        def response = OpsGenieClientTestCase.opsgenieClient.team().deleteTeam(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team", requestSent.getUrl())

        assertEquals(3, requestSent.getParameters().size())
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
    }


    @Test
    public void testDeleteTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.team(), "deleteTeam", new DeleteTeamRequest())
    }

    @Test
    public void testGetTeamSuccessfully() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "team1Id");
        jsonContent.put(TestConstants.API.NAME, "team1");
        jsonContent.put(TestConstants.API.MEMBERS, [[user: "user1@xyz.com", role: 'user'], [user: "user2@xyz.com", role: 'admin']]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetTeamRequest request = new GetTeamRequest();
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setName(jsonContent[TestConstants.API.NAME]);
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.team().getTeam(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.NAME], response.getTeam().name)
        assertEquals(jsonContent[TestConstants.API.ID], response.getTeam().id)

        def members = response.getTeam().getMembers()
        assertEquals(2, members.size());
        assertTrue(members.contains(new TeamMember("user1@xyz.com")))
        assertTrue(members.contains(new TeamMember("user2@xyz.com", TeamMember.Role.admin)))

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/team", requestSent.getUrl())
    }

    @Test
    public void testGetTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.team(), "getTeam", new GetTeamRequest(id: "team1"))
    }


    @Test
    public void testListTeamsSuccessfully() throws Exception {
        Map team1Content = new HashMap();
        team1Content.put(TestConstants.API.ID, "team1Id");
        team1Content.put(TestConstants.API.NAME, "team1");
        team1Content.put(TestConstants.API.MEMBERS, [[user: "user1@xyz.com", role: 'user']]);
        Map team2Content = new HashMap();
        team2Content.put(TestConstants.API.ID, "team2Id");
        team2Content.put(TestConstants.API.NAME, "team2");
        team2Content.put(TestConstants.API.MEMBERS, [[user: "user2@xyz.com", role: 'admin']]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(teams: [team1Content, team2Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListTeamsRequest request = new ListTeamsRequest();
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.team().listTeams(request)
        assertEquals(2, response.getTeams().size())
        Team team = response.getTeams().find { it.id == team1Content[TestConstants.API.ID] }
        assertEquals(team1Content[TestConstants.API.NAME], team.name)
        assertEquals(team1Content[TestConstants.API.ID], team.id)
        def members = team.getMembers()
        assertEquals(1, members.size());
        assertTrue(members.contains(new TeamMember("user1@xyz.com")))


        team = response.getTeams().find { it.id == team2Content[TestConstants.API.ID] }
        assertEquals(team2Content[TestConstants.API.NAME], team.name)
        assertEquals(team2Content[TestConstants.API.ID], team.id)

        members = team.getMembers()
        assertEquals(1, members.size());
        assertTrue(members.contains(new TeamMember("user2@xyz.com", TeamMember.Role.admin)))

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/team", requestSent.getUrl())
    }

    @Test
    public void testListTeamsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.team(), "listTeams", new ListTeamsRequest())
    }

    @Test
    public void testListTeamLogsSuccessfully() throws Exception {
        def jsonResponse = [
                logs   : [
                        [owner: "user1@xyz.com", log: "log message1", createdAt: 1000],
                        [owner: "user2@xyz.com", log: "log message2", createdAt: 2000],
                ],
                lastKey: "lastKeyVal",
                took   : 1
        ]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(jsonResponse), HttpStatus.SC_OK, "application/json; charset=utf-8"))
        ListTeamLogsRequest request = new ListTeamLogsRequest();
        request.setId("team1")
        request.setName("teamName1")
        request.setApiKey("customer1")
        request.setLastKey("lastKey")
        request.setSortOrder(ListTeamLogsRequest.SortOrder.asc)
        request.setLimit(10)

        def response = OpsGenieClientTestCase.opsgenieClient.team().listTeamLogs(request)

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team/log", requestSent.getUrl())

        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID])
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME])
        assertEquals(request.getLastKey(), requestSent.getParameters()[TestConstants.API.LAST_KEY])
        assertEquals(request.getSortOrder().name(), requestSent.getParameters()[TestConstants.API.ORDER])
        assertEquals("" + request.getLimit(), requestSent.getParameters()[TestConstants.API.LIMIT])

        assertEquals("lastKeyVal", response.getLastKey())
        assertEquals(2, response.getTeamLogs().size())

        def log = response.getTeamLogs().find { it.owner == "user1@xyz.com" }
        assertEquals("log message1", log.log)
        assertEquals(1000, log.createdAt)

        log = response.getTeamLogs().find { it.owner == "user2@xyz.com" }
        assertEquals("log message2", log.log)
        assertEquals(2000, log.createdAt)
    }

    @Test
    public void testListTeamLogsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.team(), "listTeamLogs", new ListTeamLogsRequest())
    }

    @Test
    void testAddTeamMemberSuccessfullyWithUsername() throws Exception {
        String responseStr = getResponseString("AddTeamMemberResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        AddTeamMemberRequest request = new AddTeamMemberRequest()
        request.setApiKey("customer1")
        request.setTeamIdentifier("TeamName")
        request.setTeamIdentifierType("name")
        request.setUser(new BaseUserObj(username: "user@opsgenie.com"))
        request.setRole(Role.admin)

        def response = opsgenieClient.team().addTeamMember(request)
        assertEquals(3, response.getTook())
        assertEquals("c569c016-alpc-4e20-8a28-bd5dc33b798e",response.getData().getId())
        assertEquals("TeamName",response.getData().getName())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/teams/TeamName/members", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))
    }

    @Test
    void testAddTeamMemberSuccessfullyWithUserId() throws Exception {
        String responseStr = getResponseString("AddTeamMemberResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        AddTeamMemberRequest request = new AddTeamMemberRequest()
        request.setApiKey("customer1")
        request.setTeamIdentifier("c569c016-alpc-4e20-8a28-bd5dc33b798e")
        request.setUser(new BaseUserObj(username: "user@opsgenie.com"))
        request.setRole(Role.admin)

        def response = opsgenieClient.team().addTeamMember(request)
        assertEquals(3, response.getTook())
        assertEquals("c569c016-alpc-4e20-8a28-bd5dc33b798e",response.getData().getId())
        assertEquals("TeamName",response.getData().getName())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/teams/c569c016-alpc-4e20-8a28-bd5dc33b798e/members", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))
    }

    @Test
    void testAddTeamMemberValidationException() throws Exception {
        String responseStr = getResponseString("AddTeamMemberResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        AddTeamMemberRequest request = new AddTeamMemberRequest()
        request.setApiKey("customer1")
        request.setTeamIdentifier("c569c016-alpc-4e20-8a28-bd5dc33b798e")
        request.setUser(new BaseUserObj())

        try {
            def response = opsgenieClient.team().addTeamMember(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Either id or username should be present in user field", exception.getMessage())
        }
        request.setUser(null)
        try {
            def response = opsgenieClient.team().addTeamMember(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [user]", exception.getMessage())
        }
        request.setTeamIdentifier(null)
        try {
            def response = opsgenieClient.team().addTeamMember(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]", exception.getMessage())
        }
        request.setTeamIdentifierType("test")
        try {
            def response = opsgenieClient.team().addTeamMember(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for teamIdentifierType", exception.getMessage())
        }
    }

    @Test
    void testDeleteTeamMemberSuccessfullyWithUsername() throws Exception {
        String responseStr = getResponseString("DeleteTeamMemberResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        DeleteTeamMemberRequest request = new DeleteTeamMemberRequest()
        request.setApiKey("customer1")
        request.setTeamIdentifier("TeamName")
        request.setTeamIdentifierType("name")
        request.setMemberIdentifier("user@opsgenie.com")

        def response = opsgenieClient.team().deleteTeamMember(request)
        assertEquals(1, response.getTook())
        assertEquals("c569c016-alpc-4e20-8a28-bd5dc33b798e",response.getData().getId())
        assertEquals("TeamName",response.getData().getName())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/teams/TeamName/members/user@opsgenie.com", requestSent.getUrl())
        assertEquals("application/x-www-form-urlencoded; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))
    }

    @Test
    void testDeleteTeamMemberSuccessfullyWithUserId() throws Exception {
        String responseStr = getResponseString("DeleteTeamMemberResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        DeleteTeamMemberRequest request = new DeleteTeamMemberRequest()
        request.setApiKey("customer1")
        request.setTeamIdentifier("test_team_id")
        request.setMemberIdentifier("user@opsgenie.com")

        def response = opsgenieClient.team().deleteTeamMember(request)
        assertEquals(1, response.getTook())
        assertEquals("c569c016-alpc-4e20-8a28-bd5dc33b798e",response.getData().getId())
        assertEquals("TeamName",response.getData().getName())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/teams/test_team_id/members/user@opsgenie.com", requestSent.getUrl())
        assertEquals("application/x-www-form-urlencoded; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))
    }

    @Test
    void testDeleteTeamMemberValidationException() throws Exception {
        String responseStr = getResponseString("DeleteTeamMemberResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        DeleteTeamMemberRequest request = new DeleteTeamMemberRequest()
        request.setApiKey("customer1")
        request.setTeamIdentifier("test_team_id")
        try {
            def response = opsgenieClient.team().deleteTeamMember(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [username or userId]", exception.getMessage())
        }

        request.setTeamIdentifier(null)
        try {
            def response = opsgenieClient.team().deleteTeamMember(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]", exception.getMessage())
        }
        request.setTeamIdentifierType("test")
        try {
            def response = opsgenieClient.team().deleteTeamMember(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for teamIdentifierType", exception.getMessage())
        }
    }

    private String getResponseString(String responseType) {
        def jsonSlurper = new JsonSlurper()
        JSONObject data = jsonSlurper.parse(new FileReader("sdk/src/test/resources/TeamOpsgenieClient.json")) as JSONObject
        Object responseJson = data.get(responseType)
        String responseStr = new JsonBuilder(responseJson).toPrettyString()
        responseStr
    }

}
