package com.ifountain.opsgenie.client.model.beans;

import java.util.List;

/**
 * FlatWhoIsOnCall bean
 */
public class FlatWhoIsOnCall extends BaseWhoIsOnCall {
    private List<String> recipients;

    /**
     * OnCall recipients
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets OnCall recipients
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

}
