package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.AlertRecipient;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for a list alert  recipients request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#listAlertRecipients(ListAlertRecipientsRequest)
 */
public class ListAlertRecipientsResponse extends BaseResponse {
    private List<AlertRecipient> users;
    private Map<String, List<AlertRecipient>> groups;

    /**
     * Returns alert recipients with type user
     * @see com.ifountain.client.opsgenie.model.beans.AlertRecipient
     */
    public List<AlertRecipient> getUsers() {
        return users;
    }

    /**
     * Sets alert recipients with type user
     * @see com.ifountain.client.opsgenie.model.beans.AlertRecipient
     */
    public void setUsers(List<AlertRecipient> users) {
        this.users = users;
    }

    /**
     * Returns alert recipients with type group
     * @see com.ifountain.client.opsgenie.model.beans.AlertRecipient
     */
    public Map<String, List<AlertRecipient>> getGroups() {
        return groups;
    }

    /**
     * Sets alert recipients with type group
     * @see com.ifountain.client.opsgenie.model.beans.AlertRecipient
     */
    public void setGroups(Map<String, List<AlertRecipient>> groups) {
        this.groups = groups;
    }

    /**
     * @see com.ifountain.client.model.BaseResponse#deserialize(java.util.Map)
     * @param data
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        List<Map<String, Object>> usersData = (List<Map<String, Object>>) data.get(ClientConstants.API.USERS);
        Map<String, List<Map<String, Object>>> groupsData = (Map<String, List<Map<String, Object>>>) data.get(ClientConstants.API.GROUPS);
        users = new ArrayList<AlertRecipient>();
        groups = new HashMap<String, List<AlertRecipient>>();
        for(Map<String, Object> userData:usersData){
            AlertRecipient recipient = new AlertRecipient();
            recipient.fromMap(userData);
            users.add(recipient);
        }
        for(Map.Entry<String, List<Map<String, Object>>> groupData:groupsData.entrySet()){
            List<AlertRecipient> groupRecipients = new ArrayList<AlertRecipient>();
            for(Map<String, Object> userData:groupData.getValue()){
                AlertRecipient recipient = new AlertRecipient();
                recipient.fromMap(userData);
                groupRecipients.add(recipient);
            }
            groups.put(groupData.getKey(), groupRecipients);
        }
    }
}
