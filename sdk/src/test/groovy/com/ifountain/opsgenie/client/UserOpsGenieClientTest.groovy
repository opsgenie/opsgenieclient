package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.BaseUserObj
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
import org.apache.http.client.methods.HttpPut
import org.json.JSONObject
import org.junit.Test
import java.time.Instant
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse

/**
 * @author Manisha Singla
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
        assertEquals(request.getStartDate().toInstant(), Instant.parse(jsonContent[TestConstants.API.START_DATE] as CharSequence))
        assertEquals(request.getEndDate().toInstant(), Instant.parse(jsonContent[TestConstants.API.END_DATE] as CharSequence))
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

        request.setStartDate(null)
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
        assertEquals(request.getStartDate().toInstant(), Instant.parse(jsonContent[TestConstants.API.START_DATE] as CharSequence))
        assertEquals(request.getEndDate().toInstant(), Instant.parse(jsonContent[TestConstants.API.END_DATE] as CharSequence))
        assertEquals(request.getFromUser().getUsername(), jsonContent[TestConstants.API.FROM_USER][TestConstants.API.USERNAME])
        assertEquals(request.getToUser().getUsername(), jsonContent[TestConstants.API.TO_USER][TestConstants.API.USERNAME])
    }

    @Test
    void testUpdateForwardingThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "updateForwarding", new UpdateForwardingRequest())
        String responseStr = getResponseString("UpdateForwardingResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        UpdateForwardingRequest request = getUpdateForwardingRequest()

        request.setStartDate(null)
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
        assertEquals(Instant.parse("2017-07-05T08:00:00.000Z"),response.getForwarding().getStartDate().toInstant())
        assertEquals(Instant.parse("2017-07-06T18:00:00.000Z"),response.getForwarding().getEndDate().toInstant())

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
        assertEquals(Instant.parse("2017-07-05T08:00:00.000Z"),response.getForwarding().getStartDate().toInstant())
        assertEquals(Instant.parse("2017-07-06T18:00:00.000Z"),response.getForwarding().getEndDate().toInstant())

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
        assertEquals(Instant.parse("2017-05-08T21:00:00.000Z"),forwarding.getStartDate().toInstant())
        assertEquals(Instant.parse("2017-05-09T21:00:00.000Z"),forwarding.getEndDate().toInstant())

        forwarding = forwardings.find { it.id == "e2a4ea3a-7abb-4bb9-8a05-00de82de544d" }
        assertEquals("", forwarding.alias)
        assertEquals("e2a4ea3a-7abb-4bb9-8a05-00de82de544d", forwarding.id)
        assertEquals("4b26961a-a837-49d2-a1fe-0973013e3c3b", forwarding.toUser.getId())
        assertEquals("e58d6ee3-37bd-432f-9ded-64808b761ae0", forwarding.fromUser.getId())
        assertEquals("user2@opsgenie.com", forwarding.toUser.getUsername())
        assertEquals("user@opsgenie.com", forwarding.fromUser.getUsername())
        assertEquals(Instant.parse("2017-05-22T21:00:00.000Z"),forwarding.getStartDate().toInstant())
        assertEquals(Instant.parse("2017-05-24T21:00:00.000Z"),forwarding.getEndDate().toInstant())

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
        assertEquals(Instant.parse("2017-05-23T08:38:28.017Z"),response.getUser().getCreatedAt().toInstant())
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
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.user(), "listUsers", new ListUsersRequest())
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


    private String getResponseString(String responseType) {
        def jsonSlurper = new JsonSlurper()
        JSONObject data = jsonSlurper.parse(new FileReader("sdk/src/test/resources/UserOpsGenieClient.json")) as JSONObject
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
        Date current = Calendar.getInstance().getTime()
        request.setStartDate(current + 1)
        request.setEndDate(current + 2)
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
        Date current = Calendar.getInstance().getTime()
        request.setStartDate(current + 1)
        request.setEndDate(current + 2)
        request
    }
}
