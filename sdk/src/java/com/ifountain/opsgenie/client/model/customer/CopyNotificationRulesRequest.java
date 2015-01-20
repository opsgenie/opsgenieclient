package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make a copy notification rules api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#copyNotificationRules(com.ifountain.opsgenie.client.model.customer.CopyNotificationRulesRequest)
 */
public class CopyNotificationRulesRequest extends BaseRequest<CopyNotificationRulesResponse> {
    private String fromUser;
    private List<String> toUsers;
    private List<String> ruleTypes;

    /**
     * Username of the template user. This user's notification rules will be used for copying.
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets username of the template user. his user's notification rules will be used for copying.
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Gets the list of users that rules will be copied to.
     */
    public List<String> getToUsers() {
        return toUsers;
    }

    /**
     * Sets the user list to copy to. Specify a list of the users which you want to copy the rules to. You can use the username of a user, the name of a group, or to copy to all users, "all".
     */
    public void setToUsers(List<String> toUsers) {
        this.toUsers = toUsers;
    }

    /**
     * Gets the list of rule types
     */
    public List<String> getRuleTypes() {
        return ruleTypes;
    }

    /**
     *  Sets the list of rule types. Specify a list of the action types you want to copy the rules of. It can contain "New Alert", "Acknowledged Alert" or for all types of notification rules, "all". The total list of valid types are:
     *  "all",
     *  "New Alert",
     *  "Acknowledged Alert",
     *  "Closed Alert",
     *  "Schedule Start",
     *  "Renotified Alert",
     *  "Assigned Alert",
     *  "Add Note"
     */
    public void setRuleTypes(List<String> ruleTypes) {
        this.ruleTypes = ruleTypes;
    }

    /**
     * Rest api uri of copy notification rules operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/copyNotificationRules";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public CopyNotificationRulesResponse createResponse() {
        return new CopyNotificationRulesResponse();
    }

    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map res = super.serialize();
        if(fromUser != null){
            res.put(OpsGenieClientConstants.API.FROM_USER, fromUser);
        }
        if(toUsers != null){
            res.put(OpsGenieClientConstants.API.TO_USERS, toUsers);
        }
        if(ruleTypes != null){
            res.put(OpsGenieClientConstants.API.RULE_TYPES, ruleTypes);
        }
        return res;
    }
}
