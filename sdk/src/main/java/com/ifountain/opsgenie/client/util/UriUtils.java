package com.ifountain.opsgenie.client.util;

import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author bkama
 * @version 30/04/2018 11:38
 */
public class UriUtils {
    private static final String PARAMETER_SEPARATOR = "&";
    private static final String NAME_VALUE_SEPARATOR = "=";

    public static URI generateUri(String uriStr) throws OpsGenieClientValidationException {
        URL url = null;
        URI uri = null;
        try {
            url = new URL(uriStr);
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } catch (URISyntaxException e) {
            throw getBadUrlException(uriStr);
        } catch (MalformedURLException e) {
            throw getBadUrlException(uriStr);
        }
        return uri;
    }

    public static URI generateUriWithParams(String uriStr, Map<String, Object> parameters) throws OpsGenieClientValidationException {
        String queryParams = getQueryParams(uriStr, parameters);

        URL url = null;
        URI uri = null;
        try {
            url = new URL(uriStr);
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), queryParams, url.getRef());
        } catch (URISyntaxException e) {
            throw getBadUrlException(uriStr);
        } catch (MalformedURLException e) {
            throw getBadUrlException(uriStr);
        }
        return uri;
    }

    private static String getQueryParams(String uriStr, Map<String, Object> parameters) throws OpsGenieClientValidationException {
        URI uri = generateUri(uriStr);

        List<NameValuePair> optionsInQuery = URLEncodedUtils.parse(uri, "UTF-8");
        List<NameValuePair> queryParams = getNameValuePairsFromMap(parameters);

        for (NameValuePair nvp : optionsInQuery) {
            if (!parameters.containsKey(nvp.getName())) {
                queryParams.add(nvp);
            }
        }

        final StringBuilder result = new StringBuilder();
        for (final NameValuePair parameter : queryParams) {
            if (result.length() > 0) {
                result.append(PARAMETER_SEPARATOR);
            }
            result.append(parameter.getName());
            result.append(NAME_VALUE_SEPARATOR);
            result.append(parameter.getValue());
        }
        return result.toString();
    }

    private static List<NameValuePair> getNameValuePairsFromMap(Map<String, Object> params) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> o : params.entrySet()) {
            if (o.getValue() != null) {
                if (o.getValue() instanceof Collection) {
                    Collection col = (Collection) o.getValue();
                    for (Object content : col) {
                        formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(content)));
                    }
                } else if (o.getValue().getClass().isArray()) {
                    int length = Array.getLength(o.getValue());
                    for (int i = 0; i < length; i++) {
                        Object content = Array.get(o.getValue(), i);
                        formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(content)));
                    }
                } else {
                    formparams.add(new BasicNameValuePair(o.getKey(), String.valueOf(o.getValue())));
                }
            }

        }
        return formparams;
    }

    private static OpsGenieClientValidationException getBadUrlException(String uriStr) {
        return new OpsGenieClientValidationException("Bad request URL " + uriStr, 400);
    }

}

