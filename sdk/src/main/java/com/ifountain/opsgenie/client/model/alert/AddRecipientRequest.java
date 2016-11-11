package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make an add recipient call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addRecipient(AddRecipientRequest)
 */
public class AddRecipientRequest extends BaseAlertRequestWithNoteAndUser<AddRecipientResponse, AddRecipientRequest> {
    private String recipient;

    /**
     * Rest api uri of add recipient operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/recipient";
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

    public AddRecipientRequest withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddRecipientResponse createResponse() {
        return new AddRecipientResponse();
    }
}
