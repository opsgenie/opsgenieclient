package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientValidationException;

import java.io.File;
import java.util.Map;

/**
 * Container for the parameters to make a file based attach api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:38 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#attach(FileAttachRequest)
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

    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        throw new UnsupportedOperationException("unsupported method serialize");
    }
}
