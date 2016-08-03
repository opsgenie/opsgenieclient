package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by burak on 5/11/2015.
 */
public abstract class AlertsRequest<T extends BaseResponse> extends BaseRequest<T>{

    public enum Status{
        open,
        closed,
        acked,
        unacked,
        seen,
        notseen
    }
    public enum Operator{
        and,
        or
    }

    private Long createdAfter;
    private Long updatedAfter;
    private Long createdBefore;
    private Long updatedBefore;
    private Integer limit;
    private Status status;
    private List<String> tags;
    private Operator tagsOperator;

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
		Map parameters = new HashMap();
		if (getApiKey() != null) 
			parameters.put(OpsGenieClientConstants.API.API_KEY, getApiKey());
        if (getCreatedAfter() != null)
            parameters.put(OpsGenieClientConstants.API.CREATED_AFTER, getCreatedAfter());
        if (getCreatedBefore() != null)
            parameters.put(OpsGenieClientConstants.API.CREATED_BEFORE, getCreatedBefore());
        if (getUpdatedAfter() != null)
            parameters.put(OpsGenieClientConstants.API.UPDATED_AFTER, getUpdatedAfter());
        if (getUpdatedBefore() != null)
            parameters.put(OpsGenieClientConstants.API.UPDATED_BEFORE, getUpdatedBefore());
        if (getLimit() != null)
            parameters.put(OpsGenieClientConstants.API.LIMIT, getLimit());
        if (getStatus() != null)
            parameters.put(OpsGenieClientConstants.API.STATUS, getStatus().name());
        if (getTags() != null)
            parameters.put(OpsGenieClientConstants.API.TAGS, getTags());
        if (getTagsOperator() != null)
            parameters.put(OpsGenieClientConstants.API.TAGS_OPERATOR, getTagsOperator().name());
        return parameters;
    }


    public Long getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Long createdAfter) {
        this.createdAfter = createdAfter;
    }

    public Long getUpdatedAfter() {
        return updatedAfter;
    }

    public void setUpdatedAfter(Long updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    public Long getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Long getUpdatedBefore() {
        return updatedBefore;
    }

    public void setUpdatedBefore(Long updatedBefore) {
        this.updatedBefore = updatedBefore;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Operator getTagsOperator() {
        return tagsOperator;
    }

    public void setTagsOperator(Operator tagsOperator) {
        this.tagsOperator = tagsOperator;
    }
}
