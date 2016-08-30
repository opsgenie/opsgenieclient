package com.ifountain.opsgenie.client.model.alert;

import java.util.List;

/**
 * @author Tuba Ozturk
 * @version 27.7.2015 15:31
 */
public class AddTagsRequest extends BaseAlertRequestWithSource<AddTagsResponse>{
    private String user;
    private String note;
    private List<String> tags;

    /**
     * Rest api uri of add tags operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/tags";
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * The user who is performing the add tags operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add tags operation.
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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddTagsResponse createResponse() {
        return new AddTagsResponse();
    }
}
