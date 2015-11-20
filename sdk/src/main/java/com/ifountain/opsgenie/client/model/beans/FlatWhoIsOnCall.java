package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * FlatWhoIsOnCall bean
 */
public class FlatWhoIsOnCall extends BaseWhoIsOnCall{
    private List<String> recipients;

    /**
     * OnCall recipients
     * @return
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets OnCall recipients
     * @param recipients
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public Map toMap() {
        Map json = super.toMap();
        json.put(OpsGenieClientConstants.API.RECIPIENTS, recipients);
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        super.fromMap(map);
        recipients = (List<String>) map.get(OpsGenieClientConstants.API.RECIPIENTS);
    }
}
