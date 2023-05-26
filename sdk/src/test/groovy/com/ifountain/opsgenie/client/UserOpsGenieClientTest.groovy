package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.User
import com.ifountain.opsgenie.client.model.beans.UserRole
import com.ifountain.opsgenie.client.model.user.*
import com.ifountain.opsgenie.client.model.user.forward.*
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
import java.text.SimpleDateFormat
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse

/**
 * @author Manisha Singla
 */
class UserOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    public void testAddForwardingSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"forwarding1Id\",\"alias\":\"alias1\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddForwardingRequest request = new AddForwardingRequest();
        request.setApiKey("customer1");
        request.setAlias("forwarding1");
        request.setStartDate(new Date());
        request.setFromUser("user1");
        request.setToUser("user1");
        request.setTimeZone(TimeZone.getTimeZone("Etc/GMT+2"));
        request.setEndDate(new Date(System.currentTimeMillis() + 100000000l));

        def response = OpsGenieClientTestCase.opsgenieClient.user().addForwarding(request)
        assertEquals("forwarding1Id", response.getId())
        assertEquals("alias1", response.getAlias())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/forward", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(request.getTimeZone());

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getAlias(), jsonContent[TestConstants.API.ALIAS])
        assertEquals(sdf.format(request.getStartDate()), jsonContent[TestConstants.API.START_DATE])
        assertEquals(sdf.format(request.getEndDate()), jsonContent[TestConstants.API.END_DATE])
        assertEquals(request.getFromUser(), jsonContent[TestConstants.API.FROM_USER])
        assertEquals(request.getToUser(), jsonContent[TestConstants.API.TO_USER])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
    }

    @Test
    public void testAddForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "addForwarding", new AddForwardingRequest())
    }

    @Test
    public void testUpdateForwardingSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"forwarding1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateForwardingRequest request = new UpdateForwardingRequest();
        request.setId("forwarding1Id");
        request.setApiKey("customer1");
        request.setAlias("forwarding1");
        request.setStartDate(new Date());
        request.setFromUser("user1");
        request.setToUser("user1");
        request.setTimeZone(TimeZone.getTimeZone("Etc/GMT+2"));
        request.setEndDate(new Date(System.currentTimeMillis() + 100000000l));

        def response = OpsGenieClientTestCase.opsgenieClient.user().updateForwarding(request)
        assertEquals("forwarding1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/forward", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(request.getTimeZone());

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getAlias(), jsonContent[TestConstants.API.ALIAS])
        assertEquals(sdf.format(request.getStartDate()), jsonContent[TestConstants.API.START_DATE])
        assertEquals(sdf.format(request.getEndDate()), jsonContent[TestConstants.API.END_DATE])
        assertEquals(request.getFromUser(), jsonContent[TestConstants.API.FROM_USER])
        assertEquals(request.getToUser(), jsonContent[TestConstants.API.TO_USER])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
    }

    @Test
    public void testUpdateForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "updateForwarding", new UpdateForwardingRequest())
    }

    @Test
    public void testDeleteForwardingSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteForwardingRequest request = new DeleteForwardingRequest();
        request.setId("forwarding1Id");
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.user().deleteForwarding(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]

        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/forward", requestSent.getUrl())

        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])

        //test with alias
        request = new DeleteForwardingRequest();
        request.setId("alias1");
        request.setApiKey("customer2");

        response = OpsGenieClientTestCase.opsgenieClient.user().deleteForwarding(request)
        assertEquals(1, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]

        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/forward", requestSent.getUrl())

        assertEquals(request.getAlias(), requestSent.getParameters()[TestConstants.API.ALIAS]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
    }

    @Test
    public void testDeleteForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "deleteForwarding", new DeleteForwardingRequest())
    }

    @Test
    public void testGetForwardingSuccessfully() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-5"));

        def startDate = new Date()
        def endDate = new Date(System.currentTimeMillis() + 1000000)

        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "forwarding1");
        jsonContent.put(TestConstants.API.START_DATE, sdf.format(startDate));
        jsonContent.put(TestConstants.API.END_DATE, sdf.format(endDate));
        jsonContent.put(TestConstants.API.FROM_USER, "user1");
        jsonContent.put(TestConstants.API.TO_USER, "user2");
        jsonContent.put(TestConstants.API.ALIAS, "forwarding1Alias");
        jsonContent.put(TestConstants.API.TIMEZONE, sdf.getTimeZone().getID());
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetForwardingRequest request = new GetForwardingRequest();
        request.setId("forwarding1");
        request.setAlias("forwarding1Alias");
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.user().getForwarding(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.ALIAS], response.getForwarding().alias)
        assertEquals(jsonContent[TestConstants.API.ID], response.getForwarding().id)
        assertEquals(jsonContent[TestConstants.API.TO_USER], response.getForwarding().toUser)
        assertEquals(jsonContent[TestConstants.API.FROM_USER], response.getForwarding().fromUser)
        assertEquals(sdf.format(startDate), sdf.format(response.getForwarding().startDate))
        assertEquals(sdf.format(endDate), sdf.format(response.getForwarding().endDate))
        assertEquals(sdf.getTimeZone(), response.getForwarding().timeZone)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getAlias(), requestSent.getParameters()[TestConstants.API.ALIAS]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/forward", requestSent.getUrl())
    }

    @Test
    public void testGetForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "getForwarding", new GetForwardingRequest())
    }

    @Test
    public void testListForwardingsSuccessfully() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-5"));

        def startDate1 = new Date()
        def endDate1 = new Date(System.currentTimeMillis() + 1000000)
        def startDate2 = new Date(System.currentTimeMillis() + 2000000)
        def endDate2 = new Date(System.currentTimeMillis() + 3000000)

        Map jsonContent1 = new HashMap();
        jsonContent1.put(TestConstants.API.ID, "forwarding1");
        jsonContent1.put(TestConstants.API.START_DATE, sdf.format(startDate1));
        jsonContent1.put(TestConstants.API.END_DATE, sdf.format(endDate1));
        jsonContent1.put(TestConstants.API.FROM_USER, "user1");
        jsonContent1.put(TestConstants.API.TO_USER, "user2");
        jsonContent1.put(TestConstants.API.ALIAS, "forwarding1Alias");
        jsonContent1.put(TestConstants.API.TIMEZONE, sdf.getTimeZone().getID());
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-3"));
        Map jsonContent2 = new HashMap();
        jsonContent2.put(TestConstants.API.ID, "forwarding2");
        jsonContent2.put(TestConstants.API.START_DATE, sdf.format(startDate2));
        jsonContent2.put(TestConstants.API.END_DATE, sdf.format(endDate2));
        jsonContent2.put(TestConstants.API.FROM_USER, "user3");
        jsonContent2.put(TestConstants.API.TO_USER, "user4");
        jsonContent2.put(TestConstants.API.ALIAS, "forwarding2Alias");
        jsonContent2.put(TestConstants.API.TIMEZONE, sdf.getTimeZone().getID());
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson([took: 1, forwardings: [jsonContent1, jsonContent2]]).getBytes(), 200, "application/json; charset=utf-8"))

        ListForwardingsRequest request = new ListForwardingsRequest();
        request.setUser("user1");
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.user().listForwardings(request)
        assertEquals(1, response.getTook())
        def forwardings = response.getForwardings();
        assertEquals(2, forwardings.size())
        def forwarding = forwardings.find { it.alias == jsonContent1[TestConstants.API.ALIAS] }
        assertEquals(jsonContent1[TestConstants.API.ALIAS], forwarding.alias)
        assertEquals(jsonContent1[TestConstants.API.ID], forwarding.id)
        assertEquals(jsonContent1[TestConstants.API.TO_USER], forwarding.toUser)
        assertEquals(jsonContent1[TestConstants.API.FROM_USER], forwarding.fromUser)
        assertEquals(sdf.format(startDate1), sdf.format(forwarding.startDate))
        assertEquals(sdf.format(endDate1), sdf.format(forwarding.endDate))
        assertEquals(TimeZone.getTimeZone("Etc/GMT-5"), forwarding.timeZone)

        forwarding = forwardings.find { it.alias == jsonContent2[TestConstants.API.ALIAS] }
        assertEquals(jsonContent2[TestConstants.API.ALIAS], forwarding.alias)
        assertEquals(jsonContent2[TestConstants.API.ID], forwarding.id)
        assertEquals(jsonContent2[TestConstants.API.TO_USER], forwarding.toUser)
        assertEquals(jsonContent2[TestConstants.API.FROM_USER], forwarding.fromUser)
        assertEquals(sdf.format(startDate2), sdf.format(forwarding.startDate))
        assertEquals(sdf.format(endDate2), sdf.format(forwarding.endDate))
        assertEquals(TimeZone.getTimeZone("Etc/GMT-3"), forwarding.timeZone)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getUser(), requestSent.getParameters()[TestConstants.API.USER]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/forward", requestSent.getUrl())
    }

    @Test
    public void testListForwardingsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "listForwardings", new ListForwardingsRequest())
    }


    @Test
    void testAddUserSuccessfully() throws Exception {
        String responseStr = getResponseString("AddUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        AddUserRequest request = getAddUserRequest()

        def response = opsgenieClient.user().addUser(request)
        assertEquals("test_user_id", response.getUserData().getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getFullName(), jsonContent[TestConstants.API.FULLNAME])
        assertEquals(request.getUserRole().getName(), jsonContent[TestConstants.API.ROLE][TestConstants.API.NAME])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertEquals(User.getLocaleId(request.getLocale()), jsonContent[TestConstants.API.LOCALE])
        assertEquals(request.getInvitationDisabled(), jsonContent[TestConstants.API.INVITATION_DISABLED])
    }

    @Test
    void testAddUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "addUser", new AddUserRequest())
    }

    @Test
    void testAddUserRequestValidationException() throws Exception {
        String responseStr = getResponseString("AddUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        AddUserRequest request = getAddUserRequest()
        request.setRole(null)
        try{
            def response = opsgenieClient.user().addUser(request)
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [role]")
        }
        request.setFullName(null)
        try{
            def response = opsgenieClient.user().addUser(request)
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [fullname]")
        }
        request.setUsername(null)
        try{
            def response = opsgenieClient.user().addUser(request)
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [username]")
        }
    }

    @Test
    void testUpdateUserSuccessfully() throws Exception {
        String responseStr = getResponseString("UpdateUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        UpdateUserRequest request = getUpdateUserRequest()

        def response = opsgenieClient.user().updateUser(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPatch.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users/user1Id", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getIdentifier(), jsonContent[TestConstants.API.IDENTIFIER])
        assertEquals(request.getFullName(), jsonContent[TestConstants.API.FULLNAME])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertEquals(User.getLocaleId(request.getLocale()), jsonContent[TestConstants.API.LOCALE])
    }

    @Test
    void testUpdateUserWithNoUpdateProperty() throws Exception {
        String responseStr = getResponseString("UpdateUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        UpdateUserRequest request = new UpdateUserRequest()
        request.setApiKey("customer1")
        request.setIdentifier("user1Id")

        def response = opsgenieClient.user().updateUser(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPatch.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users/user1Id", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getIdentifier(), jsonContent[TestConstants.API.IDENTIFIER])
        assertFalse(jsonContent.containsKey(TestConstants.API.USERNAME))
        assertFalse(jsonContent.containsKey(TestConstants.API.FULLNAME))
        assertFalse(jsonContent.containsKey(TestConstants.API.ROLE))
        assertFalse(jsonContent.containsKey(TestConstants.API.TIMEZONE))
        assertFalse(jsonContent.containsKey(TestConstants.API.LOCALE))
    }

    @Test
    void testUpdateUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "updateUser", new UpdateUserRequest())
        String responseStr = getResponseString("UpdateUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        UpdateUserRequest request = new UpdateUserRequest()
        request.setApiKey("customer1")
        try{
            def response = opsgenieClient.user().updateUser(request)
        }
        catch(OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [id or username]")
        }
    }

    @Test
    void testDeleteUserSuccessfully() throws Exception {
        String responseStr = getResponseString("DeleteUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        DeleteUserRequest request = new DeleteUserRequest()
        request.setApiKey("customer1")
        request.setIdentifier("user1Id")

        def response = opsgenieClient.user().deleteUser(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/users/user1Id", requestSent.getUrl())
    }

    @Test
    void testDeleteUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "deleteUser", new DeleteUserRequest())
        String responseStr = getResponseString("DeleteUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        DeleteUserRequest request = new DeleteUserRequest()
        request.setApiKey("customer1")
        try{
            def response = opsgenieClient.user().deleteUser(request)
        }
        catch(OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [id or username]")
        }
    }

    @Test
    void testGetUserSuccessfully() throws Exception {
        String responseStr = getResponseString("GetUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        GetUserRequest request = new GetUserRequest()
        request.setIdentifier("f4df67fe-15cb-4c88-ad9c-a1a83a785fe6")
        request.setApiKey("customer1")
        request.setExpand("contact")

        def response = opsgenieClient.user().getUser(request)
        assertEquals(false,response.getUser().getBlocked())
        assertEquals(false,response.getUser().getVerified())
        assertEquals(0, response.getTook())
        assertEquals("john.doe@opsgenie.com", response.getUser().username)
        assertEquals("f4df67fe-15cb-4c88-ad9c-a1a83a785fe6", response.getUser().id)
        assertEquals("john doe", response.getUser().fullName)
        assertEquals("Admin",response.getUser().getRole().getId())
        assertEquals("Admin",response.getUser().getRole().getName())
        assertEquals("doejohn",response.getUser().getSkypeUsername())
        assertEquals(TimeZone.getTimeZone("GMT").getID(), response.getUser().getTimeZone().getID())
        assertEquals(User.getLocaleId(Locale.US).toString(), response.getUser().getLocale().toString())
        assertEquals(2, response.getUser().getTags().size())
        assertEquals("US",response.getUser().getUserAddress().getCountry())
        assertEquals("Indiana",response.getUser().getUserAddress().getState())
        assertEquals("Terre Haute",response.getUser().getUserAddress().getCity())
        assertEquals("567 Stratford Park",response.getUser().getUserAddress().getLine())
        assertEquals("47802",response.getUser().getUserAddress().getZipCode())
        assertEquals(2,response.getUser().getDetails().get("detail1key").size())
        assertEquals(1,response.getUser().getDetails().get("detail2key").size())
        assertEquals("2017-05-23T08:38:28.17Z",response.getUser().getCreatedAt())
        assertEquals(1,response.getUser().getContacts().size())
        assertEquals(1,response.getExpandable().size())
        assertEquals("contact",response.getExpandable().get(0))
        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/users/f4df67fe-15cb-4c88-ad9c-a1a83a785fe6", requestSent.getUrl())
    }

    @Test
    void testGetUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "getUser", new GetUserRequest(identifier: "usr1"))
        String responseStr = getResponseString("GetUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        GetUserRequest request = new GetUserRequest()
        request.setApiKey("customer1")
        try{
            def response = opsgenieClient.user().getUser(request)
        }
        catch(OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [username or id]")
        }
        request.setIdentifier("test_identifier")
        request.setExpand("invalid_expand")
        try{
            def response = opsgenieClient.user().getUser(request)
        }
        catch(OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Invalid Values for expand")
        }
    }

    @Test
    void testListUserSuccessfully() throws Exception {
        String responseStr = getResponseString("ListUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        ListUsersRequest request = new ListUsersRequest()
        request.setApiKey("customer1")

        def response = opsgenieClient.user().listUsers(request)
        assertEquals(2, response.users.size())
        User user = response.getUsers().find { it.id == "b5b92115-bfe7-43eb-8c2a-e467f2e5ddc4" }
        assertEquals("john.doe@opsgenie.com", user.getUsername())
        assertEquals("b5b92115-bfe7-43eb-8c2a-e467f2e5ddc4", user.getId())
        assertEquals("john doe", user.getFullName())
        assertEquals(TimeZone.getTimeZone("Europe/Kirov").getID(), user.getTimeZone().getID())
        assertEquals("Admin",user.getRole().getId())
        assertEquals("Admin",user.getRole().getName())
        assertEquals(false,user.getBlocked())
        assertEquals(false,user.getVerified())

        user = response.getUsers().find { it.id == "e07c63f0-dd8c-4ad4-983e-4ee7dc600463" }
        assertEquals("jane.doe@opsgenie.com", user.getUsername())
        assertEquals("e07c63f0-dd8c-4ad4-983e-4ee7dc600463", user.getId())
        assertEquals("jane doe", user.getFullName())
        assertEquals(TimeZone.getTimeZone("Europe/Moscow").getID(), user.getTimeZone().getID())
        assertEquals("Admin",user.getRole().getId())
        assertEquals("Admin",user.getRole().getName())
        assertEquals(false,user.getBlocked())
        assertEquals(false,user.getVerified())

        assertEquals("http://api.opsgenie.com/v2/users?limit=2&offset=1&order=DESC&sort=fullName&query=role%3Aadmin",response.getPaging().getPrev())
        assertEquals("http://api.opsgenie.com/v2/users?limit=2&offset=5&order=DESC&sort=fullName&query=role%3Aadmin",response.getPaging().getNext())
        assertEquals("http://api.opsgenie.com/v2/users?limit=2&offset=0&order=DESC&sort=fullName&query=role%3Aadmin",response.getPaging().getFirst())
        assertEquals("http://api.opsgenie.com/v2/users?limit=2&offset=6&order=DESC&sort=fullName&query=role%3Aadmin",response.getPaging().getLast())
        assertEquals("d2c50d0c-1c44-4fa5-99d4-20d1e7ca9938",response.getRequestId())
        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/users", requestSent.getUrl())
    }

    @Test
    void testListUserSuccessfullyWithParams() throws Exception {
        String responseStr = getResponseString("ListUserResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        ListUsersRequest request = getListUserRequest()

        def response = opsgenieClient.user().listUsers(request)
        assertEquals(2, response.users.size())
        User user = response.getUsers().find { it.id == "b5b92115-bfe7-43eb-8c2a-e467f2e5ddc4" }
        assertEquals("john.doe@opsgenie.com", user.getUsername())
        assertEquals("b5b92115-bfe7-43eb-8c2a-e467f2e5ddc4", user.getId())
        assertEquals("john doe", user.getFullName())
        assertEquals(TimeZone.getTimeZone("Europe/Kirov").getID(), user.getTimeZone().getID())
        assertEquals("Admin",user.getRole().getId())
        assertEquals("Admin",user.getRole().getName())
        assertEquals(false,user.getBlocked())
        assertEquals(false,user.getVerified())

        user = response.getUsers().find { it.id == "e07c63f0-dd8c-4ad4-983e-4ee7dc600463" }
        assertEquals("jane.doe@opsgenie.com", user.getUsername())
        assertEquals("e07c63f0-dd8c-4ad4-983e-4ee7dc600463", user.getId())
        assertEquals("jane doe", user.getFullName())
        assertEquals(TimeZone.getTimeZone("Europe/Moscow").getID(), user.getTimeZone().getID())
        assertEquals("Admin",user.getRole().getId())
        assertEquals("Admin",user.getRole().getName())
        assertEquals(false,user.getBlocked())
        assertEquals(false,user.getVerified())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/users", requestSent.getUrl())
    }

    @Test
    void testListUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.user(), "listUsers", new ListUsersRequest())
    }

    private String getResponseString(String responseType) {
        def jsonSlurper = new JsonSlurper()
        JSONObject data = jsonSlurper.parse(new FileReader("sdk/src/test/resources/UserOpsGenieClient.json")) as JSONObject
        Object responseJson = data.get(responseType)
        String responseStr = new JsonBuilder(responseJson).toPrettyString()
        responseStr
    }

    private AddUserRequest getAddUserRequest() {
        AddUserRequest request = new AddUserRequest()
        request.setApiKey("test_apiKey")
        request.setUsername("user1@xyz.com")
        UserRole userRole = new UserRole()
        userRole.setName("admin")
        request.setRole(userRole)
        request.setFullName("user1 user1")
        request.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"))
        request.setLocale(Locale.CHINA)
        request.setInvitationDisabled(true)
        request
    }

    private UpdateUserRequest getUpdateUserRequest() {
        UpdateUserRequest request = new UpdateUserRequest()
        request.setApiKey("customer1")
        request.setIdentifier("user1Id")
        request.setFullName("user1 user1")
        request.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"))
        request.setLocale(Locale.CHINA)
        request
    }

    private ListUsersRequest getListUserRequest() {
        ListUsersRequest request = new ListUsersRequest()
        request.setApiKey("customer1")
        request.setLimit(40)
        request.setOffset(10)
        request.setSort(ListUsersRequest.SortField.username)
        request.setOrder(ListUsersRequest.SortOrder.asc)
        request.setQuery("role")
        request
    }

}
