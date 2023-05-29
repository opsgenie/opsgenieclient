package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.BaseUserObj
import com.ifountain.opsgenie.client.model.beans.User
import com.ifountain.opsgenie.client.model.user.*
import com.ifountain.opsgenie.client.model.user.forward.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.json.JSONObject
import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse

/**
 * Created by Manisha Singla
 * Date: 5/29/23
 * Time: 2:13 PM
 */
class UserOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    void testAddForwardingSuccessfully() throws Exception {
        String responseStr = getResponseString("AddForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        AddForwardingRequest request = getAddForwardingRequest()

        def response = opsgenieClient.user().addForwarding(request)
        assertEquals("6667cb5b-a5ce-4550-9469-1dbcabac6ffb", response.getData().getId())
        assertEquals("ruleAlias", response.getData().getAlias())
        assertEquals(9, response.getTook())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/forwarding-rules", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getAlias(), jsonContent[TestConstants.API.ALIAS])
        assertEquals(request.getStartDate(), jsonContent[TestConstants.API.START_DATE])
        assertEquals(request.getEndDate(), jsonContent[TestConstants.API.END_DATE])
        assertEquals(request.getFromUser().getId(), jsonContent[TestConstants.API.FROM_USER][TestConstants.API.ID])
        assertEquals(request.getToUser().getId(), jsonContent[TestConstants.API.TO_USER][TestConstants.API.ID])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
    }

