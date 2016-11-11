package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Abstract Container for the alert requests.
 *
 * @author Burak Celen
 */
public abstract class AlertsRequest<T extends BaseResponse, K extends AlertsRequest> extends BaseRequest<T, K> {

    private Long createdAfter;
    private Long updatedAfter;
    private Long createdBefore;
    private Long updatedBefore;
    private Integer limit;
    private Status status;
    private List<String> tags;
    private Operator tagsOperator;

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

    @JsonProperty("status")
    public String getStatusName() {
        if (status != null)
            return status.name();
        return null;
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

    @JsonProperty("tagsOperator")
    public String getTagsOperatorName() {
        if (tagsOperator != null)
            return tagsOperator.name();
        return null;
    }

    public Operator getTagsOperator() {
        return tagsOperator;
    }

    public void setTagsOperator(Operator tagsOperator) {
        this.tagsOperator = tagsOperator;
    }

    public K withCreatedAfter(Long createdAfter) {
        this.createdAfter = createdAfter;
        return (K) this;
    }

    public K withUpdatedAfter(Long updatedAfter) {
        this.updatedAfter = updatedAfter;
        return (K) this;
    }

    public K withCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
        return (K) this;
    }

    public K withUpdatedBefore(Long updatedBefore) {
        this.updatedBefore = updatedBefore;
        return (K) this;
    }

    public K withLimit(Integer limit) {
        this.limit = limit;
        return (K) this;
    }

    public K withStatus(Status status) {
        this.status = status;
        return (K) this;
    }

    public K withTags(List<String> tags) {
        this.tags = tags;
        return (K) this;
    }

    public K withTagsOperator(Operator tagsOperator) {
        this.tagsOperator = tagsOperator;
        return (K) this;
    }

    public enum Status {
        open,
        closed,
        acked,
        unacked,
        seen,
        notseen
    }

    public enum Operator {
        and,
        or
    }

}
