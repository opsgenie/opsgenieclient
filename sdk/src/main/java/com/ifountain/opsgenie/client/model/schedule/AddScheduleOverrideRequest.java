package com.ifountain.opsgenie.client.model.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * ontainer for the parameters to make an add schedule override api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 8:54 AM
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addScheduleOverride(AddScheduleOverrideRequest)
 */
public class AddScheduleOverrideRequest extends BaseRequest<AddScheduleOverrideResponse> {
    private String alias;
    private String schedule;
    private String user;
    private Date startDate;
    private Date endDate;
    private TimeZone timeZone;
    private List<String> rotationIds;

    /**
     * Rest api uri of schedule override create operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule/override";
    }

    /**
     * A user defined identifier for the schedule override.
     * Provides ability to assign a known id and later use this id to perform additional actions for the same override.
     * If an override exists with specified alias for from user, it will update existing one.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the schedule override.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }


    /**
     * Id or the name of the schedule that the override will belong to.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the id or the name of the schedule that the override will belong to.
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /*
     * Rotation id list that override will apply.
     */
    public List<String> getRotationIds() {
        return rotationIds;
    }

    /*
     * * Sets the rotation id list that override will apply.
     */
    public void setRotationIds(List<String> rotationId) {
        this.rotationIds = rotationId;
    }

    /**
     * Username of user whom the override will be created for.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the username of user whom the override will be created for
     */
    public void setUser(String user) {
        this.user = user;
    }


    /**
     * Start date of the schedule override.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the schedule override.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of the schedule override.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of the schedule override.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Timezone to determine forwarding start and end dates. If not given GMT is used.
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine schedule override start and end dates. If not given GMT is used.
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
		Map json = new HashMap();
		if (getApiKey() != null) 
			json.put(OpsGenieClientConstants.API.API_KEY, getApiKey());
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        TimeZone tz;
        if (getTimeZone() != null) {
            tz = getTimeZone();

        } else {
            tz = TimeZone.getTimeZone("GMT");
        }
        sdf.setTimeZone(tz);
        json.put(OpsGenieClientConstants.API.TIMEZONE, tz.getID());
        if (getEndDate() != null) {
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(getEndDate()));
        }
        if (getStartDate() != null) {
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(getStartDate()));
        }
        if (user != null) {
            json.put(OpsGenieClientConstants.API.USER, getUser());
        }
        if (alias != null) {
            json.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        }
        if (schedule != null) {
            json.put(OpsGenieClientConstants.API.SCHEDULE, getSchedule());
        }
        if (rotationIds != null) {
            json.put(OpsGenieClientConstants.API.ROTATION_IDS, getRotationIds());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddScheduleOverrideResponse createResponse() {
        return new AddScheduleOverrideResponse();
    }
}
