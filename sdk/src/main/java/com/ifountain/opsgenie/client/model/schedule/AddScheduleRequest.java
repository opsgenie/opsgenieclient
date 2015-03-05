package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.ScheduleLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
 */
public class AddScheduleRequest extends BaseRequest<AddScheduleResponse> {
    private String name;
    private Boolean enabled = null;
    private TimeZone timeZone;
    private List<ScheduleLayer> layers;


    /**
     * Rest api uri of addding schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * Name of schedule
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Layers of schedule
     */
    public List<ScheduleLayer> getLayers() {
        return layers;
    }

    /**
     * Sets layers of schedule
     */
    public void setLayers(List<ScheduleLayer> layers) {
        this.layers = layers;
    }

    /**
     * Returns enable/disable state of schedule
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable/disable state of schedule
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Timezone of schedule
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone of schedule
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        if(enabled != null){
            json.put(OpsGenieClientConstants.API.ENABLED, enabled);
        }
        if(timeZone != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, timeZone.getID());
        }
        if(layers != null){
            List<Map> layerMaps = new ArrayList<Map>();
            for(ScheduleLayer layer: layers){
                layer.setScheduleTimeZone(getTimeZone());
                layerMaps.add(layer.toMap());
            }
            json.put(OpsGenieClientConstants.API.LAYERS, layerMaps);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddScheduleResponse createResponse() {
        return new AddScheduleResponse();
    }
}
