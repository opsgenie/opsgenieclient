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
     * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listSchedules(com.ifountain.opsgenie.client.model.schedule.ListScheduleRequest)
     */
    @Override
    public ListScheduleResponse listSchedules(ListScheduleRequest listScheduleRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListScheduleResponse) httpClient.doGetRequest(listScheduleRequest);
    }

}
