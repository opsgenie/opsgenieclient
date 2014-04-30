package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Escalation bean
 */
public class Escalation  implements IBean {
    private String id;
    private String name;
    private List<EscalationRule> rules;

    /**
     * Id of escalation
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation
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
     * Sets the name of escalation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Rules of escalation
     * @see EscalationRule
     */
    public List<EscalationRule> getRules() {
        return rules;
    }

    /**
     * Sets the rules of escalation
     * @see EscalationRule
     */
    public void setRules(List<EscalationRule> rules) {
        this.rules = rules;
    }

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(ClientConstants.API.ID, id);
        json.put(ClientConstants.API.NAME, name);
        if(rules != null){
            List<Map> ruleMaps = new ArrayList<Map>();
            for(EscalationRule escalationRule:rules){
                ruleMaps.add(escalationRule.toMap());
            }
            json.put(ClientConstants.API.RULES, ruleMaps);
        }
        return json;
    }

    @Override
    public void fromMap(Map resp) {
        setId((String) resp.get(ClientConstants.API.ID));
        setName((String) resp.get(ClientConstants.API.NAME));
        List<Map> ruleMaps = (List<Map>) resp.get(ClientConstants.API.RULES);
        if(ruleMaps != null){
            rules = new ArrayList<EscalationRule>();
            for(Map ruleMap:ruleMaps){
                EscalationRule escalationRule = new EscalationRule();
                escalationRule.fromMap(ruleMap);
                rules.add(escalationRule);
            }
        }
    }
}
