package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Alert;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for an get alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#getAlert(GetAlertRequest)
 */
public class GetAlertResponse extends BaseResponse {
    private Alert alert;

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    /**
     * The id of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getId() {
        return alert.getId();
    }

    /**
     * Sets the id of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setId(String id) {
        this.alert.setId(id);
    }

    /**
     * User defined identifier of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getAlias() {
        return alert.getAlias();
    }

    /**
     * Sets the user defined identifier of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setAlias(String alias) {
        this.alert.setAlias(alias);
    }

    /**
     * Alert text.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getMessage() {
        return alert.getMessage();
    }

    /**
     * Sets the alert text.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setMessage(String message) {
        this.alert.setMessage(message);
    }

    /**
     * Alert text in long form.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getDescription() {
        return alert.getDescription();
    }

    /**
     * Sets the alert text in long form.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setDescription(String description) {
        this.alert.setDescription(description);
    }

    /**
     * Source of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getSource() {
        return alert.getSource();
    }

    /**
     * Sets the source of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setSource(String source) {
        this.alert.setSource(source);
    }

    /**
     * The entity that the alert is related to.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getEntity() {
        return alert.getEntity();
    }

    /**
     * Sets the entitiy that the alert is related to.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setEntity(String entity) {
        this.alert.setEntity(entity);
    }

    /**
     * The status of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getStatus() {
        return alert.getStatus().name();
    }

    /**
     * Sets the status of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setStatus(String status) {
        this.alert.setStatus(Alert.Status.valueOf(status));
    }

    /**
     * The owner of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public String getOwner() {
        return alert.getOwner();
    }

    /**
     * Sets the owner of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setOwner(String owner) {
        this.alert.setOwner(owner);
    }

    /**
     * The creation time of alert in milliseconds.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public long getCreatedAt() {
        return alert.getCreatedAt();
    }

    /**
     * Sets the creation time of the alert in milliseconds.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setCreatedAt(long createdAt) {
        this.alert.setCreatedAt(createdAt);
    }

    /**
     * Count of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public int getCount() {
        return alert.getCount();
    }

    /**
     * Sets the count of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setCount(int count) {
        this.alert.setCount(count);
    }

    /**
     * Indicates whether the alert is seen by any recipient.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public boolean isSeen() {
        return alert.isSeen();
    }

    /**
     * Sets seen state of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setSeen(boolean seen) {
        alert.setSeen(seen);
    }

    /**
     * The acknowledged state of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public boolean isAcknowledged() {
        return alert.isAcknowledged();
    }

    /**
     * Sets the acknowledged state of the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setAcknowledged(boolean acknowledged) {
        this.alert.setAcknowledged(acknowledged);
    }

    /**
     * The user names of individuval users or group names that will receive the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public List<String> getRecipients() {
        return alert.getRecipients();
    }

    /**
     * Sets the user names of individuval users or group names that will receive the alert.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setRecipients(List<String> recipients) {
        this.alert.setRecipients(recipients);
    }

    /**
     * List of alert labels.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public List<String> getTags() {
        return alert.getTags();
    }

    /**
     * Sets the list of alert labels.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setTags(List<String> tags) {
        this.alert.setTags(tags);
    }

    /**
     * List of actions that can be executed.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public List<String> getActions() {
        return alert.getActions();
    }

    /**
     * Sets the list of actions that can be executed.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setActions(List<String> actions) {
        this.alert.setActions(actions);
    }

    /**
     * Additional alert properties.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public Map<String, String> getDetails() {
        return alert.getDetails();
    }

    /**
     * Sets the additional alert properties.
     * @deprecated
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse#getAlert()
     * @see Alert
     */
    public void setDetails(Map<String, String> details) {
        this.alert.setDetails(details);
    }


    /**
     * @see BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        alert = new Alert();
        alert.fromMap(data);
    }
}
