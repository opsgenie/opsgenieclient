package com.ifountain.opsgenie.client.model;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/14/12 4:08 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public abstract class BaseResponse implements Response{
    private boolean success = true;
    private long took = 0;
    @JsonIgnore
    private String json;
    private static ObjectMapper mapper = new ObjectMapper();
    private static SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);

    public BaseResponse() {
        mapper.setDateFormat(sdf);
    }
    /**
     * True if the operation is successful.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the operation success state.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns the number of milliseconds that have elapsed during the operation.
     */
    public long getTook() {
        return took;
    }

    /**
     * Sets the number of milliseconds that have elapsed during the operation.
     */
    public void setTook(long took) {
        this.took = took;
    }

    /**
     * Raw JSON data
     */
    public String getJson() {
        return json;
    }

    /**
     * Sets raw JSON data
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Convert map data to response
     */
    public void deserialize(Map data) throws ParseException {
        try {
            mapper.readerForUpdating(this).readValue(mapper.writeValueAsString(data));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParseException(e.getMessage(), -1);
        }
    }


    public void fromJson(String json) throws JsonProcessingException, IOException, ParseException {
        mapper.readerForUpdating(this).readValue(json);
        this.json = json;
    }

    public String toJson() throws JsonProcessingException, IOException {
        return mapper.writeValueAsString(this);
    }
}
