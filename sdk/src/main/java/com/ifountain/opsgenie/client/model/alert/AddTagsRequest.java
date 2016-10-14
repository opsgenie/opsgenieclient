package com.ifountain.opsgenie.client.model.alert;

import java.util.List;

/**
 * @author Tuba Ozturk
 */
public class AddTagsRequest extends AddNoteRequest {
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddTagsResponse createResponse() {
        return new AddTagsResponse();
    }
}
