package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.AlertRecipient;

import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for a list alert recipients request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertRecipients(ListAlertRecipientsRequest)
 * @deprecated As of release 2.8.0, will not be supported
 */
@Deprecated
public class ListAlertRecipientsResponse extends BaseResponse {
    private List<AlertRecipient> users;
    private Map<String, List<AlertRecipient>> groups;

    /**
     * Returns alert recipients with type user
     *
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public List<AlertRecipient> getUsers() {
        return users;
    }

    /**
     * Sets alert recipients with type user
     *
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public void setUsers(List<AlertRecipient> users) {
        this.users = users;
    }

    /**
     * Returns alert recipients with type group
     *
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public Map<String, List<AlertRecipient>> getGroups() {
        return groups;
    }

    /**
     * Sets alert recipients with type group
     *
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public void setGroups(Map<String, List<AlertRecipient>> groups) {
        this.groups = groups;
    }

}
