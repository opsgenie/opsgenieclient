package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.BaseUserObj
import com.ifountain.opsgenie.client.model.beans.Role
import com.ifountain.opsgenie.client.model.beans.Team
import com.ifountain.opsgenie.client.model.beans.Team.TeamMember
import com.ifountain.opsgenie.client.model.team.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPatch
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
    void testAddTeamSuccessfully() throws Exception {
        String responseStr = getResponseString("AddTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        AddTeamRequest request = getAddTeamRequest()

        def response = opsgenieClient.team().addTeam(request)
        assertEquals("a30b5c45-6alp-422f-9d41-67b10a67282a", response.getTeamData().getId())
        assertEquals("TeamName",response.getTeamData().getName())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/teams", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])

        def members = jsonContent[TestConstants.API.MEMBERS]
        assertEquals(3, members.size())
        def member1 = members.find { it.role == "admin"}
        assertNotNull(member1)
        assertEquals("user@opsgenie.com", member1[TestConstants.API.USER][TestConstants.API.USERNAME])

        def member2 = members.find { it.role == "user"}
        assertNotNull(member2)
        assertEquals("user3@opsgenie.com", member2[TestConstants.API.USER][TestConstants.API.USERNAME])
        assertEquals("1f2alp91-bca3-4ae2-bdea-b02e94d10953", member2[TestConstants.API.USER][TestConstants.API.ID])
    }

    private AddTeamRequest getAddTeamRequest() {
        AddTeamRequest request = new AddTeamRequest()
        request.setApiKey("customer1")
        request.setName("TeamName")
        request.setDescription("Team Description")
        request.setMembers([new TeamMember(user: new BaseUserObj(username: "user@opsgenie.com"), role: Role.admin),
                            new TeamMember(user: new BaseUserObj(id: "00564944-b42f-4b95-a882-ee9a5alpb9bb")),
                            new TeamMember(user: new BaseUserObj(id: "1f2alp91-bca3-4ae2-bdea-b02e94d10953", username: "user3@opsgenie.com"), role: Role.user)
        ])
        request
    }

    @Test
    void testUpdateTeamSuccessfully() throws Exception {
        String responseStr = getResponseString("UpdateTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        UpdateTeamRequest request = new UpdateTeamRequest()
        request.setApiKey("customer1")
        request.setId("a30alp45-65bf-422f-9d41-67b10a67282a")
        request.setName("teamNameUpdated")

        def response = opsgenieClient.team().updateTeam(request)
        assertEquals("a30alp45-65bf-422f-9d41-67b10a67282a", response.getTeamData().getId())
        assertEquals("TeamName",response.getTeamData().getName())
        assertEquals(0, response.getTook())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPatch.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/teams/a30alp45-65bf-422f-9d41-67b10a67282a", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
    }

    @Test
    void testUpdateTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.team(), "updateTeam", new UpdateTeamRequest())
        String responseStr = getResponseString("UpdateTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        UpdateTeamRequest request = new UpdateTeamRequest()
        request.setApiKey("customer1")
        request.setId("a30alp45-65bf-422f-9d41-67b10a67282a")
        request.setMembers([new TeamMember(user: new BaseUserObj(username: "user@opsgenie.com"), role: Role.admin),
                            new TeamMember(user: new BaseUserObj(id: "00564944-b42f-4b95-a882-ee9a5alpb9bb")),
                            new TeamMember(user: new BaseUserObj(id: "1f2alp91-bca3-4ae2-bdea-b02e94d10953", username: "user3@opsgenie.com"), role: Role.user)
        ])
        TeamMember member = request.getMembers().get(2)
        member.getUser().setId(null)
        member.getUser().setUsername(null)
        try{
            def response = opsgenieClient.team().updateTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Either username or id of user should be present",exception.getMessage())
        }
        member.setUser(null)
        try{
            def response = opsgenieClient.team().updateTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("User should be present in member field",exception.getMessage())
        }
        request.setId(null)
        try{
            def response = opsgenieClient.team().updateTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [teamId]",exception.getMessage())
        }
    }

    @Test
    void testDeleteTeamSuccessfully() throws Exception {
        String responseStr = getResponseString("DeleteTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        DeleteTeamRequest request = new DeleteTeamRequest()
        request.setApiKey("customer1")
        request.setIdentifier("team1Id")

        def response = opsgenieClient.team().deleteTeam(request)
        assertEquals(0, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/teams/team1Id", requestSent.getUrl())
    }

    @Test
    void testDeleteTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.team(), "deleteTeam", new DeleteTeamRequest())
        String responseStr = getResponseString("DeleteTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        DeleteTeamRequest request = new DeleteTeamRequest()
        request.setApiKey("customer1")
        try {
            def response = opsgenieClient.team().deleteTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]",exception.getMessage())
        }
        request.setIdentifierType("test")
        try {
            def response = opsgenieClient.team().deleteTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for identifierType",exception.getMessage())
        }
    }

    @Test
    void testGetTeamSuccessfully() throws Exception {
        String responseStr = getResponseString("GetTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        GetTeamRequest request = new GetTeamRequest()
        request.setIdentifier("TeamName")
        request.setIdentifierType("name")
        request.setApiKey("customer1")

        def response = opsgenieClient.team().getTeam(request)
        assertEquals(0, response.getTook())
        assertEquals("TeamName", response.getTeam().name)
        assertEquals("a30alp45-65bf-422f-9d41-67b10a67282a", response.getTeam().id)

        def members = response.getTeam().getMembers()
        assertEquals(3, members.size())
        assertEquals("a9514028-2bca-4510-a51f-4b65f2c33alp",members.get(0).getUser().getId())
        assertEquals("user@opsgenie.com",members.get(0).getUser().getUsername())
        assertEquals("admin",members.get(0).getRole())

        assertEquals("00564944-b42f-4b95-a882-ee9a5aalp9bb",members.get(1).getUser().getId())
        assertEquals("user2@opsgenie.com",members.get(1).getUser().getUsername())
        assertEquals("user",members.get(1).getRole())

        assertEquals("1f281991-bca3-4ae2-bdea-b02e94dalp53",members.get(2).getUser().getId())
        assertEquals("user3@opsgenie.com",members.get(2).getUser().getUsername())
        assertEquals("user",members.get(2).getRole())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals(request.getIdentifierType(), requestSent.getParameters()[TestConstants.API.IDENTIFIER_TYPE])
        assertEquals("/v2/teams/TeamName", requestSent.getUrl())
    }

    @Test
    void testGetTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.team(), "getTeam", new GetTeamRequest(identifier: "team1"))
        String responseStr = getResponseString("GetTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        GetTeamRequest request = new GetTeamRequest()
        request.setApiKey("customer1")
        try {
            def response = opsgenieClient.team().getTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]",exception.getMessage())
        }
        request.setIdentifierType("test")
        try {
            def response = opsgenieClient.team().getTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for identifierType",exception.getMessage())
        }
    }


    @Test
    void testListTeamsSuccessfully() throws Exception {
        String responseStr = getResponseString("ListTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        ListTeamsRequest request = new ListTeamsRequest()
        request.setApiKey("customer1")

        def response = opsgenieClient.team().listTeams(request)
        assertEquals(1,response.getTook())
        assertEquals(3, response.getTeams().size())

        Team team = response.getTeams().find { it.id == "90098alp9-f0e3-41d3-a060-0ea895027630" }
        assertEquals("ops_team", team.name)
        assertEquals("", team.description)

        team = response.getTeams().find { it.id == "a30alp45-65bf-422f-9d41-67b10a67282a" }
        assertEquals("TeamName2", team.name)
        assertEquals("Description", team.description)

        team = response.getTeams().find { it.id == "c569c016-alp9-4e20-8a28-bd5dc33b798e" }
        assertEquals("TeamName", team.name)
        assertEquals("", team.description)

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/teams", requestSent.getUrl())
    }

    @Test
    void testListTeamsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.team(), "listTeams", new ListTeamsRequest())
    }

    @Test
    void testListTeamLogsSuccessfully() throws Exception {
        String responseStr = getResponseString("ListTeamLogsResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        ListTeamLogsRequest request = getListLogsRequest()

        def response = opsgenieClient.team().listTeamLogs(request)

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/teams/teamName1/logs", requestSent.getUrl())

        assertEquals(request.getIdentifierType(), requestSent.getParameters()[TestConstants.API.IDENTIFIER_TYPE])
        assertEquals(request.getOffset(), requestSent.getParameters()[TestConstants.API.OFFSET])
        assertEquals(request.getSortOrder().name(), requestSent.getParameters()[TestConstants.API.ORDER])
        assertEquals(20, requestSent.getParameters()[TestConstants.API.LIMIT].toInteger())

        assertEquals("1495523940765000268", response.getTeamLogsData().getOffset())
        assertEquals(2, response.getTeamLogsData().getTeamLogs().size())

        def log = response.getTeamLogsData().getTeamLogs().find { it.log == "Updated members." }
        assertEquals("System", log.owner)
        assertEquals("2017-05-23T07:45:51.56Z", log.createdDate)

        log = response.getTeamLogsData().getTeamLogs().find { it.log == "Created team" }
        assertEquals("System", log.owner)
        assertEquals("2017-05-23T07:19:00.765Z", log.createdDate)
    }

    private ListTeamLogsRequest getListLogsRequest() {
        ListTeamLogsRequest request = new ListTeamLogsRequest();
        request.setIdentifierType("name")
        request.setIdentifier("teamName1")
        request.setApiKey("customer1")
        request.setOffset("lastKey")
        request.setSortOrder(ListTeamLogsRequest.SortOrder.asc)
        request.setLimit(10)
        request
    }

    @Test
    void testListTeamLogsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.team(), "listTeamLogs", new ListTeamLogsRequest())
        String responseStr = getResponseString("ListTeamLogsResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        ListTeamLogsRequest request = getListLogsRequest()
        request.setApiKey("customer1")
        request.setIdentifier(null)
        try {
            def response = opsgenieClient.team().listTeamLogs(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]",exception.getMessage())
        }
        request.setIdentifierType("test")
        try {
            def response = opsgenieClient.team().listTeamLogs(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for identifierType",exception.getMessage())
        }
    }
    @Test
    void testAddTeamThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.team(), "addTeam", new AddTeamRequest())
        String responseStr = getResponseString("AddTeamResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        AddTeamRequest request = getAddTeamRequest()
        TeamMember member = request.getMembers().get(2)
        member.getUser().setId(null)
        member.getUser().setUsername(null)
        try{
            def response = opsgenieClient.team().addTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Either username or id of user should be present",exception.getMessage())
        }
        member.setUser(null)
        try{
            def response = opsgenieClient.team().addTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("User should be present in member field",exception.getMessage())
        }
        request.setName("")
        try{
            def response = opsgenieClient.team().addTeam(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name]",exception.getMessage())
        }
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
