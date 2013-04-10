package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.beans.Forwarding;
import com.ifountain.opsgenie.client.model.beans.User;
import com.ifountain.opsgenie.client.model.user.*;
import com.ifountain.opsgenie.client.model.user.forward.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ifountain.opsgenie.client.JsonOpgenieHttpClient.*;

/**
 * Inner User Client
 */
public class InnerUserOpsGenieClient implements IUserOpsGenieClient {
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerUserOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IUserOpsGenieClient#addForwarding(com.ifountain.opsgenie.client.model.user.forward.AddForwardingRequest)
     */
    @Override
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, OpsGenieClientException {
        Map<String, String> json = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (addForwardingRequest.getTimeZone() != null) {
            sdf.setTimeZone(addForwardingRequest.getTimeZone());
            json.put(OpsGenieClientConstants.API.TIMEZONE, addForwardingRequest.getTimeZone().getID());
        }
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addForwardingRequest.getCustomerKey());
        if (addForwardingRequest.getEndDate() != null) {
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(addForwardingRequest.getEndDate()));
        }
        if (addForwardingRequest.getStartDate() != null) {
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(addForwardingRequest.getStartDate()));
        }
        json.put(OpsGenieClientConstants.API.FROM_USER, addForwardingRequest.getFromUser());
        json.put(OpsGenieClientConstants.API.TO_USER, addForwardingRequest.getToUser());
        json.put(OpsGenieClientConstants.API.ALIAS, addForwardingRequest.getAlias());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(addForwardingRequest, json);
        AddForwardingResponse response = new AddForwardingResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IUserOpsGenieClient#updateForwarding(com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest)
     */
    @Override
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, OpsGenieClientException {
        Map<String, String> json = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (updateForwardingRequest.getTimeZone() != null) {
            sdf.setTimeZone(updateForwardingRequest.getTimeZone());
            json.put(OpsGenieClientConstants.API.TIMEZONE, updateForwardingRequest.getTimeZone().getID());
        }
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, updateForwardingRequest.getCustomerKey());
        if (updateForwardingRequest.getEndDate() != null) {
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(updateForwardingRequest.getEndDate()));
        }
        if (updateForwardingRequest.getStartDate() != null) {
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(updateForwardingRequest.getStartDate()));
        }
        json.put(OpsGenieClientConstants.API.FROM_USER, updateForwardingRequest.getFromUser());
        json.put(OpsGenieClientConstants.API.TO_USER, updateForwardingRequest.getToUser());
        json.put(OpsGenieClientConstants.API.ALIAS, updateForwardingRequest.getAlias());
        json.put(OpsGenieClientConstants.API.ID, updateForwardingRequest.getId());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(updateForwardingRequest, json);
        UpdateForwardingResponse response = new UpdateForwardingResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IUserOpsGenieClient#deleteForwarding(com.ifountain.opsgenie.client.model.user.forward.DeleteForwardingRequest)
     */
    @Override
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.ID, deleteForwardingRequest.getId());
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, deleteForwardingRequest.getCustomerKey());
        OpsGenieJsonResponse resp = httpClient.doDeleteRequest(deleteForwardingRequest, json);
        DeleteForwardingResponse response = new DeleteForwardingResponse();
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;

    }

    /**
     * @see IUserOpsGenieClient#getForwarding(com.ifountain.opsgenie.client.model.user.forward.GetForwardingRequest)
     */
    @Override
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.ID, getForwardingRequest.getId());
        parameters.put(OpsGenieClientConstants.API.ALIAS, getForwardingRequest.getAlias());
        parameters.put(OpsGenieClientConstants.API.CUSTOMER_KEY, getForwardingRequest.getCustomerKey());

        OpsGenieJsonResponse jsonResponse = httpClient.doGetRequest(getForwardingRequest, parameters);
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (jsonResponse.getJson().containsKey(OpsGenieClientConstants.API.TIMEZONE)) {
            sdf.setTimeZone(TimeZone.getTimeZone((String) jsonResponse.getJson().get(OpsGenieClientConstants.API.TIMEZONE)));
        }

        GetForwardingResponse response = new GetForwardingResponse();
        response.setJson(new String(jsonResponse.getContent(), "utf-8"));
        Forwarding forwarding = createForwardingFromParameters(jsonResponse.getJson(), sdf);
        response.setTook(((Number) jsonResponse.getJson().get("took")).longValue());
        response.setForwarding(forwarding);
        return response;
    }

    /**
     * @see IUserOpsGenieClient#listForwardings(com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest)
     */
    @Override
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.USER, listForwardingsRequest.getUser());
        parameters.put(OpsGenieClientConstants.API.CUSTOMER_KEY, listForwardingsRequest.getCustomerKey());
        OpsGenieJsonResponse resp = httpClient.doGetRequest(listForwardingsRequest, parameters);
        ListForwardingsResponse response = new ListForwardingsResponse();
        List<Forwarding> forwardings = new ArrayList<Forwarding>();
        if (resp.getJson().containsKey("forwardings")) {
            List<Map> forwardingMaps = (List<Map>) resp.getJson().get("forwardings");
            for (Map forwardingMap : forwardingMaps) {
                SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
                if (forwardingMap.containsKey(OpsGenieClientConstants.API.TIMEZONE)) {
                    sdf.setTimeZone(TimeZone.getTimeZone((String) forwardingMap.get(OpsGenieClientConstants.API.TIMEZONE)));
                }
                Forwarding forwarding = createForwardingFromParameters(forwardingMap, sdf);
                forwardings.add(forwarding);
            }
        }
        response.setJson(new String(resp.getContent(), "utf-8"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        response.setForwardings(forwardings);
        return response;
    }

    /**
     * @see IUserOpsGenieClient#addUser(com.ifountain.opsgenie.client.model.user.AddUserRequest)
     */
    @Override
    public AddUserResponse addUser(AddUserRequest addUserRequest) throws IOException, OpsGenieClientException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addUserRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.USERNAME, addUserRequest.getUsername());
        json.put(OpsGenieClientConstants.API.FULLNAME, addUserRequest.getFullname());
        if(addUserRequest.getRole() != null){
            json.put(OpsGenieClientConstants.API.ROLE, addUserRequest.getRole().name());
        }
        if(addUserRequest.getTimeZone() != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, addUserRequest.getTimeZone().getID());
        }
        OpsGenieJsonResponse resp = httpClient.doPostRequest(addUserRequest, json);
        AddUserResponse response = new AddUserResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IUserOpsGenieClient#updateUser(com.ifountain.opsgenie.client.model.user.UpdateUserRequest)
     */
    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException, OpsGenieClientException {
        Map<String, String> json = new HashMap<String, String>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, updateUserRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.ID, updateUserRequest.getId());
        if(updateUserRequest.getUsername() != null){
            json.put(OpsGenieClientConstants.API.USERNAME, updateUserRequest.getUsername());
        }
        if(updateUserRequest.getFullname() != null){
            json.put(OpsGenieClientConstants.API.FULLNAME, updateUserRequest.getFullname());
        }
        if(updateUserRequest.getRole() != null){
            json.put(OpsGenieClientConstants.API.ROLE, updateUserRequest.getRole().name());
        }
        if(updateUserRequest.getTimeZone() != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, updateUserRequest.getTimeZone().getID());
        }
        OpsGenieJsonResponse resp = httpClient.doPostRequest(updateUserRequest, json);
        UpdateUserResponse response = new UpdateUserResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IUserOpsGenieClient#deleteUser(com.ifountain.opsgenie.client.model.user.DeleteUserRequest)
     */
    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, deleteUserRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.ID, deleteUserRequest.getId());
        OpsGenieJsonResponse resp = httpClient.doDeleteRequest(deleteUserRequest, json);
        DeleteUserResponse response = new DeleteUserResponse();
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IUserOpsGenieClient#getUser(com.ifountain.opsgenie.client.model.user.GetUserRequest)
     */
    @Override
    public GetUserResponse getUser(GetUserRequest getUserRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, getUserRequest.getCustomerKey());
        if(getUserRequest.getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getUserRequest.getId());
        }
        if(getUserRequest.getUsername() != null){
            json.put(OpsGenieClientConstants.API.USERNAME, getUserRequest.getUsername());
        }
        OpsGenieJsonResponse resp = httpClient.doGetRequest(getUserRequest, json);
        GetUserResponse response = new GetUserResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        User user = createUserFromParameters(resp.getJson());
        response.setUser(user);
        return response;
    }

    /**
     * @see IUserOpsGenieClient#listUsers(com.ifountain.opsgenie.client.model.user.ListUserRequest)
     */
    @Override
    public ListUserResponse listUsers(ListUserRequest listUserRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, listUserRequest.getCustomerKey());
        OpsGenieJsonResponse resp = httpClient.doGetRequest(listUserRequest, json);
        ListUserResponse response = new ListUserResponse();
        List<Map> usersData = (List<Map>) resp.getJson().get(OpsGenieClientConstants.API.USERS);
        List<User> users = new ArrayList<User>();
        for(Map userData:usersData){
            User user = createUserFromParameters(userData);
            users.add(user);
        }
        response.setUsers(users);
        return response;
    }

    private User createUserFromParameters(Map resp) throws ParseException {
        User user = new User();
        user.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        user.setUsername((String) resp.get(OpsGenieClientConstants.API.USERNAME));
        user.setFullname((String) resp.get(OpsGenieClientConstants.API.FULLNAME));
        user.setRole(User.Role.valueOf((String) resp.get(OpsGenieClientConstants.API.ROLE)));
        user.setState((String) resp.get(OpsGenieClientConstants.API.STATE));
        user.setGroups((List<String>) resp.get(OpsGenieClientConstants.API.GROUPS));
        user.setEscalations((List<String>) resp.get(OpsGenieClientConstants.API.ESCALATIONS));
        user.setSchedules((List<String>) resp.get(OpsGenieClientConstants.API.SCHEDULES));
        user.setTimeZone(TimeZone.getTimeZone((String) resp.get(OpsGenieClientConstants.API.TIMEZONE)));
        return user;
    }

    private Forwarding createForwardingFromParameters(Map resp, SimpleDateFormat sdf) throws ParseException {
        Forwarding forwarding = new Forwarding();
        forwarding.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        forwarding.setAlias((String) resp.get(OpsGenieClientConstants.API.ALIAS));
        forwarding.setFromUser((String) resp.get(OpsGenieClientConstants.API.FROM_USER));
        forwarding.setToUser((String) resp.get(OpsGenieClientConstants.API.TO_USER));
        forwarding.setTimeZone(sdf.getTimeZone());
        forwarding.setStartDate(sdf.parse((String) resp.get(OpsGenieClientConstants.API.START_DATE)));
        forwarding.setEndDate(sdf.parse((String) resp.get(OpsGenieClientConstants.API.END_DATE)));
        return forwarding;
    }
}
