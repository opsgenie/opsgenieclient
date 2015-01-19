package com.ifountain.opsgenie.client.script.util


import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.model.BaseRequest
import com.ifountain.opsgenie.client.model.beans.Group
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import com.ifountain.opsgenie.client.model.group.*
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*
/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyGroupTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testAddGroup() throws Exception {
        _testAddGroup(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddGroup(true);

    }

    public void _testAddGroup(boolean useConfig) throws Exception {
        def params = [name: "group1", users: ["user1@xyz.com"]];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new AddGroupResponse()
        opsGenieClient.group().setAddGroupResponse(expectedResponse);
        Map response = proxy.addGroup(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddGroupRequest request = executedRequests[0] as AddGroupRequest;

        assertEquals("group1", request.getName())
        assertEquals(1, request.getUsers().size());
        assertEquals("user1@xyz.com", request.getUsers()[0]);
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddGroupReturningException() throws Exception {
        _testeReturningException("addGroup", [apiKey: "customer1"], new Exception("Group does not exist."))
    }


    @Test
    public void testAddGroupMember() throws Exception {
        _testAddGroupMember(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddGroupMember(true);

    }

    public void _testAddGroupMember(boolean useConfig) throws Exception {
        def params = [name: "group1", users: ["user1@xyz.com"]];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new AddGroupMemberResponse()
        opsGenieClient.group().setAddGroupMemberResponse(expectedResponse);
        Map response = proxy.addGroupMember(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddGroupMemberRequest request = executedRequests[0] as AddGroupMemberRequest;

        assertEquals("group1", request.getName())
        assertEquals(1, request.getUsers().size());
        assertEquals("user1@xyz.com", request.getUsers()[0]);
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddGroupMemberReturningException() throws Exception {
        _testeReturningException("addGroupMember", [apiKey: "customer1"], new Exception("Group does not exist."))
    }


    @Test
    public void testRemoveroupMember() throws Exception {
        _testRemoveGroupMember(false);
        opsGenieClient.getExecutedRequests().clear()
        _testRemoveGroupMember(true);

    }

    public void _testRemoveGroupMember(boolean useConfig) throws Exception {
        def params = [name: "group1", users: ["user1@xyz.com"]];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new DeleteGroupMemberResponse()
        opsGenieClient.group().setDeleteGroupMemberResponse(expectedResponse);
        Map response = proxy.deleteGroupMember(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteGroupMemberRequest request = executedRequests[0] as DeleteGroupMemberRequest;

        assertEquals("group1", request.getName())
        assertEquals(1, request.getUsers().size());
        assertEquals("user1@xyz.com", request.getUsers()[0]);
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testRemoveGroupMemberReturningException() throws Exception {
        _testeReturningException("deleteGroupMember", [apiKey: "customer1"], new Exception("Group does not exist."))
    }


    @Test
    public void testUpdateGroup() throws Exception {
        _testUpdateGroup(false);
        opsGenieClient.getExecutedRequests().clear()
        _testUpdateGroup(true);

    }

    public void _testUpdateGroup(boolean useConfig) throws Exception {
        def params = [id: "group1Id", name: "group1", users: ["user1@xyz.com"]];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new UpdateGroupResponse()
        opsGenieClient.group().setUpdateGroupResponse(expectedResponse);
        Map response = proxy.updateGroup(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UpdateGroupRequest request = executedRequests[0] as UpdateGroupRequest;

        assertEquals("group1Id", request.getId())
        assertEquals("group1", request.getName())
        assertEquals(1, request.getUsers().size());
        assertEquals("user1@xyz.com", request.getUsers()[0]);
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testUpdateGroupReturningException() throws Exception {
        _testeReturningException("updateGroup", [apiKey: "customer1"], new Exception("Group does not exist."))
    }

    @Test
    public void testDeleteGroup() throws Exception {
        _testDeleteGroup(false);
        opsGenieClient.getExecutedRequests().clear()
        _testDeleteGroup(true);

    }

    public void _testDeleteGroup(boolean useConfig) throws Exception {
        def params = [id: "group1Id", name: "group1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new DeleteGroupResponse()
        opsGenieClient.group().setDeleteGroupResponse(expectedResponse);
        Map response = proxy.deleteGroup(params)
        assertEquals(true, response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteGroupRequest request = executedRequests[0] as DeleteGroupRequest;

        assertEquals("group1Id", request.getId())
        assertEquals("group1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testDeleteGroupReturningException() throws Exception {
        _testeReturningException("deleteGroup", [apiKey: "customer1"], new Exception("Group does not exist."))
    }

    @Test
    public void testGetGroup() throws Exception {
        _testGetGroup(false);
        opsGenieClient.getExecutedRequests().clear()
        _testGetGroup(true);

    }

    public void _testGetGroup(boolean useConfig) throws Exception {
        def params = [id: "group1Id", name: "group1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new GetGroupResponse()
        expectedResponse.setGroup(new Group(id: "group1"))
        opsGenieClient.group().setGetGroupResponse(expectedResponse);
        Map response = proxy.getGroup(params)
        assertEquals("group1", response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetGroupRequest request = executedRequests[0] as GetGroupRequest;

        assertEquals("group1Id", request.getId())
        assertEquals("group1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testGetGroupReturningException() throws Exception {
        _testeReturningException("getGroup", [apiKey: "customer1"], new Exception("Group does not exist."))
    }

    @Test
    public void testListGroup() throws Exception {
        _testListGroup(false);
        opsGenieClient.getExecutedRequests().clear()
        _testListGroup(true);

    }

    public void _testListGroup(boolean useConfig) throws Exception {
        def params = [:];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new ListGroupsResponse()
        expectedResponse.setGroups([new Group(id: "group1")])
        opsGenieClient.group().setListGroupsResponse(expectedResponse);
        List<Map> response = proxy.listGroups(params)
        assertEquals(1, response.size())
        assertEquals("group1", response[0][TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListGroupsRequest request = executedRequests[0] as ListGroupsRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testListGroupReturningException() throws Exception {
        _testeReturningException("listGroups", [apiKey: "customer1"], new Exception("Group does not exist."))
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
}
