package com.ifountain.opsgenie.client.model.schedule;

/**
 * Container for the parameters to make an update schedule override api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 11:13 AM
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateScheduleOverride(UpdateScheduleOverrideRequest)
 */
public class UpdateScheduleOverrideRequest extends AddScheduleOverrideRequest{
    @Override
    public UpdateScheduleOverrideResponse createResponse() {
        return new UpdateScheduleOverrideResponse();
    }
}
