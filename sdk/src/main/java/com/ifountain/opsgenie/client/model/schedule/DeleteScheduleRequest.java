package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a delete schedule api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#deleteSchedule(DeleteScheduleRequest)
 */
public class DeleteScheduleRequest extends BaseRequest<DeleteScheduleResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of deleting schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * Id of schedule to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of schedule to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteScheduleResponse createResponse() {
        return new DeleteScheduleResponse();
    }
}
