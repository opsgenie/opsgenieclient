package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.io.File;

/**
 * Container for the parameters to make an file based attach api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:38 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#attach(FileAttachRequest)
 */
public class FileAttachRequest extends AttachRequest {
    private File file;

    public FileAttachRequest() {
    }

    /**
     * The file that will be attached to the specified alert.
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the file that will be attached to the specified alert.
     */
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        throw new UnsupportedOperationException("unsupported method serialize");
    }

}
