package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.schedule.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for schedule related operations
 *
 * @see OpsGenieClient
 */
public interface IScheduleOpsGenieClient {
    /**
     * Adds a schedule at OpsGenie.
     *
     * @param addScheduleRequest Object to construct request parameters.
     * @return <code>AddScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.schedule.AddScheduleRequest
     * @see com.ifountain.client.opsgenie.model.schedule.AddScheduleResponse
     */
    public AddScheduleResponse addSchedule(AddScheduleRequest addScheduleRequest) throws IOException, ClientException, ParseException;

    /**
     * Updates schedule at OpsGenie.
     *
     * @param updateScheduleRequest Object to construct request parameters.
     * @return <code>UpdateScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.schedule.UpdateScheduleRequest
     * @see com.ifountain.client.opsgenie.model.schedule.UpdateScheduleResponse
     */
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest updateScheduleRequest) throws IOException, ClientException, ParseException;

    /**
     * Deletes a schedule at OpsGenie.
     *
     * @param deleteScheduleRequest Object to construct request parameters.
     * @return <code>DeleteScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.schedule.DeleteScheduleRequest
     * @see com.ifountain.client.opsgenie.model.schedule.DeleteScheduleResponse
     */
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest deleteScheduleRequest) throws IOException, ClientException, ParseException;

    /**
     * Get schedule details
     *
     * @param getScheduleRequest Object to construct request parameters.
     * @return <code>GetScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.schedule.GetScheduleRequest
     * @see com.ifountain.client.opsgenie.model.schedule.GetScheduleResponse
     */
    public GetScheduleResponse getSchedule(GetScheduleRequest getScheduleRequest) throws IOException, ClientException, ParseException;


    /**
     * Return current on call participant list fo schedules
     *
     * @param whoIsOnCallRequest Object to construct request parameters.
     * @return <code>WhoIsOnCallRequest</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.schedule.WhoIsOnCallRequest
     * @see com.ifountain.client.opsgenie.model.schedule.WhoIsOnCallResponse
     */
    public WhoIsOnCallResponse whoIsOnCall(WhoIsOnCallRequest whoIsOnCallRequest) throws IOException, ClientException, ParseException;

    /**
     * Return current on call participant list of all schedules
     *
     * @param listWhoIsOnCallRequest Object to construct request parameters.
     * @return <code>ListWhoIsOnCallResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.schedule.ListWhoIsOnCallRequest
     * @see com.ifountain.client.opsgenie.model.schedule.ListWhoIsOnCallResponse
     */
    public ListWhoIsOnCallResponse listWhoIsOnCall(ListWhoIsOnCallRequest listWhoIsOnCallRequest) throws IOException, ClientException, ParseException;

    /**
     * List schedules of customer
     *
     * @param listSchedulesRequest Object to construct request parameters.
     * @return <code>ListSchedulesResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.schedule.ListSchedulesRequest
     * @see com.ifountain.client.opsgenie.model.schedule.ListSchedulesResponse
     */
    public ListSchedulesResponse listSchedules(ListSchedulesRequest listSchedulesRequest) throws IOException, ClientException, ParseException;
}