    @Test
    void testAddForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "addForwarding", new AddForwardingRequest())
        String responseStr = getResponseString("AddForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        AddForwardingRequest request = getAddForwardingRequest()
        request.setEndDate("2017-07-06T18:00:00")
        try{
            def response = opsgenieClient.user().addForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for endDate",exception.getMessage())
        }
        request.setStartDate("2017-07-06T18:00:00")
        try{
            def response = opsgenieClient.user().addForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for startDate",exception.getMessage())
        }
        request.setStartDate("")
        try{
            def response = opsgenieClient.user().addForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [startDate or endDate]",exception.getMessage())
        }
        request.getToUser().setId("")
        try{
            def response = opsgenieClient.user().addForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Either username or id in to user is mandatory",exception.getMessage())
        }
        request.setToUser(null)
        try{
            def response = opsgenieClient.user().addForwarding(request)
        } catch (OpsGenieClientValidationException exception) {
            assertEquals("Missing mandatory property [toUser]", exception.getMessage())
        }
        request.getFromUser().setId("")
        try{
            def response = opsgenieClient.user().addForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Either username or id in from user is mandatory",exception.getMessage())
        }
        request.setFromUser(null)
        try{
            def response = opsgenieClient.user().addForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [fromUser]",exception.getMessage())
        }
    }

    @Test
    void testUpdateForwardingSuccessfully() throws Exception {
        String responseStr = getResponseString("UpdateForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        UpdateForwardingRequest request = getUpdateForwardingRequest()

        def response = opsgenieClient.user().updateForwarding(request)
        assertEquals("ruleAlias", response.getData().getAlias())
        assertEquals(3, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPut.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/forwarding-rules/ruleAlias", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getStartDate(), jsonContent[TestConstants.API.START_DATE])
        assertEquals(request.getEndDate(), jsonContent[TestConstants.API.END_DATE])
        assertEquals(request.getFromUser().getUsername(), jsonContent[TestConstants.API.FROM_USER][TestConstants.API.USERNAME])
        assertEquals(request.getToUser().getUsername(), jsonContent[TestConstants.API.TO_USER][TestConstants.API.USERNAME])
    }

    @Test
    void testUpdateForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "updateForwarding", new UpdateForwardingRequest())
        String responseStr = getResponseString("UpdateForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        UpdateForwardingRequest request = getUpdateForwardingRequest()
        request.setEndDate("2017-07-06T18:00:00")
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for endDate",exception.getMessage())
        }
        request.setStartDate("2017-07-06T18:00:00")
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for startDate",exception.getMessage())
        }
        request.setStartDate("")
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [startDate or endDate]",exception.getMessage())
        }
        request.getToUser().setUsername("")
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Either username or id in to user is mandatory",exception.getMessage())
        }
        request.setToUser(null)
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception) {
            assertEquals("Missing mandatory property [toUser]", exception.getMessage())
        }
        request.getFromUser().setUsername("")
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Either username or id in from user is mandatory",exception.getMessage())
        }
        request.setFromUser(null)
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [fromUser]",exception.getMessage())
        }
        request.setIdentifier(null)
        try{
            def response = opsgenieClient.user().updateForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [alias or id]",exception.getMessage())
        }
    }

    @Test
    void testDeleteForwardingSuccessfully() throws Exception {
        String responseStr = getResponseString("DeleteForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        DeleteForwardingRequest request = new DeleteForwardingRequest()
        request.setApiKey("customer1")
        request.setIdentifier("ruleAlias")
        request.setIdentifierType("alias")

        def response = opsgenieClient.user().deleteForwarding(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]

        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/forwarding-rules/ruleAlias", requestSent.getUrl())

        //test with alias
        request = new DeleteForwardingRequest();
        request.setApiKey("customer1")
        request.setIdentifier("6667cb5b-a5ce-4550-9469-1dbcabac6ffb")

        response = opsgenieClient.user().deleteForwarding(request)
        assertEquals(1, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]

        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/forwarding-rules/6667cb5b-a5ce-4550-9469-1dbcabac6ffb", requestSent.getUrl())
    }

    @Test
    void testDeleteForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "deleteForwarding", new DeleteForwardingRequest())
        String responseStr = getResponseString("DeleteForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        DeleteForwardingRequest request = new DeleteForwardingRequest()
        request.setApiKey("customer1")
        try{
            def response = opsgenieClient.user().deleteForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [alias or id]",exception.getMessage())
        }
    }

    @Test
    void testGetForwardingSuccessfully() throws Exception {
        String responseStr = getResponseString("GetForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        GetForwardingRequest request = new GetForwardingRequest()
        request.setApiKey("customer1")
        request.setIdentifier("ruleAlias")
        request.setIdentifierType("alias")

        def response = opsgenieClient.user().getForwarding(request)
        assertEquals(0, response.getTook())
        assertEquals("ruleAlias", response.getForwarding().alias)
        assertEquals("6667cb5b-a5ce-4550-9469-1dbcabac6ffb", response.getForwarding().id)
        assertEquals("4b26961a-a837-49d2-a1fe-0973013e3c3b", response.getForwarding().toUser.getId())
        assertEquals("e58d6ee3-37bd-432f-9ded-64808b761ae0", response.getForwarding().fromUser.getId())
        assertEquals("user@opsgenie.com", response.getForwarding().toUser.getUsername())
        assertEquals("user@opsgenie.com", response.getForwarding().fromUser.getUsername())
        assertEquals("2017-07-05T08:00:00Z",response.getForwarding().getStartDate())
        assertEquals("2017-07-06T18:00:00Z",response.getForwarding().getEndDate())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/forwarding-rules/ruleAlias", requestSent.getUrl())

        //test with id as identifier type
        request = new GetForwardingRequest()
        request.setApiKey("customer1")
        request.setIdentifier("6667cb5b-a5ce-4550-9469-1dbcabac6ffb")

        response = opsgenieClient.user().getForwarding(request)
        assertEquals(0, response.getTook())
        assertEquals("ruleAlias", response.getForwarding().alias)
        assertEquals("6667cb5b-a5ce-4550-9469-1dbcabac6ffb", response.getForwarding().id)
        assertEquals("4b26961a-a837-49d2-a1fe-0973013e3c3b", response.getForwarding().toUser.getId())
        assertEquals("e58d6ee3-37bd-432f-9ded-64808b761ae0", response.getForwarding().fromUser.getId())
        assertEquals("user@opsgenie.com", response.getForwarding().toUser.getUsername())
        assertEquals("user@opsgenie.com", response.getForwarding().fromUser.getUsername())
        assertEquals("2017-07-05T08:00:00Z",response.getForwarding().getStartDate())
        assertEquals("2017-07-06T18:00:00Z",response.getForwarding().getEndDate())

        assertEquals(2, receivedRequests.size())
        requestSent = receivedRequests[1]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/forwarding-rules/6667cb5b-a5ce-4550-9469-1dbcabac6ffb", requestSent.getUrl())

    }

    @Test
    void testGetForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "getForwarding", new GetForwardingRequest())
        String responseStr = getResponseString("GetForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        GetForwardingRequest request = new GetForwardingRequest()
        request.setApiKey("customer1")
        try{
            def response = opsgenieClient.user().getForwarding(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [alias or id]",exception.getMessage())
        }
    }

    @Test
    void testListForwardingsSuccessfully() throws Exception {
        String responseStr = getResponseString("ListForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        ListForwardingsRequest request = new ListForwardingsRequest()
        request.setApiKey("customer1")

        def response = opsgenieClient.user().listForwardings(request)
        assertEquals(0, response.getTook())
        def forwardings = response.getForwardings()
        assertEquals(2, forwardings.size())
        def forwarding = forwardings.find { it.id == "5c4694d5-37a4-4ada-99cc-0652296e9397" }
        assertEquals("", forwarding.alias)
        assertEquals("5c4694d5-37a4-4ada-99cc-0652296e9397", forwarding.id)
        assertEquals("4b26961a-a837-49d2-a1fe-0973013e3c3b", forwarding.toUser.getId())
        assertEquals("e58d6ee3-37bd-432f-9ded-64808b761ae0", forwarding.fromUser.getId())
        assertEquals("user2@opsgenie.com", forwarding.toUser.getUsername())
        assertEquals("user@opsgenie.com", forwarding.fromUser.getUsername())
        assertEquals("2017-05-08T21:00:00Z",forwarding.getStartDate())
        assertEquals("2017-05-09T21:00:00Z",forwarding.getEndDate())

        forwarding = forwardings.find { it.id == "e2a4ea3a-7abb-4bb9-8a05-00de82de544d" }
        assertEquals("", forwarding.alias)
        assertEquals("e2a4ea3a-7abb-4bb9-8a05-00de82de544d", forwarding.id)
        assertEquals("4b26961a-a837-49d2-a1fe-0973013e3c3b", forwarding.toUser.getId())
        assertEquals("e58d6ee3-37bd-432f-9ded-64808b761ae0", forwarding.fromUser.getId())
        assertEquals("user2@opsgenie.com", forwarding.toUser.getUsername())
        assertEquals("user@opsgenie.com", forwarding.fromUser.getUsername())
        assertEquals("2017-05-22T21:00:00Z",forwarding.getStartDate())
        assertEquals("2017-05-24T21:00:00Z",forwarding.getEndDate())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/forwarding-rules", requestSent.getUrl())
    }

    @Test
    void testListForwardingsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "listForwardings", new ListForwardingsRequest())
    }

    @Test
    public void testAddUserSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"user1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddUserRequest request = new AddUserRequest();
        request.setApiKey("customer1");
        request.setUsername("user1@xyz.com");
        request.setRole(User.Role.user);
        request.setFullname("user1 user1");
        request.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));
        request.setLocale(Locale.CHINA);

        def response = OpsGenieClientTestCase.opsgenieClient.user().addUser(request)
        assertEquals("user1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getFullname(), jsonContent[TestConstants.API.FULLNAME])
        assertEquals(request.getUserRole().getName(), jsonContent[TestConstants.API.ROLE])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertEquals(User.getLocaleId(request.getLocale()), jsonContent[TestConstants.API.LOCALE])
    }

    @Test
    public void testAddUserSuccessfullyWithoutTimezoneAndLocale() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"user1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddUserRequest request = new AddUserRequest();
        request.setApiKey("customer1");
        request.setUsername("user1@xyz.com");
        request.setRole(User.Role.user);
        request.setFullname("user1 user1");

        def response = OpsGenieClientTestCase.opsgenieClient.user().addUser(request)
        assertEquals("user1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getFullname(), jsonContent[TestConstants.API.FULLNAME])
        assertEquals(request.getUserRole().getName(), jsonContent[TestConstants.API.ROLE])
        assertFalse(jsonContent.containsKey(TestConstants.API.TIMEZONE))
        assertFalse(jsonContent.containsKey(TestConstants.API.LOCALE))
    }

    @Test
    public void testAddUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "addUser", new AddUserRequest())
    }

    @Test
    public void testUpdateUserSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"user1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateUserRequest request = new UpdateUserRequest();
        request.setApiKey("customer1");
        request.setId("user1Id");
        request.setRole(User.Role.user);
        request.setFullname("user1 user1");
        request.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));
        request.setLocale(Locale.CHINA);

        def response = OpsGenieClientTestCase.opsgenieClient.user().updateUser(request)
        assertEquals("user1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getFullname(), jsonContent[TestConstants.API.FULLNAME])
        assertEquals(request.getUserRole().getName(), jsonContent[TestConstants.API.ROLE])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertEquals(User.getLocaleId(request.getLocale()), jsonContent[TestConstants.API.LOCALE])
    }

    @Test
    public void testUpdateUserWithNoUpdateProperty() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"user1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateUserRequest request = new UpdateUserRequest();
        request.setApiKey("customer1");
        request.setId("user1Id");

        def response = OpsGenieClientTestCase.opsgenieClient.user().updateUser(request)
        assertEquals("user1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertFalse(jsonContent.containsKey(TestConstants.API.USERNAME))
        assertFalse(jsonContent.containsKey(TestConstants.API.FULLNAME))
        assertFalse(jsonContent.containsKey(TestConstants.API.ROLE))
        assertFalse(jsonContent.containsKey(TestConstants.API.TIMEZONE))
        assertFalse(jsonContent.containsKey(TestConstants.API.LOCALE))
    }

    @Test
    public void testUpdateUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "updateUser", new UpdateUserRequest())
    }

    @Test
    public void testDeleteUserSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"user1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteUserRequest request = new DeleteUserRequest();
        request.setApiKey("customer1");
        request.setId("user1Id");

        def response = OpsGenieClientTestCase.opsgenieClient.user().deleteUser(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user", requestSent.getUrl())

        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
    }

    @Test
    public void testDeleteUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "deleteUser", new DeleteUserRequest())
    }

    @Test
    public void testGetUserSuccessfully() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "user1id");
        jsonContent.put(TestConstants.API.USERNAME, "user1@xyz.com");
        jsonContent.put(TestConstants.API.FULLNAME, "user1 user1");
        jsonContent.put(TestConstants.API.ROLE, User.Role.admin.name());
        jsonContent.put(TestConstants.API.TIMEZONE, "Etc/GMT+6");
        jsonContent.put(TestConstants.API.LOCALE, User.getLocaleId(Locale.CHINA));
        jsonContent.put(TestConstants.API.STATE, "active");
        jsonContent.put(TestConstants.API.GROUPS, ["group1", "group2"]);
        jsonContent.put(TestConstants.API.ESCALATIONS, ["esc1", "esc2"]);
        jsonContent.put(TestConstants.API.SCHEDULES, ["sch1", "sch2"]);
        jsonContent.put(TestConstants.API.CONTACTS, [["method": "email", "to": "user1@xyz.com"], ["method": "email", "to": "user1@gmail.com"]])
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetUserRequest request = new GetUserRequest();
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setUsername(jsonContent[TestConstants.API.USERNAME]);
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.user().getUser(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.USERNAME], response.getUser().username)
        assertEquals(jsonContent[TestConstants.API.ID], response.getUser().id)
        assertEquals(jsonContent[TestConstants.API.FULLNAME], response.getUser().fullname)
        assertEquals(jsonContent[TestConstants.API.STATE], response.getUser().state.name())
        assertEquals(jsonContent[TestConstants.API.GROUPS], response.getUser().groups)
        assertEquals(jsonContent[TestConstants.API.ESCALATIONS], response.getUser().escalations)
        assertEquals(jsonContent[TestConstants.API.SCHEDULES], response.getUser().schedules)
        assertEquals(jsonContent[TestConstants.API.CONTACTS].sort(), response.getUser().contacts.sort())
        assertEquals(Locale.CHINA, response.getUser().locale)
        assertEquals(TimeZone.getTimeZone(jsonContent[TestConstants.API.TIMEZONE]).getID(), response.getUser().timeZone.getID())
        assertEquals(User.Role.valueOf(jsonContent[TestConstants.API.ROLE]), response.getUser().role)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user", requestSent.getUrl())
    }

    @Test
    public void testGetUserSuccessfullyWithUsername() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "user1id");
        jsonContent.put(TestConstants.API.USERNAME, "user1@xyz.com");
        jsonContent.put(TestConstants.API.FULLNAME, "user1 user1");
        jsonContent.put(TestConstants.API.ROLE, User.Role.admin.name());
        jsonContent.put(TestConstants.API.TIMEZONE, "Etc/GMT+6");
        jsonContent.put(TestConstants.API.STATE, "active");
        jsonContent.put(TestConstants.API.GROUPS, ["group1", "group2"]);
        jsonContent.put(TestConstants.API.ESCALATIONS, ["esc1", "esc2"]);
        jsonContent.put(TestConstants.API.SCHEDULES, ["sch1", "sch2"]);
        jsonContent.put(TestConstants.API.CONTACTS, [["method": "email", "to": "user1@xyz.com"], ["method": "email", "to": "user1@gmail.com"]])
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetUserRequest request = new GetUserRequest();
        request.setUsername(jsonContent[TestConstants.API.USERNAME]);
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.user().getUser(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.USERNAME], response.getUser().username)
        assertEquals(jsonContent[TestConstants.API.ID], response.getUser().id)
        assertEquals(jsonContent[TestConstants.API.FULLNAME], response.getUser().fullname)
        assertEquals(jsonContent[TestConstants.API.STATE], response.getUser().state.name())
        assertEquals(jsonContent[TestConstants.API.GROUPS], response.getUser().groups)
        assertEquals(jsonContent[TestConstants.API.ESCALATIONS], response.getUser().escalations)
        assertEquals(jsonContent[TestConstants.API.SCHEDULES], response.getUser().schedules)
        assertEquals(jsonContent[TestConstants.API.CONTACTS].sort(), response.getUser().contacts.sort())
        assertEquals(TimeZone.getTimeZone(jsonContent[TestConstants.API.TIMEZONE]).getID(), response.getUser().timeZone.getID())
        assertEquals(User.Role.valueOf(jsonContent[TestConstants.API.ROLE]), response.getUser().role)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertFalse(requestSent.getParameters().containsKey(TestConstants.API.ID));
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user", requestSent.getUrl())
    }

    @Test
    public void testGetUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "getUser", new GetUserRequest(id: "usr1"))
    }

    @Test
    public void testListUserSuccessfully() throws Exception {
        Map user1Content = new HashMap();
        user1Content.put(TestConstants.API.ID, "user1id");
        user1Content.put(TestConstants.API.USERNAME, "user1@xyz.com");
        user1Content.put(TestConstants.API.FULLNAME, "user1 user1");
        user1Content.put(TestConstants.API.ROLE, User.Role.admin.name());
        user1Content.put(TestConstants.API.TIMEZONE, "Etc/GMT+6");
        user1Content.put(TestConstants.API.STATE, "active");
        user1Content.put(TestConstants.API.GROUPS, ["group1", "group2"]);
        user1Content.put(TestConstants.API.ESCALATIONS, ["esc1", "esc2"]);
        user1Content.put(TestConstants.API.SCHEDULES, ["sch1", "sch2"]);
        user1Content.put(TestConstants.API.CONTACTS, [["method": "email", "to": "user1@xyz.com"], ["method": "email", "to": "user1@gmail.com"]])

        Map user2Content = new HashMap();
        user2Content.put(TestConstants.API.ID, "user2id");
        user2Content.put(TestConstants.API.USERNAME, "user2@xyz.com");
        user2Content.put(TestConstants.API.FULLNAME, "user2 user2");
        user2Content.put(TestConstants.API.ROLE, User.Role.user.name());
        user2Content.put(TestConstants.API.TIMEZONE, "Etc/GMT+3");
        user2Content.put(TestConstants.API.STATE, "inactive");
        user2Content.put(TestConstants.API.GROUPS, []);
        user2Content.put(TestConstants.API.ESCALATIONS, []);
        user2Content.put(TestConstants.API.SCHEDULES, []);
        user2Content.put(TestConstants.API.CONTACTS, [[:]]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(users: [user1Content, user2Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListUsersRequest request = new ListUsersRequest();
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.user().listUsers(request)
        assertEquals(2, response.users.size())
        User user = response.getUsers().find { it.id == user1Content[TestConstants.API.ID] }
        assertEquals(user1Content[TestConstants.API.USERNAME], user.username)
        assertEquals(user1Content[TestConstants.API.ID], user.id)
        assertEquals(user1Content[TestConstants.API.FULLNAME], user.fullname)
        assertEquals(user1Content[TestConstants.API.STATE], user.state.name())
        assertEquals(user1Content[TestConstants.API.GROUPS], user.groups)
        assertEquals(user1Content[TestConstants.API.ESCALATIONS], user.escalations)
        assertEquals(user1Content[TestConstants.API.SCHEDULES], user.schedules)
        assertEquals(user1Content[TestConstants.API.CONTACTS].sort(), user.contacts.sort())
        assertEquals(TimeZone.getTimeZone(user1Content[TestConstants.API.TIMEZONE]).getID(), user.timeZone.getID())
        assertEquals(User.Role.valueOf(user1Content[TestConstants.API.ROLE]), user.role)

        user = response.getUsers().find { it.id == user2Content[TestConstants.API.ID] }
        assertEquals(user2Content[TestConstants.API.USERNAME], user.username)
        assertEquals(user2Content[TestConstants.API.ID], user.id)
        assertEquals(user2Content[TestConstants.API.FULLNAME], user.fullname)
        assertEquals(user2Content[TestConstants.API.STATE], user.state.name())
        assertEquals(user2Content[TestConstants.API.GROUPS], user.groups)
        assertEquals(user2Content[TestConstants.API.ESCALATIONS], user.escalations)
        assertEquals(user2Content[TestConstants.API.SCHEDULES], user.schedules)
        assertEquals(user2Content[TestConstants.API.CONTACTS], user.contacts)
        assertEquals(TimeZone.getTimeZone(user2Content[TestConstants.API.TIMEZONE]).getID(), user.timeZone.getID())
        assertEquals(User.Role.valueOf(user2Content[TestConstants.API.ROLE]), user.role)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1.1/json/user", requestSent.getUrl())
    }

    @Test
    public void testListUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "listUsers", new ListUsersRequest())
    }

    private String getResponseString(String responseType) {
        def jsonSlurper = new JsonSlurper()
        JSONObject data = jsonSlurper.parse(new FileReader("sdk/src/test/resources/ForwardingClient.json")) as JSONObject
        Object responseJson = data.get(responseType)
        String responseStr = new JsonBuilder(responseJson).toPrettyString()
        responseStr
    }

    private AddForwardingRequest getAddForwardingRequest() {
        AddForwardingRequest request = new AddForwardingRequest()
        request.setApiKey("customer1")
        request.setAlias("ruleAlias")
        request.setToUser(new BaseUserObj(id: "a65c6b81-ffbf-41bd-b674-b06de5d90c26"))
        request.setFromUser(new BaseUserObj(id: "ac8f4bf1-77ec-4a6d-9805-9c0a9c0d0d24"))
        request.setStartDate("2017-07-05T08:00:00Z")
        request.setEndDate("2017-07-06T18:00:00Z")
        request.setTimeZone(TimeZone.getTimeZone("Etc/GMT+2"))
        request
    }

    private UpdateForwardingRequest getUpdateForwardingRequest() {
        UpdateForwardingRequest request = new UpdateForwardingRequest()
        request.setApiKey("customer1")
        request.setIdentifier("ruleAlias")
        request.setIdentifierType("alias")
        request.setFromUser(new BaseUserObj(username: "user2@opsgenie.com"))
        request.setToUser(new BaseUserObj(username: "user@opsgenie.com"))
        request.setStartDate("2017-07-05T08:00:00Z")
        request.setEndDate("2017-07-06T18:00:00Z")
        request
    }
}
