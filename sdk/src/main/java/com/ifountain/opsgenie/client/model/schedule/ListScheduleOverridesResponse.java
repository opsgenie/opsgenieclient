package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.ScheduleOverride;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list schedule overrides request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 10:42 AM
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listScheduleOverrides(ListScheduleOverridesRequest)
 */
public class ListScheduleOverridesResponse extends BaseResponse{
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

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        scheduleOverides = new ArrayList<ScheduleOverride>();
        if (data.containsKey(OpsGenieClientConstants.API.OVERRIDES)) {
            List<Map> overrideMaps = (List<Map>) data.get(OpsGenieClientConstants.API.OVERRIDES);
            for (Map overrideMap : overrideMaps) {
                ScheduleOverride override = new ScheduleOverride();
                override.fromMap(overrideMap);
                scheduleOverides.add(override);
            }
        }
    }
}
