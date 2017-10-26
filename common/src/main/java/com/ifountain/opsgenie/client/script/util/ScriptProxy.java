package com.ifountain.opsgenie.client.script.util;

import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.opsgenie.oas.sdk.ApiClient;
import com.opsgenie.oas.sdk.api.*;
import com.opsgenie.oas.sdk.model.*;
import com.opsgenie.oas.sdk.model.AcknowledgeAlertRequest;
import com.opsgenie.oas.sdk.model.AssignAlertRequest;
import com.opsgenie.oas.sdk.model.EscalationRecipient;
import com.opsgenie.oas.sdk.model.ExecuteCustomAlertActionRequest;
import com.opsgenie.oas.sdk.model.SnoozeAlertRequest;
import com.opsgenie.oas.sdk.model.SuccessResponse;
import com.opsgenie.oas.sdk.model.TeamRecipient;
import com.opsgenie.oas.sdk.model.UnAcknowledgeAlertRequest;
import com.opsgenie.oas.sdk.model.UserRecipient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import sun.font.Script;


import java.io.File;
import java.util.*;

public class ScriptProxy {
    final String apiKey;
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

    public ScriptProxy(String apiKey) {
        this.apiKey = apiKey;
        this.apiClient = apiClient;
        apiClient.setApiKey(apiKey);
        alertApi = new AlertApi(apiClient);
        scheduleApi = new ScheduleApi(apiClient);
        heartbeatApi = new HeartbeatApi(apiClient);
        policyApi = new PolicyApi(apiClient);
        integrationApi = new IntegrationApi(apiClient);
        escalationApi = new EscalationApi(apiClient);
        userApi = new UserApi(apiClient);
        teamApi = new TeamApi(apiClient);
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
        payload.setEndTime(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.END_DATE));
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

        if (params.containsKey(OpsGenieClientConstants.API.TAGS)) {
            Object tags = params.get(OpsGenieClientConstants.API.TAGS);

            if (tags instanceof List) {
                payload.setTags((List) tags);
            } else if (tags instanceof String) {
                payload.setTags(new ArrayList<String>(Arrays.asList(((String) tags).split(","))));
            }
        }
        request.setBody(payload);

