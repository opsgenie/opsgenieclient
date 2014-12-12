package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an add team call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/12/2014 4:18 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addTeam(AddAlertTeamRequest)
 */
public class AddAlertTeamRequest extends BaseAlertRequestWithSource<AddAlertTeamResponse>{
    private String user;
    private String team;
    private String note;

    /**
     * Rest api uri of add team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/team";
    }

    /**
     * The user who is performing the add team operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add team operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * The team that will be added.
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the team that will be added.
     */
    public void setTeam(String team) {
        this.team = team;
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

    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.TEAM, getTeam());
        if (getUser() != null)
            json.put(OpsGenieClientConstants.API.USER, getUser());
        if (getNote() != null)
            json.put(OpsGenieClientConstants.API.NOTE, getNote());
        return json;
    }

    public AddAlertTeamResponse createResponse() {
        return new AddAlertTeamResponse();
    }
}
