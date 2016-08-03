package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.EscalationRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make an add escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#addEscalation(AddEscalationRequest)
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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
		Map json = new HashMap();
		if (getApiKey() != null) 
			json.put(OpsGenieClientConstants.API.API_KEY, getApiKey());
        if(name != null)
            json.put(OpsGenieClientConstants.API.NAME, getName());
        if(getRules() != null){
            List<Map> ruleMaps = new ArrayList<Map>();
            for(EscalationRule rule:rules){
                ruleMaps.add(rule.toMap());
            }
            json.put(OpsGenieClientConstants.API.RULES, ruleMaps);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddEscalationResponse createResponse() {
        return new AddEscalationResponse();
    }
}