        return successToMap(alertApi.addTags(request));
    }

    public Map removeTags(Map params) throws Exception {
        RemoveTagsFromAlertRequest request = new RemoveTagsFromAlertRequest();

        setIdentifierParameters(request, RemoveTagsFromAlertRequest.IdentifierTypeEnum.class, params);
        populateCommonParameters(request, params);

        request.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));

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

    //todo: en son bak, exception yedirttin..
    public Map attach(Map params) throws Exception {
        AddAttachmentToAlertRequest request = new AddAttachmentToAlertRequest();
        String pathname = null;
        if (params.containsKey(OpsGenieClientConstants.API.ALERT_IDENTIFIER)) {
            //v2 request
            request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_IDENTIFIER));
            if (AddAttachmentToAlertRequest.AlertIdentifierTypeEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_IDENTIFIER_TYPE)) != null) {
                request.setAlertIdentifierType(AddAttachmentToAlertRequest.AlertIdentifierTypeEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_IDENTIFIER_TYPE)));
            }
            pathname = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.FILE);
            if (pathname != null) {
                request.setFile(new File(pathname));
            }

        } else {
            params.putAll(appendAlertToIdentifierKeys(generateIdentifierParamsForDeprecatedAPIRequests(params)));
            request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_IDENTIFIER));
            if (request.getIdentifier() != null) {
                request.setAlertIdentifierType(AddAttachmentToAlertRequest.AlertIdentifierTypeEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_IDENTIFIER_TYPE)));
            }
            pathname = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT);
            if (pathname != null) {
                request.setFile(new File(pathname));
            }
        }

        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setIndexFile(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INDEX_FILE));

        return successToMap(alertApi.addAttachment(request));
    }


    //todo escalate to next test et
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

    //team sikinti
    //todo: payload sikinti yapmamali, yine de bi dene sen null'u
    public Map createAlert(Map params) throws Exception {
        CreateAlertPayload payload = new CreateAlertPayload();


        payload.setActions(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.ACTIONS));
        payload.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setDetails(ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS));
        payload.setEntity(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ENTITY));
        payload.setMessage(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.MESSAGE));
        payload.setPriority(CreateAlertPayload.PriorityEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.PRIORITY)));
        payload.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));
        List<TeamRecipient> teamRecipients = ScriptBridgeUtils.getAsObjectList(params, OpsGenieClientConstants.API.TEAMS, TeamRecipient.class);
        payload.setTeams(teamRecipients);
        List<Recipient> visibleToRecipients = ScriptBridgeUtils.getAsObjectList(params, OpsGenieClientConstants.API.VISIBLE_TO, Recipient.class);
        payload.setVisibleTo(visibleToRecipients);
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

    //TODO: emreye sor donen verinin ne alakasi var. ?
    public Map getAlert(Map params) throws Exception {
        GetAlertRequest request = new GetAlertRequest();
        setIdentifierParameters(request, GetAlertRequest.IdentifierTypeEnum.class, params);


        return JsonUtils.toMap(alertApi.getAlert(request).getData());
    }

    public List<Map> listAlertLogs(Map params) throws Exception {
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

        return beansToMap(alertApi.listLogs(request).getData());
    }


    public List<Map> listAlertRecipients(Map params) throws Exception {
        ListAlertRecipientsRequest request = new ListAlertRecipientsRequest();
        setIdentifierParameters(request, ListAlertRecipientsRequest.IdentifierTypeEnum.class, params);


        return beansToMap(alertApi.listRecipients(request).getData());
    }

    public List<Map> listAlertNotes(Map params) throws Exception {
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

        return beansToMap(alertApi.listNotes(request).getData());
    }

    //todo alert notestaki offset string, asagindaki int.
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
        ListAlertsRequest.SortEnum sort = ListAlertsRequest.SortEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT));
        if (sort != null) {
            request.setSort(sort);
        }
        return beansToMap(alertApi.listAlerts(request).getData());
    }


    //birdah`a bak.
    //todo owner'i dene calisiyor mu.
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
        String requestId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.REQUEST_ID);

        return JsonUtils.toMap(alertApi.getRequestStatus(requestId));
    }

    public Map getSavedSearch(Map params) throws Exception {
        GetSavedSearchRequest request = new GetSavedSearchRequest();
        setIdentifierParameters(request, GetSavedSearchRequest.IdentifierTypeEnum.class, params);
        return JsonUtils.toMap(alertApi.getSavedSearch(request));
    }

    public List<Map> listSavedSearch() throws Exception {
        return beansToMap(alertApi.listSavedSearches().getData());
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
        String heartbeatName = getHeartbeatName(params);

        return successToMap(heartbeatApi.deleteHeartbeat(heartbeatName));
    }

    //todo: doc'ta ownerteam var sdk'da yok api doclarinda sayisiz inconsistency var name/heartbeatName gibi
    public Map addHeartbeat(Map params) throws Exception {
        CreateHeartbeatPayload payload = new CreateHeartbeatPayload();

        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        payload.setInterval(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.INTERVAL));
        payload.setIntervalUnit(CreateHeartbeatPayload.IntervalUnitEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INTERVAL_UNIT)));
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

        return successToMap(heartbeatApi.updateHeartbeat(request));
    }


    public Map getHeartbeat(Map params) throws Exception {
        String heartbeatName = getHeartbeatName(params);
        return JsonUtils.toMap(heartbeatApi.getHeartbeat(heartbeatName));
    }


    //todo id aliyor.
    public Map enableAlertPolicy(Map params) throws Exception {
        String policyId = getPolicyIdentifier(params);

        return successToMap(policyApi.enableAlertPolicy(policyId));
    }

    public Map disableAlertPolicy(Map params) throws Exception {
        String policyId = getPolicyIdentifier(params);

        return successToMap(policyApi.disableAlertPolicy(policyId));
    }

    //todo: String id aliyor
    public Map enableIntegration(Map params) throws Exception {
        String integrationId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);
        return JsonUtils.toMap(integrationApi.enableIntegration(integrationId));

    }

    //todo: String id aliyor
    public Map disableIntegration(Map params) throws Exception {
        String integrationId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);
        return JsonUtils.toMap(integrationApi.disableIntegration(integrationId));

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
        setIdentifierParameters(request, GetEscalationRequest.IdentifierTypeEnum.class, params);

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

        return JsonUtils.toMap(teamApi.createTeam(payload));
    }

    public Map getTeam(Map params) throws Exception {
        GetTeamRequest request = new GetTeamRequest();
        setIdentifierParameters(request, GetTeamRequest.IdentifierTypeEnum.class, params);

        return JsonUtils.toMap(teamApi.getTeam(request).getData());
    }

    //team identifier burada teamId, orada identifier
    public Map updateTeam(Map params) throws Exception {
        UpdateTeamRequest request = new UpdateTeamRequest();
        UpdateTeamPayload payload = new UpdateTeamPayload();

        request.setIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TEAM_ID));

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
        UpdateUserRequest request = new UpdateUserRequest();
        UpdateUserPayload payload = new UpdateUserPayload();

        request.setIdentifier(getUserIdentifier(params));

        removeIdentifierParams(params);

        JsonUtils.fromMap(payload, params);
        request.setBody(payload);

        return successToMap(userApi.updateUser(request));
    }


    public Map addForwarding(Map params) throws Exception {
        CreateForwardingRulePayload payload = new CreateForwardingRulePayload();
        JsonUtils.fromMap(payload, params);

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

        removeIdentifierParams(params);

        JsonUtils.fromMap(payload, params);

        request.setBody(payload);


        return successToMap(forwardingRuleApi.updateForwardingRule(request));
    }

    //todo:schedule backward yalan, participants eskiden typesiz string aliyormus.. direk deserialize edemiyorsun jackson sikinti yapiyor.
    public Map addSchedule(Map params) throws Exception {
        CreateSchedulePayload payload = new CreateSchedulePayload();


        payload.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        payload.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        payload.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        TeamMeta teamMeta = new TeamMeta();
        JsonUtils.fromMap(teamMeta, ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.OWNER_TEAM));
        payload.setOwnerTeam(teamMeta);
        payload.setTimezone(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TIMEZONE));

        payload.setRotations(parseCreateScheduleRotations(params));

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
        TeamMeta teamMeta = new TeamMeta();
        JsonUtils.fromMap(teamMeta, ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.OWNER_TEAM));
        payload.setOwnerTeam(teamMeta);
        payload.setTimezone(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TIMEZONE));

        payload.setRotations(parseCreateScheduleRotations(params));

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

        JsonUtils.fromMap(request, params);

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

    private static Map appendAlertToIdentifierKeys(Map params) {
        params.put(OpsGenieClientConstants.API.ALERT_IDENTIFIER, params.remove(OpsGenieClientConstants.API.IDENTIFIER));
        params.put(OpsGenieClientConstants.API.ALERT_IDENTIFIER_TYPE, params.remove(OpsGenieClientConstants.API.IDENTIFIER_TYPE));
        return params;
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

    private static List<CreateScheduleRotationPayload> parseCreateScheduleRotations(Map params) throws Exception {
        List<CreateScheduleRotationPayload> scheduleRotations = new ArrayList<CreateScheduleRotationPayload>();
        List<Map> scheduleRotationEntries = ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.ROTATIONS);
        ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.ROTATIONS);
        if (scheduleRotationEntries != null) {
            for (Map rotationEntry : scheduleRotationEntries) {
                CreateScheduleRotationPayload rotation = new CreateScheduleRotationPayload();
                rotation.setName(ScriptBridgeUtils.getAsString(rotationEntry, OpsGenieClientConstants.API.NAME));
                rotation.setLength(ScriptBridgeUtils.getAsInt(rotationEntry, OpsGenieClientConstants.API.LENGTH));
                rotation.setStartDate(ScriptBridgeUtils.getAsDateTime(rotationEntry, OpsGenieClientConstants.API.START_DATE));
                rotation.setEndDate(ScriptBridgeUtils.getAsDateTime(rotationEntry, OpsGenieClientConstants.API.END_DATE));
                rotation.setType(ObjectUtils.defaultIfNull(CreateScheduleRotationPayload.TypeEnum.fromValue(ScriptBridgeUtils.getAsString(rotationEntry, OpsGenieClientConstants.API.TYPE)), rotation.getType()));

                List<Recipient> rotationRecipients = new ArrayList<Recipient>();

                List<Map> recipientEntries = ScriptBridgeUtils.getAsList(rotationEntry, OpsGenieClientConstants.API.PARTICIPANTS);
                if (recipientEntries != null) {
                    for (Map recipientEntry : recipientEntries) {
                        Recipient.TypeEnum recipientType = Recipient.TypeEnum.fromValue(ScriptBridgeUtils.getAsString(recipientEntry, OpsGenieClientConstants.API.TYPE));
                        if (recipientType.getValue().equals(Recipient.TypeEnum.USER.getValue())) {
                            UserRecipient userRecipient = new UserRecipient();
                            userRecipient.setUsername(ScriptBridgeUtils.getAsString(recipientEntry, OpsGenieClientConstants.API.USERNAME));
                            userRecipient.setId(ScriptBridgeUtils.getAsString(recipientEntry, OpsGenieClientConstants.API.ID));
                            rotationRecipients.add(userRecipient);
                        } else if (recipientType.getValue().equals(Recipient.TypeEnum.ESCALATION.getValue())) {
                            EscalationRecipient escalationRecipient = new EscalationRecipient();
                            escalationRecipient.setName(ScriptBridgeUtils.getAsString(recipientEntry, OpsGenieClientConstants.API.NAME));
                            escalationRecipient.setId(ScriptBridgeUtils.getAsString(recipientEntry, OpsGenieClientConstants.API.ID));
                            rotationRecipients.add(escalationRecipient);
                        } else if (recipientType.getValue().equals(Recipient.TypeEnum.TEAM.getValue())) {
                            TeamRecipient teamRecipient = new TeamRecipient();
                            teamRecipient.setName(ScriptBridgeUtils.getAsString(recipientEntry, OpsGenieClientConstants.API.NAME));
                            teamRecipient.setId(ScriptBridgeUtils.getAsString(recipientEntry, OpsGenieClientConstants.API.ID));
                            rotationRecipients.add(teamRecipient);
                        } else if (recipientType.getValue().equals(Recipient.TypeEnum.NONE.getValue())) {
                            NoRecipient recipient = new NoRecipient();
                            rotationRecipients.add(recipient);
                        }
                    }
                }
                rotation.setParticipants(rotationRecipients);


                Map timeRestrictionIntervalEntry = ScriptBridgeUtils.getAsMap(rotationEntry, OpsGenieClientConstants.API.TIME_RESTRICTION);
                if (timeRestrictionIntervalEntry != null) {

                    TimeRestrictionInterval.TypeEnum timeRestrictionType = TimeRestrictionInterval.TypeEnum.fromValue(ScriptBridgeUtils.getAsString(timeRestrictionIntervalEntry, OpsGenieClientConstants.API.TYPE));
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
        } else {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
        }
    }

    private static String getPolicyIdentifier(Map params) {
        if (params.containsKey(OpsGenieClientConstants.API.IDENTIFIER)) {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.IDENTIFIER);
        } else {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
        }
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


                Recipient.TypeEnum recipientType = Recipient.TypeEnum.fromValue(ScriptBridgeUtils.getAsString(recipientMap, OpsGenieClientConstants.API.TYPE));
                if (recipientType.getValue().equals(Recipient.TypeEnum.USER.getValue())) {
                    UserRecipient userRecipient = new UserRecipient();
                    userRecipient.setId(ScriptBridgeUtils.getAsString(recipientMap, OpsGenieClientConstants.API.ID));
                    userRecipient.setUsername(ScriptBridgeUtils.getAsString(recipientMap, OpsGenieClientConstants.API.USERNAME));
                    rule.setRecipient(userRecipient);
                } else if (recipientType.getValue().equals(Recipient.TypeEnum.SCHEDULE.getValue())) {
                    ScheduleRecipient scheduleRecipient = new ScheduleRecipient();
                    scheduleRecipient.setId(ScriptBridgeUtils.getAsString(recipientMap, OpsGenieClientConstants.API.ID));
                    scheduleRecipient.setName(ScriptBridgeUtils.getAsString(recipientMap, OpsGenieClientConstants.API.NAME));
                    rule.setRecipient(scheduleRecipient);
                } else if (recipientType.getValue().equals(Recipient.TypeEnum.TEAM.getValue())) {
                    TeamRecipient teamRecipient = new TeamRecipient();
                    teamRecipient.setId(ScriptBridgeUtils.getAsString(recipientMap, OpsGenieClientConstants.API.ID));
                    teamRecipient.setName(ScriptBridgeUtils.getAsString(recipientMap, OpsGenieClientConstants.API.NAME));
                    rule.setRecipient(teamRecipient);
                }


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
        } else if (params.containsKey(OpsGenieClientConstants.API.TEAM)) {
            teamMeta.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TEAM));
        }

        return teamMeta;
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
        return teamMembers;
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


}
