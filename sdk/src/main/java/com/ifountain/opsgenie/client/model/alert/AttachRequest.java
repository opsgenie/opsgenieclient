package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an attach api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#attach(FileAttachRequest)
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#attach(com.ifountain.opsgenie.client.model.InputStreamAttachRequest)
 */
public abstract class AttachRequest<T extends AttachRequest> extends BaseAlertRequestWithParameters<AttachResponse, T> {
    private String indexFile;

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

    public AttachRequest withIndexFile(String indexFile) {
        this.indexFile = indexFile;
        return this;
    }

    @Override
    @Deprecated
    public Map serialize() throws OpsGenieClientValidationException {
        throw new UnsupportedOperationException("unsupported method serialize");
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AttachResponse createResponse() {
        return new AttachResponse();
    }
}
