package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add forwarding api call.
 *
 * @author Mustafa Sener
 * @version 22.03.2013 08:46
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
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
     * If a forwarding exists with specified alias for user, it will update existing one.
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
     * Username of user whose notifications will be forwarded
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets Username of user whose notifications will be forwarded
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username of user who will recieve forwarded notifications
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Sets username of user who will recieve forwarded notifications
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        SimpleDateFormat sdf = new SimpleDateFormat(ClientConstants.Common.API_DATE_FORMAT);
        if (getTimeZone() != null) {
            sdf.setTimeZone(getTimeZone());
            json.put(ClientConstants.API.TIMEZONE, getTimeZone().getID());
        }
        if (getEndDate() != null) {
            json.put(ClientConstants.API.END_DATE, sdf.format(getEndDate()));
        }
        if (getStartDate() != null) {
            json.put(ClientConstants.API.START_DATE, sdf.format(getStartDate()));
        }
        if(fromUser != null){
            json.put(ClientConstants.API.FROM_USER, getFromUser());
        }
        if(toUser != null){
            json.put(ClientConstants.API.TO_USER, getToUser());
        }
        if(alias != null){
            json.put(ClientConstants.API.ALIAS, getAlias());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public AddForwardingResponse createResponse() {
        return new AddForwardingResponse();
    }
}
