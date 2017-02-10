package com.ifountain.opsgenie.client.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

public class RemoveTagsRequest extends BaseAlertRequestWithParameters<RemoveTagsResponse, RemoveTagsRequest> {
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
        return tags != null ? Strings.join(tags, ",") : null;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public RemoveTagsRequest withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public RemoveTagsResponse createResponse() {
        return new RemoveTagsResponse();
    }
}
