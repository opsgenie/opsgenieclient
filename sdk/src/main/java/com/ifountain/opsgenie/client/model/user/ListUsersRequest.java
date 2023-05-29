package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifountain.opsgenie.client.model.BaseRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make a list users api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listUsers(ListUsersRequest)
 */
public class ListUsersRequest extends BaseRequest<ListUsersResponse, ListUsersRequest> {
    private Integer limit;
    private Integer offset;
    private SortField sort;
    private SortOrder order;
    private String query;

    /**
     * Rest api uri of listing user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users";
    }

    @JsonIgnore
    public Map<String,Object> getRequestParams(){
        Map<String,Object> reqParams = new HashMap<>();
        if(limit!=null)
            reqParams.put("limit",limit);
        if(offset!=null)
            reqParams.put("offset",offset);
        if(sort!=null)
            reqParams.put("sort",sort);
        if(order!=null)
            reqParams.put("order",order);
        if(StringUtils.isNotBlank(query))
            reqParams.put("query",query);
        return reqParams;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public SortField getSort() {
        return sort;
    }

    public void setSort(SortField sort) {
        this.sort = sort;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListUsersResponse createResponse() {
        return new ListUsersResponse();
    }

    public enum SortOrder {
        asc, desc
    }

    public enum SortField {
        username, fullName, createdAt
    }
}
