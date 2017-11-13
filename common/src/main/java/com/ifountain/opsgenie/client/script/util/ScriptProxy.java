package com.ifountain.opsgenie.client.script.util;


import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.opsgenie.oas.sdk.ApiClient;
import com.opsgenie.oas.sdk.JSON;
import com.opsgenie.oas.sdk.Pair;
import com.opsgenie.oas.sdk.api.*;
import com.opsgenie.oas.sdk.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import javax.ws.rs.core.GenericType;
import java.io.File;
import java.io.InputStream;
import java.util.*;

public class ScriptProxy {
    private ApiClient apiClient;

    private AlertApi alertApi;
    private ScheduleApi scheduleApi;
    private HeartbeatApi heartbeatApi;
    private PolicyApi policyApi;
    private IntegrationApi integrationApi;
    private EscalationApi escalationApi;
    private TeamApi teamApi;
    private UserApi userApi;
    private ForwardingRuleApi forwardingRuleApi;
    private WhoIsOnCallApi whoIsOnCallApi;

    public ScriptProxy(ApiClient apiClient) {
        this.apiClient = apiClient;
        alertApi = new AlertApi(apiClient);
        scheduleApi = new ScheduleApi(apiClient);
        heartbeatApi = new HeartbeatApi(apiClient);
        policyApi = new PolicyApi(apiClient);
        integrationApi = new IntegrationApi(apiClient);
        escalationApi = new EscalationApi(apiClient);
        userApi = new UserApi(apiClient);
        teamApi = new TeamApi(apiClient);
        forwardingRuleApi = new ForwardingRuleApi(apiClient);
        whoIsOnCallApi = new WhoIsOnCallApi(apiClient);
    }


    public Map acknowledge(Map params) throws Exception {
        AcknowledgeAlertRequest request = new AcknowledgeAlertRequest();
        AcknowledgeAlertPayload payload = new AcknowledgeAlertPayload();

        setIdentifierParameters(request, AcknowledgeAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        request.setBody(payload);
        return successToMap(alertApi.acknowledgeAlert(request));

    }


    public Map unAcknowledge(Map params) throws Exception {
        UnAcknowledgeAlertRequest request = new UnAcknowledgeAlertRequest();
        UnAcknowledgeAlertPayload payload = new UnAcknowledgeAlertPayload();


        setIdentifierParameters(request, UnAcknowledgeAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        request.setBody(payload);
        return successToMap(alertApi.unAcknowledgeAlert(request));
    }

    public Map snooze(Map params) throws Exception {
        SnoozeAlertRequest request = new SnoozeAlertRequest();
        SnoozeAlertPayload payload = new SnoozeAlertPayload();

        setIdentifierParameters(request, SnoozeAlertRequest.IdentifierTypeEnum.class, params);
        if (params.containsKey(OpsGenieClientConstants.API.END_DATE)) {
            payload.setEndTime(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.END_DATE));
        } else if (params.containsKey(OpsGenieClientConstants.API.END_TIME)) {
            payload.setEndTime(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.END_TIME));
        }
        populateCommonParameters(payload, params);

        request.setBody(payload);
        return successToMap(alertApi.snoozeAlert(request));
    }

    public Map addNote(Map params) throws Exception {
        AddNoteToAlertRequest request = new AddNoteToAlertRequest();
        AddNoteToAlertPayload payload = new AddNoteToAlertPayload();

        setIdentifierParameters(request, AddNoteToAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        request.setBody(payload);

        return successToMap(alertApi.addNote(request));
    }

    public Map addTeam(Map params) throws Exception {
        AddTeamToAlertRequest request = new AddTeamToAlertRequest();
        AddTeamToAlertPayload payload = new AddTeamToAlertPayload();

        setIdentifierParameters(request, AddTeamToAlertRequest.IdentifierTypeEnum.class, params);
        TeamRecipient teamRecipient = null;

        if (params.containsKey(OpsGenieClientConstants.API.TEAM)) {
            teamRecipient = new TeamRecipient();
            Object team = params.get(OpsGenieClientConstants.API.TEAM);

            if (team instanceof String) {
                teamRecipient.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TEAM));
            } else if (team instanceof Map) {
                JsonUtils.fromMap(teamRecipient, (Map) team);
            }
        }

        payload.setTeam(teamRecipient);


        populateCommonParameters(payload, params);

