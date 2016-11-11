package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * @author Mehmet Mustafa Demir
 */
public abstract class BaseAlertRequestWithNoteAndUser<T extends BaseResponse, K extends BaseAlertRequestWithNoteAndUser> extends BaseAlertRequestWithSource<T, K> {
    private String note;
    private String user;

    /**
     * The note that will be added to alert.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note that will be added to alert.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * The user who is performing the add note operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add note operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    public K withUser(String user) {
        this.user = user;
        return (K) this;
    }

    public K withNote(String note) {
        this.note = note;
        return (K) this;
    }

}
