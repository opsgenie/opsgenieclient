package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.notificationRule.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Group NotificationRule
 */
public class InnerNotificationRuleOpsGenieClient implements INotificationRuleOpsGenieClient {
	private JsonOpsgenieHttpClient httpClient;

	/**
	 * Constructs a new notificationRule client to invoke service methods on OpsGenie for
	 * notificationRules using the specified client and root URI.
	 */
	public InnerNotificationRuleOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#addNotificationRule(com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleRequest)
	 */
	@Override
	public AddNotificationRuleResponse addNotificationRule(AddNotificationRuleRequest addNotificationRuleRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (AddNotificationRuleResponse) httpClient.doPostRequest(addNotificationRuleRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRule(com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleRequest)
	 */
	@Override
	public UpdateNotificationRuleResponse updateNotificationRule(UpdateNotificationRuleRequest updateNotificationRuleRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (UpdateNotificationRuleResponse) httpClient.doPostRequest(updateNotificationRuleRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#deleteNotificationRule(com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleRequest)
	 */
	@Override
	public DeleteNotificationRuleResponse deleteNotificationRule(DeleteNotificationRuleRequest deleteNotificationRuleRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (DeleteNotificationRuleResponse) httpClient.doDeleteRequest(deleteNotificationRuleRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#enableNotificationRule(com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleRequest)
	 */
	@Override
	public EnableNotificationRuleResponse enableNotificationRule(EnableNotificationRuleRequest enableNotificationRuleRequest)
			throws ParseException, OpsGenieClientException, IOException {
		return (EnableNotificationRuleResponse) httpClient.doPostRequest(enableNotificationRuleRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#getNotificationRule(com.ifountain.opsgenie.client.model.notificationRule.GetNotificationRuleRequest)
	 */
	@Override
	public GetNotificationRuleResponse getNotificationRule(GetNotificationRuleRequest getNotificationRuleRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (GetNotificationRuleResponse) httpClient.doGetRequest(getNotificationRuleRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#listNotificationRule(com.ifountain.opsgenie.client.model.notificationRule.ListNotificationRulesRequest)
	 */
	@Override
	public ListNotificationRulesResponse listNotificationRule(ListNotificationRulesRequest listNotificationRuleRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (ListNotificationRulesResponse) httpClient.doGetRequest(listNotificationRuleRequest);
	}
	
	
	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#addNotificationRuleStep(com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleStepRequest)
	 */
	@Override
	public AddNotificationRuleStepResponse addNotificationRuleStep(AddNotificationRuleStepRequest addNotificationRuleStepRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (AddNotificationRuleStepResponse) httpClient.doPostRequest(addNotificationRuleStepRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRuleStep(com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleStepRequest)
	 */
	@Override
	public UpdateNotificationRuleStepResponse updateNotificationRuleStep(UpdateNotificationRuleStepRequest updateNotificationRuleStepRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (UpdateNotificationRuleStepResponse) httpClient.doPostRequest(updateNotificationRuleStepRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#deleteNotificationRuleStep(com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleStepRequest)
	 */
	@Override
	public DeleteNotificationRuleStepResponse deleteNotificationRuleStep(DeleteNotificationRuleStepRequest deleteNotificationRuleStepRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (DeleteNotificationRuleStepResponse) httpClient.doDeleteRequest(deleteNotificationRuleStepRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#enableNotificationRuleStep(com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleStepRequest)
	 */
	@Override
	public EnableNotificationRuleStepResponse enableNotificationRuleStep(EnableNotificationRuleStepRequest enableNotificationRuleStepRequest)
			throws ParseException, OpsGenieClientException, IOException {
		return (EnableNotificationRuleStepResponse) httpClient.doPostRequest(enableNotificationRuleStepRequest);
	}

	@Override
	public ChangeNotificationRuleOrderResponse changeNotificationRule(ChangeNotificationRuleOrderRequest changeNotificationRuleRequest)throws IOException, OpsGenieClientException, ParseException {
		return (ChangeNotificationRuleOrderResponse) httpClient.doPostRequest(changeNotificationRuleRequest);
	}

	@Override
	public RepeatNotificationRuleResponse repeatNotificationRule(RepeatNotificationRuleRequest repeatNotificationRuleRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (RepeatNotificationRuleResponse) httpClient.doPostRequest(repeatNotificationRuleRequest);
	}

	

}
