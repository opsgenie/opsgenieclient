package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.beans.RenotifyRecipient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make an renotify alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:17 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#renotify(com.ifountain.opsgenie.client.model.alert.RenotifyRequest)
 */
public class RenotifyRequest extends BaseAlertRequestWithSource<RenotifyResponse> {
    private String user;
    private String note;
    private List<RenotifyRecipient> recipients;

    /**
     * Rest api uri of renotify alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/renotify";
    }

    /**
     * Return recipients
     * @see RenotifyRecipient
     */
    public List<RenotifyRecipient> getRecipients() {
        return recipients;
    }

    /**
     * Recipients list
     * @see RenotifyRecipient
     */
    public void setRecipients(List<RenotifyRecipient> recipients) {
        this.recipients = recipients;
    }

    /**
     * The user who is performing the renotify alert operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the renotify alert operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Additional alert note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional alert note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if (getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, getUser());
        if (getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, getNote());
        if (getRecipients() != null){
            List<String> recipientNames = new ArrayList<String>();
            for(RenotifyRecipient recipient:recipients){
                recipientNames.add(recipient.getRecipient());
            }
            json.put(OpsGenieClientConstants.API.RECIPIENTS, recipientNames);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public RenotifyResponse createResponse() {
        return new RenotifyResponse();
    }
}
