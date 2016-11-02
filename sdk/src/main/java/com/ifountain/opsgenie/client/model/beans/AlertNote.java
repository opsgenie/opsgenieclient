package com.ifountain.opsgenie.client.model.beans;

/**
 * Alert bean
 */
public class AlertNote extends Bean {
    private String owner;
    private String note;
    private long createdAt;

    public static com.ifountain.opsgenie.client.model.beans.AlertNote anAlertNote() {
        return new com.ifountain.opsgenie.client.model.beans.AlertNote();
    }

    /**
     * @return Owner of the comment
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the comment
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return The note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return The creation time of the note in nanoseconds
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of the note in nanoseconds
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public AlertNote withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public AlertNote withNote(String note) {
        this.note = note;
        return this;
    }

    public AlertNote withCreatedAt(long createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
