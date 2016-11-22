package com.ifountain.opsgenie.client.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.TimeZone;

/**
 * This interface made for models(beans) which have time(date) and time zone objects.
 * Current client SDK parsing those objects with given timezone.
 * To do it sdk firstly parse the JSON to model.
 * After that it parse id again with the timezone of the object.
 * In this way sdk parse the model according to the time zone of the model.
 *
 * @see com.ifountain.opsgenie.client.util.JsonUtils
 */
public interface ObjectWithTimeZone {
    @JsonIgnore
    TimeZone getObjectTimeZone();
}
