package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents the OpsGenie service response for an unAcknowledge alert request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#unAcknowledge(UnAcknowledgeRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.SuccessResponse}
 */
@Deprecated
public class UnAcknowledgeResponse extends BaseResponse {
}
