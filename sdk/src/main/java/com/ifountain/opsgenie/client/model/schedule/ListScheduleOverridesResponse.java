package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.ScheduleOverride;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Represents OpsGenie service response for list schedule overrides request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listScheduleOverrides(ListScheduleOverridesRequest)
 */
public class ListScheduleOverridesResponse extends BaseResponse {
    @JsonProperty("overrides")
    private List<ScheduleOverride> scheduleOverides;

    /**
     * Gets override objects.
     */
    public List<ScheduleOverride> getScheduleOverides() {
        return scheduleOverides;
    }

    /**
     * Sets override objects.
     */
    public void setScheduleOverides(List<ScheduleOverride> scheduleOverides) {
        this.scheduleOverides = scheduleOverides;
    }

}
