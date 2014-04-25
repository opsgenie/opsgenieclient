package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an attach api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:38 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#attach(FileAttachRequest)
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#attach(com.ifountain.client.opsgenie.model.InputStreamAttachRequest)
 */
public abstract class AttachRequest extends BaseAlertRequestWithSource<AttachResponse> {
    private String indexFile;
    private String user;
    private String note;

    /**
     * Rest api uri of attach operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/attach";
    }

    public String getIndexFile() {
        return indexFile;
    }

    public void setIndexFile(String indexFile) {
        this.indexFile = indexFile;
    }

    /**
     * The user who is performing attach operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing attach operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Additional alert note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional alert note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        throw new UnsupportedOperationException("unsupported method serialize");
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public AttachResponse createResponse() {
        return new AttachResponse();
    }
}
