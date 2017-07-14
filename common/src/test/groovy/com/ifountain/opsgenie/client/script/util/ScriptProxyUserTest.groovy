package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.model.BaseRequest
import com.ifountain.opsgenie.client.model.beans.Forwarding
import com.ifountain.opsgenie.client.model.beans.User
import com.ifountain.opsgenie.client.model.customer.CopyNotificationRulesRequest
import com.ifountain.opsgenie.client.model.customer.CopyNotificationRulesResponse
import com.ifountain.opsgenie.client.model.user.*
import com.ifountain.opsgenie.client.model.user.forward.*
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import org.junit.Before
import org.junit.Test

import java.text.SimpleDateFormat

import static org.junit.Assert.assertEquals
import static org.junit.Assert.fail

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyUserTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }


    @Test
    public void testAddUser() throws Exception {
        _testAddUser(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddUser(true);

    }

    public void _testAddUser(boolean useConfig) throws Exception {
        def params = [username: "user1@xyz.com", fullname: "user1", role: "user", timezone: "Etc/GMT+7",
                      locale: Locale.CHINA.toString()];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new AddUserResponse()
        opsGenieClient.user().setAddUserResponse(expectedResponse);
        Map response = proxy.addUser(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddUserRequest request = executedRequests[0] as AddUserRequest;

        assertEquals("user1@xyz.com", request.getUsername())
        assertEquals("user1", request.getFullname())
        assertEquals("user", request.getRole().name())
        assertEquals(TimeZone.getTimeZone("Etc/GMT+7").getID(), request.getTimeZone().getID())
        assertEquals(Locale.CHINA, request.getLocale())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddUserReturningException() throws Exception {
        _testeReturningException("addUser", [apiKey: "customer1"], new Exception("User does not exist."))
    }

    @Test
    public void testUpdateUser() throws Exception {
        _testUpdateUser(false);
        opsGenieClient.getExecutedRequests().clear()
        _testUpdateUser(true);

    }

    public void _testUpdateUser(boolean useConfig) throws Exception {
        def params = [id: "user1Id", username: "user1@xyz.com", fullname: "user1", role: "user", timezone: "Etc/GMT+7", locale: Locale.CHINA.toString()];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new UpdateUserResponse()
        opsGenieClient.user().setUpdateUserResponse(expectedResponse);
        Map response = proxy.updateUser(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UpdateUserRequest request = executedRequests[0] as UpdateUserRequest;

        assertEquals("user1Id", request.getId())
        assertEquals("user1", request.getFullname())
        assertEquals("user", request.getRole().name())
        assertEquals(Locale.CHINA, request.getLocale())
        assertEquals(TimeZone.getTimeZone("Etc/GMT+7").getID(), request.getTimeZone().getID())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testUpdateUserReturningException() throws Exception {
        _testeReturningException("updateUser", [apiKey: "customer1"], new Exception("User does not exist."))
    }

    @Test
    public void testDeleteUser() throws Exception {
        _testDeleteUser(false);
        opsGenieClient.getExecutedRequests().clear()
        _testDeleteUser(true);

    }

    public void _testDeleteUser(boolean useConfig) throws Exception {
        def params = [id: "user1Id", username: "user1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new DeleteUserResponse()
        opsGenieClient.user().setDeleteUserResponse(expectedResponse);
        Map response = proxy.deleteUser(params)
        assertEquals(true, response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteUserRequest request = executedRequests[0] as DeleteUserRequest;

        assertEquals("user1Id", request.getId())
        assertEquals("user1", request.getUsername())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testDeleteUserReturningException() throws Exception {
        _testeReturningException("deleteUser", [apiKey: "customer1"], new Exception("User does not exist."))
    }

    @Test
    public void testGetUser() throws Exception {
        _testGetUser(false);
        opsGenieClient.getExecutedRequests().clear()
        _testGetUser(true);
    }

    public void _testGetUser(boolean useConfig) throws Exception {
        def params = [id: "user1Id", username: "user1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new GetUserResponse()
        expectedResponse.setUser(new User(id: "user1"))
        opsGenieClient.user().setGetUserResponse(expectedResponse);
        Map response = proxy.getUser(params)
        assertEquals("user1", response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetUserRequest request = executedRequests[0] as GetUserRequest;

        assertEquals("user1Id", request.getId())
        assertEquals("user1", request.getUsername())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testGetUserReturningException() throws Exception {
        _testeReturningException("getUser", [apiKey: "customer1"], new Exception("User does not exist."))
    }

    @Test
    public void testListUser() throws Exception {
        _testListUser(false);
        opsGenieClient.getExecutedRequests().clear()
        _testListUser(true);

    }

    public void _testListUser(boolean useConfig) throws Exception {
        def params = [:];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new ListUsersResponse()
        expectedResponse.setUsers([new User(id: "user1")])
        opsGenieClient.user().setListUsersResponse(expectedResponse);
        List<Map> response = proxy.listUsers(params)
        assertEquals(1, response.size())
        assertEquals("user1", response[0][TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListUsersRequest request = executedRequests[0] as ListUsersRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testListUserReturningException() throws Exception {
        _testeReturningException("listUsers", [apiKey: "customer1"], new Exception("User does not exist."))
    }

    @Test
    public void testAddForwarding() throws Exception {
        _testAddForwarding(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddForwarding(true);

    }

    public void _testAddForwarding(boolean useConfig) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"))
        Date dateobject = new Date();
        String dateString = sdf.format(dateobject);
        Date dateobjectWithoutSeconds = sdf.parse(dateString);

        def params = [alias: "forwarding1", fromUser: "user1@xyz.com", toUser: "user2@xyz.com", startDate: "2013-01-22 22:00", endDate: dateString, timezone: "Etc/GMT+7"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new AddForwardingResponse()
        opsGenieClient.user().setAddForwardingResponse(expectedResponse);
        Map response = proxy.addForwarding(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddForwardingRequest request = executedRequests[0] as AddForwardingRequest;
        assertEquals("user1@xyz.com", request.getFromUser())
        assertEquals("user2@xyz.com", request.getToUser())
        assertEquals("forwarding1", request.getAlias())
        assertEquals(sdf.parse(params.startDate), request.getStartDate())
        assertEquals(dateobjectWithoutSeconds, request.getEndDate())
        assertEquals(TimeZone.getTimeZone(params.timezone).getID(), request.getTimeZone().getID())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddForwardingReturningException() throws Exception {
        _testeReturningException("addForwarding", [apiKey: "customer1"], new Exception("Forwarding does not exist."))
    }

    @Test
    public void testUpdateForwarding() throws Exception {
        _testUpdateForwarding(false);
        opsGenieClient.getExecutedRequests().clear()
        _testUpdateForwarding(true);

    }

    public void _testUpdateForwarding(boolean useConfig) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"))
        Date dateobject = new Date();
        String dateString = sdf.format(dateobject);
        Date dateobjectWithoutSeconds = sdf.parse(dateString);

        def params = [id: "forwarding1Id", alias: "forwarding1", fromUser: "user1@xyz.com", toUser: "user2@xyz.com", startDate: dateString, endDate: "2013-01-22 22:00", timezone: TimeZone.getTimeZone("Etc/GMT+7")];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new UpdateForwardingResponse()
        opsGenieClient.user().setUpdateForwardingResponse(expectedResponse);
        Map response = proxy.updateForwarding(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UpdateForwardingRequest request = executedRequests[0] as UpdateForwardingRequest;

        assertEquals("forwarding1Id", request.getId())
        assertEquals("user1@xyz.com", request.getFromUser())
        assertEquals("user2@xyz.com", request.getToUser())
        assertEquals("forwarding1", request.getAlias())
        assertEquals(dateobjectWithoutSeconds, request.getStartDate())
        assertEquals(sdf.parse(params.endDate), request.getEndDate())
        assertEquals(params.timezone.getID(), request.getTimeZone().getID())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testUpdateForwardingReturningException() throws Exception {
        _testeReturningException("updateForwarding", [apiKey: "customer1"], new Exception("Forwarding does not exist."))
    }

    @Test
    public void testDeleteForwarding() throws Exception {
        _testDeleteForwarding(false);
        opsGenieClient.getExecutedRequests().clear()
        _testDeleteForwarding(true);

    }

    public void _testDeleteForwarding(boolean useConfig) throws Exception {
        def params = [id: "forwarding1Id", forwardingname: "forwarding1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new DeleteForwardingResponse()
        opsGenieClient.user().setDeleteForwardingResponse(expectedResponse);
        Map response = proxy.deleteForwarding(params)
        assertEquals(true, response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteForwardingRequest request = executedRequests[0] as DeleteForwardingRequest;

        assertEquals("forwarding1Id", request.getId())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testDeleteForwardingReturningException() throws Exception {
        _testeReturningException("deleteForwarding", [apiKey: "customer1"], new Exception("Forwarding does not exist."))
    }

    @Test
    public void testGetForwarding() throws Exception {
        _testGetForwarding(false);
        opsGenieClient.getExecutedRequests().clear()
        _testGetForwarding(true);
    }

    public void _testGetForwarding(boolean useConfig) throws Exception {
        def params = [id: "forwarding1Id", alias: "forwarding1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new GetForwardingResponse()
        expectedResponse.setForwarding(new Forwarding(id: "forwarding1"))
        opsGenieClient.user().setGetForwardingResponse(expectedResponse);
        Map response = proxy.getForwarding(params)
        assertEquals("forwarding1", response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetForwardingRequest request = executedRequests[0] as GetForwardingRequest;

        assertEquals("forwarding1Id", request.getId())
        assertEquals("forwarding1", request.getAlias())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testGetForwardingReturningException() throws Exception {
        _testeReturningException("getForwarding", [apiKey: "customer1"], new Exception("Forwarding does not exist."))
    }

    @Test
    public void testListForwarding() throws Exception {
        _testListForwarding(false);
        opsGenieClient.getExecutedRequests().clear()
        _testListForwarding(true);

    }

    public void _testListForwarding(boolean useConfig) throws Exception {
        def params = [:];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new ListForwardingsResponse()
        expectedResponse.setForwardings([new Forwarding(id: "forwarding1")])
        opsGenieClient.user().setListForwardingsResponse(expectedResponse);
        List<Map> response = proxy.listForwardings(params)
        assertEquals(1, response.size())
        assertEquals("forwarding1", response[0][TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListForwardingsRequest request = executedRequests[0] as ListForwardingsRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testListForwardingReturningException() throws Exception {
        _testeReturningException("listForwardings", [apiKey: "customer1"], new Exception("Forwarding does not exist."))
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
    public void testCopyNotificationRules() throws Exception {
        _testCopyNotificationRules(false);
        opsGenieClient.getExecutedRequests().clear()
        _testCopyNotificationRules(true);

    }

    public void _testCopyNotificationRules(boolean useConfig) throws Exception {
        def params = [fromUser: "user1", toUsers: ["user2", "user3"], ruleTypes: ["New Alert", "Alert Closed"]];

        if (!useConfig) {
            params.apiKey = "customer1";
        }

        CopyNotificationRulesResponse expectedResponse = new CopyNotificationRulesResponse();
        expectedResponse.setJson("{\"status\" : \"process started\", \"code\" : 200}");

        opsGenieClient.setCopyNotificationRulesResponse(expectedResponse);
        Map response = proxy.copyNotificationRules(params);

        assertEquals("process started", response.status);
        assertEquals(200, response.code);

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size());
        CopyNotificationRulesRequest request = executedRequests[0] as CopyNotificationRulesRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey());
        } else {
            assertEquals("customer1", request.getApiKey());
        }

        assertEquals("user1", request.getFromUser());
        assertEquals(["user2", "user3"], request.getToUsers());
        assertEquals(["New Alert", "Alert Closed"], request.getRuleTypes());
    }
}
