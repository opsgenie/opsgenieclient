package com.ifountain.opsgenie.client.model;

import java.io.File;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 3:38 PM
 */
public class AttachRequest extends BaseRequest {
    private String alertId;
    private String alias;
    private String indexFile;
    private File file;
    private String user;
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/attach";
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIndexFile() {
        return indexFile;
    }

    public void setIndexFile(String indexFile) {
        this.indexFile = indexFile;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
