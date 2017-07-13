package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.io.File;

/**
 * Container for the parameters to make an file based attach api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#addAlertAttachment(com.ifountain.opsgenie.client.model.customer.AddAlertAttachmentRequest)
 */
public class AddAlertAttachmentRequest extends BaseRequest<AddAlertAttachmentResponse, AddAlertAttachmentRequest> {
    private String alertIdentifier;
    private AlertIdentifierType alertIdentifierType = AlertIdentifierType.ID;
    private File file;
    private String user;
    private String indexFile;

    /**
     * Rest api uri of adding heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/alerts/" + alertIdentifier + "/attachments";
    }

    @Override
    public AddAlertAttachmentResponse createResponse() {
        return new AddAlertAttachmentResponse();
    }

    /**
     * Return identifier of the alert that attachment will be added
     */
    public String getAlertIdentifier() {
        return alertIdentifier;
    }

    /**
     * Sets identifier of the alert that attachment will be added
     */
    public void setAlertIdentifier(String alertIdentifier) {
        this.alertIdentifier = alertIdentifier;
    }

    public AddAlertAttachmentRequest withAlertIdentifier(String alertIdentifier) {
        this.alertIdentifier = alertIdentifier;
        return this;
    }

    /**
     * Return identifier type of the alert that attachment will be added
     */
    public AlertIdentifierType getAlertIdentifierType() {
        return alertIdentifierType;
    }

    /**
     * Sets identifier type of the alert that attachment wll be added
     */
    public void setAlertIdentifierType(AlertIdentifierType alertIdentifierType) {
        this.alertIdentifierType = alertIdentifierType;
    }

    public AddAlertAttachmentRequest withAlertIdentifierType(AlertIdentifierType alertIdentifierType) {
        this.alertIdentifierType = alertIdentifierType;
        return this;
    }

    /**
     * Return attached file
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets attached file
     */
    public void setFile(File file) {
        this.file = file;
    }

    public AddAlertAttachmentRequest withFile(File file) {
        this.file = file;
        return this;
    }

    /**
     * Return default owner of the execution
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets default owner of the execution
     */
    public void setUser(String user) {
        this.user = user;
    }

    public AddAlertAttachmentRequest withUser(String user) {
        this.user = user;
        return this;
    }

    /**
     * Return name of html file which will be shown when attachment clicked on UI
     */
    public String getIndexFile() {
        return indexFile;
    }

    /**
     * Sets name of html file which will be shown when attachment clicked on UI
     */
    public void setIndexFile(String indexFile) {
        this.indexFile = indexFile;
    }

    public AddAlertAttachmentRequest withIndexFile(String indexFile) {
        this.indexFile = indexFile;
        return this;
    }

    public enum AlertIdentifierType {
        ID("id"), TINY("tiny"), ALIAS("alias");

        String value;

        AlertIdentifierType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}


