package com.ifountain.opsgenie.client.model.user.forward;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.beans.BaseUserObj;
import org.apache.commons.lang3.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add forwarding api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingRequest extends BaseRequest<AddForwardingResponse, AddForwardingRequest> implements ObjectWithTimeZone {
    private String alias;
    private BaseUserObj fromUser;
    private BaseUserObj toUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date endDate;
    @JsonProperty("timezone")
    private TimeZone timeZone = TimeZone.getTimeZone("GMT");

    /**
     * Rest api uri of forwarding create operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/forwarding-rules";
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when api key is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (fromUser == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.FROM_USER);
        if(StringUtils.isEmpty(fromUser.getUsername()) && StringUtils.isEmpty(fromUser.getId()))
            throw OpsGenieClientValidationException.error("Either username or id in from user is mandatory");
        if(toUser == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.TO_USER);
        if(StringUtils.isEmpty(toUser.getUsername()) && StringUtils.isEmpty(toUser.getId()))
            throw OpsGenieClientValidationException.error("Either username or id in to user is mandatory");
        if(startDate == null || endDate == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.START_DATE,OpsGenieClientConstants.API.END_DATE);
        if(startDate.before(Calendar.getInstance().getTime()))
            throw OpsGenieClientValidationException.error("Start Time can not be before now.");
        if(startDate.after(endDate))
            throw OpsGenieClientValidationException.error("End time should be later than start time.");
    }

    /**
     * A user defined identifier for the forwarding. Provides ability to assign
     * a known id and later use this id to perform additional actions for the
     * same forwarding. If a forwarding exists with specified alias for from
     * user, it will update existing one.
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
     * Username and id of user which forwarding will be created for
     */
    public BaseUserObj getFromUser() {
        return fromUser;
    }

    /**
     * Sets Username and id of user who forwarding will be created for
     */
    public void setFromUser(BaseUserObj fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username and id of user who forwarding will be directed to
     */
    public BaseUserObj getToUser() {
        return toUser;
    }

    /**
     * Sets user whom forwarding will be directed to
     */
    public void setToUser(BaseUserObj toUser) {
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
     * Timezone to determine forwarding start and end dates. If not given GMT is
     * used.
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine forwarding start and end dates. If not given
     * GMT is used.
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddForwardingResponse createResponse() {
        return new AddForwardingResponse();
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }

    public AddForwardingRequest withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public AddForwardingRequest withFromUser(BaseUserObj fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public AddForwardingRequest withToUser(BaseUserObj toUser) {
        this.toUser = toUser;
        return this;
    }

    public AddForwardingRequest withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public AddForwardingRequest withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public AddForwardingRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

}
