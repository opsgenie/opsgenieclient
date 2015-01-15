package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Escalation bean
 */
public class Escalation  implements IBean{
    private String id;
    private String name;
    private String team;
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

    /**
     * Name of the team that escalation belongs to, if any
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the name of the team that escalation belongs to.
     */
    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(OpsGenieClientConstants.API.ID, id);
        json.put(OpsGenieClientConstants.API.NAME, name);
        json.put(OpsGenieClientConstants.API.TEAM, team);
        if(rules != null){
            List<Map> ruleMaps = new ArrayList<Map>();
            for(EscalationRule escalationRule:rules){
                ruleMaps.add(escalationRule.toMap());
            }
            json.put(OpsGenieClientConstants.API.RULES, ruleMaps);
        }
        return json;
    }

    @Override
    public void fromMap(Map resp) {
        setId((String) resp.get(OpsGenieClientConstants.API.ID));
        setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        setTeam((String) resp.get(OpsGenieClientConstants.API.TEAM));
        List<Map> ruleMaps = (List<Map>) resp.get(OpsGenieClientConstants.API.RULES);
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
