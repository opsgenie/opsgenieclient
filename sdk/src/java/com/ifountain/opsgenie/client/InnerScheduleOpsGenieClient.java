package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.beans.Schedule;
import com.ifountain.opsgenie.client.model.beans.ScheduleRule;
import com.ifountain.opsgenie.client.model.beans.ScheduleRuleRestriction;
import com.ifountain.opsgenie.client.model.schedule.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ifountain.opsgenie.client.JsonOpgenieHttpClient.OpsGenieJsonResponse;

/**
 * Inner Schedule Client
 */
public class InnerScheduleOpsGenieClient implements IScheduleOpsGenieClient{
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerScheduleOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(com.ifountain.opsgenie.client.model.schedule.AddScheduleRequest)
     */
    @Override
    public AddScheduleResponse addSchedule(AddScheduleRequest addScheduleRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addScheduleRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.NAME, addScheduleRequest.getName());
        if(addScheduleRequest.getRules() != null){
            List<Map<String, Object>> ruleMaps = rulesToMap(addScheduleRequest.getRules());
            json.put(OpsGenieClientConstants.API.RULES, ruleMaps);
        }
        OpsGenieJsonResponse resp = httpClient.doPostRequest(addScheduleRequest, json);
        AddScheduleResponse response = new AddScheduleResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateSchedule(com.ifountain.opsgenie.client.model.schedule.UpdateScheduleRequest)
     */
    @Override
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest updateScheduleRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, updateScheduleRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.ID, updateScheduleRequest.getId());
        if(updateScheduleRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, updateScheduleRequest.getName());
        }
        if(updateScheduleRequest.getRules() != null){
            json.put(OpsGenieClientConstants.API.RULES, rulesToMap(updateScheduleRequest.getRules()));
        }
        OpsGenieJsonResponse resp = httpClient.doPostRequest(updateScheduleRequest, json);
        UpdateScheduleResponse response = new UpdateScheduleResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteSchedule(com.ifountain.opsgenie.client.model.schedule.DeleteScheduleRequest)
     */
    @Override
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest deleteScheduleRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, deleteScheduleRequest.getCustomerKey());
        if(deleteScheduleRequest.getId() != null){
            json.put(OpsGenieClientConstants.API.ID, deleteScheduleRequest.getId());
        }
        if(deleteScheduleRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, deleteScheduleRequest.getName());
        }
        OpsGenieJsonResponse resp = httpClient.doDeleteRequest(deleteScheduleRequest, json);
        DeleteScheduleResponse response = new DeleteScheduleResponse();
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest)
     */
    @Override
    public GetScheduleResponse getSchedule(GetScheduleRequest getScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, getScheduleRequest.getCustomerKey());
        if(getScheduleRequest.getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getScheduleRequest.getId());
        }
        if(getScheduleRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, getScheduleRequest.getName());
        }
        OpsGenieJsonResponse resp = httpClient.doGetRequest(getScheduleRequest, json);
        GetScheduleResponse response = new GetScheduleResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        Schedule schedule = createScheduleFromParameters(resp.getJson());
        response.setSchedule(schedule);
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(com.ifountain.opsgenie.client.model.schedule.ListScheduleRequest)
     */
    @Override
    public ListScheduleResponse listSchedules(ListScheduleRequest listScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, listScheduleRequest.getCustomerKey());
        OpsGenieJsonResponse resp = httpClient.doGetRequest(listScheduleRequest, json);
        ListScheduleResponse response = new ListScheduleResponse();
        List<Map> schedulesData = (List<Map>) resp.getJson().get(OpsGenieClientConstants.API.ESCALATIONS);
        List<Schedule> schedules = new ArrayList<Schedule>();
        for(Map scheduleData:schedulesData){
            Schedule schedule = createScheduleFromParameters(scheduleData);
            schedules.add(schedule);
        }
        response.setSchedules(schedules);
        return response;
    }

    private Schedule createScheduleFromParameters(Map resp) throws ParseException {
        Schedule schedule = new Schedule();
        schedule.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        schedule.setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        List<ScheduleRule> scheduleRules = new ArrayList<ScheduleRule>();
        List<Map> ruleMaps = (List<Map>) resp.get(OpsGenieClientConstants.API.RULES);
        for(Map ruleMap:ruleMaps){
            scheduleRules.add(createScheduleRuleFromParameters(ruleMap));
        }
        schedule.setRules(scheduleRules);
        return schedule;
    }
    private ScheduleRule createScheduleRuleFromParameters(Map resp) throws ParseException {
        ScheduleRule scheduleRule = new ScheduleRule();
        scheduleRule.setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        scheduleRule.setType((String) resp.get(OpsGenieClientConstants.API.TYPE));
        scheduleRule.setDelay(((Number) resp.get(OpsGenieClientConstants.API.DELAY)).intValue());
        return scheduleRule;
    }

    private ScheduleRuleRestriction createRestrictionFromParameters(Map resp) throws ParseException {
        ScheduleRuleRestriction scheduleRule = new ScheduleRuleRestriction();
        scheduleRule.setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        scheduleRule.setType((String) resp.get(OpsGenieClientConstants.API.TYPE));
        scheduleRule.setDelay(((Number) resp.get(OpsGenieClientConstants.API.DELAY)).intValue());
        return scheduleRule;
    }

    private List<Map<String, Object>> rulesToMap(List<ScheduleRule> rules) {
        List<Map<String, Object>> ruleMaps = new ArrayList<Map<String, Object>>();
        for(ScheduleRule rule:rules){
            Map<String, Object> ruleMap = new HashMap<String, Object>();
            ruleMap.put(OpsGenieClientConstants.API.NAME, rule.getName());
            ruleMap.put(OpsGenieClientConstants.API.TYPE, rule.getType());
            ruleMap.put(OpsGenieClientConstants.API.DELAY, rule.getDelay());
        }
        return ruleMaps;
    }
}
