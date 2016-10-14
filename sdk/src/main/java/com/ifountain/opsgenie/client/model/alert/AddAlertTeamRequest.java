package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make an add team call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addTeam(AddAlertTeamRequest)
 */
public class AddAlertTeamRequest extends AddNoteRequest {
    private String team;

    /**
     * Rest api uri of add team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/team";
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

    public AddAlertTeamResponse createResponse() {
        return new AddAlertTeamResponse();
    }
}
