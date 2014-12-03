package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.schedule.*;

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
     * @see com.ifountain.opsgenie.client.model.schedule.AddScheduleRequest
     * @see com.ifountain.opsgenie.client.model.schedule.AddScheduleResponse
     */
    public AddScheduleResponse addSchedule(AddScheduleRequest addScheduleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates schedule at OpsGenie.
     *
     * @param updateScheduleRequest Object to construct request parameters.
     * @return <code>UpdateScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.UpdateScheduleRequest
     * @see com.ifountain.opsgenie.client.model.schedule.UpdateScheduleResponse
     */
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest updateScheduleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a schedule at OpsGenie.
     *
     * @param deleteScheduleRequest Object to construct request parameters.
     * @return <code>DeleteScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.DeleteScheduleRequest
     * @see com.ifountain.opsgenie.client.model.schedule.DeleteScheduleResponse
     */
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest deleteScheduleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Get schedule details
     *
     * @param getScheduleRequest Object to construct request parameters.
     * @return <code>GetScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.GetScheduleRequest
     * @see com.ifountain.opsgenie.client.model.schedule.GetScheduleResponse
     */
    public GetScheduleResponse getSchedule(GetScheduleRequest getScheduleRequest) throws IOException, OpsGenieClientException, ParseException;


    /**
     * Return current on call participant list fo schedules
     *
     * @param whoIsOnCallRequest Object to construct request parameters.
     * @return <code>WhoIsOnCallRequest</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.WhoIsOnCallRequest
     * @see com.ifountain.opsgenie.client.model.schedule.WhoIsOnCallResponse
     */
    public WhoIsOnCallResponse whoIsOnCall(WhoIsOnCallRequest whoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Return current on call participant list of all schedules
     *
     * @param listWhoIsOnCallRequest Object to construct request parameters.
     * @return <code>ListWhoIsOnCallResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest
     * @see com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallResponse
     */
    public ListWhoIsOnCallResponse listWhoIsOnCall(ListWhoIsOnCallRequest listWhoIsOnCallRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List schedules of customer
     *
     * @param listSchedulesRequest Object to construct request parameters.
     * @return <code>ListSchedulesResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.ListSchedulesRequest
     * @see com.ifountain.opsgenie.client.model.schedule.ListSchedulesResponse
     */
    public ListSchedulesResponse listSchedules(ListSchedulesRequest listSchedulesRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Export schedule to .ics file
     *
     * @param exportScheduleRequest Object to construct request parameters.
     * @return <code>ExportScheduleRequest</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.ExportScheduleRequest
     * @see com.ifountain.opsgenie.client.model.schedule.ExportScheduleResponse
     */
    public ExportScheduleResponse exportSchedule(ExportScheduleRequest exportScheduleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Adds an override for a schedule. All of alert notifications directed the the schedule will be sent to the specified user during configured
     * time settings.
     *
     * @param addScheduleOverrideRequest  Object to construct request parameters
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.AddScheduleOverrideRequest
     * @see com.ifountain.opsgenie.client.model.schedule.AddScheduleOverrideResponse
     */
    public AddScheduleOverrideResponse addScheduleOverride(AddScheduleOverrideRequest addScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes the specified schedule override.
     *
     * @param deleteScheduleOverrideRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.DeleteScheduleOverrideRequest
     * @see com.ifountain.opsgenie.client.model.schedule.DeleteScheduleOverrideResponse
     */
    public DeleteScheduleOverrideResponse deleteScheduleOverride(DeleteScheduleOverrideRequest deleteScheduleOverrideRequest) throws IOException, OpsGenieClientException, ParseException;
}
