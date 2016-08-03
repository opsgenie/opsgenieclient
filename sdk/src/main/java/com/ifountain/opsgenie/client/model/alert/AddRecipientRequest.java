package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make an add recipient call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 1/9/13 4:03 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addRecipient(AddRecipientRequest)
 */
public class AddRecipientRequest extends BaseAlertRequestWithSource<AddRecipientResponse> {
    private String user;
    private String recipient;
    private String note;

    /**
     * Rest api uri of add recipient operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/recipient";
    }

    /**
     * The user who is performing the add recipient operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add recipient operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * The recipient that will be added.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the recipient that will be added.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Additional alert note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional alert note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddRecipientResponse createResponse() {
        return new AddRecipientResponse();
    }
}
