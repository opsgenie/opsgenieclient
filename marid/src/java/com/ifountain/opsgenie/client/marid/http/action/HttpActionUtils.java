package com.ifountain.opsgenie.client.marid.http.action;

import com.ifountain.opsgenie.client.marid.http.HTTPResponse;
import com.ifountain.client.util.JsonUtils;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpActionUtils {
    public static HTTPResponse createDefaultHttpResponse() {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        return createResponseAsJson(res, HttpResponseStatus.OK);
    }

    public static HTTPResponse createResponseAsJson(Map<String, Object> json, HttpResponseStatus status) {
        HTTPResponse response = new HTTPResponse();
        response.setStatus(status);
        try {
            byte[] contentBytes = JsonUtils.toJsonAsBytes(json);
            response.setContent(contentBytes);
            response.setContentType("application/json; charset=UTF-8");
        } catch (IOException ignored) {
        }
        return response;
    }

    public static String detailedMessage(Throwable t) {
        if (t == null) {
            return "Unknown";
        }
        if (t.getCause() != null) {
            StringBuilder sb = new StringBuilder();
            while (t != null) {
                if (t.getMessage() != null) {
                    sb.append(t.getClass().getSimpleName()).append("[");
                    sb.append(t.getMessage());
                    sb.append("]");
                    sb.append("; ");
                }
                t = t.getCause();
                if (t != null) {
                    sb.append("nested: ");
                }
            }
            return sb.toString();
        } else {
            return t.getClass().getSimpleName() + "[" + t.getMessage() + "]";
        }
    }
}
