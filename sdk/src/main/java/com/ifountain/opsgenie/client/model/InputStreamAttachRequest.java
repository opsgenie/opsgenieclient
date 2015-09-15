package com.ifountain.opsgenie.client.model;

import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.model.alert.AttachRequest;
import com.ifountain.opsgenie.client.model.alert.FileAttachRequest;

import java.io.InputStream;

/**
 * Container for the parameters to make an {@link java.io.InputStream} based attach api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:38 PM
 * @see IOpsGenieClient#alert() (InputStreamAttachRequest)
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#attach(FileAttachRequest)
 */
public class InputStreamAttachRequest extends AttachRequest {
    private InputStream inputStream;
    private String fileName;

    public InputStreamAttachRequest() {
    }

    /**
     * The InputStream whose content will be attached to the specified alert.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets the {@link java.io.InputStream} whose content will be attached to the specified alert.
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    /**
     *  The file name of attachment
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *  Sets the file name of attachment
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
