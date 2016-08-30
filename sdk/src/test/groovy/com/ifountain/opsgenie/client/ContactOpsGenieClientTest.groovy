package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.Contact
import com.ifountain.opsgenie.client.model.contact.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * Created by MEHMET MUSTAFA DEMİR
 * Date: 7/29/16
 * Time: 10:49 AM
 */
class ContactOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    public void testAddContactSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"contact1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddContactRequest request = new AddContactRequest();
        request.setApiKey("customer1");
        request.setUsername("user1")
        request.setMethod(Contact.Method.EMAIL)
        request.setTo("To")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().addContact(request);
        assertEquals("contact1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getMethod().value(), jsonContent[TestConstants.API.METHOD])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO]);
    }

    @Test
    public void testAddContactSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"contact1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddContactRequest request = new AddContactRequest();
        request.setApiKey("customer1");
        request.setUserId("user1")
        request.setMethod(Contact.Method.EMAIL)
        request.setTo("To")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().addContact(request);
        assertEquals("contact1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getMethod().value(), jsonContent[TestConstants.API.METHOD])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO]);
    }

    @Test
    public void testUpdateContactSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"contact1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateContactRequest request = new UpdateContactRequest();
        request.setApiKey("customer1");
        request.setId("user1Id");
        request.setUsername("user1")
        request.setTo("To")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().updateContact(request)
        assertEquals("contact1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO]);
    }

    @Test
    public void testUpdateContactSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"contact1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateContactRequest request = new UpdateContactRequest();
        request.setApiKey("customer1");
        request.setId("user1Id");
        request.setUserId("user1")
        request.setTo("To")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().updateContact(request)
        assertEquals("contact1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO]);
    }

    @Test
    public void testDeleteContactSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DeleteContactRequest request = new DeleteContactRequest();
        request.setApiKey("customer1");
        request.setId("contact1Id");
        request.setUsername("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().deleteContact(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact", requestSent.getUrl())

        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME])
    }

    @Test
    public void testDeleteContactSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DeleteContactRequest request = new DeleteContactRequest();
        request.setApiKey("customer1");
        request.setId("contact1Id");
        request.setUserId("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().deleteContact(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact", requestSent.getUrl())

        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID])
    }

    @Test
    public void testEnableContactWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        EnableContactRequest request = new EnableContactRequest();
        request.setApiKey("customer1");
        request.setId("contact1")
        request.setUsername("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().enableContact(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
    }

    @Test
    public void testEnableContactWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        EnableContactRequest request = new EnableContactRequest();
        request.setApiKey("customer1");
        request.setId("contact1")
        request.setUserId("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().enableContact(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
    }


    @Test
    public void testDisableContactWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DisableContactRequest request = new DisableContactRequest();
        request.setApiKey("customer1");
        request.setId("contact1")
        request.setUsername("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().disableContact(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
    }

    @Test
    public void testDisableContactWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DisableContactRequest request = new DisableContactRequest();
        request.setApiKey("customer1");
        request.setId("contact1")
        request.setUserId("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.contact().disableContact(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/contact/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
    }

    @Test
    public void testGetContactSuccessfullyWithUserName() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.DISABLED_REASON, "reason");
        jsonContent.put(TestConstants.API.METHOD, "email");
        jsonContent.put(TestConstants.API.TO, "john@opsgenie.com");
        jsonContent.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        jsonContent.put(TestConstants.API.ENABLED, true);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetContactRequest request = new GetContactRequest();
        request.setApiKey("customer1");
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setUsername("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().getContact(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.DISABLED_REASON], response.getContact().getDisabledReason())
        assertEquals(jsonContent[TestConstants.API.METHOD], response.getContact().getMethod().value())
        assertEquals(jsonContent[TestConstants.API.TO], response.getContact().getTo())
        assertEquals(jsonContent[TestConstants.API.ID], response.getContact().getId())
        assertEquals(jsonContent[TestConstants.API.ENABLED], response.getContact().getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }

    @Test
    public void testGetContactSuccessfullyWithUserId() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.DISABLED_REASON, "reason");
        jsonContent.put(TestConstants.API.METHOD, "email");
        jsonContent.put(TestConstants.API.TO, "john@opsgenie.com");
        jsonContent.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        jsonContent.put(TestConstants.API.ENABLED, true);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetContactRequest request = new GetContactRequest();
        request.setApiKey("customer1");
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setUserId("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().getContact(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.DISABLED_REASON], response.getContact().getDisabledReason())
        assertEquals(jsonContent[TestConstants.API.METHOD], response.getContact().getMethod().value())
        assertEquals(jsonContent[TestConstants.API.TO], response.getContact().getTo())
        assertEquals(jsonContent[TestConstants.API.ID], response.getContact().getId())
        assertEquals(jsonContent[TestConstants.API.ENABLED], response.getContact().getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }

    @Test
    public void testListContactSuccessfullyWithUserName() throws Exception {
        Map contact1Content = new HashMap();
        contact1Content.put(TestConstants.API.DISABLED_REASON, "reason1");
        contact1Content.put(TestConstants.API.METHOD, "sms");
        contact1Content.put(TestConstants.API.TO, "1-9999999999");
        contact1Content.put(TestConstants.API.ID, "60b77613-f86f-4642-bf32-dee2aa678e4d");
        contact1Content.put(TestConstants.API.ENABLED, true);

        Map contact2Content = new HashMap();
        contact2Content.put(TestConstants.API.DISABLED_REASON, "reason2");
        contact2Content.put(TestConstants.API.METHOD, "voice");
        contact2Content.put(TestConstants.API.TO, "1-9999999998");
        contact2Content.put(TestConstants.API.ID, "6987706b-eaa9-4801-9269-94b13c2eda9a");
        contact2Content.put(TestConstants.API.ENABLED, false);

        Map contact3Content = new HashMap();
        contact3Content.put(TestConstants.API.DISABLED_REASON, "reason3");
        contact3Content.put(TestConstants.API.METHOD, "email");
        contact3Content.put(TestConstants.API.TO, "john@opsgenie.com");
        contact3Content.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        contact3Content.put(TestConstants.API.ENABLED, false);

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(userContacts: [contact1Content, contact2Content, contact3Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListContactsRequest request = new ListContactsRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().listContact(request)
        assertEquals(3, response.getContacts().size())
        Contact contact = response.getContacts().find { it.id == contact1Content[TestConstants.API.ID] }
        assertEquals(contact1Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact1Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact1Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact1Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact1Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact2Content[TestConstants.API.ID] }
        assertEquals(contact2Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact2Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact2Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact2Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact2Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact3Content[TestConstants.API.ID] }
        assertEquals(contact3Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact3Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact3Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact3Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact3Content[TestConstants.API.ENABLED], contact.getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }

    @Test
    public void testListContactSuccessfullyWithUserId() throws Exception {
        Map contact1Content = new HashMap();
        contact1Content.put(TestConstants.API.DISABLED_REASON, "reason1");
        contact1Content.put(TestConstants.API.METHOD, "sms");
        contact1Content.put(TestConstants.API.TO, "1-9999999999");
        contact1Content.put(TestConstants.API.ID, "60b77613-f86f-4642-bf32-dee2aa678e4d");
        contact1Content.put(TestConstants.API.ENABLED, true);

        Map contact2Content = new HashMap();
        contact2Content.put(TestConstants.API.DISABLED_REASON, "reason2");
        contact2Content.put(TestConstants.API.METHOD, "voice");
        contact2Content.put(TestConstants.API.TO, "1-9999999998");
        contact2Content.put(TestConstants.API.ID, "6987706b-eaa9-4801-9269-94b13c2eda9a");
        contact2Content.put(TestConstants.API.ENABLED, false);

        Map contact3Content = new HashMap();
        contact3Content.put(TestConstants.API.DISABLED_REASON, "reason3");
        contact3Content.put(TestConstants.API.METHOD, "email");
        contact3Content.put(TestConstants.API.TO, "john@opsgenie.com");
        contact3Content.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        contact3Content.put(TestConstants.API.ENABLED, false);

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(userContacts: [contact1Content, contact2Content, contact3Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListContactsRequest request = new ListContactsRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().listContact(request)
        assertEquals(3, response.getContacts().size())
        Contact contact = response.getContacts().find { it.id == contact1Content[TestConstants.API.ID] }
        assertEquals(contact1Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact1Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact1Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact1Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact1Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact2Content[TestConstants.API.ID] }
        assertEquals(contact2Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact2Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact2Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact2Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact2Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact3Content[TestConstants.API.ID] }
        assertEquals(contact3Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact3Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact3Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact3Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact3Content[TestConstants.API.ENABLED], contact.getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID]);
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }
}
