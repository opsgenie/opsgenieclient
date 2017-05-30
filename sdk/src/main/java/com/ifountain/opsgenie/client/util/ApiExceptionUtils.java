package com.ifountain.opsgenie.client.util;

import com.ifountain.opsgenie.client.swagger.ApiException;
import com.ifountain.opsgenie.client.swagger.model.ErrorResponse;

import java.io.IOException;
import java.util.Map;

public class ApiExceptionUtils {

    public static ErrorResponse parse(ApiException ex) throws IOException {
        Map json = JsonUtils.parse(ex.getResponseBody());

        return new ErrorResponse()
                .requestId(json.get("requestId").toString())
                .took(Float.parseFloat(json.get("took").toString()))
                .code(ex.getCode())
                .message(json.get("message").toString())
                .responseHeaders(ex.getResponseHeaders());

    }
}
