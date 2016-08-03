package com.ifountain.opsgenie.client.model.policy;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make an enable policy api call.
 *
 * @see com.ifountain.opsgenie.client.IPolicyOpsGenieClient#enablePolicy(com.ifountain.opsgenie.client.model.policy.EnablePolicyRequest)
 */
public class EnablePolicyRequest extends BaseRequest<EnablePolicyResponse> {
	private String name;

	/**
	 * Rest api uri of enable/disable policy operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/alert/policy/enable";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	public EnablePolicyResponse createResponse() {
		return new EnablePolicyResponse();
	}

}
