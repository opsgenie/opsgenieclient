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
     * List schedules of customer
     *
     * @param listScheduleRequest Object to construct request parameters.
     * @return <code>ListScheduleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.schedule.ListScheduleRequest
     * @see com.ifountain.opsgenie.client.model.schedule.ListScheduleResponse
     */
    public ListScheduleResponse listSchedules(ListScheduleRequest listScheduleRequest) throws IOException, OpsGenieClientException, ParseException;
}
