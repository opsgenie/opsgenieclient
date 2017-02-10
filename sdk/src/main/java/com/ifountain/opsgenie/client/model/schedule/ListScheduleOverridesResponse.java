package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.ScheduleOverride;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Represents OpsGenie service response for list schedule overrides request.
 *
 * @author Sezgin Kucukkaraaslan
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


    /**
     * @deprecated use getOverrides
     */
    @Deprecated
    @JsonIgnore
    public List<ScheduleOverride> getScheduleOverides() {
        return overrides;
    }

    /**
     * @deprecated use setOverrides
     */
    @Deprecated
    @JsonIgnore
    public void setScheduleOverides(List<ScheduleOverride> overrides) {
        this.overrides = overrides;
    }

    @Override
    public void fromJson(String json) throws IOException, ParseException {
        super.fromJson(json);
        if (overrides != null)
            for (ScheduleOverride override : overrides)
                override.setTime();
    }

}