        request.setBody(payload);
        return successToMap(alertApi.addTeam(request));
    }

    public Map addTags(Map params) throws Exception {
        AddTagsToAlertRequest request = new AddTagsToAlertRequest();
        AddTagsToAlertPayload payload = new AddTagsToAlertPayload();

        setIdentifierParameters(request, AddTagsToAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        payload.setTags(parseTags(params));
        request.setBody(payload);

        return successToMap(alertApi.addTags(request));
    }

    public Map removeTags(Map params) throws Exception {
        RemoveTagsFromAlertRequest request = new RemoveTagsFromAlertRequest();

        setIdentifierParameters(request, RemoveTagsFromAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(request, params);

        request.setTags(parseTags(params));

        return successToMap(alertApi.removeTags(request));
    }

    public Map addDetails(Map params) throws Exception {
        AddDetailsToAlertRequest request = new AddDetailsToAlertRequest();
        AddDetailsToAlertPayload payload = new AddDetailsToAlertPayload();

        setIdentifierParameters(request, AddDetailsToAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        payload.setDetails(ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS));

        request.setBody(payload);

        return successToMap(alertApi.addDetails(request));
    }

    public Map removeDetails(Map params) throws Exception {
        RemoveDetailsFromAlertRequest request = new RemoveDetailsFromAlertRequest();

        setIdentifierParameters(request, RemoveDetailsFromAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(request, params);

        request.setKeys(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.KEYS));

        return successToMap(alertApi.removeDetails(request));
    }

    public Map assign(Map params) throws Exception {
        AssignAlertRequest request = new AssignAlertRequest();
        AssignAlertPayload payload = new AssignAlertPayload();

        setIdentifierParameters(request, AssignAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);
        UserRecipient userRecipient = null;
        if (params.containsKey(OpsGenieClientConstants.API.OWNER)) {
            userRecipient = new UserRecipient();
            Object owner = params.get(OpsGenieClientConstants.API.OWNER);

            if (owner instanceof String) {
                userRecipient.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OWNER));
            } else if (owner instanceof Map) {
                JsonUtils.fromMap(userRecipient, (Map) owner);
            }
        }

        payload.setOwner(userRecipient);
        request.setBody(payload);
        return successToMap(alertApi.assignAlert(request));
    }

    public Map attach(Map params) throws Exception {
        AddAttachmentToAlertRequest request = new AddAttachmentToAlertRequest();

        Map identifierParams = generateIdentifierParamsForDeprecatedAPIRequests(params);

        request.setAlertIdentifierType(getEnumFromValue(AddAttachmentToAlertRequest.AlertIdentifierTypeEnum.class, (String) identifierParams.get(OpsGenieClientConstants.API.IDENTIFIER_TYPE)));
        request.setIdentifier((String) identifierParams.get(OpsGenieClientConstants.API.IDENTIFIER));
        request.setIndexFile(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INDEX_FILE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));

        if (ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT) != null) {
            request.setFile(new File(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT)));
        } else if (params.containsKey(OpsgenieClientApplicationConstants.ScriptProxy.INPUT_STREAM) && params.containsKey(OpsgenieClientApplicationConstants.ScriptProxy.FILE_NAME)) {
            InputStream inputStream = (InputStream) params.get(OpsgenieClientApplicationConstants.ScriptProxy.INPUT_STREAM);
            String fileName = ScriptBridgeUtils.getAsString(params, OpsgenieClientApplicationConstants.ScriptProxy.FILE_NAME);
            if (fileName != null) {
                String tempDir = System.getProperty("java.io.tmpdir");
                File file;
                if (tempDir != null) {
                    if (StringUtils.endsWith(tempDir, File.separator)) {
                        file = new File(tempDir + fileName);
                    } else {
                        file = new File(tempDir + File.separator + fileName);
                    }
                    FileUtils.copyInputStreamToFile(inputStream, file);
                    file.deleteOnExit();
                    request.setFile(file);
                }
            }
        }

        return successToMap(alertApi.addAttachment(request));

    }


    public Map escalateToNext(Map params) throws Exception {
        EscalateAlertRequest request = new EscalateAlertRequest();
        EscalateAlertToNextPayload payload = new EscalateAlertToNextPayload();

        setIdentifierParameters(request, EscalateAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        EscalationRecipient escalationRecipient = new EscalationRecipient();

        if (params.containsKey(OpsGenieClientConstants.API.ESCALATION)) {
            Map<String, String> escalationMap = ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.ESCALATION);
            JsonUtils.fromMap(escalationRecipient, escalationMap);

        } else if (params.containsKey(OpsGenieClientConstants.API.ESCALATION_ID)) {
            escalationRecipient.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ESCALATION_ID));
        } else if (params.containsKey(OpsGenieClientConstants.API.ESCALATION_NAME)) {
            escalationRecipient.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ESCALATION_NAME));
        }

        payload.setEscalation(escalationRecipient);

        request.setBody(payload);
        return successToMap(alertApi.escalateAlert(request));
    }

    public Map closeAlert(Map params) throws Exception {
        CloseAlertRequest request = new CloseAlertRequest();
        CloseAlertPayload payload = new CloseAlertPayload();

        setIdentifierParameters(request, CloseAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        request.setBody(payload);

        return successToMap(alertApi.closeAlert(request));
    }

    public Map createAlert(Map params) throws Exception {
        CreateAlertPayload payload = new CreateAlertPayload();


        payload.setActions(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.ACTIONS));
        payload.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setDetails(ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS));
        payload.setEntity(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ENTITY));
        payload.setMessage(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.MESSAGE));
        payload.setPriority(ObjectUtils.defaultIfNull(CreateAlertPayload.PriorityEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.PRIORITY)), payload.getPriority()));
        payload.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));
        List<TeamRecipient> teamRecipients;
        List teamEntries = ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.TEAMS);
        if (teamEntries != null && !teamEntries.isEmpty() && teamEntries.get(0) instanceof String) {
            teamRecipients = new ArrayList<TeamRecipient>();
            for (Object teamName : teamEntries) {
                TeamRecipient teamRecipient = new TeamRecipient();
                teamRecipient.setName((String) teamName);
                teamRecipients.add(teamRecipient);
            }
        } else {
            teamRecipients = ScriptBridgeUtils.getAsObjectList(params, OpsGenieClientConstants.API.TEAMS, TeamRecipient.class);
        }
        payload.setTeams(teamRecipients);
        List<Recipient> visibleToRecipients = ScriptBridgeUtils.getAsRecipientList(params, OpsGenieClientConstants.API.VISIBLE_TO);
        payload.setVisibleTo(visibleToRecipients);

        populateCommonParameters(payload, params);

        return successToMap(alertApi.createAlert(payload));
    }


    public Map deleteAlert(Map params) throws Exception {
        DeleteAlertRequest request = new DeleteAlertRequest();
        setIdentifierParameters(request, DeleteAlertRequest.IdentifierTypeEnum.class, params);

        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(alertApi.deleteAlert(request));
    }

    public Map executeAlertAction(Map params) throws Exception {
        ExecuteCustomAlertActionRequest request = new ExecuteCustomAlertActionRequest();
        ExecuteCustomAlertActionPayload payload = new ExecuteCustomAlertActionPayload();

        setIdentifierParameters(request, ExecuteCustomAlertActionRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(payload, params);

        request.setActionName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ACTION));
        request.setBody(payload);

        return successToMap(alertApi.executeCustomAlertAction(request));
    }

    public Map getAlert(Map params) throws Exception {
        GetAlertRequest request = new GetAlertRequest();
        setIdentifierParameters(request, GetAlertRequest.IdentifierTypeEnum.class, params);

        GetAlertResponse getAlertResponse = alertApi.getAlert(request);

        Map resp = JsonUtils.toMap(getAlertResponse.getData());
        resp.put(OpsGenieClientConstants.API.REQUEST_ID, getAlertResponse.getRequestId());
        //for backward compatability
        resp.put(OpsGenieClientConstants.API.ALERT_ID, resp.get(OpsGenieClientConstants.API.ID));
        return resp;

    }

    public Map listAlertLogs(Map params) throws Exception {
        ListAlertLogsRequest request = new ListAlertLogsRequest();

        setIdentifierParameters(request, ListAlertLogsRequest.IdentifierTypeEnum.class, params);


        ListAlertLogsRequest.DirectionEnum direction = ListAlertLogsRequest.DirectionEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DIRECTION));
        if (direction != null) {
            request.setDirection(direction);
        }
        request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        request.setOffset(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OFFSET));
        ListAlertLogsRequest.OrderEnum order = ListAlertLogsRequest.OrderEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER));
        if (order != null) {
            request.setOrder(order);
        }

        ListAlertLogsResponse listAlertLogsResponse = alertApi.listLogs(request);
        Map<String, Object> res = new HashMap<String, Object>();
        if (listAlertLogsResponse.getPaging() != null) {
            Map<String, Object> paging = new HashMap<String, Object>();
            paging.put(OpsGenieClientConstants.API.FIRST, listAlertLogsResponse.getPaging().getFirst());
            paging.put(OpsGenieClientConstants.API.NEXT, listAlertLogsResponse.getPaging().getNext());
            res.put(OpsGenieClientConstants.API.PAGING, paging);
        }

        res.put(OpsGenieClientConstants.API.LOGS, beansToMap(listAlertLogsResponse.getData()));
        res.put(OpsGenieClientConstants.API.REQUEST_ID, listAlertLogsResponse.getRequestId());
        res.put(OpsGenieClientConstants.API.TOOK, listAlertLogsResponse.getTook());
        return res;

    }


    public Map listAlertRecipients(Map params) throws Exception {
        ListAlertRecipientsRequest request = new ListAlertRecipientsRequest();
        setIdentifierParameters(request, ListAlertRecipientsRequest.IdentifierTypeEnum.class, params);


        ListAlertRecipientsResponse resp = alertApi.listRecipients(request);
        Map res = new HashMap();
        res.put(OpsGenieClientConstants.API.USERS, beansToMap(resp.getData()));
        res.put(OpsGenieClientConstants.API.TOOK, resp.getTook());
        res.put(OpsGenieClientConstants.API.REQUEST_ID, resp.getRequestId());
        return res;
    }

    public Map listAlertNotes(Map params) throws Exception {
        ListAlertNotesRequest request = new ListAlertNotesRequest();
        setIdentifierParameters(request, ListAlertNotesRequest.IdentifierTypeEnum.class, params);


        ListAlertNotesRequest.DirectionEnum direction = ListAlertNotesRequest.DirectionEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DIRECTION));
        if (direction != null) {
            request.setDirection(direction);
        }
        request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        request.setOffset(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OFFSET));
        ListAlertNotesRequest.OrderEnum order = ListAlertNotesRequest.OrderEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER));
        if (order != null) {
            request.setOrder(order);
        }


        ListAlertNotesResponse listAlertNotesResponse = alertApi.listNotes(request);
        Map<String, Object> res = new HashMap<String, Object>();

        if (listAlertNotesResponse.getPaging() != null) {
            Map<String, Object> paging = new HashMap<String, Object>();
            paging.put(OpsGenieClientConstants.API.FIRST, listAlertNotesResponse.getPaging().getFirst());
            paging.put(OpsGenieClientConstants.API.NEXT, listAlertNotesResponse.getPaging().getNext());
            res.put(OpsGenieClientConstants.API.PAGING, paging);
        }

        res.put(OpsGenieClientConstants.API.NOTES, beansToMap(listAlertNotesResponse.getData()));
        res.put(OpsGenieClientConstants.API.REQUEST_ID, listAlertNotesResponse.getRequestId());
        res.put(OpsGenieClientConstants.API.TOOK, listAlertNotesResponse.getTook());
        return res;
    }


    public List<Map> listAlerts(Map params) throws Exception {
        ListAlertsRequest request = new ListAlertsRequest();

        request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        request.setOffset(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.OFFSET));
        ListAlertsRequest.OrderEnum order = ListAlertsRequest.OrderEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER));
        if (order != null) {
            request.setOrder(order);
        }
        request.setQuery(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.QUERY));
        request.setSearchIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SEARCH_IDENTIFIER));
        ListAlertsRequest.SearchIdentifierTypeEnum searchIdentifierType = ListAlertsRequest.SearchIdentifierTypeEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SEARCH_IDENTIFIER_TYPE));
        if (searchIdentifierType != null) {
            request.setSearchIdentifierType(searchIdentifierType);
        }
        ListAlertsRequest.SortEnum sort = ObjectUtils.defaultIfNull(ListAlertsRequest.SortEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT)), ListAlertsRequest.SortEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT_BY)));
        if (sort != null) {
            request.setSort(sort);
        }
        return beansToMap(alertApi.listAlerts(request).getData());
    }

    public Map addSavedSearch(Map params) throws Exception {
        CreateSavedSearchPayload payload = new CreateSavedSearchPayload();
        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        payload.setQuery(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.QUERY));
        UserRecipient owner = new UserRecipient();
        JsonUtils.fromMap(owner, ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.OWNER));
        payload.setOwner(owner);
        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setTeams(ScriptBridgeUtils.getAsObjectList(params, OpsGenieClientConstants.API.TEAMS, TeamRecipient.class));

        return JsonUtils.toMap(alertApi.createSavedSearches(payload));
    }

    public Map deleteSavedSearch(Map params) throws Exception {
        DeleteSavedSearchRequest request = new DeleteSavedSearchRequest();
        setIdentifierParameters(request, DeleteSavedSearchRequest.IdentifierTypeEnum.class, params);
        return successToMap(alertApi.deleteSavedSearch(request));
    }

    public Map getRequestStatus(Map params) throws Exception {
        String requestId = ObjectUtils.defaultIfNull(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.REQUEST_ID), ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));

        return JsonUtils.toMap(alertApi.getRequestStatus(requestId));
    }

    public Map getSavedSearch(Map params) throws Exception {
        GetSavedSearchRequest request = new GetSavedSearchRequest();
        setIdentifierParameters(request, GetSavedSearchRequest.IdentifierTypeEnum.class, params);
        return JsonUtils.toMap(alertApi.getSavedSearch(request));
    }

    public Map listSavedSearch() throws Exception {
        return JsonUtils.toMap(alertApi.listSavedSearches());
    }

    public Map heartbeat(Map params) throws Exception {
        String heartbeatName = getHeartbeatName(params);
        return successToMap(heartbeatApi.ping(heartbeatName));
    }

    public Map deleteHeartbeat(Map params) throws Exception {
        String heartbeatName = getHeartbeatName(params);
        return successToMap(heartbeatApi.deleteHeartbeat(heartbeatName));
    }

    public Map enableHeartbeat(Map params) throws Exception {
        Boolean enabled = ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLE);
        if (enabled != null && !enabled) {
            return disableHeartbeat(params);
        }
        String heartbeatName = getHeartbeatName(params);

        return JsonUtils.toMap(heartbeatApi.enableHeartbeat(heartbeatName));
    }


    public Map disableHeartbeat(Map params) throws Exception {
        String heartbeatName = getHeartbeatName(params);

        return JsonUtils.toMap(heartbeatApi.disableHeartbeat(heartbeatName));
    }

    public Map addHeartbeat(Map params) throws Exception {
        CreateHeartbeatPayload payload = new CreateHeartbeatPayload();

        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        payload.setInterval(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.INTERVAL));
        CreateHeartbeatPayload.IntervalUnitEnum intervalUnit = CreateHeartbeatPayload.IntervalUnitEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INTERVAL_UNIT));
        if (intervalUnit != null) {
            payload.setIntervalUnit(intervalUnit);
        }
        payload.setName(getHeartbeatName(params));

        return JsonUtils.toMap(heartbeatApi.createHeartbeat(payload));
    }

    public Map updateHeartbeat(Map params) throws Exception {
        UpdateHeartbeatRequest request = new UpdateHeartbeatRequest();
        UpdateHeartbeatPayload payload = new UpdateHeartbeatPayload();

        request.setName(getHeartbeatName(params));
        payload.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        payload.setInterval(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.INTERVAL));
        UpdateHeartbeatPayload.IntervalUnitEnum intervalUnit = UpdateHeartbeatPayload.IntervalUnitEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INTERVAL_UNIT));
        if (intervalUnit != null) {
            payload.setIntervalUnit(intervalUnit);
        }
        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        request.setBody(payload);

        return JsonUtils.toMap(heartbeatApi.updateHeartbeat(request));
    }


    public Map getHeartbeat(Map params) throws Exception {
        String heartbeatName = getHeartbeatName(params);
        return JsonUtils.toMap(heartbeatApi.getHeartbeat(heartbeatName));
    }

    @Deprecated
    public Map enableAutomation(Map params) throws Exception {
        return enableAlertPolicy(params);
    }

    public Map enableAlertPolicy(Map params) throws Exception {
        Boolean enabled = ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED);
        if (enabled != null && !enabled) {
            return disableAlertPolicy(params);
        }
        String policyId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);

        return successToMap(policyApi.enableAlertPolicy(policyId));
    }

    public Map disableAlertPolicy(Map params) throws Exception {
        String policyId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);

        return successToMap(policyApi.disableAlertPolicy(policyId));
    }

    public Map enableIntegration(Map params) throws Exception {
        Boolean enabled = ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED);
        if (enabled != null && !enabled) {
            return disableIntegration(params);
        }
        String integrationId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);
        return JsonUtils.toMap(integrationApi.enableIntegration(integrationId));

    }

    public Map disableIntegration(Map params) throws Exception {
        String integrationId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);
        return JsonUtils.toMap(integrationApi.disableIntegration(integrationId));

    }

    public Map sendToIntegration(String endPoint, Map contentParams, Map httpParams) throws Exception {
        List<Pair> queryParams = new ArrayList<Pair>();
        for (Object key : httpParams.keySet()) {
            queryParams.add(new Pair((String) key, (String) httpParams.get(key)));
        }
        if (!httpParams.containsKey(OpsGenieClientConstants.API.API_KEY)) {
            Map<String, String> credentials = new HashMap<String, String>();
            apiClient.getAuthentication("GenieKey").applyToParams(null, credentials);
            queryParams.add(new Pair(OpsGenieClientConstants.API.API_KEY, StringUtils.substringAfter(credentials.get(OpsGenieClientConstants.API.AUTHORIZATION), OpsGenieClientConstants.API.GENIE_KEY).trim()));
        }
        return JsonUtils.toMap(apiClient.invokeAPI(endPoint, "POST", queryParams, contentParams, new HashMap<String, String>(), null, "application/json", "application/json", new String[]{}, new GenericType<Object>() {
        }));
    }

    public Map addEscalation(Map params) throws Exception {
        CreateEscalationPayload payload = new CreateEscalationPayload();

        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setOwnerTeam(parseOwnerTeamBackwardsCompatible(params));
        payload.setRules(parseEscalationRules(params));

        return successToMap(escalationApi.createEscalation(payload));
    }

    public Map deleteEscalation(Map params) throws Exception {
        DeleteEscalationRequest request = new DeleteEscalationRequest();

        setIdentifierParameters(request, DeleteEscalationRequest.IdentifierTypeEnum.class, params);

        return successToMap(escalationApi.deleteEscalation(request));
    }

    public Map getEscalation(Map params) throws Exception {
        GetEscalationRequest request = new GetEscalationRequest();
        setIdentifierParameters(request, GetEscalationRequest.IdentifierTypeEnum.class, params);

        return JsonUtils.toMap(escalationApi.getEscalation(request).getData());
    }

    public List<Map> listEscalations(Map params) throws Exception {

        return beansToMap(escalationApi.listEscalations().getData());
    }

    public Map updateEscalation(Map params) throws Exception {
        UpdateEscalationRequest request = new UpdateEscalationRequest();
        UpdateEscalationPayload payload = new UpdateEscalationPayload();
        setIdentifierParameters(request, UpdateEscalationRequest.IdentifierTypeEnum.class, params);

        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setOwnerTeam(parseOwnerTeamBackwardsCompatible(params));
        payload.setRules(parseEscalationRules(params));

        request.setBody(payload);

        return successToMap(escalationApi.updateEscalation(request));
    }


    public Map createTeam(Map params) throws Exception {
        CreateTeamPayload payload = new CreateTeamPayload();

        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setMembers(parseTeamMembers(params));

        return successToMap(teamApi.createTeam(payload));
    }

    public Map getTeam(Map params) throws Exception {
        GetTeamRequest request = new GetTeamRequest();
        setIdentifierParameters(request, GetTeamRequest.IdentifierTypeEnum.class, params);

        return JsonUtils.toMap(teamApi.getTeam(request).getData());
    }

    public Map updateTeam(Map params) throws Exception {
        UpdateTeamRequest request = new UpdateTeamRequest();
        UpdateTeamPayload payload = new UpdateTeamPayload();


        request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TEAM_ID));
        if (request.getIdentifier() == null) {
            request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER));
        }

        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        payload.setMembers(parseTeamMembers(params));


        request.setBody(payload);

        return successToMap(teamApi.updateTeam(request));
    }

    public Map deleteTeam(Map params) throws Exception {
        DeleteTeamRequest request = new DeleteTeamRequest();

        setIdentifierParameters(request, DeleteTeamRequest.IdentifierTypeEnum.class, params);
        return successToMap(teamApi.deleteTeam(request));
    }

    public List<Map> listTeams(Map params) throws Exception {
        List<String> expand = getExpand(params);
        return beansToMap(teamApi.listTeams(expand).getData());
    }

    public List<Map> listTeamLogs(Map params) throws Exception {
        ListTeamLogsRequest request = new ListTeamLogsRequest();

        setIdentifierParameters(request, ListTeamLogsRequest.IdentifierTypeEnum.class, params);

        request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        request.setOffset(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OFFSET));
        ListTeamLogsRequest.OrderEnum order = ListTeamLogsRequest.OrderEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER));
        if (order != null) {
            request.setOrder(order);
        }

        return beansToMap(teamApi.listTeamLogs(request).getData().getLogs());
    }

    public Map addUser(Map params) throws Exception {
        updateDeprecatedUserInfo(params);
        CreateUserPayload payload = new CreateUserPayload();
        JsonUtils.fromMap(payload, params);
        return successToMap(userApi.createUser(payload));
    }

    public Map deleteUser(Map params) throws Exception {
        String userId = getUserIdentifier(params);

        return successToMap(userApi.deleteUser(userId));
    }

    public Map getUser(Map params) throws Exception {
        GetUserRequest request = new GetUserRequest();
        String userId = getUserIdentifier(params);

        request.setIdentifier(userId);
        request.setExpand(getExpand(params));

        return JsonUtils.toMap(userApi.getUser(request).getData());
    }

    public List<Map> listUsers(Map params) throws Exception {
        updateDeprecatedUserInfo(params);
        ListUsersRequest request = new ListUsersRequest();
        request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        request.setOffset(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.OFFSET));
        request.setQuery(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.QUERY));
        String order = ObjectUtils.defaultIfNull(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER), ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT_ORDER));
        ListUsersRequest.OrderEnum orderEnum = ListUsersRequest.OrderEnum.fromValue(order);
        if (orderEnum != null) {
            request.setOrder(orderEnum);
        }
        String sort = ObjectUtils.defaultIfNull(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT), ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT_FIELD));
        request.setSortField(sort);

        return beansToMap(userApi.listUsers(request).getData());
    }

    public Map updateUser(Map params) throws Exception {
        updateDeprecatedUserInfo(params);
        UpdateUserRequest request = new UpdateUserRequest();
        UpdateUserPayload payload = new UpdateUserPayload();

        request.setIdentifier(getUserIdentifier(params));

        removeIdentifierParams(params);

        JsonUtils.fromMap(payload, params);
        request.setBody(payload);

        return successToMap(userApi.updateUser(request));
    }

    //backward sikinti
    public Map addForwarding(Map params) throws Exception {
        CreateForwardingRulePayload payload = new CreateForwardingRulePayload();
        payload.setFromUser(parseUserMetaBackwardsCompatible(params.get(OpsGenieClientConstants.API.FROM_USER)));
        payload.setToUser(parseUserMetaBackwardsCompatible(params.get(OpsGenieClientConstants.API.TO_USER)));
        payload.setStartDate(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.START_DATE));
        payload.setEndDate(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.END_DATE));
        payload.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));

        return JsonUtils.toMap(forwardingRuleApi.createForwardingRule(payload));
    }

    public Map deleteForwarding(Map params) throws Exception {
        DeleteForwardingRuleRequest request = new DeleteForwardingRuleRequest();
        setIdentifierParameters(request, DeleteForwardingRuleRequest.IdentifierTypeEnum.class, params);

        return successToMap(forwardingRuleApi.deleteForwardingRule(request));
    }

    public Map getForwarding(Map params) throws Exception {
        GetForwardingRuleRequest request = new GetForwardingRuleRequest();
        setIdentifierParameters(request, GetForwardingRuleRequest.IdentifierTypeEnum.class, params);

        return JsonUtils.toMap(forwardingRuleApi.getForwardingRule(request).getData());
    }

    public List<Map> listForwardings(Map params) throws Exception {
        return beansToMap(forwardingRuleApi.listForwardingRules().getData());
    }

    public Map updateForwarding(Map params) throws Exception {
        UpdateForwardingRuleRequest request = new UpdateForwardingRuleRequest();
        UpdateForwardingRulePayload payload = new UpdateForwardingRulePayload();

        setIdentifierParameters(request, UpdateForwardingRuleRequest.IdentifierTypeEnum.class, params);

        payload.setFromUser(parseUserMetaBackwardsCompatible(params.get(OpsGenieClientConstants.API.FROM_USER)));
        payload.setToUser(parseUserMetaBackwardsCompatible(params.get(OpsGenieClientConstants.API.TO_USER)));
        payload.setStartDate(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.START_DATE));
        payload.setEndDate(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.END_DATE));

        request.setBody(payload);

        return successToMap(forwardingRuleApi.updateForwardingRule(request));
    }

    public Map addSchedule(Map params) throws Exception {
        CreateSchedulePayload payload = new CreateSchedulePayload();


        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        payload.setOwnerTeam(parseOwnerTeamBackwardsCompatible(params));
        payload.setTimezone(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TIMEZONE));

        payload.setRotations(parseRotations(params));

        return JsonUtils.toMap(scheduleApi.createSchedule(payload));
    }


    public Map deleteSchedule(Map params) throws Exception {
        DeleteScheduleRequest request = new DeleteScheduleRequest();
        setIdentifierParameters(request, DeleteScheduleRequest.IdentifierTypeEnum.class, params);

        return successToMap(scheduleApi.deleteSchedule(request));
    }

    public Map getSchedule(Map params) throws Exception {
        GetScheduleRequest request = new GetScheduleRequest();

        setIdentifierParameters(request, GetScheduleRequest.IdentifierTypeEnum.class, params);

        return JsonUtils.toMap(scheduleApi.getSchedule(request).getData());
    }


    public List<Map> listSchedules(Map params) throws Exception {

        List<String> expand = getExpand(params);
        return beansToMap(scheduleApi.listSchedules(expand).getData());
    }

    public Map updateSchedule(Map params) throws Exception {
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        UpdateSchedulePayload payload = new UpdateSchedulePayload();


        setIdentifierParameters(request, UpdateScheduleRequest.IdentifierTypeEnum.class, params);

        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        payload.setOwnerTeam(parseOwnerTeamBackwardsCompatible(params));
        payload.setTimezone(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TIMEZONE));

        payload.setRotations(parseRotations(params));

        request.setBody(payload);


        return JsonUtils.toMap(scheduleApi.updateSchedule(request));
    }

    public Map whoIsOnCall(Map params) throws Exception {
        GetOnCallRequest request = new GetOnCallRequest();

        if (params.containsKey(OpsGenieClientConstants.API.SCHEDULE_IDENTIFIER)) {
            request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SCHEDULE_IDENTIFIER));
            GetOnCallRequest.ScheduleIdentifierTypeEnum scheduleIdentifierType = GetOnCallRequest.ScheduleIdentifierTypeEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SCHEDULE_IDENTIFIER_TYPE));
            if (scheduleIdentifierType != null) {
                request.setScheduleIdentifierType(scheduleIdentifierType);
            }
        } else if (params.containsKey(OpsGenieClientConstants.API.ID)) {
            request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
            request.setScheduleIdentifierType(GetOnCallRequest.ScheduleIdentifierTypeEnum.ID);
        } else if (params.containsKey(OpsGenieClientConstants.API.NAME)) {
            request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
            request.setScheduleIdentifierType(GetOnCallRequest.ScheduleIdentifierTypeEnum.NAME);
        }

        request.setDate(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.DATE));
        request.setFlat(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.FLAT));

        return JsonUtils.toMap(whoIsOnCallApi.getOnCalls(request).getData());
    }


    public Map flatWhoIsOnCall(Map params) throws Exception {
        params.put(OpsGenieClientConstants.API.FLAT, true);
        return whoIsOnCall(params);
    }

    public List<Map> listWhoIsOnCall(Map params) throws Exception {
        ListOnCallsRequest request = new ListOnCallsRequest();
        request.setDate(ObjectUtils.defaultIfNull(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.DATE), ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.TIME)));

        request.setFlat(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.FLAT));


        return beansToMap(whoIsOnCallApi.listOnCalls(request).getData());
    }


    private Map successToMap(Object response) throws Exception {
        Map mapResponse = new HashMap();

        if (response instanceof SuccessResponse) {
            return JsonUtils.toMap(response);
        } else {
            mapResponse.put(OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS, false);
        }

        return mapResponse;
    }

    private List<Map> beansToMap(Iterable beans) {
        List<Map> beanMaps = new ArrayList<Map>();
        for (Object bean : beans) {
            try {
                beanMaps.add(JsonUtils.toMap(bean));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return beanMaps;
    }

    private static void updateIdentifierParamsIfDeprecated(Map params) {
        if (!params.containsKey(OpsGenieClientConstants.API.IDENTIFIER)) {
            params.putAll(generateIdentifierParamsForDeprecatedAPIRequests(params));
        }
    }

    private static Map generateIdentifierParamsForDeprecatedAPIRequests(Map params) {
        Map<String, String> identifierParams = new HashMap<String, String>();
        String identifier = null;
        String identifierType = null;
        if (params.containsKey(OpsGenieClientConstants.API.ALERT_ID)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID);
            identifierType = OpsGenieClientConstants.API.ID;
        } else if (params.containsKey(OpsGenieClientConstants.API.TINY_ID)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TINY_ID);
            identifierType = OpsGenieClientConstants.API.TINY;
        } else if (params.containsKey(OpsGenieClientConstants.API.ID)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID);
            identifierType = OpsGenieClientConstants.API.ID;
        } else if (params.containsKey(OpsGenieClientConstants.API.ALIAS)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS);
            identifierType = OpsGenieClientConstants.API.ALIAS;
        } else if (params.containsKey(OpsGenieClientConstants.API.NAME)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
            identifierType = OpsGenieClientConstants.API.NAME;
        }
        identifierParams.put(OpsGenieClientConstants.API.IDENTIFIER, identifier);
        identifierParams.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE, identifierType);
        return identifierParams;
    }

    public static void removeIdentifierParams(Map params) {
        params.remove(OpsGenieClientConstants.API.IDENTIFIER);
        params.remove(OpsGenieClientConstants.API.IDENTIFIER_TYPE);
        params.remove(OpsGenieClientConstants.API.ID);
        params.remove(OpsGenieClientConstants.API.ALERT_ID);
        params.remove(OpsGenieClientConstants.API.TINY_ID);
        params.remove(OpsGenieClientConstants.API.ALIAS);
        params.remove(OpsGenieClientConstants.API.USERNAME);
    }


    private static <T extends Enum<T>> T getEnumFromValue(Class<T> enumType, String value) {
        for (T enumItem : EnumSet.allOf(enumType)) {
            if (enumItem.toString().equals(value)) {
                return enumItem;
            }
        }
        return null;
    }

    private static <T> void populateCommonParameters(T object, Map params) throws Exception {
        MethodUtils.invokeMethod(object, "setNote", ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        MethodUtils.invokeMethod(object, "setUser", ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        MethodUtils.invokeMethod(object, "setSource", ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
    }

    private static List<CreateScheduleRotationPayload> parseRotations(Map params) throws Exception {
        List<CreateScheduleRotationPayload> scheduleRotations = new ArrayList<CreateScheduleRotationPayload>();
        List<Map> scheduleRotationEntries = ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.ROTATIONS);

        if (scheduleRotationEntries != null) {
            for (Map rotationEntry : scheduleRotationEntries) {
                CreateScheduleRotationPayload rotation = new CreateScheduleRotationPayload();
                rotation.setName(ScriptBridgeUtils.getAsString(rotationEntry, OpsGenieClientConstants.API.NAME));
                rotation.setLength(ScriptBridgeUtils.getAsInt(rotationEntry, OpsGenieClientConstants.API.LENGTH));
                rotation.setStartDate(ScriptBridgeUtils.getAsDateTime(rotationEntry, OpsGenieClientConstants.API.START_DATE));
                rotation.setEndDate(ScriptBridgeUtils.getAsDateTime(rotationEntry, OpsGenieClientConstants.API.END_DATE));
                rotation.setType(ObjectUtils.defaultIfNull(CreateScheduleRotationPayload.TypeEnum.fromValue(ScriptBridgeUtils.getAsString(rotationEntry, OpsGenieClientConstants.API.TYPE)), rotation.getType()));

                rotation.setParticipants(ScriptBridgeUtils.getAsRecipientList(rotationEntry, OpsGenieClientConstants.API.PARTICIPANTS));


                Map timeRestrictionIntervalEntry = ScriptBridgeUtils.getAsMap(rotationEntry, OpsGenieClientConstants.API.TIME_RESTRICTION);
                if (timeRestrictionIntervalEntry != null) {

                    TimeRestrictionInterval.TypeEnum timeRestrictionType = TimeRestrictionInterval.TypeEnum.fromValue(ScriptBridgeUtils.getAsString(timeRestrictionIntervalEntry, OpsGenieClientConstants.API.TYPE));
                    if (timeRestrictionType != null) {
                        if (timeRestrictionType.getValue().equals(TimeRestrictionInterval.TypeEnum.WEEKDAY_AND_TIME_OF_DAY.getValue())) {
                            WeekdayTimeRestrictionInterval weekdayTimeRestrictionInterval = new WeekdayTimeRestrictionInterval();
                            List<Map> weekDayTimeRestrictionEntries = ScriptBridgeUtils.getAsList(timeRestrictionIntervalEntry, OpsGenieClientConstants.API.RESTRICTIONS);
                            if (weekDayTimeRestrictionEntries != null) {
                                List<WeekdayTimeRestriction> restrictions = new ArrayList<WeekdayTimeRestriction>();
                                for (Map weekDayTimeRestrictionEntry : weekDayTimeRestrictionEntries) {
                                    WeekdayTimeRestriction weekdayTimeRestriction = new WeekdayTimeRestriction();
                                    weekdayTimeRestriction.setStartDay(ObjectUtils.defaultIfNull(WeekdayTimeRestriction.StartDayEnum.fromValue(ScriptBridgeUtils.getAsString(weekDayTimeRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_START_DAY)), weekdayTimeRestriction.getStartDay()));
                                    weekdayTimeRestriction.setEndDay(ObjectUtils.defaultIfNull(WeekdayTimeRestriction.EndDayEnum.fromValue(ScriptBridgeUtils.getAsString(weekDayTimeRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_END_DAY)), weekdayTimeRestriction.getEndDay()));
                                    weekdayTimeRestriction.setStartHour(ScriptBridgeUtils.getAsInt(weekDayTimeRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_START_HOUR));
                                    weekdayTimeRestriction.setEndHour(ScriptBridgeUtils.getAsInt(weekDayTimeRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_END_HOUR));
                                    weekdayTimeRestriction.setStartMin(ScriptBridgeUtils.getAsInt(weekDayTimeRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_START_MINUTE));
                                    weekdayTimeRestriction.setEndMin(ScriptBridgeUtils.getAsInt(weekDayTimeRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_END_MINUTE));
                                    restrictions.add(weekdayTimeRestriction);
                                }
                                weekdayTimeRestrictionInterval.setRestrictions(restrictions);
                                rotation.setTimeRestriction(weekdayTimeRestrictionInterval);
                            }
                        } else if (timeRestrictionType.getValue().equals(TimeRestrictionInterval.TypeEnum.TIME_OF_DAY.getValue())) {
                            Map timeOfDayRestrictionEntry = ScriptBridgeUtils.getAsMap(timeRestrictionIntervalEntry, OpsGenieClientConstants.API.RESTRICTION);
                            TimeOfDayRestrictionInterval timeOfDayRestrictionInterval = new TimeOfDayRestrictionInterval();
                            TimeOfDayRestriction timeOfDayRestriction = new TimeOfDayRestriction();
                            timeOfDayRestriction.setStartHour(ScriptBridgeUtils.getAsInt(timeOfDayRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_START_HOUR));
                            timeOfDayRestriction.setEndHour(ScriptBridgeUtils.getAsInt(timeOfDayRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_END_HOUR));
                            timeOfDayRestriction.setStartMin(ScriptBridgeUtils.getAsInt(timeOfDayRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_START_MINUTE));
                            timeOfDayRestriction.setEndMin(ScriptBridgeUtils.getAsInt(timeOfDayRestrictionEntry, OpsGenieClientConstants.API.RESTRICTION_END_MINUTE));
                            timeOfDayRestrictionInterval.setRestriction(timeOfDayRestriction);
                            rotation.setTimeRestriction(timeOfDayRestrictionInterval);
                        }

                    }
                }


                scheduleRotations.add(rotation);
            }
        }

        return scheduleRotations.isEmpty() ? null : scheduleRotations;
    }

    private static <T, S extends Enum<S>> void setIdentifierParameters(T object, Class<S> enumType, Map params) throws Exception {
        updateIdentifierParamsIfDeprecated(params);
        MethodUtils.invokeMethod(object, "setIdentifier", ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER));
        MethodUtils.invokeMethod(object, "setIdentifierType", ObjectUtils.defaultIfNull(getEnumFromValue(enumType, ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER_TYPE)), MethodUtils.invokeMethod(object, "getIdentifierType")));
    }

    private static String getHeartbeatName(Map params) {
        if (params.containsKey(OpsGenieClientConstants.API.HEARTBEAT_NAME)) {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.HEARTBEAT_NAME);
        } else if (params.containsKey(OpsGenieClientConstants.API.NAME)) {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
        } else if (params.containsKey(OpsGenieClientConstants.API.SOURCE)) {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE);
        }
        return null;
    }


    private static List<EscalationRule> parseEscalationRules(Map params) throws Exception {

        List<EscalationRule> escalationRules = new ArrayList<EscalationRule>();
        List<Map> objectList = ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.RULES);

        if (objectList != null) {
            for (Map ruleEntry : objectList) {
                EscalationRule rule = new EscalationRule();
                EscalationRule.ConditionEnum condition = EscalationRule.ConditionEnum.fromValue(ScriptBridgeUtils.getAsString(ruleEntry, OpsGenieClientConstants.API.CONDITION));
                if (condition != null) {
                    rule.setCondition(condition);
                }
                EscalationRule.NotifyTypeEnum notifyType = EscalationRule.NotifyTypeEnum.fromValue(ScriptBridgeUtils.getAsString(ruleEntry, OpsGenieClientConstants.API.NOTIFY_TYPE));
                if (notifyType != null) {
                    rule.setNotifyType(notifyType);
                }
                Map recipientMap = ScriptBridgeUtils.getAsMap(ruleEntry, OpsGenieClientConstants.API.RECIPIENT);
                rule.setRecipient(ScriptBridgeUtils.getAsRecipient(recipientMap));

                Map durationMap = ScriptBridgeUtils.getAsMap(ruleEntry, OpsGenieClientConstants.API.DELAY);
                Duration duration = new Duration();
                duration.setTimeAmount(ScriptBridgeUtils.getAsLong(durationMap, OpsGenieClientConstants.API.TIME_AMOUNT));
                Duration.TimeUnitEnum timeUnit = Duration.TimeUnitEnum.fromValue(ScriptBridgeUtils.getAsString(durationMap, OpsGenieClientConstants.API.TIME_UNIT));
                if (timeUnit != null) {
                    duration.setTimeUnit(timeUnit);
                }
                rule.setDelay(duration);
                escalationRules.add(rule);
            }
        }

        return escalationRules;
    }


    private static TeamMeta parseOwnerTeamBackwardsCompatible(Map params) throws Exception {
        TeamMeta teamMeta = new TeamMeta();

        if (params.containsKey(OpsGenieClientConstants.API.OWNER_TEAM)) {
            Map team = ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.OWNER_TEAM);
            JsonUtils.fromMap(teamMeta, team);
            return teamMeta;
        } else if (params.containsKey(OpsGenieClientConstants.API.TEAM)) {
            teamMeta.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TEAM));
            return teamMeta;
        }

        return null;
    }

    private static List<TeamMember> parseTeamMembers(Map params) throws Exception {
        List<TeamMember> teamMembers = new ArrayList<TeamMember>();
        List<Map> objectList = ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.MEMBERS);

        if (objectList != null) {
            for (Map memberEntry : objectList) {
                TeamMember teamMember = new TeamMember();
                TeamMember.RoleEnum role = getEnumFromValue(TeamMember.RoleEnum.class, ScriptBridgeUtils.getAsString(memberEntry, OpsGenieClientConstants.API.ROLE));
                if (role != null) {
                    teamMember.setRole(role);
                }
                UserMeta userMeta = new UserMeta();
                JsonUtils.fromMap(userMeta, ScriptBridgeUtils.getAsMap(memberEntry, OpsGenieClientConstants.API.USER));
                teamMember.setUser(userMeta);
                teamMembers.add(teamMember);
            }
        }
        return teamMembers.isEmpty() ? null : teamMembers;
    }

    private static List<String> getExpand(Map params) {
        String expandRaw = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.EXPAND);
        List<String> expand = null;
        if (expandRaw != null) {
            expand = new ArrayList<String>(Arrays.asList((expandRaw).split(",")));
        }
        return expand;
    }

    private static String getUserIdentifier(Map params) {
        String userId = null;
        if (params.containsKey(OpsGenieClientConstants.API.IDENTIFIER)) {
            userId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);
        } else if (params.containsKey(OpsGenieClientConstants.API.ID)) {
            userId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID);
        } else if (params.containsKey(OpsGenieClientConstants.API.USERNAME)) {
            userId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USERNAME);
        }
        return userId;
    }

    private static UserMeta parseUserMetaBackwardsCompatible(Object userMetaEntry) throws Exception {
        if (userMetaEntry == null) {
            return null;
        }
        UserMeta userMeta = new UserMeta();

        if (userMetaEntry instanceof String) {
            userMeta.setUsername((String) userMetaEntry);
        } else if (userMetaEntry instanceof Map) {
            JsonUtils.fromMap(userMeta, (Map) userMetaEntry);
        } else {
            return null;
        }

        return userMeta;
    }

    private static List<String> parseTags(Map params) {
        if (params.containsKey(OpsGenieClientConstants.API.TAGS)) {
            Object tags = params.get(OpsGenieClientConstants.API.TAGS);

            if (tags instanceof List) {
                return (List) tags;
            } else if (tags instanceof String) {
                return new ArrayList<String>(Arrays.asList(((String) tags).split(",")));
            }
        }
        return null;
    }

    private static void updateDeprecatedUserInfo(Map params) {
        if (params.get(OpsGenieClientConstants.API.ROLE) instanceof String) {
            Map userRole = new HashMap<String, String>();
            userRole.put(OpsGenieClientConstants.API.NAME, params.get(OpsGenieClientConstants.API.ROLE));
            params.put(OpsGenieClientConstants.API.ROLE, userRole);
        }
        if (params.containsKey(OpsGenieClientConstants.API.FULLNAME)) {
            params.put(OpsGenieClientConstants.API.FULLNAME_CAMEL_CASE, params.remove(OpsGenieClientConstants.API.FULLNAME));
        }
        if (params.containsKey(OpsGenieClientConstants.API.TIMEZONE)) {
            params.put(OpsGenieClientConstants.API.TIMEZONE_CAMEL_CASE, params.remove(OpsGenieClientConstants.API.TIMEZONE));
        }
        if (params.containsKey(OpsGenieClientConstants.API.SORT)) {
            params.put(OpsGenieClientConstants.API.SORT_FIELD, params.remove(OpsGenieClientConstants.API.SORT));
        }
        if (params.containsKey(OpsGenieClientConstants.API.ORDER)) {
            params.put(OpsGenieClientConstants.API.SORT_ORDER, params.remove(OpsGenieClientConstants.API.ORDER));
        }
    }


    public AlertApi getAlertApi() {
        return alertApi;
    }

    public void setAlertApi(AlertApi alertApi) {
        this.alertApi = alertApi;
    }
}
