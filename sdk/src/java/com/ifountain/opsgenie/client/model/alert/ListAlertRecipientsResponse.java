package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Alert;
import com.ifountain.opsgenie.client.model.beans.AlertRecipient;

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
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertRecipients(ListAlertRecipientsRequest)
 */
public class ListAlertRecipientsResponse extends BaseResponse {
    private List<AlertRecipient> users;
    private Map<String, List<AlertRecipient>> groups;

    /**
     * Returns alert recipients with type user
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public List<AlertRecipient> getUsers() {
        return users;
    }

    /**
     * Sets alert recipients with type user
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public void setUsers(List<AlertRecipient> users) {
        this.users = users;
    }

    /**
     * Returns alert recipients with type group
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public Map<String, List<AlertRecipient>> getGroups() {
        return groups;
    }

    /**
     * Sets alert recipients with type group
     * @see com.ifountain.opsgenie.client.model.beans.AlertRecipient
     */
    public void setGroups(Map<String, List<AlertRecipient>> groups) {
        this.groups = groups;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> usersData = (List<Map>) data.get(OpsGenieClientConstants.API.USERS);
        Map<String, List<Map>> groupsData = (Map<String, List<Map>>) data.get(OpsGenieClientConstants.API.GROUPS);
        users = new ArrayList<AlertRecipient>();
        groups = new HashMap<String, List<AlertRecipient>>();
        for(Map userData:usersData){
            AlertRecipient recipient = new AlertRecipient();
            recipient.fromMap(userData);
            users.add(recipient);
        }
        for(Map.Entry<String, List<Map>> groupData:groupsData.entrySet()){
            List<AlertRecipient> groupRecipients = new ArrayList<AlertRecipient>();
            for(Map userData:groupData.getValue()){
                AlertRecipient recipient = new AlertRecipient();
                recipient.fromMap(userData);
                groupRecipients.add(recipient);
            }
            groups.put(groupData.getKey(), groupRecipients);
        }
    }
}
