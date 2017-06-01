package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents the OpsGenie service response for an acknowledge alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#acknowledge(AcknowledgeRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.SuccessResponse}
 */
@Deprecated
public class AcknowledgeResponse extends BaseResponse {
}
