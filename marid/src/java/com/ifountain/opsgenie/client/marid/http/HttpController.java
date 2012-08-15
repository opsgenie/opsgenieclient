package com.ifountain.opsgenie.client.marid.http;

import com.ifountain.opsgenie.client.marid.http.action.ScriptAction;
import com.ifountain.opsgenie.client.marid.http.util.PathTrie;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/16/12
 * Time: 11:42 AM
 */
public class HttpController {
    private PathTrie<RequestAction> getHandlers = new PathTrie<RequestAction>();
    private PathTrie<RequestAction> postHandlers = new PathTrie<RequestAction>();
    private PathTrie<RequestAction> putHandlers = new PathTrie<RequestAction>();
    private PathTrie<RequestAction> deleteHandlers = new PathTrie<RequestAction>();

    private HttpController() {
    }

    private static HttpController instance;

    public static synchronized HttpController getInstance() {
        if (instance == null) {
            instance = new HttpController();
        }
        return instance;
    }

    public static synchronized void destroyInstance() {
        if (instance != null) {
            instance.destroy();
        }
        instance = null;
    }

    public void destroy() {
        getHandlers = new PathTrie<RequestAction>();
        postHandlers = new PathTrie<RequestAction>();
        putHandlers = new PathTrie<RequestAction>();
        deleteHandlers = new PathTrie<RequestAction>();
    }

    public HTTPResponse dispatchRequest(HTTPRequest request) {

        PathTrie<RequestAction> pathTrie = getPathTrie(request);
        if(pathTrie != null){
            Map<String, String> params = new HashMap<String, String>();
            RequestAction action = pathTrie.retrieve(request.getUrl(), params);
            if (action != null) {
                request.appendParameters(params);
                return action.execute(request);
            }
        }
        HTTPResponse response = new HTTPResponse();
        String message = "No handler found for url [" + request.getUrlWithMethod()+"]";
        response.setStatus(HttpResponseStatus.BAD_REQUEST);
        byte[] content = message.getBytes();
        response.setContent(content);
        response.setContentType("text/plain; charset=UTF-8");
        return response;
    }

    private PathTrie<RequestAction> getPathTrie(HTTPRequest request) {
        String method = request.getMethod();
        if (method.equals(HttpMethod.GET.getName())) {
            return getHandlers;
        } else if (method.equals(HttpMethod.POST.getName())) {
            return postHandlers;
        } else if (method.equals(HttpMethod.PUT.getName())) {
            return putHandlers;
        } else if (method.equals(HttpMethod.DELETE.getName())) {
            return deleteHandlers;
        }
        return null;
    }

    public void register(RequestAction requestAction, String url, HttpMethod... supportedMethods) throws Exception {
        for (HttpMethod method : supportedMethods) {
            PathTrie<RequestAction> handlers;
            if (method.equals(HttpMethod.GET)) {
                handlers = getHandlers;
            } else if (method.equals(HttpMethod.POST)) {
                handlers = postHandlers;
            } else if (method.equals(HttpMethod.PUT)) {
                handlers = putHandlers;
            } else if (method.equals(HttpMethod.DELETE)) {
                handlers = deleteHandlers;
            } else {
                throw new Exception("Invalid HTTP action");
            }
            handlers.insert(url, requestAction);
        }
    }

    public static void registerActions() throws Exception {
        new ScriptAction().register();
    }

    private static class CaseInsensitiveDecoder implements PathTrie.Decoder{
        @Override
        public String decode(String value) {
            return String.valueOf(value).toLowerCase();
        }
    }
}
