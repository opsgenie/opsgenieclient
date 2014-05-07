package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.http.JsonHttpClient;
import com.ifountain.client.opsgenie.model.schedule.*;

import java.io.IOException;
import java.text.ParseException;

/**
* Inner Schedule Client
*/
public class InnerScheduleOpsGenieClient implements IScheduleOpsGenieClient{
    private JsonHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerScheduleOpsGenieClient(JsonHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IScheduleOpsGenieClient#addSchedule(com.ifountain.client.opsgenie.model.schedule.AddScheduleRequest)
     */
    @Override
    public AddScheduleResponse addSchedule(AddScheduleRequest addScheduleRequest) throws IOException, ClientException, ParseException {
        return (AddScheduleResponse) httpClient.doPostRequest(addScheduleRequest);
    }

    /**
     * @see IScheduleOpsGenieClient#updateSchedule(com.ifountain.client.opsgenie.model.schedule.UpdateScheduleRequest)
     */
    @Override
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest updateScheduleRequest) throws IOException, ClientException, ParseException {
        return (UpdateScheduleResponse) httpClient.doPostRequest(updateScheduleRequest);
    }

    /**
     * @see IScheduleOpsGenieClient#deleteSchedule(com.ifountain.client.opsgenie.model.schedule.DeleteScheduleRequest)
     */
    @Override
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest deleteScheduleRequest) throws IOException, ClientException, ParseException {
        return (DeleteScheduleResponse) httpClient.doDeleteRequest(deleteScheduleRequest);
    }

    /**
     * @see IScheduleOpsGenieClient#getSchedule(com.ifountain.client.opsgenie.model.schedule.GetScheduleRequest)
     */
    @Override
    public GetScheduleResponse getSchedule(GetScheduleRequest getScheduleRequest) throws IOException, ClientException, ParseException {
        return (GetScheduleResponse) httpClient.doGetRequest(getScheduleRequest);
    }

    /**
     * @see IScheduleOpsGenieClient#whoIsOnCall(com.ifountain.client.opsgenie.model.schedule.WhoIsOnCallRequest)
     */
    @Override
    public WhoIsOnCallResponse whoIsOnCall(WhoIsOnCallRequest whoIsOnCallRequest) throws IOException, ClientException, ParseException {
        return (WhoIsOnCallResponse) httpClient.doGetRequest(whoIsOnCallRequest);
    }

    /**
     * @see IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.client.opsgenie.model.schedule.ListWhoIsOnCallRequest)
     */
    @Override
    public ListWhoIsOnCallResponse listWhoIsOnCall(ListWhoIsOnCallRequest listWhoIsOnCallRequest) throws IOException, ClientException, ParseException {
        return (ListWhoIsOnCallResponse) httpClient.doGetRequest(listWhoIsOnCallRequest);
    }

    /**
     * @see IScheduleOpsGenieClient#listSchedules(com.ifountain.client.opsgenie.model.schedule.ListSchedulesRequest)
     */
    @Override
    public ListSchedulesResponse listSchedules(ListSchedulesRequest listSchedulesRequest) throws IOException, ClientException, ParseException {
        return (ListSchedulesResponse) httpClient.doGetRequest(listSchedulesRequest);
    }

}
