package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.schedule.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Schedule Client
 */
public class InnerScheduleOpsGenieClient implements IScheduleOpsGenieClient {
    private JsonOpsgenieHttpClient httpClient;
    private StreamOpsgenieHttpClient streamOpsgenieHttpClient;

    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerScheduleOpsGenieClient(JsonOpsgenieHttpClient httpClient, StreamOpsgenieHttpClient streamOpsgenieHttpClient) {
        this.httpClient = httpClient;
        this.streamOpsgenieHttpClient = streamOpsgenieHttpClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(com.ifountain.opsgenie.client.model.schedule.AddScheduleRequest)
     */
    @Override
    public AddScheduleResponse addSchedule(AddScheduleRequest addScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddScheduleResponse) httpClient.doPostRequestV2(addScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateSchedule(com.ifountain.opsgenie.client.model.schedule.UpdateScheduleRequest)
     */
    @Override
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest updateScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateScheduleResponse) httpClient.doPatchRequestV2(updateScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteSchedule(com.ifountain.opsgenie.client.model.schedule.DeleteScheduleRequest)
     */
    @Override
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest deleteScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteScheduleResponse) httpClient.doDeleteRequestV2(deleteScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest)
     */
    @Override
    public GetScheduleResponse getSchedule(GetScheduleRequest getScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetScheduleResponse) httpClient.doGetRequestV2(getScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#whoIsOnCall(com.ifountain.opsgenie.client.model.schedule.WhoIsOnCallRequest)
     */
    @Override
    public WhoIsOnCallResponse whoIsOnCall(WhoIsOnCallRequest whoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException {
        return (WhoIsOnCallResponse) httpClient.doGetRequest(whoIsOnCallRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#flatWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.FlatWhoIsOnCallRequest)
     */
    @Override
    public FlatWhoIsOnCallResponse flatWhoIsOnCall(FlatWhoIsOnCallRequest flatWhoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException {
        return (FlatWhoIsOnCallResponse) httpClient.doGetRequest(flatWhoIsOnCallRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest)
     */
    @Override
    public ListWhoIsOnCallResponse listWhoIsOnCall(ListWhoIsOnCallRequest listWhoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListWhoIsOnCallResponse) httpClient.doGetRequest(listWhoIsOnCallRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listFlatWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListFlatWhoIsOnCallRequest)
     */
    @Override
    public ListFlatWhoIsOnCallResponse listFlatWhoIsOnCall(ListFlatWhoIsOnCallRequest listFlatWhoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListFlatWhoIsOnCallResponse) httpClient.doGetRequest(listFlatWhoIsOnCallRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(com.ifountain.opsgenie.client.model.schedule.ListSchedulesRequest)
     */
    @Override
    public ListSchedulesResponse listSchedules(ListSchedulesRequest listSchedulesRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListSchedulesResponse) httpClient.doGetRequestV2(listSchedulesRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#exportSchedule(com.ifountain.opsgenie.client.model.schedule.ExportScheduleRequest)
     */
    @Override
    public ExportScheduleResponse exportSchedule(ExportScheduleRequest exportScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ExportScheduleResponse) streamOpsgenieHttpClient.doGetRequest(exportScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addScheduleOverride(com.ifountain.opsgenie.client.model.schedule.AddScheduleOverrideRequest)
     */
    @Override
    public AddScheduleOverrideResponse addScheduleOverride(AddScheduleOverrideRequest addScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddScheduleOverrideResponse) httpClient.doPostRequest(addScheduleOverrideRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteScheduleOverride(com.ifountain.opsgenie.client.model.schedule.DeleteScheduleOverrideRequest)
     */
    @Override
    public DeleteScheduleOverrideResponse deleteScheduleOverride(DeleteScheduleOverrideRequest deleteScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteScheduleOverrideResponse) httpClient.doDeleteRequest(deleteScheduleOverrideRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateScheduleOverride(com.ifountain.opsgenie.client.model.schedule.UpdateScheduleOverrideRequest)
     */
    @Override
    public UpdateScheduleOverrideResponse updateScheduleOverride(UpdateScheduleOverrideRequest updateScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateScheduleOverrideResponse) httpClient.doPostRequest(updateScheduleOverrideRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getScheduleOverride(com.ifountain.opsgenie.client.model.schedule.GetScheduleOverrideRequest)
     */
    @Override
    public GetScheduleOverrideResponse getScheduleOverride(GetScheduleOverrideRequest getScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetScheduleOverrideResponse) httpClient.doGetRequest(getScheduleOverrideRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listScheduleOverrides(com.ifountain.opsgenie.client.model.schedule.ListScheduleOverridesRequest)
     */
    @Override
    public ListScheduleOverridesResponse listScheduleOverrides(ListScheduleOverridesRequest listScheduleOverridesRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListScheduleOverridesResponse) httpClient.doGetRequest(listScheduleOverridesRequest);
    }
}
