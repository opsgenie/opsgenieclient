package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.ScheduleOverride;

import java.util.List;

/**
 * Represents OpsGenie service response for list schedule overrides request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listScheduleOverrides(ListScheduleOverridesRequest)
 */
public class ListScheduleOverridesResponse extends BaseResponse {
    private List<ScheduleOverride> overrides;

    /**
     * Gets override objects.
     */
    public List<ScheduleOverride> getOverrides() {
        return overrides;
    }

    /**
     * Sets override objects.
     */
    public void setOverrides(List<ScheduleOverride> overrides) {
        this.overrides = overrides;
    }

}
