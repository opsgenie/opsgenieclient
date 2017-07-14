package com.ifountain.opsgenie.client.script.util;

import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.opsgenie.client.model.beans.*;
import com.ifountain.opsgenie.client.model.customer.*;
import com.ifountain.opsgenie.client.model.escalation.*;
import com.ifountain.opsgenie.client.model.group.*;
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest;
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest;
import com.ifountain.opsgenie.client.model.schedule.*;
import com.ifountain.opsgenie.client.model.user.*;
import com.ifountain.opsgenie.client.model.user.forward.*;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.swagger.model.*;
import com.ifountain.opsgenie.client.swagger.model.AddAlertTeamRequest;
import com.ifountain.opsgenie.client.swagger.model.CloseAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.CreateAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.DeleteAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.GetAlertResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertLogsRequest;
import com.ifountain.opsgenie.client.swagger.model.ListAlertLogsResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertNotesRequest;
import com.ifountain.opsgenie.client.swagger.model.ListAlertNotesResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertRecipientsResponse;
import com.ifountain.opsgenie.client.swagger.model.ListAlertsRequest;
import com.ifountain.opsgenie.client.util.JsonUtils;

import groovy.lang.Tuple;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptProxy {
    final String apiKey;
    IOpsGenieClient opsGenieClient;

    public ScriptProxy(IOpsGenieClient opsGenieClient, String apiKey) {
        this.opsGenieClient = opsGenieClient;
        this.apiKey = apiKey;

        if (opsGenieClient instanceof OpsGenieClient) {
            ((OpsGenieClient) this.opsGenieClient).setApiKey(this.apiKey);
        }
    }

    public Map acknowledge(Map params) throws Exception {
        AcknowledgeAlertRequest request = new AcknowledgeAlertRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().acknowledgeAlert((String) identifierParams.get(0), (String) identifierParams.get(1), request));
    }

    public Map unAcknowledge(Map params) throws Exception {
        UnAcknowledgeAlertRequest request = new UnAcknowledgeAlertRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().unAcknowledgeAlert((String) identifierParams.get(0), (String) identifierParams.get(1), request));
    }

    public Map snooze(Map params) throws Exception {
        SnoozeAlertRequest request = new SnoozeAlertRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setEndTime(ScriptBridgeUtils.getAsDateTime(params, OpsGenieClientConstants.API.END_DATE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().snoozeAlert((String) identifierParams.get(0), request, (String) identifierParams.get(1)));
    }

    public Map renotify(Map params) throws Exception {
        RenotifyRequest request = new RenotifyRequest();

        populateAlertRequestWithId(request, params);

        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));

        List<String> recipientList = ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.RECIPIENTS);
        if (recipientList != null) {
            request.setRecipients(recipientList);
        }

        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alert().renotify(request));
    }

    public Map addNote(Map params) throws Exception {
        AddAlertNoteRequest request = new AddAlertNoteRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().addNote((String) identifierParams.get(0), request, (String) identifierParams.get(1)));
    }

    public Map addRecipient(Map params) throws Exception {
        AddRecipientRequest request = new AddRecipientRequest();
        populateAlertRequestWithId(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setRecipient(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.RECIPIENT));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alert().addRecipient(request));
    }

    public Map addTeam(Map params) throws Exception {
        AddAlertTeamRequest request = new AddAlertTeamRequest();
        Tuple identifierParams = getIdentifierParams(params);
        TeamRecipient teamObj = null;

        if (params.containsKey(OpsGenieClientConstants.API.TEAM)) {
            teamObj = new TeamRecipient();
            Object team = params.get(OpsGenieClientConstants.API.TEAM);

            if (team instanceof String) {
                teamObj.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TEAM));
            } else if (team instanceof Map) {
                if (((Map) team).containsKey(OpsGenieClientConstants.API.NAME)) {
                    teamObj.setName(ScriptBridgeUtils.getAsString((Map) team, OpsGenieClientConstants.API.NAME));
                }
                if (((Map) team).containsKey(OpsGenieClientConstants.API.ID)) {
                    teamObj.setId(ScriptBridgeUtils.getAsString((Map) team, OpsGenieClientConstants.API.ID));
                }
            }
        }

        request.setTeam(teamObj);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().addTeam((String) identifierParams.get(0), request, (String) identifierParams.get(1)));
    }

    public Map addTags(Map params) throws Exception {
        AddAlertTagsRequest request = new AddAlertTagsRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().addTags((String) identifierParams.get(0), request, (String) identifierParams.get(1)));
    }

    public Map removeTags(Map params) throws Exception {
        DeleteAlertTagsRequest request = new DeleteAlertTagsRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        request.setIdentifier((String) identifierParams.get(0));
        request.setIdentifierType(DeleteAlertTagsRequest.IdentifierTypeEnum.fromValue((String) identifierParams.get(1)));

        return successToMap(this.opsGenieClient.alertV2().deleteTags(request));
    }

    public Map addDetails(Map params) throws Exception {
        AddAlertDetailsRequest request = new AddAlertDetailsRequest();
        Tuple identifierParams = getIdentifierParams(params);

        Map<String, String> objMap = new HashMap<String, String>();
        Map<String, String> strMap = ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS);

        if (strMap != null) {
            for(Map.Entry<String, String> entry : strMap.entrySet()) {
                objMap.put(entry.getKey(), entry.getValue());
            }
        }

        request.setDetails(objMap);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));

        return successToMap(this.opsGenieClient.alertV2().addDetails((String) identifierParams.get(0), request, (String) identifierParams.get(1)));
    }

    public Map removeDetails(Map params) throws Exception {
        DeleteAlertDetailsRequest request = new DeleteAlertDetailsRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setKeys(ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.KEYS));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));

        request.setIdentifier((String) identifierParams.get(0));
        request.setIdentifierType(DeleteAlertDetailsRequest.IdentifierTypeEnum.fromValue((String) identifierParams.get(1)));

        return successToMap(this.opsGenieClient.alertV2().deleteDetails(request));
    }

    public Map assign(Map params) throws Exception {
        AssignAlertRequest request = new AssignAlertRequest();
        Tuple identifierParams = getIdentifierParams(params);
        UserRecipient ownerObj = null;

        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));


        if (params.containsKey(OpsGenieClientConstants.API.OWNER)) {
            ownerObj = new UserRecipient();
            Object owner = params.get(OpsGenieClientConstants.API.OWNER);

            if (owner instanceof String) {
                ownerObj.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OWNER));
            } else if (owner instanceof Map) {
                if (((Map) owner).containsKey(OpsGenieClientConstants.API.USERNAME)) {
                    ownerObj.setUsername(ScriptBridgeUtils.getAsString((Map) owner, OpsGenieClientConstants.API.USERNAME));
                }
                if (((Map) owner).containsKey(OpsGenieClientConstants.API.ID)) {
                    ownerObj.setId(ScriptBridgeUtils.getAsString((Map) owner, OpsGenieClientConstants.API.ID));
                }
            }
        }

        request.setOwner(ownerObj);
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().assignAlert((String) identifierParams.get(0), request, (String) identifierParams.get(1)));
    }

    public Map attach(Map params) throws Exception {
        AttachResponse resp;
        if (ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT) != null) {
            FileAttachRequest fileAttachRequest = new FileAttachRequest();
            String attachmentFilePath = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT);
            fileAttachRequest.setFile(new File(attachmentFilePath));
            populateAttachmentRequestCommonProps(fileAttachRequest, params);
            resp = this.opsGenieClient.alert().attach(fileAttachRequest);
        } else {
            InputStreamAttachRequest inputStreamAttachRequest = new InputStreamAttachRequest();
            InputStream inputStream = (InputStream) params.get(OpsgenieClientApplicationConstants.ScriptProxy.INPUT_STREAM);
            String fileName = ScriptBridgeUtils.getAsString(params, OpsgenieClientApplicationConstants.ScriptProxy.FILE_NAME);
            inputStreamAttachRequest.setInputStream(inputStream);
            inputStreamAttachRequest.setFileName(fileName);
            populateAttachmentRequestCommonProps(inputStreamAttachRequest, params);
            resp = this.opsGenieClient.alert().attach(inputStreamAttachRequest);
        }
        return successToMap(resp);
    }

    public Map escalateToNext(Map params) throws Exception {
        EscalateAlertToNextRequest request = new EscalateAlertToNextRequest();
        Tuple identifierParams = getIdentifierParams(params);
        EscalationRecipient escalationObj = new EscalationRecipient();

        if (params.containsKey(OpsGenieClientConstants.API.ESCALATION)) {
            Map<String, String> escalationMap = ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.ESCALATION);

            if (escalationMap.containsKey(OpsGenieClientConstants.API.ID)) {
                escalationObj.setId(ScriptBridgeUtils.getAsString(escalationMap, OpsGenieClientConstants.API.ID));
            } else if (escalationMap.containsKey(OpsGenieClientConstants.API.NAME)) {
                escalationObj.setName(ScriptBridgeUtils.getAsString(escalationMap, OpsGenieClientConstants.API.NAME));
            }
        } else if (params.containsKey(OpsGenieClientConstants.API.ESCALATION_ID)) {
            escalationObj.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ESCALATION_ID));
        } else if (params.containsKey(OpsGenieClientConstants.API.ESCALATION_NAME)) {
            escalationObj.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ESCALATION_NAME));
        }

        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setEscalation(escalationObj);
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().escalateAlert((String) identifierParams.get(0), request, (String) identifierParams.get(1)));
    }

    public Map closeAlert(Map params) throws Exception {
        CloseAlertRequest request = new CloseAlertRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));

        return successToMap(this.opsGenieClient.alertV2().closeAlert((String) identifierParams.get(0), (String) identifierParams.get(1), request));
}

    public Map createAlert(Map params) throws Exception {
        CreateAlertRequest request = new CreateAlertRequest();

        if (params.containsKey(OpsGenieClientConstants.API.TEAMS)) {
            List<TeamRecipient> teamsObjList = new ArrayList<TeamRecipient>();
            List<Map> teamsList = ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.TEAMS);

            if (teamsList != null) {
                for (Map teamsEntry : teamsList) {
                    TeamRecipient teamObj = new TeamRecipient();

                    if (teamsEntry.containsKey(OpsGenieClientConstants.API.NAME)) {
                        teamObj.setName((String) teamsEntry.get(OpsGenieClientConstants.API.NAME));
                    }

                    if (teamsEntry.containsKey(OpsGenieClientConstants.API.ID)) {
                        teamObj.setId((String) teamsEntry.get(OpsGenieClientConstants.API.ID));
                    }

                    teamsObjList.add(teamObj);
                }

                request.setTeams(teamsObjList);
            }
        }

        request.setActions(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.ACTIONS));
        request.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));
        request.setDetails(ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS));
        request.setMessage(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.MESSAGE));
        request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setEntity(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ENTITY));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));

        return successToMap(this.opsGenieClient.alertV2().createAlert(request));
    }

    public Map deleteAlert(Map params) throws Exception {
        DeleteAlertRequest request = new DeleteAlertRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        request.setIdentifier((String) identifierParams.get(0));
        request.setIdentifierType(DeleteAlertRequest.IdentifierTypeEnum.fromValue((String) identifierParams.get(1)));

        return successToMap(this.opsGenieClient.alertV2().deleteAlert(request));
    }

    public Map executeAlertAction(Map params) throws Exception {
        ExecuteCustomAlertActionRequest request = new ExecuteCustomAlertActionRequest();
        Tuple identifierParams = getIdentifierParams(params);

        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alertV2().executeCustomAction(((String) identifierParams.get(0)), ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ACTION), ((String) identifierParams.get(1)), request));
    }

    public Map getAlert(Map params) throws Exception {
        Tuple identifierParams = getIdentifierParams(params);

        GetAlertResponse getAlertResponse = this.opsGenieClient.alertV2().getAlert(((String) identifierParams.get(0)), ((String) identifierParams.get(1)));

        Map resp = JsonUtils.toMap(getAlertResponse.getData());
        resp.put(OpsGenieClientConstants.API.REQUEST_ID, getAlertResponse.getRequestId());
        //for backward compatability
        resp.put(OpsGenieClientConstants.API.ALERT_ID, resp.get(OpsGenieClientConstants.API.ID));
        return resp;
    }

    public Map listAlertLogs(Map params) throws Exception {
        ListAlertLogsRequest request = new ListAlertLogsRequest();
        Tuple identifierParams = getIdentifierParams(params);

        if (params.containsKey(OpsGenieClientConstants.API.OFFSET)) {
            request.setOffset(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OFFSET));
        }
        if (params.containsKey(OpsGenieClientConstants.API.LIMIT)) {
            request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        }
        if (params.containsKey(OpsGenieClientConstants.API.ORDER)) {
            request.setOrder(ListAlertLogsRequest.OrderEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER)));
        }
        if (params.containsKey(OpsGenieClientConstants.API.DIRECTION)) {
            request.setDirection(ListAlertLogsRequest.DirectionEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DIRECTION)));
        }

        request.setIdentifier((String) identifierParams.get(0));
        request.setIdentifierType(ListAlertLogsRequest.IdentifierTypeEnum.fromValue((String) identifierParams.get(1)));

        ListAlertLogsResponse listAlertLogsResponse = this.opsGenieClient.alertV2().listLogs(request);
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
        Tuple identifierParams = getIdentifierParams(params);
        ListAlertRecipientsResponse resp = this.opsGenieClient.alertV2().listRecipients(((String) identifierParams.get(0)), ((String) identifierParams.get(1)));
        Map res = new HashMap();
        res.put(OpsGenieClientConstants.API.USERS, beansToMap(resp.getData()));
        res.put(OpsGenieClientConstants.API.TOOK, resp.getTook());
        res.put(OpsGenieClientConstants.API.REQUEST_ID, resp.getRequestId());
        return res;
    }

    public Map listAlertNotes(Map params) throws Exception {
        ListAlertNotesRequest request = new ListAlertNotesRequest();
        Tuple identifierParams = getIdentifierParams(params);

        if (params.containsKey(OpsGenieClientConstants.API.OFFSET)) {
            request.setOffset(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OFFSET));
        }
        if (params.containsKey(OpsGenieClientConstants.API.LIMIT)) {
            request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        }
        if (params.containsKey(OpsGenieClientConstants.API.ORDER)) {
            request.setOrder(ListAlertNotesRequest.OrderEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER)));
        }
        if (params.containsKey(OpsGenieClientConstants.API.DIRECTION)) {
            request.setDirection(ListAlertNotesRequest.DirectionEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DIRECTION)));
        }

        request.setIdentifier((String) identifierParams.get(0));
        request.setIdentifierType(ListAlertNotesRequest.IdentifierTypeEnum.fromValue((String) identifierParams.get(1)));

        ListAlertNotesResponse listAlertNotesResponse = this.opsGenieClient.alertV2().listNotes(request);
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

        if (params.containsKey(OpsGenieClientConstants.API.LIMIT)) {
            request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        }
        if (params.containsKey(OpsGenieClientConstants.API.ORDER)) {
            request.setOrder(ListAlertsRequest.OrderEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER)));
        }
        if (params.containsKey(OpsGenieClientConstants.API.SORT)) {
            request.setSort(ListAlertsRequest.SortEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT)));
        }
        if (params.containsKey(OpsGenieClientConstants.API.OFFSET)) {
            request.setOffset(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.OFFSET));
        }
        if (params.containsKey(OpsGenieClientConstants.API.QUERY)) {
            request.setQuery(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.QUERY));
        }

        if (params.containsKey(OpsGenieClientConstants.API.SEARCH_IDENTIFIER)) {
            request.setSearchIdentifier(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SEARCH_IDENTIFIER));
        }
        if (params.containsKey(OpsGenieClientConstants.API.SEARCH_IDENTIFIER_TYPE)) {
            request.setSearchIdentifierType(ListAlertsRequest.SearchIdentifierTypeEnum.fromValue(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SEARCH_IDENTIFIER_TYPE)));
        }

        return beansToMap(this.opsGenieClient.alertV2().listAlerts(request).getData());
    }

    public Map countAlerts(Map params) throws Exception {
        CountAlertsRequest request = new CountAlertsRequest();
        populateCommonProps(request, params);
        populateAlertsRequest(request, params);

        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.COUNT, this.opsGenieClient.alert().countAlerts(request).getCount());
        return mapResponse;
    }

    public Map takeOwnership(Map params) throws Exception {
        TakeOwnershipRequest request = new TakeOwnershipRequest();
        populateAlertRequestWithId(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));

        return successToMap(this.opsGenieClient.alert().takeOwnership(request));
    }

    public Map addSavedSearch(Map params) throws Exception {
        AddSavedSearchRequest request = new AddSavedSearchRequest();

        if (params.containsKey(OpsGenieClientConstants.API.NAME)) {
            request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        }

        if (params.containsKey(OpsGenieClientConstants.API.QUERY)) {
            request.setQuery(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.QUERY));
        }

        if (params.containsKey(OpsGenieClientConstants.API.OWNER)) {
            UserRecipient ownerObj = new UserRecipient();

            Map ownerMap = ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.OWNER);

            if (ownerMap.containsKey(OpsGenieClientConstants.API.ID)) {
                ownerObj.setId(ScriptBridgeUtils.getAsString(ownerMap, OpsGenieClientConstants.API.ID));
            }

            if (ownerMap.containsKey(OpsGenieClientConstants.API.USERNAME)) {
                ownerObj.setUsername(ScriptBridgeUtils.getAsString(ownerMap, OpsGenieClientConstants.API.USERNAME));
            }

            request.setOwner(ownerObj);
        }

        if (params.containsKey(OpsGenieClientConstants.API.DESCRIPTION)) {
            request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        }

        if (params.containsKey(OpsGenieClientConstants.API.TEAMS)) {
            List<TeamRecipient> teamsObjList = new ArrayList<TeamRecipient>();
            List<Map> teamsList = ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.TEAMS);

            if (teamsList != null) {
                for (Map teamsEntry : teamsList) {
                    TeamRecipient teamObj = new TeamRecipient();

                    if (teamsEntry.containsKey(OpsGenieClientConstants.API.NAME)) {
                        teamObj.setName((String) teamsEntry.get(OpsGenieClientConstants.API.NAME));
                    }

                    if (teamsEntry.containsKey(OpsGenieClientConstants.API.ID)) {
                        teamObj.setId((String) teamsEntry.get(OpsGenieClientConstants.API.ID));
                    }

                    teamsObjList.add(teamObj);
                }

                request.setTeams(teamsObjList);
            }
        }

        return JsonUtils.toMap(this.opsGenieClient.alertV2().addSavedSearches(request));
    }

    public Map deleteSavedSearch(Map params) throws Exception {
        String identifier = null;
        String identifierType = null;

        if (params.containsKey(OpsGenieClientConstants.API.ID)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID);
            identifierType = OpsGenieClientConstants.API.ID;
        } else if (params.containsKey(OpsGenieClientConstants.API.NAME)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
            identifierType = OpsGenieClientConstants.API.NAME;
        }

        return successToMap(this.opsGenieClient.alertV2().deleteSavedSearch(identifier, identifierType));
    }

    public Map getRequestStatus(Map params) throws Exception {
        String requestId = null;

        if (params.containsKey(OpsGenieClientConstants.API.REQUEST_ID)) {
            requestId = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.REQUEST_ID);
        }

        return JsonUtils.toMap(this.opsGenieClient.alertV2().getRequestStatus(requestId));
    }

    public Map getSavedSearch(Map params) throws Exception {
        String identifier = null;
        String identifierType = null;

        if (params.containsKey(OpsGenieClientConstants.API.ID)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID);
            identifierType = OpsGenieClientConstants.API.ID;
        } else if (params.containsKey(OpsGenieClientConstants.API.NAME)) {
            identifier = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
            identifierType = OpsGenieClientConstants.API.NAME;
        }

        return JsonUtils.toMap(this.opsGenieClient.alertV2().getSavedSearch(identifier, identifierType));
    }

    public Map listSavedSearch() throws Exception {
        return JsonUtils.toMap(this.opsGenieClient.alertV2().listSavedSearches());
    }

    public Map heartbeat(Map params) throws Exception {
        HeartbeatRequest request = new HeartbeatRequest();
        populateCommonProps(request, params);
        request.setName(getHeartbeatName(params));

        HeartbeatResponse resp = this.opsGenieClient.heartbeat(request);
        Map mapResponse = new HashMap();
        mapResponse.put("heartbeat", resp.getHeartbeat());
        return mapResponse;
    }

    public Map deleteHeartbeat(Map params) throws Exception {
        DeleteHeartbeatRequest request = new DeleteHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        return successToMap(this.opsGenieClient.deleteHeartbeat(request));
    }

    public Map enableHeartbeat(Map params) throws Exception {
        EnableHeartbeatRequest request = new EnableHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        request.setEnable(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLE));
        EnableHeartbeatResponse response = this.opsGenieClient.enableHeartbeat(request);
        return successToMap(response);
    }

    public Map addHeartbeat(Map params) throws Exception {
        AddHeartbeatRequest request = new AddHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        request.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        request.setInterval(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.INTERVAL));
        String intervalUnitStr = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INTERVAL_UNIT);
        if (intervalUnitStr != null) {
            request.setIntervalUnit(Heartbeat.IntervalUnit.valueOf(intervalUnitStr));
        }
        request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        return successToMap(this.opsGenieClient.addHeartbeat(request));
    }

    public Map updateHeartbeat(Map params) throws Exception {
        UpdateHeartbeatRequest request = new UpdateHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        request.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        request.setInterval(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.INTERVAL));
        String intervalUnitStr = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INTERVAL_UNIT);
        if (intervalUnitStr != null) {
            request.setIntervalUnit(Heartbeat.IntervalUnit.valueOf(intervalUnitStr));
        }
        request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        return successToMap(this.opsGenieClient.updateHeartbeat(request));
    }

    public Map getHeartbeat(Map params) throws Exception {
        GetHeartbeatRequest request = new GetHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        return JsonUtils.toMap(this.opsGenieClient.getHeartbeat(request).getHeartbeat());
    }

    private String getHeartbeatName(Map params) {
        if (params.containsKey(OpsGenieClientConstants.API.SOURCE)) {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE);
        } else {
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
        }
    }

    public List<Map> listHeartbeats(Map params) throws Exception {
        ListHeartbeatsRequest request = new ListHeartbeatsRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.listHeartbeats(request).getHeartbeats());
    }

    @Deprecated
    public Map enableAutomation(Map params) throws Exception {
        return enableAlertPolicy(params);
    }

    public Map enableAlertPolicy(Map params) throws Exception {
        EnableAlertPolicyRequest request = new EnableAlertPolicyRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        Boolean enabled = ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED);
        request.setEnabled(enabled);

        return successToMap(this.opsGenieClient.alertPolicy().enableAlertPolicy(request));
    }

    public Map enableIntegration(Map params) throws Exception {
        EnableIntegrationRequest request = new EnableIntegrationRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        return successToMap(this.opsGenieClient.integration().enableIntegration(request));
    }

    public Map sendToIntegration(String endPoint, Map contentParams, Map httpParams) throws Exception {
        SendToIntegrationRequest request = new SendToIntegrationRequest();
        populateCommonProps(request, contentParams);
        request.setEndPoint(endPoint);
        request.setContentParameters(contentParams);
        request.setHttpParameters(httpParams);
        return successToMap(this.opsGenieClient.integration().sendToIntegration(request));
    }

    public Map addEscalation(Map params) throws Exception {
        AddEscalationRequest request = new AddEscalationRequest();
        populateCommonProps(request, params);
        Escalation escalation = new Escalation();
        JsonUtils.fromMap(escalation, params);
        request.setName(escalation.getName());
        request.setRules(escalation.getRules());

        AddEscalationResponse resp = this.opsGenieClient.escalation().addEscalation(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteEscalation(Map params) throws Exception {
        DeleteEscalationRequest request = new DeleteEscalationRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return successToMap(this.opsGenieClient.escalation().deleteEscalation(request));
    }

    public Map getEscalation(Map params) throws Exception {
        GetEscalationRequest request = new GetEscalationRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return JsonUtils.toMap(this.opsGenieClient.escalation().getEscalation(request).getEscalation());
    }

    public List<Map> listEscalations(Map params) throws Exception {
        ListEscalationsRequest request = new ListEscalationsRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.escalation().listEscalations(request).getEscalations());
    }

    public Map updateEscalation(Map params) throws Exception {
        UpdateEscalationRequest request = new UpdateEscalationRequest();
        populateCommonProps(request, params);
        Escalation escalation = new Escalation();
        JsonUtils.fromMap(escalation, params);
        request.setId(escalation.getId());
        request.setName(escalation.getName());
        request.setRules(escalation.getRules());

        UpdateEscalationResponse resp = this.opsGenieClient.escalation().updateEscalation(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addGroup(Map params) throws Exception {
        AddGroupRequest request = new AddGroupRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        JsonUtils.fromMap(group, params);
        request.setName(group.getName());
        request.setUsers(group.getUsers());

        AddGroupResponse resp = this.opsGenieClient.group().addGroup(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addGroupMember(Map params) throws Exception {
        AddGroupMemberRequest request = new AddGroupMemberRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        JsonUtils.fromMap(group, params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());

        AddGroupMemberResponse resp = this.opsGenieClient.group().addGroupMember(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteGroupMember(Map params) throws Exception {
        DeleteGroupMemberRequest request = new DeleteGroupMemberRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        JsonUtils.fromMap(group, params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());

        AddGroupMemberResponse resp = this.opsGenieClient.group().deleteGroupMember(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteGroup(Map params) throws Exception {
        DeleteGroupRequest request = new DeleteGroupRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return successToMap(this.opsGenieClient.group().deleteGroup(request));
    }

    public Map getGroup(Map params) throws Exception {
        GetGroupRequest request = new GetGroupRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return JsonUtils.toMap(this.opsGenieClient.group().getGroup(request).getGroup());
    }

    public List<Map> listGroups(Map params) throws Exception {
        ListGroupsRequest request = new ListGroupsRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.group().listGroups(request).getGroups());
    }

    public Map updateGroup(Map params) throws Exception {
        UpdateGroupRequest request = new UpdateGroupRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        JsonUtils.fromMap(group, params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());

        UpdateGroupResponse resp = this.opsGenieClient.group().updateGroup(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addUser(Map params) throws Exception {
        AddUserRequest request = new AddUserRequest();
        populateCommonProps(request, params);
        User user = new User();
        JsonUtils.fromMap(user, params);
        request.setUsername(user.getUsername());
        request.setFullname(user.getFullname());
        request.setUserRole(user.getUserRole());
        request.setTimeZone(user.getTimeZone());
        request.setLocale(user.getLocale());

        AddUserResponse resp = this.opsGenieClient.user().addUser(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteUser(Map params) throws Exception {
        DeleteUserRequest request = new DeleteUserRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USERNAME));

        return successToMap(this.opsGenieClient.user().deleteUser(request));
    }

    public Map getUser(Map params) throws Exception {
        GetUserRequest request = new GetUserRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USERNAME));

        return JsonUtils.toMap(this.opsGenieClient.user().getUser(request).getUser());
    }

    public List<Map> listUsers(Map params) throws Exception {
        ListUsersRequest request = new ListUsersRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.user().listUsers(request).getUsers());
    }

    public Map updateUser(Map params) throws Exception {
        UpdateUserRequest request = new UpdateUserRequest();
        populateCommonProps(request, params);
        User user = new User();
        JsonUtils.fromMap(user, params);
        request.setId(user.getId());
        request.setFullname(user.getFullname());
        request.setUserRole(user.getUserRole());
        request.setTimeZone(user.getTimeZone());
        request.setLocale(user.getLocale());

        UpdateUserResponse resp = this.opsGenieClient.user().updateUser(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }


    public Map addForwarding(Map params) throws Exception {
        AddForwardingRequest request = new AddForwardingRequest();
        populateCommonProps(request, params);
        Forwarding forwarding = new Forwarding();
        JsonUtils.fromMap(forwarding, params);
        request.setAlias(forwarding.getAlias());
        request.setStartDate(forwarding.getStartDate());
        request.setEndDate(forwarding.getEndDate());
        request.setFromUser(forwarding.getFromUser());
        request.setToUser(forwarding.getToUser());
        request.setTimeZone(forwarding.getTimeZone());

        AddForwardingResponse resp = this.opsGenieClient.user().addForwarding(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteForwarding(Map params) throws Exception {
        DeleteForwardingRequest request = new DeleteForwardingRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));

        return successToMap(this.opsGenieClient.user().deleteForwarding(request));
    }

    public Map getForwarding(Map params) throws Exception {
        GetForwardingRequest request = new GetForwardingRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));

        return JsonUtils.toMap(this.opsGenieClient.user().getForwarding(request).getForwarding());
    }

    public List<Map> listForwardings(Map params) throws Exception {
        ListForwardingsRequest request = new ListForwardingsRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.user().listForwardings(request).getForwardings());
    }

    public Map updateForwarding(Map params) throws Exception {
        UpdateForwardingRequest request = new UpdateForwardingRequest();
        populateCommonProps(request, params);
        Forwarding forwarding = new Forwarding();
        JsonUtils.fromMap(forwarding, params);
        request.setId(forwarding.getId());
        request.setAlias(forwarding.getAlias());
        request.setStartDate(forwarding.getStartDate());
        request.setEndDate(forwarding.getEndDate());
        request.setFromUser(forwarding.getFromUser());
        request.setToUser(forwarding.getToUser());
        request.setTimeZone(forwarding.getTimeZone());

        UpdateForwardingResponse resp = this.opsGenieClient.user().updateForwarding(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addSchedule(Map params) throws Exception {
        correctRestrictionAndParticipantParams(params);
        AddScheduleRequest request = new AddScheduleRequest();
        populateCommonProps(request, params);
        Schedule schedule = new Schedule();
        JsonUtils.fromMap(schedule, params);
        request.setEnabled(schedule.isEnabled());
        request.setName(schedule.getName());
        request.setRotations(schedule.getRotations());
        request.setTimeZone(schedule.getTimeZone());

        AddScheduleResponse resp = this.opsGenieClient.schedule().addSchedule(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    private void correctRestrictionAndParticipantParams(Map params) {
        List<Map> rotations = null;
        if (params.containsKey(OpsGenieClientConstants.API.RULES)) {
            rotations = (List<Map>) params.get(OpsGenieClientConstants.API.RULES);
        } else if (params.containsKey(OpsGenieClientConstants.API.LAYERS)) {
            rotations = (List<Map>) params.get(OpsGenieClientConstants.API.LAYERS);
        } else if (params.containsKey(OpsGenieClientConstants.API.ROTATIONS)) {
            rotations = (List<Map>) params.get(OpsGenieClientConstants.API.ROTATIONS);
        }
        if (rotations != null) {
            for (Map rotationMap : rotations) {
                if (rotationMap.containsKey(OpsGenieClientConstants.API.RESTRICTIONS)) {
                    List<Map> restrictions = (List<Map>) rotationMap.get(OpsGenieClientConstants.API.RESTRICTIONS);
                    for (Map restriction : restrictions) {
                        int startHour = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.START_HOUR);
                        int startMinute = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.START_MINUTE);
                        int endHour = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.END_HOUR);
                        int endMinute = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.END_MINUTE);
                        restriction.put(OpsGenieClientConstants.API.START_TIME, "" + startHour + ":" + startMinute);
                        restriction.put(OpsGenieClientConstants.API.END_TIME, "" + endHour + ":" + endMinute);
                    }
                }
                if (rotationMap.containsKey(OpsGenieClientConstants.API.PARTICIPANTS)) {
                    List<String> participants = (List<String>) rotationMap.get(OpsGenieClientConstants.API.PARTICIPANTS);
                    List<Map> participantMaps = new ArrayList<Map>();
                    for (String participant : participants) {
                        Map participantMap = new HashMap();
                        participantMap.put(OpsGenieClientConstants.API.PARTICIPANT, participant);
                        participantMaps.add(participantMap);
                    }
                    rotationMap.put(OpsGenieClientConstants.API.PARTICIPANTS, participantMaps);
                }
            }

        }
    }

    public Map deleteSchedule(Map params) throws Exception {
        DeleteScheduleRequest request = new DeleteScheduleRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return successToMap(this.opsGenieClient.schedule().deleteSchedule(request));
    }

    public Map getSchedule(Map params) throws Exception {
        GetScheduleRequest request = new GetScheduleRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return JsonUtils.toMap(this.opsGenieClient.schedule().getSchedule(request).getSchedule());
    }

    public Map whoIsOnCall(Map params) throws Exception {
        WhoIsOnCallRequest request = new WhoIsOnCallRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        request.setTimeZone(ScriptBridgeUtils.getAsTimeZone(params, OpsGenieClientConstants.API.TIMEZONE));
        request.setTime(ScriptBridgeUtils.getAsDate(params, OpsGenieClientConstants.API.TIME));
        return JsonUtils.toMap(this.opsGenieClient.schedule().whoIsOnCall(request).getWhoIsOnCall());
    }

    public Map flatWhoIsOnCall(Map params) throws Exception {
        FlatWhoIsOnCallRequest request = new FlatWhoIsOnCallRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        request.setTimeZone(ScriptBridgeUtils.getAsTimeZone(params, OpsGenieClientConstants.API.TIMEZONE));
        request.setTime(ScriptBridgeUtils.getAsDate(params, OpsGenieClientConstants.API.TIME));
        return JsonUtils.toMap(this.opsGenieClient.schedule().flatWhoIsOnCall(request).getWhoIsOnCall());
    }

    public List<Map> listWhoIsOnCall(Map params) throws Exception {
        ListWhoIsOnCallRequest request = new ListWhoIsOnCallRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.schedule().listWhoIsOnCall(request).getWhoIsOnCallList());
    }

    public List<Map> listFlatWhoIsOnCall(Map params) throws Exception {
        ListFlatWhoIsOnCallRequest request = new ListFlatWhoIsOnCallRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.schedule().listFlatWhoIsOnCall(request).getWhoIsOnCallList());
    }

    public List<Map> listSchedules(Map params) throws Exception {
        ListSchedulesRequest request = new ListSchedulesRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.schedule().listSchedules(request).getSchedules());
    }

    public Map updateSchedule(Map params) throws Exception {
        correctRestrictionAndParticipantParams(params);
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        populateCommonProps(request, params);
        Schedule schedule = new Schedule();
        JsonUtils.fromMap(schedule, params);
        request.setId(schedule.getId());
        request.setEnabled(schedule.isEnabled());
        request.setName(schedule.getName());
        request.setRotations(schedule.getRotations());
        request.setTimeZone(schedule.getTimeZone());

        UpdateScheduleResponse resp = this.opsGenieClient.schedule().updateSchedule(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map copyNotificationRules(Map params) throws Exception {
        CopyNotificationRulesRequest request = new CopyNotificationRulesRequest();
        populateCommonProps(request, params);
        request.setFromUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.FROM_USER));
        request.setToUsers(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TO_USERS));
        request.setRuleTypes(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.RULE_TYPES));

        return JsonUtils.parse(this.opsGenieClient.copyNotificationRules(request).getJson());
    }

    private void populateAlertRequestWithId(BaseAlertRequestWithId request, Map params) {
        Tuple identifierParams = getIdentifierParams(params);
        populateCommonProps(request, params);

        if (OpsGenieClientConstants.API.ALERT_ID.equals(identifierParams.get(1)) || OpsGenieClientConstants.API.ID.equals(identifierParams.get(1))) {
            request.setId((String) identifierParams.get(0));
        } else if (OpsGenieClientConstants.API.TINY.equals(identifierParams.get(1))) {
            request.setTinyId((String) identifierParams.get(0));
        } else {
            request.setAlias((String) identifierParams.get(0));
        }
    }

    private void populateAttachmentRequestCommonProps(AttachRequest request, Map params) {
        populateAlertRequestWithId(request, params);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setIndexFile(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INDEX_FILE));
    }

    protected Tuple getIdentifierParams(Map params) {
        if (params.containsKey(OpsGenieClientConstants.API.ALERT_ID)) {
            return new Tuple(new Object[]{ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID), OpsGenieClientConstants.API.ID});
        } else if (params.containsKey(OpsGenieClientConstants.API.TINY_ID)) {
            return new Tuple(new Object[]{ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TINY_ID), OpsGenieClientConstants.API.TINY});
        } else if (params.containsKey(OpsGenieClientConstants.API.ID)) {
            return new Tuple(new Object[]{ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID), OpsGenieClientConstants.API.ID});
        } else if (params.containsKey(OpsGenieClientConstants.API.ALIAS)) {
            return new Tuple(new Object[]{ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS), OpsGenieClientConstants.API.ALIAS});
        } else {
            return new Tuple(new Object[]{null, null});
        }
    }

    protected void populateCommonProps(BaseRequest request, Map params) {
        String apiKeyFromParam;
        if (params.containsKey(OpsGenieClientConstants.API.CUSTOMER_KEY)) {
            apiKeyFromParam = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY);
        } else {
            apiKeyFromParam = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.API_KEY);
        }
        if (apiKeyFromParam == null) {
            apiKeyFromParam = apiKey;
        }
        request.setApiKey(apiKeyFromParam);
    }

    protected void populateAlertsRequest(AlertsRequest request, Map params) {
        request.setCreatedAfter(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.CREATED_AFTER));
        request.setCreatedBefore(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.CREATED_BEFORE));
        request.setUpdatedAfter(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.UPDATED_AFTER));
        request.setUpdatedBefore(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.UPDATED_BEFORE));
        request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        if (params.containsKey(OpsGenieClientConstants.API.STATUS)) {
            request.setStatus(AlertsRequest.Status.valueOf(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.STATUS)));
        }
        if (params.containsKey(OpsGenieClientConstants.API.TAGS)) {
            request.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));
        }
        if (params.containsKey(OpsGenieClientConstants.API.TAGS_OPERATOR)) {
            request.setTagsOperator(AlertsRequest.Operator.valueOf(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TAGS_OPERATOR)));
        }
    }

    protected Map successToMap(Object response) throws Exception {
        Map mapResponse = new HashMap();

        if (response instanceof SuccessResponse) {
            return JsonUtils.toMap(response);
        } else {
            mapResponse.put(OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS, ((BaseResponse) response).isSuccess());
        }

        return mapResponse;
    }

    protected List<Map> beansToMap(Iterable beans) {
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
}
