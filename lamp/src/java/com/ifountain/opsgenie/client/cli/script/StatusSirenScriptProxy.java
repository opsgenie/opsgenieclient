package com.ifountain.opsgenie.client.cli.script;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.model.IBean;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.statussiren.model.Status;
import com.ifountain.client.statussiren.model.incident.*;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.script.util.ScriptBridgeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tuba Ozturk
 * @version 15.5.2014 09:46
 */
public class StatusSirenScriptProxy {
    IStatusSirenClient statusSirenClient;
    String apiKey;

    public StatusSirenScriptProxy(IStatusSirenClient statusSirenClient, String apiKey) {
        this.statusSirenClient = statusSirenClient;
        this.apiKey = apiKey;
    }

    public Map<String,Object> createIncident(Map<String,Object> params) throws Exception{
        CreateIncidentRequest request = new CreateIncidentRequest();
        populateCommonProps(request,params);
        request.setMessage(ScriptBridgeUtils.getAsString(params, ClientConstants.API.MESSAGE));
        if(params.containsKey(ClientConstants.API.STATUS)){
            String status = ScriptBridgeUtils.getAsString(params, ClientConstants.API.STATUS);
            request.setStatus(Status.findByKey(status));
        }
        if(params.containsKey(ClientConstants.API.DESCRIPTION)){
            request.setDescription(ScriptBridgeUtils.getAsString(params, ClientConstants.API.DESCRIPTION));
        }
        CreateIncidentResponse response = statusSirenClient.createIncident(request);
        Map<String,Object> responseMap = new HashMap<String, Object>();
        responseMap.put(ClientConstants.API.INCIDENT_ID,response.getIncidentId());
        return responseMap;
    }

    public Map<String,Object> resolveIncident(Map<String,Object> params) throws Exception{
        ResolveIncidentRequest request = new ResolveIncidentRequest();
        populateCommonProps(request,params);
        populateRequestWithId(request,params);
        if(params.containsKey(ClientConstants.API.MESSAGE)){
            request.setMessage(ScriptBridgeUtils.getAsString(params, ClientConstants.API.MESSAGE));
        }

        return successToMap(statusSirenClient.resolveIncident(request));
    }

    public Map<String,Object> deleteIncident(Map<String,Object> params) throws Exception{
        DeleteIncidentRequest request = new DeleteIncidentRequest();
        populateCommonProps(request,params);
        populateRequestWithId(request,params);

        return successToMap(statusSirenClient.deleteIncident(request));
    }

    public Map<String,Object> updateIncident(Map<String,Object> params) throws Exception{
        UpdateIncidentRequest request = new UpdateIncidentRequest();
        populateCommonProps(request,params);
        populateRequestWithId(request,params);

        if(params.containsKey(ClientConstants.API.MESSAGE)){
            request.setMessage(ScriptBridgeUtils.getAsString(params, ClientConstants.API.MESSAGE));
        }
        if(params.containsKey(ClientConstants.API.STATUS)){
            String status = ScriptBridgeUtils.getAsString(params, ClientConstants.API.STATUS);
            request.setStatus(Status.findByKey(status));
        }

        return successToMap(statusSirenClient.updateIncident(request));
    }

    public Map<String,Object> getIncident(Map<String,Object> params) throws Exception{
        GetIncidentRequest request = new GetIncidentRequest();
        populateCommonProps(request,params);
        populateRequestWithId(request,params);

        return  statusSirenClient.getIncident(request).getIncident().toMap();
    }

    public List<Map<String,Object>> listIncidents(Map<String,Object> params) throws Exception{
        ListIncidentsRequest request = new ListIncidentsRequest();
        populateCommonProps(request, params);
        if(params.containsKey(ClientConstants.API.CREATED_AFTER)) {
            request.setCreatedAfter(ScriptBridgeUtils.getAsLong(params, ClientConstants.API.CREATED_AFTER));
        }
        if(params.containsKey(ClientConstants.API.CREATED_BEFORE)) {
            request.setCreatedBefore(ScriptBridgeUtils.getAsLong(params, ClientConstants.API.CREATED_BEFORE));
        }
        request.setLimit(ScriptBridgeUtils.getAsInt(params, ClientConstants.API.LIMIT));
        if(params.containsKey(ClientConstants.API.ORDER)){
            request.setOrder(ListIncidentsRequest.Order.valueOf(ScriptBridgeUtils.getAsString(params, ClientConstants.API.ORDER)));
        }

        return beansToMap(statusSirenClient.listIncidents(request).getIncidents());
    }

    protected void populateCommonProps(BaseRequest request, Map params){
        String apiKeyFromParam = ScriptBridgeUtils.getAsString(params, ClientConstants.API.API_KEY);

        if(apiKeyFromParam == null){
            apiKeyFromParam = apiKey;
        }
        request.setApiKey(apiKeyFromParam);
        populateRequestWithService((BaseIncidentRequestWithService)request,params);
    }

    private void populateRequestWithService(BaseIncidentRequestWithService request, Map params){
        if(params.containsKey(ClientConstants.API.SERVICE)){
            request.setService(ScriptBridgeUtils.getAsString(params, ClientConstants.API.SERVICE));
        }
    }

    private void populateRequestWithId(BaseIncidentRequestWithId request,Map<String,Object>params){
        if(params.containsKey(ClientConstants.API.INCIDENT_ID)){
            request.setIncidentId(ScriptBridgeUtils.getAsLong(params, ClientConstants.API.INCIDENT_ID));
        }
    }

    protected Map<String,Object> successToMap(BaseResponse response){
        Map<String,Object> mapResponse = new HashMap<String,Object>();
        mapResponse.put(OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS, response.isSuccess());
        return mapResponse;
    }

    protected List<Map<String,Object>> beansToMap(List<? extends IBean> beans){
        List<Map<String,Object>> beanMaps = new ArrayList<Map<String,Object>>();
        for(IBean bean:beans){
            beanMaps.add(bean.toMap());
        }
        return beanMaps;
    }
}
