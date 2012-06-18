package com.ifountain.opsgenie.client.util;

import java.util.List;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 4:08 PM
 */
public class Strings {

    public static String join(List<String> list, String seperator) {
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        for (String next : list) {
            if (first) {
                first = false;
            } else {
                buffer.append(seperator);
            }
            buffer.append(next);
        }
        return buffer.toString();
    }

}
