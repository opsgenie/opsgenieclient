package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 4/19/13
 * Time: 4:15 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IBean {
    public Map toMap();
    public void fromMap(Map map) throws ParseException;
}
