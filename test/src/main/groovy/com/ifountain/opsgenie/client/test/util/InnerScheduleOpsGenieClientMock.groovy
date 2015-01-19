package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.IScheduleOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.schedule.*

import java.text.ParseException;

public class InnerScheduleOpsGenieClientMock implements IScheduleOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private AddScheduleResponse addScheduleResponse;
    private UpdateScheduleResponse updateScheduleResponse;
    private DeleteScheduleResponse deleteScheduleResponse;
    private GetScheduleResponse getScheduleResponse;
    private ListSchedulesResponse listSchedulesResponse;
    private WhoIsOnCallResponse whoIsOnCallResponse;
    private ListWhoIsOnCallResponse listWhoIsOnCallResponse;
    private ExportScheduleResponse exportScheduleResponse;
    private AddScheduleOverrideResponse addScheduleOverrideResponse;
    private UpdateScheduleOverrideResponse updateScheduleOverrideResponse;
    private GetScheduleOverrideResponse getScheduleOverrideResponse;
    private ListScheduleOverridesResponse listScheduleOverridesResponse;
    private DeleteScheduleOverrideResponse deleteScheduleOverrideResponse;
    public InnerScheduleOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public AddScheduleResponse addSchedule(AddScheduleRequest addScheduleRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(addScheduleRequest);
        return addScheduleResponse;
    }

    @Override
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest updateScheduleRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(updateScheduleRequest);
        return updateScheduleResponse;
    }

    @Override
    public ListSchedulesResponse listSchedules(ListSchedulesRequest listSchedulesRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(listSchedulesRequest);
        return listSchedulesResponse;
    }

    @Override
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest deleteScheduleRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(deleteScheduleRequest);
        return deleteScheduleResponse;
    }

    @Override
    public GetScheduleResponse getSchedule(GetScheduleRequest getScheduleRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(getScheduleRequest);
        return getScheduleResponse;
    }
    @Override
    public WhoIsOnCallResponse whoIsOnCall(WhoIsOnCallRequest whoIsOnCallRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(whoIsOnCallRequest);
        return whoIsOnCallResponse;
    }
    @Override
    public ListWhoIsOnCallResponse listWhoIsOnCall(ListWhoIsOnCallRequest listWhoIsOnCallRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(listWhoIsOnCallRequest);
        return listWhoIsOnCallResponse;
    }
    @Override
    public ExportScheduleResponse exportSchedule(ExportScheduleRequest exportScheduleRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(exportScheduleRequest);
        return exportScheduleResponse;
    }

    @Override
    public AddScheduleOverrideResponse addScheduleOverride(AddScheduleOverrideRequest addScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(addScheduleOverrideRequest);
        return addScheduleOverrideResponse;
    }

    @Override
    public UpdateScheduleOverrideResponse updateScheduleOverride(UpdateScheduleOverrideRequest updateScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(updateScheduleOverrideRequest);
        return updateScheduleOverrideResponse;
    }

    @Override
    public DeleteScheduleOverrideResponse deleteScheduleOverride(DeleteScheduleOverrideRequest deleteScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(deleteScheduleOverrideRequest);
        return deleteScheduleOverrideResponse;
    }

    @Override
    public GetScheduleOverrideResponse getScheduleOverride(GetScheduleOverrideRequest getScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(getScheduleOverrideRequest);
        return getScheduleOverrideResponse;
    }

    @Override
    public ListScheduleOverridesResponse listScheduleOverrides(ListScheduleOverridesRequest listScheduleOverridesRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(listScheduleOverridesRequest);
        return listScheduleOverridesResponse;
    }

    public void setAddScheduleResponse(AddScheduleResponse addScheduleResponse) {
        this.addScheduleResponse = addScheduleResponse;
    }

    public void setUpdateScheduleResponse(UpdateScheduleResponse updateScheduleResponse) {
        this.updateScheduleResponse = updateScheduleResponse;
    }

    public void setDeleteScheduleResponse(DeleteScheduleResponse deleteScheduleResponse) {
        this.deleteScheduleResponse = deleteScheduleResponse;
    }

    public void setGetScheduleResponse(GetScheduleResponse getScheduleResponse) {
        this.getScheduleResponse = getScheduleResponse;
    }

    public void setListSchedulesResponse(ListSchedulesResponse listSchedulesResponse) {
        this.listSchedulesResponse = listSchedulesResponse;
    }

    public void setWhoIsOnCallResponse(WhoIsOnCallResponse whoIsOnCallResponse) {
        this.whoIsOnCallResponse = whoIsOnCallResponse;
    }

    public void setListWhoIsOnCallResponse(ListWhoIsOnCallResponse listWhoIsOnCallResponse) {
        this.listWhoIsOnCallResponse = listWhoIsOnCallResponse;
    }

    public void setExportScheduleResponse(ExportScheduleResponse exportScheduleResponse) {
        this.exportScheduleResponse = exportScheduleResponse;
    }

    public void setAddScheduleOverrideResponse(AddScheduleOverrideResponse addScheduleOverrideResponse) {
        this.addScheduleOverrideResponse = addScheduleOverrideResponse;
    }

    public void setUpdateScheduleOverrideResponse(UpdateScheduleOverrideResponse updateScheduleOverrideResponse) {
        this.updateScheduleOverrideResponse = updateScheduleOverrideResponse;
    }

    public void setGetScheduleOverrideResponse(GetScheduleOverrideResponse getScheduleOverrideResponse) {
        this.getScheduleOverrideResponse = getScheduleOverrideResponse;
    }

    public void setListScheduleOverridesResponse(ListScheduleOverridesResponse listScheduleOverridesResponse) {
        this.listScheduleOverridesResponse = listScheduleOverridesResponse;
    }

    public void setDeleteScheduleOverrideResponse(DeleteScheduleOverrideResponse deleteScheduleOverrideResponse) {
        this.deleteScheduleOverrideResponse = deleteScheduleOverrideResponse;
    }
}
