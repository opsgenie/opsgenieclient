package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost

import com.ifountain.opsgenie.client.model.group.AddGroupRequest
import com.ifountain.opsgenie.client.model.group.UpdateGroupRequest
import com.ifountain.opsgenie.client.model.group.DeleteGroupRequest
import com.ifountain.opsgenie.client.model.group.GetGroupRequest
import com.ifountain.opsgenie.client.model.beans.Group
import com.ifountain.opsgenie.client.model.group.ListGroupsRequest
import com.ifountain.opsgenie.client.model.group.AddGroupMemberRequest
import com.ifountain.opsgenie.client.model.group.DeleteGroupMemberRequest
import org.apache.commons.lang.StringUtils
import org.junit.Test
import static org.junit.Assert.*
/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 11:09 AM
 */
class GroupOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Test
    public void testAddGroupSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"group1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddGroupRequest request = new AddGroupRequest();
        request.setApiKey("customer1");
        request.setName("group1");
        request.setUsers(["user1@xyz.com", "user2@xyz.com"]);

        def response = OpsGenieClientTestCase.opsgenieClient.group().addGroup(request)
        assertEquals("group1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/group", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getUsers(), jsonContent[TestConstants.API.USERS])
    }


    @Test
    public void testAddGroupThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.group(), "addGroup", new AddGroupRequest())
    }


    @Test
    public void testAddGroupMemberSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"group1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddGroupMemberRequest request = new AddGroupMemberRequest();
        request.setApiKey("customer1");
        request.setName("group1");
        request.setUsers(["user1@xyz.com", "user2@xyz.com"]);

        def response = OpsGenieClientTestCase.opsgenieClient.group().addGroupMember(request)
        assertEquals("group1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/group/member", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(StringUtils.join(request.getUsers(), ","), jsonContent[TestConstants.API.USERS])
    }


    @Test
    public void testAddGroupMemberThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.group(), "addGroupMember", new AddGroupMemberRequest())
    }

    @Test
    public void testDeleteGroupMemberSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"group1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteGroupMemberRequest request = new DeleteGroupMemberRequest();
        request.setApiKey("customer1");
        request.setName("group1");
        request.setUsers(["user1@xyz.com", "user2@xyz.com"]);

        def response = OpsGenieClientTestCase.opsgenieClient.group().deleteGroupMember(request)
        assertEquals("group1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/group/member", requestSent.getUrl())

        def parameters = requestSent.getParameters()
        assertEquals(request.getApiKey(), parameters[TestConstants.API.API_KEY])
        assertEquals(request.getName(), parameters[TestConstants.API.NAME])
        assertEquals(StringUtils.join(request.getUsers(), ","), parameters[TestConstants.API.USERS])
    }


    @Test
    public void testDeleteGroupMemberThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.group(), "deleteGroupMember", new DeleteGroupMemberRequest())
    }

    @Test
    public void testUpdateGroupSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"group1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateGroupRequest request = new UpdateGroupRequest();
        request.setApiKey("customer1");
        request.setId("group1Id");
        request.setName("user1@xyz.com");
        request.setUsers(["user1@xyz.com"]);

        def response = OpsGenieClientTestCase.opsgenieClient.group().updateGroup(request)
        assertEquals("group1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/group", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getUsers(), jsonContent[TestConstants.API.USERS])
    }

    @Test
    public void testUpdateGroupWithPartialUpdate() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"group1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateGroupRequest request = new UpdateGroupRequest();
        request.setApiKey("customer1");
        request.setId("group1Id");

        def response = OpsGenieClientTestCase.opsgenieClient.group().updateGroup(request)
        assertEquals("group1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(2, jsonContent.size())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])

        //test update name only
        request = new UpdateGroupRequest();
        request.setApiKey("customer1");
        request.setId("group1Id");
        request.setName("group1Updated");

        response = OpsGenieClientTestCase.opsgenieClient.group().updateGroup(request)
        assertEquals("group1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]

        jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(3, jsonContent.size())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])

        //test update users only
        request = new UpdateGroupRequest();
        request.setApiKey("customer1");
        request.setId("group1Id");
        request.setUsers(["user1@xyz.com"]);

        response = OpsGenieClientTestCase.opsgenieClient.group().updateGroup(request)
        assertEquals("group1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(3, receivedRequests.size());
        requestSent = receivedRequests[2]

        jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(3, jsonContent.size())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUsers(), jsonContent[TestConstants.API.USERS])
    }

    @Test
    public void testUpdateGroupThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.group(), "updateGroup", new UpdateGroupRequest())
    }

    @Test
    public void testDeleteGroupSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteGroupRequest request = new DeleteGroupRequest();
        request.setApiKey("customer1");
        request.setId("group1Id");
        request.setName("group1");

        def response = OpsGenieClientTestCase.opsgenieClient.group().deleteGroup(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/group", requestSent.getUrl())

        assertEquals(3, requestSent.getParameters().size())
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
    }


    @Test
    public void testDeleteUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.group(), "deleteGroup", new DeleteGroupRequest())
    }

    @Test
    public void testGetGroupSuccessfully() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "group1id");
        jsonContent.put(TestConstants.API.NAME, "group1");
        jsonContent.put(TestConstants.API.USERS, ["user1@xyz.com", "user2@xyz.com"]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetGroupRequest request = new GetGroupRequest();
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setName(jsonContent[TestConstants.API.NAME]);
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.group().getGroup(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.NAME], response.getGroup().name)
        assertEquals(jsonContent[TestConstants.API.ID], response.getGroup().id)
        assertEquals(jsonContent[TestConstants.API.USERS], response.getGroup().users)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/group", requestSent.getUrl())
    }

    @Test
    public void testGetUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.group(), "getGroup", new GetGroupRequest(id: "group1"))
    }


    @Test
    public void testListGroupsSuccessfully() throws Exception {
        Map group1Content = new HashMap();
        group1Content.put(TestConstants.API.ID, "group1id");
        group1Content.put(TestConstants.API.NAME, "group1");
        group1Content.put(TestConstants.API.USERS, ["user1@xyz.com"]);
        Map group2Content = new HashMap();
        group2Content.put(TestConstants.API.ID, "group2id");
        group1Content.put(TestConstants.API.NAME, "group2");
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(groups: [group1Content, group2Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListGroupsRequest request = new ListGroupsRequest();
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.group().listGroups(request)
        assertEquals(2, response.groups.size())
        Group group = response.getGroups().find { it.id == group1Content[TestConstants.API.ID] }
        assertEquals(group1Content[TestConstants.API.NAME], group.name)
        assertEquals(group1Content[TestConstants.API.ID], group.id)
        assertEquals(group1Content[TestConstants.API.USERS], group.users)

        group = response.getGroups().find { it.id == group2Content[TestConstants.API.ID] }
        assertEquals(group2Content[TestConstants.API.NAME], group.name)
        assertEquals(group2Content[TestConstants.API.ID], group.id)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/group", requestSent.getUrl())
    }

    @Test
    public void testListGroupsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.group(), "listGroups", new ListGroupsRequest())
    }


}
