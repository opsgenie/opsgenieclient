package com.ifountain.opsgenie.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.model.account.GetAccountInfoRequest;
import com.ifountain.opsgenie.client.model.account.GetAccountInfoResponse;
import com.ifountain.opsgenie.client.model.beans.Contact;
import com.ifountain.opsgenie.client.model.contact.*;
import com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest;
import com.ifountain.opsgenie.client.model.schedule.GetScheduleResponse;
import com.ifountain.opsgenie.client.model.schedule.IdentifierType;
import com.ifountain.opsgenie.client.util.JsonUtils;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws OpsGenieClientException, IOException, ParseException {
//        ApiClient apiClient = new ApiClient();
//        apiClient.setApiKey("GenieKey 9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        AlertApi alertApi = new AlertApi(apiClient);
//        ListAlertsRequest params = new ListAlertsRequest();
//        System.out.println(alertApi.listAlerts(params));
//        System.out.println("Hello Hello");
//
//        OpsGenieHttpClient opsGenieHttpClient = new OpsGenieHttpClient();
//        JsonOpsgenieHttpClient httpClient = new JsonOpsgenieHttpClient(opsGenieHttpClient);
//        httpClient.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        StreamOpsgenieHttpClient streamOpsgenieHttpClient = new StreamOpsgenieHttpClient(opsGenieHttpClient);
//        InnerScheduleOpsGenieClient innerScheduleOpsGenieClient = new InnerScheduleOpsGenieClient(httpClient,streamOpsgenieHttpClient);
//        GetScheduleRequest getScheduleRequest = new GetScheduleRequest();
//        getScheduleRequest.setIdentifierType("name");
//        getScheduleRequest.setIdentifier("CFN_schedule");
//        GetScheduleResponse response = innerScheduleOpsGenieClient.getSchedule(getScheduleRequest);
//        System.out.println(response.getJson());
//
//        OpsGenieClient opsGenieClient = new OpsGenieClient();
//        opsGenieClient.setRootUri("https://api.opsgenie.com");
//        GetHeartbeatRequest request = new GetHeartbeatRequest();
//        request.setName("QWtrr");
//        request.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        try {
//            System.out.println(opsGenieClient.getHeartbeat(request).getJson());
//        } catch(Exception e){
//            System.out.println("Hello Exception");
//        }
//        EnableHeartbeatRequest request = new EnableHeartbeatRequest();
//        request.setName("QWtrr");
//        request.setEnable(false);
//        request.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        System.out.println(opsGenieClient.enableHeartbeat(request).getJson());

//        OpsGenieHttpClient opsGenieHttpClient = new OpsGenieHttpClient();
//        JsonOpsgenieHttpClient httpClient = new JsonOpsgenieHttpClient(opsGenieHttpClient);
//        httpClient.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        InnerAccountOpsGenieClient innerAccountOpsGenieClient = new InnerAccountOpsGenieClient(httpClient);
//        GetAccountInfoRequest request = new GetAccountInfoRequest();
//        GetAccountInfoResponse response = innerAccountOpsGenieClient.getAccount(request);
//        System.out.println(response);

//        OpsGenieHttpClient opsGenieHttpClient = new OpsGenieHttpClient();
//        JsonOpsgenieHttpClient httpClient = new JsonOpsgenieHttpClient(opsGenieHttpClient);
//        httpClient.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        InnerContactOpsGenieClient innerContactOpsGenieClient = new InnerContactOpsGenieClient(httpClient);
//        AddContactRequest request = new AddContactRequest();
//        request.setMethod(Contact.Method.fromName("sms"));
//        request.setTo("test sms");
//        request.setUserIdentifier("e750cd98-5f6c-4fea-812d-5a51a3b40e6c");
//        AddContactResponse response = innerContactOpsGenieClient.addContact(request);
//        System.out.println(response);

//        OpsGenieHttpClient opsGenieHttpClient = new OpsGenieHttpClient();
//        JsonOpsgenieHttpClient httpClient = new JsonOpsgenieHttpClient(opsGenieHttpClient);
//        httpClient.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        InnerContactOpsGenieClient innerContactOpsGenieClient = new InnerContactOpsGenieClient(httpClient);
//        UpdateContactRequest request = new UpdateContactRequest();
//        request.setTo("atodmal1@atlassian.com");
//        request.setUserIdentifier("e750cd98-5f6c-4fea-812d-5a51a3b40e6c");
//        request.setContactId("930ce3cc-7a98-4b49-825c-fd700203259a");
//        UpdateContactResponse response = innerContactOpsGenieClient.updateContact(request);
//        System.out.println(response);

        //api key: ff898148-247e-4055-8ec0-d5573ea715a1
        OpsGenieHttpClient opsGenieHttpClient = new OpsGenieHttpClient();
        JsonOpsgenieHttpClient httpClient = new JsonOpsgenieHttpClient(opsGenieHttpClient);
        httpClient.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
        InnerContactOpsGenieClient innerContactOpsGenieClient = new InnerContactOpsGenieClient(httpClient);
        GetContactRequest request = new GetContactRequest();
        request.setUserIdentifier("e750cd98-5f6c-4fea-812d-5a51a3b40e6c");
        request.setContactId("930ce3cc-7a98-4b49-825c-fd700203259a");
        GetContactResponse response = innerContactOpsGenieClient.getContact(request);
        System.out.println(response);
//
//        OpsGenieHttpClient opsGenieHttpClient = new OpsGenieHttpClient();
//        JsonOpsgenieHttpClient httpClient = new JsonOpsgenieHttpClient(opsGenieHttpClient);
//        httpClient.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        InnerContactOpsGenieClient innerContactOpsGenieClient = new InnerContactOpsGenieClient(httpClient);
//        ListContactsRequest request = new ListContactsRequest();
//        request.setUserIdentifier("e750cd98-5f6c-4fea-812d-5a51a3b40e6c");
//        ListContactsResponse response = innerContactOpsGenieClient.listContact(request);
//        System.out.println(response);

//
//        OpsGenieHttpClient opsGenieHttpClient = new OpsGenieHttpClient();
//        JsonOpsgenieHttpClient httpClient = new JsonOpsgenieHttpClient(opsGenieHttpClient);
//        httpClient.setApiKey("9e4f6868-f8e0-4f31-8e14-ac661604d692");
//        InnerContactOpsGenieClient innerContactOpsGenieClient = new InnerContactOpsGenieClient(httpClient);
//        EnableContactRequest request = new EnableContactRequest();
//        request.setUserIdentifier("e750cd98-5f6c-4fea-812d-5a51a3b40e6c");
//        request.setContactId("930ce3cc-7a98-4b49-825c-fd700203259a");
//        EnableContactResponse response = innerContactOpsGenieClient.enableContact(request);
//        System.out.println(response);


    }
}
