package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingRequest extends BaseRequest<AddForwardingResponse> {
    private String alias;
    private String fromUser;
    private String toUser;
    private Date startDate;
    private Date endDate;
    private TimeZone timeZone;

    /**
     * Rest api uri of forwarding create operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * A user defined identifier for the forwarding.
     * Provides ability to assign a known id and later use this id to perform additional actions for the same forwarding.
     * If a forwarding exists with specified alias for from user, it will update existing one.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the forwarding.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Username of user  which forwarding will be created for
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets Username of user who forwarding will be created for
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username of user who forwarding will be directed to
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Sets username of user who forwarding will be directed to
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * Start date of forwarding will be started
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of forwarding will be started
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of forwarding will be discarded
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of forwarding will be discarded
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Timezone to determine forwarding start and end dates
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine forwarding start and end dates
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
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (getTimeZone() != null) {
            sdf.setTimeZone(getTimeZone());
            json.put(OpsGenieClientConstants.API.TIMEZONE, getTimeZone().getID());
        }
        if (getEndDate() != null) {
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(getEndDate()));
        }
        if (getStartDate() != null) {
            json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(getStartDate()));
        }
        if(fromUser != null){
            json.put(OpsGenieClientConstants.API.FROM_USER, getFromUser());
        }
        if(toUser != null){
            json.put(OpsGenieClientConstants.API.TO_USER, getToUser());
        }
        if(alias != null){
            json.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddForwardingResponse createResponse() {
        return new AddForwardingResponse();
    }
}
