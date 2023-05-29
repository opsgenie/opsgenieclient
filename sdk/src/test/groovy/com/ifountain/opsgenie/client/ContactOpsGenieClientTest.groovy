package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.Contact
import com.ifountain.opsgenie.client.model.contact.*
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
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * @auhtor Manisha Singla
 */
class ContactOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    void testAddContactSuccessfullyWithUserIdentifier() throws Exception {
        String responseStr = getResponseString("AddContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(),201,"application/json; charset=utf-8"))

        AddContactRequest request = getAddContactRequest()

        def response = opsgenieClient.contact().addContact(request);
        assertEquals("contact1Id", response.getData().getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users/test_user_identifier/contacts", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getMethod().value(), jsonContent[TestConstants.API.METHOD])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO]);
    }

    @Test
    void testAddContactValidationFailure() throws Exception{
        String responseStr = getResponseString("AddContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(),4000,"application/json; charset=utf-8"))
        AddContactRequest request = getAddContactRequest()
        request.setUserIdentifier(null);
        try{
            def response = opsgenieClient.contact().addContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [userIdentifier]");
        }
        request.setTo(null)
        try{
            def response = opsgenieClient.contact().addContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [to]");
        }
        request.setTo("to");
        request.setMethod(null);
        try{
            def response = opsgenieClient.contact().addContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [method]");
        }
    }

    @Test
    void testUpdateContactSuccessfullyWithUserIdentifier() throws Exception {
        String responseStr = getResponseString("UpdateContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(),200,"application/json; charset=utf-8"))

        UpdateContactRequest request = getUpdateContactRequest()

        def response = opsgenieClient.contact().updateContact(request)
        assertEquals("contact1Id", response.getData().getId())
        assertEquals(0, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPatch.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users/test_user_identifier/contacts/test_contact_id", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO]);
    }

    @Test
    void testUpdateContactValidationFailure() throws Exception{
        String responseStr = getResponseString("UpdateContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(),4000,"application/json; charset=utf-8"))
        UpdateContactRequest request = getUpdateContactRequest()
        request.setUserIdentifier(null);
        try{
            def response = opsgenieClient.contact().updateContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [userIdentifier]");
        }
        request.setTo(null)
        request.setUserIdentifier("test_user_identifier")
        try{
            def response = opsgenieClient.contact().updateContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [to]");
        }
        request.setContactId(null)
        try{
            def response = opsgenieClient.contact().updateContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [contactId]");
        }
    }

    @Test
    void testDeleteContactSuccessfullyWithUserIdentifier() throws Exception {
        String responseStr = getResponseString("DeleteContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        DeleteContactRequest request = getDeleteContactRequest()

        def response = opsgenieClient.contact().deleteContact(request)
        assertEquals(0, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users/test_user_identifier/contacts/contact1Id", requestSent.getUrl())
    }

    @Test
    void testDeleteContactValidationFailure() throws Exception{
        String responseStr = getResponseString("DeleteContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        DeleteContactRequest request = getDeleteContactRequest()
        request.setUserIdentifier(null);
        try{
            def response = opsgenieClient.contact().deleteContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [userIdentifier]");
        }
        request.setContactId(null)
        try{
            def response = opsgenieClient.contact().deleteContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [contactId]");
        }
    }

    @Test
    void testEnableContactSuccessfullyWithUserIdentifier() throws Exception {
        String responseStr = getResponseString("EnableContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        EnableContactRequest request = getEnableContactRequest()

        def response = opsgenieClient.contact().enableContact(request)

        assertTrue(response.success)
        assertEquals(2, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users/test_user_identifier/contacts/contact1/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));
    }

    @Test
    void testEnableContactValidationFailure() throws Exception{
        String responseStr = getResponseString("EnableContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        EnableContactRequest request = getEnableContactRequest()

        request.setUserIdentifier(null);
        try{
            def response = opsgenieClient.contact().enableContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [userIdentifier]");
        }
        request.setContactId(null)
        try{
            def response = opsgenieClient.contact().enableContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [contactId]");
        }
    }

    @Test
    void testDisableContactSuccessfullyWithUserIdentifier() throws Exception {
        String responseStr = getResponseString("DisableContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        DisableContactRequest request = getDisableContactRequest()

        def response = opsgenieClient.contact().disableContact(request);

        assertTrue(response.success)
        assertEquals(2, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/users/test_user_identifier/contacts/contact1/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE))
    }

    @Test
    void testDisableContactValidationFailure() throws Exception{
        String responseStr = getResponseString("DisableContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        DisableContactRequest request = getDisableContactRequest()

        request.setUserIdentifier(null);
        try{
            def response = opsgenieClient.contact().disableContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [userIdentifier]");
        }
        request.setContactId(null)
        try{
            def response = opsgenieClient.contact().disableContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [contactId]");
        }
    }

    @Test
    void testGetContactSuccessfullyWithUserIdentifier() throws Exception {
        String responseStr = getResponseString("GetContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        GetContactRequest request = getGetContactRequest()

        def response = opsgenieClient.contact().getContact(request)
        assertEquals(0, response.getTook())
        assertEquals("email", response.getContact().getMethod().value())
        assertEquals("user@opsgenie.com", response.getContact().getTo())
        assertEquals("test_id", response.getContact().getId())
        assertEquals(true, response.getContact().getStatus().getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/users/test_user_identifier/contacts/contact1", requestSent.getUrl())
    }

    @Test
    void testGetContactValidationFailure() throws Exception{
        String responseStr = getResponseString("GetContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        GetContactRequest request = getGetContactRequest()

        request.setUserIdentifier(null);
        try{
            def response = opsgenieClient.contact().getContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [userIdentifier]");
        }
        request.setContactId(null)
        try{
            def response = opsgenieClient.contact().getContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [contactId]");
        }
    }

    @Test
    void testListContactSuccessfullyWithUserIdentifier() throws Exception {
        String responseStr = getResponseString("ListContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        ListContactsRequest request = getListContactsRequest()

        def response = opsgenieClient.contact().listContact(request)
        assertEquals(2, response.getUserContacts().size())
        assertEquals(0, response.getTook())

        Contact contact = response.getUserContacts().find { it.id == "test_id1" }
        assertEquals("email", contact.getMethod().value())
        assertEquals("user1@opsgenie.com", contact.getTo())
        assertEquals("test_id1", contact.getId())
        assertEquals(true, contact.getStatus().getEnabled())
        assertEquals(0,contact.getApplyOrder())

        contact = response.getUserContacts().find { it.id == "test_id2" }
        assertEquals("sms", contact.getMethod().value())
        assertEquals("1-9999999999", contact.getTo())
        assertEquals("test_id2", contact.getId())
        assertEquals(true, contact.getStatus().getEnabled())
        assertEquals(1,contact.getApplyOrder())

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals("/v2/users/test_user_identifier/contacts", requestSent.getUrl())
    }

    @Test
    void testListContactsValidationFailure() throws Exception{
        String responseStr = getResponseString("ListContactResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        ListContactsRequest request = getListContactsRequest()

        request.setUserIdentifier(null);
        try{
            def response = opsgenieClient.contact().listContact(request);
        }
        catch (OpsGenieClientValidationException exception){
            assertEquals(exception.getMessage(),"Missing mandatory property [userIdentifier]");
        }
    }

    private String getResponseString(String responseType) {
        def jsonSlurper = new JsonSlurper()
        JSONObject data = jsonSlurper.parse(new FileReader("sdk/src/test/resources/ContactOpsgenieClient.json")) as JSONObject
        Object responseJson = data.get(responseType)
        String responseStr = new JsonBuilder(responseJson).toPrettyString()
        responseStr
    }

    private AddContactRequest getAddContactRequest() {
        AddContactRequest request = new AddContactRequest();
        request.setApiKey("apiKey")
        request.setMethod(Contact.Method.EMAIL)
        request.setUserIdentifier("test_user_identifier")
        request.setTo("To")
        request
    }

    private UpdateContactRequest getUpdateContactRequest() {
        UpdateContactRequest request = new UpdateContactRequest();
        request.setApiKey("apiKey");
        request.setContactId("test_contact_id");
        request.setUserIdentifier("test_user_identifier")
        request.setTo("To")
        request
    }

    private DeleteContactRequest getDeleteContactRequest() {
        DeleteContactRequest request = new DeleteContactRequest()
        request.setApiKey("customer1");
        request.setContactId("contact1Id");
        request.setUserIdentifier("test_user_identifier")
        request
    }

    private GetContactRequest getGetContactRequest() {
        GetContactRequest request = new GetContactRequest()
        request.setApiKey("apiKey")
        request.setContactId("contact1")
        request.setUserIdentifier("test_user_identifier")
        request
    }

    private DisableContactRequest getDisableContactRequest() {
        DisableContactRequest request = new DisableContactRequest();
        request.setApiKey("apiKey")
        request.setContactId("contact1")
        request.setUserIdentifier("test_user_identifier")
        request
    }

    private EnableContactRequest getEnableContactRequest() {
        EnableContactRequest request = new EnableContactRequest();
        request.setApiKey("apiKey")
        request.setContactId("contact1")
        request.setUserIdentifier("test_user_identifier")
        request
    }

    private ListContactsRequest getListContactsRequest() {
        ListContactsRequest request = new ListContactsRequest()
        request.setApiKey("apiKey")
        request.setUserIdentifier("test_user_identifier")
        request
    }
}
