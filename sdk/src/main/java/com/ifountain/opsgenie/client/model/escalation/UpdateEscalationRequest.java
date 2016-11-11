package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.EscalationRule;

import java.util.List;

/**
 * Container for the parameters to make an update escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#updateEscalation(UpdateEscalationRequest)
 */
public class UpdateEscalationRequest extends BaseRequest<UpdateEscalationResponse, UpdateEscalationRequest> {
    private String id;
    private String name;
    private List<EscalationRule> rules;
    private String team;
    private String description;
    private Integer repeatInterval;

    /**
     * Id of escalation to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation to be updated.
     */
    public void setId(String id) {
        this.id = id;
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

    public UpdateEscalationRequest withName(String name) {
        this.name = name;
        return this;
    }

    public UpdateEscalationRequest withRules(List<EscalationRule> rules) {
        this.rules = rules;
        return this;
    }

    public UpdateEscalationRequest withTeam(String team) {
        this.team = team;
        return this;
    }

    public UpdateEscalationRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public UpdateEscalationRequest withRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
        return this;
    }

    public UpdateEscalationRequest withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Rest api uri of updating escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateEscalationResponse createResponse() {
        return new UpdateEscalationResponse();
    }
}
