package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.User
import com.ifountain.opsgenie.client.model.user.*
import com.ifountain.opsgenie.client.model.user.forward.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.junit.Test

import java.text.SimpleDateFormat

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 11:09 AM
 */
public class UserOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

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
}
