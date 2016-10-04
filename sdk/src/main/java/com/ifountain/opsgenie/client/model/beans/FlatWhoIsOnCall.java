package com.ifountain.opsgenie.client.model.beans;

import java.util.List;

/**
 * FlatWhoIsOnCall bean
 */
public class FlatWhoIsOnCall extends BaseWhoIsOnCall {
    private List<String> recipients;
    private Boolean isEnabled;

    /**
     * OnCall recipients
     *
     * @return
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets OnCall recipients
     *
     * @param recipients
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
