package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.EscalationRule;

import java.util.List;

/**
 * Container for the parameters to make an add escalation api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#addEscalation(AddEscalationRequest)
 */
public class AddEscalationRequest extends BaseRequest<AddEscalationResponse> {
    private String name;
    private List<EscalationRule> rules;
    private String team;
    private String description;
    private Integer repeatInterval;

    /**
     * Rest api uri of addding escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    /**
     * Name of escalation
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of escalation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Rules of escalation
     */
    public List<EscalationRule> getRules() {
        return rules;
    }

    /**
     * Sets rules of escalation
     */
    public void setRules(List<EscalationRule> rules) {
        this.rules = rules;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddEscalationResponse createResponse() {
        return new AddEscalationResponse();
    }

    /**
     * Name of team
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets Name of team
     */
    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
