package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.beans.Escalation;
import com.ifountain.opsgenie.client.model.beans.EscalationRule;
import com.ifountain.opsgenie.client.model.escalation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ifountain.opsgenie.client.JsonOpgenieHttpClient.OpsGenieJsonResponse;

/**
 * Inner Escalation Client
 */
public class InnerEscalationOpsGenieClient implements IEscalationOpsGenieClient{
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerEscalationOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IEscalationOpsGenieClient#addEscalation(com.ifountain.opsgenie.client.model.escalation.AddEscalationRequest)
     */
    @Override
    public AddEscalationResponse addEscalation(AddEscalationRequest addEscalationRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addEscalationRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.NAME, addEscalationRequest.getName());
        if(addEscalationRequest.getRules() != null){
            List<Map<String, Object>> ruleMaps = rulesToMap(addEscalationRequest.getRules());
            json.put(OpsGenieClientConstants.API.RULES, ruleMaps);
        }
        OpsGenieJsonResponse resp = httpClient.doPostRequest(addEscalationRequest, json);
        AddEscalationResponse response = new AddEscalationResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IEscalationOpsGenieClient#updateEscalation(com.ifountain.opsgenie.client.model.escalation.UpdateEscalationRequest)
     */
    @Override
    public UpdateEscalationResponse updateEscalation(UpdateEscalationRequest updateEscalationRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, updateEscalationRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.ID, updateEscalationRequest.getId());
        if(updateEscalationRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, updateEscalationRequest.getName());
        }
        if(updateEscalationRequest.getRules() != null){
            json.put(OpsGenieClientConstants.API.RULES, rulesToMap(updateEscalationRequest.getRules()));
        }
        OpsGenieJsonResponse resp = httpClient.doPostRequest(updateEscalationRequest, json);
        UpdateEscalationResponse response = new UpdateEscalationResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IEscalationOpsGenieClient#deleteEscalation(com.ifountain.opsgenie.client.model.escalation.DeleteEscalationRequest)
     */
    @Override
    public DeleteEscalationResponse deleteEscalation(DeleteEscalationRequest deleteEscalationRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, deleteEscalationRequest.getCustomerKey());
        if(deleteEscalationRequest.getId() != null){
            json.put(OpsGenieClientConstants.API.ID, deleteEscalationRequest.getId());
        }
        if(deleteEscalationRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, deleteEscalationRequest.getName());
        }
        OpsGenieJsonResponse resp = httpClient.doDeleteRequest(deleteEscalationRequest, json);
        DeleteEscalationResponse response = new DeleteEscalationResponse();
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see IEscalationOpsGenieClient#getEscalation(com.ifountain.opsgenie.client.model.escalation.GetEscalationRequest)
     */
    @Override
    public GetEscalationResponse getEscalation(GetEscalationRequest getEscalationRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, getEscalationRequest.getCustomerKey());
        if(getEscalationRequest.getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getEscalationRequest.getId());
        }
        if(getEscalationRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, getEscalationRequest.getName());
        }
        OpsGenieJsonResponse resp = httpClient.doGetRequest(getEscalationRequest, json);
        GetEscalationResponse response = new GetEscalationResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        Escalation escalation = createEscalationFromParameters(resp.getJson());
        response.setEscalation(escalation);
        return response;
    }

    /**
     * @see IEscalationOpsGenieClient#listEscalations(com.ifountain.opsgenie.client.model.escalation.ListEscalationRequest)
     */
    @Override
    public ListEscalationResponse listEscalations(ListEscalationRequest listEscalationRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, listEscalationRequest.getCustomerKey());
        OpsGenieJsonResponse resp = httpClient.doGetRequest(listEscalationRequest, json);
        ListEscalationResponse response = new ListEscalationResponse();
        List<Map> escalationsData = (List<Map>) resp.getJson().get(OpsGenieClientConstants.API.ESCALATIONS);
        List<Escalation> escalations = new ArrayList<Escalation>();
        for(Map escalationData:escalationsData){
            Escalation escalation = createEscalationFromParameters(escalationData);
            escalations.add(escalation);
        }
        response.setEscalations(escalations);
        return response;
    }

    private Escalation createEscalationFromParameters(Map resp) throws ParseException {
        Escalation escalation = new Escalation();
        escalation.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        escalation.setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        List<EscalationRule> escalationRules = new ArrayList<EscalationRule>();
        List<Map> ruleMaps = (List<Map>) resp.get(OpsGenieClientConstants.API.RULES);
        for(Map ruleMap:ruleMaps){
            escalationRules.add(createEscalationRuleFromParameters(ruleMap));
        }
        escalation.setRules(escalationRules);
        return escalation;
    }
    private EscalationRule createEscalationRuleFromParameters(Map resp) throws ParseException {
        EscalationRule escalationRule = new EscalationRule();
        escalationRule.setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        escalationRule.setType((String) resp.get(OpsGenieClientConstants.API.TYPE));
        escalationRule.setDelay(((Number) resp.get(OpsGenieClientConstants.API.DELAY)).intValue());
        return escalationRule;
    }

    private List<Map<String, Object>> rulesToMap(List<EscalationRule> rules) {
        List<Map<String, Object>> ruleMaps = new ArrayList<Map<String, Object>>();
        for(EscalationRule rule:rules){
            Map<String, Object> ruleMap = new HashMap<String, Object>();
            ruleMap.put(OpsGenieClientConstants.API.NAME, rule.getName());
            ruleMap.put(OpsGenieClientConstants.API.TYPE, rule.getType());
            ruleMap.put(OpsGenieClientConstants.API.DELAY, rule.getDelay());
        }
        return ruleMaps;
    }
}
