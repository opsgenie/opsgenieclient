package com.ifountain.opsgenie.client.model.alert;


import com.ifountain.opsgenie.client.util.Strings;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class RemoveTagsRequest extends BaseAlertRequestWithSource<RemoveTagsResponse>{
    private String user;
    private String note;
    private List<String> tags;

    /**
     * Rest api uri of remove tags operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/tags";
    }

    @JsonProperty("tags")
    public String getTagsJoin() {
        return  tags != null ? Strings.join(tags, ",") : null;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * The user who is performing the remove tags operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the remove tags operation.
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public RemoveTagsResponse createResponse() {
        return new RemoveTagsResponse();
    }
}
