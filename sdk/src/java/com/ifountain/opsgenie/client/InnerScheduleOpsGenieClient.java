package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.schedule.*;

import java.io.IOException;
import java.text.ParseException;

/**
* Inner Schedule Client
*/
public class InnerScheduleOpsGenieClient implements IScheduleOpsGenieClient{
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
        return (AddScheduleResponse) httpClient.doPostRequest(addScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateSchedule(com.ifountain.opsgenie.client.model.schedule.UpdateScheduleRequest)
     */
    @Override
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest updateScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateScheduleResponse) httpClient.doPostRequest(updateScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteSchedule(com.ifountain.opsgenie.client.model.schedule.DeleteScheduleRequest)
     */
    @Override
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest deleteScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteScheduleResponse) httpClient.doDeleteRequest(deleteScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#getSchedule(com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest)
     */
    @Override
    public GetScheduleResponse getSchedule(GetScheduleRequest getScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetScheduleResponse) httpClient.doGetRequest(getScheduleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#whoIsOnCall(com.ifountain.opsgenie.client.model.schedule.WhoIsOnCallRequest)
     */
    @Override
    public WhoIsOnCallResponse whoIsOnCall(WhoIsOnCallRequest whoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException {
        return (WhoIsOnCallResponse) httpClient.doGetRequest(whoIsOnCallRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest)
     */
    @Override
    public ListWhoIsOnCallResponse listWhoIsOnCall(ListWhoIsOnCallRequest listWhoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListWhoIsOnCallResponse) httpClient.doGetRequest(listWhoIsOnCallRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(com.ifountain.opsgenie.client.model.schedule.ListSchedulesRequest)
     */
    @Override
    public ListSchedulesResponse listSchedules(ListSchedulesRequest listSchedulesRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListSchedulesResponse) httpClient.doGetRequest(listSchedulesRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#exportSchedule(com.ifountain.opsgenie.client.model.schedule.ExportScheduleRequest)
     */
    @Override
    public ExportScheduleResponse exportSchedule(ExportScheduleRequest exportScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ExportScheduleResponse) streamOpsgenieHttpClient.doGetRequest(exportScheduleRequest);
    }
}
