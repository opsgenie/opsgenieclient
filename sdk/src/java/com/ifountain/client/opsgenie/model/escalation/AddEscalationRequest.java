package com.ifountain.client.opsgenie.model.escalation;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.opsgenie.model.beans.EscalationRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make an add escalation api call.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 09:38
 * @see com.ifountain.client.opsgenie.IEscalationOpsGenieClient#addEscalation(AddEscalationRequest)
 */
public class AddEscalationRequest extends BaseRequest<AddEscalationResponse> {
    private String name;
    private List<EscalationRule> rules;

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
     * @see com.ifountain.client.opsgenie.model.beans.EscalationRule
     */
    public List<EscalationRule> getRules() {
        return rules;
    }

    /**
     * Sets rules of escalation
     * @see com.ifountain.client.opsgenie.model.beans.EscalationRule
     */
    public void setRules(List<EscalationRule> rules) {
        this.rules = rules;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(name != null){
            json.put(ClientConstants.API.NAME, getName());
        }
        if(getRules() != null){
            List<Map> ruleMaps = new ArrayList<Map>();
            for(EscalationRule rule:rules){
                ruleMaps.add(rule.toMap());
            }
            json.put(ClientConstants.API.RULES, ruleMaps);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public AddEscalationResponse createResponse() {
        return new AddEscalationResponse();
    }
}
