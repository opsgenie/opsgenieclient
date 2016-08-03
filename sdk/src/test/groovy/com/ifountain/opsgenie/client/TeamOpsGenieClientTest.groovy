package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.Team
import com.ifountain.opsgenie.client.model.team.AddTeamMemberRequest
import com.ifountain.opsgenie.client.model.team.AddTeamRequest
import com.ifountain.opsgenie.client.model.team.DeleteTeamMemberRequest
import com.ifountain.opsgenie.client.model.team.DeleteTeamRequest
import com.ifountain.opsgenie.client.model.team.GetTeamRequest
import com.ifountain.opsgenie.client.model.team.ListTeamLogsRequest
import com.ifountain.opsgenie.client.model.team.ListTeamsRequest
import com.ifountain.opsgenie.client.model.team.UpdateTeamRequest
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
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
        request.setMembers([new Team.TeamMember("user1@xyz.com", Team.TeamMember.Role.admin), new Team.TeamMember("user2@xyz.com")]);

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
        assertEquals(Team.TeamMember.Role.admin.toString(), member1[TestConstants.API.ROLE])

        def member2 = members.find { it.user == "user2@xyz.com" }
        assertNotNull(member2)
        assertEquals(Team.TeamMember.Role.user.toString(), member2[TestConstants.API.ROLE])
    }


    @Test
    public void testAddTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.team(), "addTeam", new AddTeamRequest())
    }

    @Test
    public void testAddTeamMemberSuccessfullyWithUsername() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddTeamMemberRequest request = new AddTeamMemberRequest();
        request.setApiKey("customer1");
        request.setUsername("user1")
        request.setRole(Team.TeamMember.Role.admin)
        request.setName("team1");

        def response = OpsGenieClientTestCase.opsgenieClient.team().addTeamMember(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team/member", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getRole().toString(), jsonContent[TestConstants.API.ROLE])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
    }

    @Test
    public void testAddTeamMemberSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddTeamMemberRequest request = new AddTeamMemberRequest();
        request.setApiKey("customer1");
        request.setUserId("user1")
        request.setRole(Team.TeamMember.Role.user)
        request.setName("team1");

        def response = OpsGenieClientTestCase.opsgenieClient.team().addTeamMember(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team/member", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getRole().toString(), jsonContent[TestConstants.API.ROLE])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
    }

    @Test
    public void testDeleteTeamMemberSuccessfullyWithUsername() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteTeamMemberRequest request = new DeleteTeamMemberRequest();
        request.setApiKey("customer1");
        request.setUsername("user1")
        request.setName("team1");

        def response = OpsGenieClientTestCase.opsgenieClient.team().deleteTeamMember(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team/member", requestSent.getUrl())


        assertEquals(3, requestSent.getParameters().size())
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME])
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME])
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
    }

    @Test
    public void testDeleteTeamMemberSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteTeamMemberRequest request = new DeleteTeamMemberRequest();
        request.setApiKey("customer1");
        request.setUserId("user1")
        request.setName("team1");

        def response = OpsGenieClientTestCase.opsgenieClient.team().deleteTeamMember(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/team/member", requestSent.getUrl())

        assertEquals(3, requestSent.getParameters().size())
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME])
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID])
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])

    }

    @Test
    public void testUpdateTeamSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"team1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateTeamRequest request = new UpdateTeamRequest();
        request.setApiKey("customer1");
        request.setId("team1Id");
        request.setName("teamNameUpdated");
        request.setMembers([new Team.TeamMember("user1@xyz.com", Team.TeamMember.Role.admin), new Team.TeamMember("user2@xyz.com")]);

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
        assertEquals(Team.TeamMember.Role.admin.toString(), member1[TestConstants.API.ROLE])

        def member2 = members.find { it.user == "user2@xyz.com" }
        assertNotNull(member2)
        assertEquals(Team.TeamMember.Role.user.toString(), member2[TestConstants.API.ROLE])
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
        assertTrue(members.contains(new Team.TeamMember("user1@xyz.com")))
        assertTrue(members.contains(new Team.TeamMember("user2@xyz.com", Team.TeamMember.Role.admin)))

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
        assertTrue(members.contains(new Team.TeamMember("user1@xyz.com")))


        team = response.getTeams().find { it.id == team2Content[TestConstants.API.ID] }
        assertEquals(team2Content[TestConstants.API.NAME], team.name)
        assertEquals(team2Content[TestConstants.API.ID], team.id)

        members = team.getMembers()
        assertEquals(1, members.size());
        assertTrue(members.contains(new Team.TeamMember("user2@xyz.com", Team.TeamMember.Role.admin)))

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


}
