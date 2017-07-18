package com.ifountain.opsgenie.client.test.util

import com.ifountain.opsgenie.client.*
import com.ifountain.opsgenie.client.model.Request
import com.ifountain.opsgenie.client.model.customer.*
import com.ifountain.opsgenie.client.util.ClientConfiguration

import java.text.ParseException

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:11 AM
 */
public class OpsGenieClientMock implements IOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor = new OpsGenieClientMockRequestProcessor();
    private String rootUri;
    private ClientConfiguration clientConfiguration;
    private InnerUserOpsGenieClientMock userOpsGenieClientMock = new InnerUserOpsGenieClientMock(requestProcessor);
    private InnerAlertOpsGenieClientMock alertOpsGenieClientMock = new InnerAlertOpsGenieClientMock(requestProcessor);
    private InnerAlertV2OpsGenieClientMock alertV2OpsGenieClientMock = new InnerAlertV2OpsGenieClientMock(requestProcessor);
    private InnerEscalationOpsGenieClientMock escalationOpsGenieClientMock = new InnerEscalationOpsGenieClientMock(requestProcessor);
    private InnerGroupOpsGenieClientMock groupOpsGenieClientMock = new InnerGroupOpsGenieClientMock(requestProcessor);
    private InnerScheduleOpsGenieClientMock scheduleOpsGenieClientMock = new InnerScheduleOpsGenieClientMock(requestProcessor);
    private InnerAlertPolicyOpsGenieClientMock alertPolicyOpsGenieClientMock = new InnerAlertPolicyOpsGenieClientMock(requestProcessor);
    private InnerIntegrationOpsGenieClientMock integrationOpsGenieClientMock = new InnerIntegrationOpsGenieClientMock(requestProcessor);
    private InnerTeamOpsGenieClientMock innerTeamOpsGenieClientMock = new InnerTeamOpsGenieClientMock(requestProcessor);
    private HeartbeatResponse heartbeatResponse;
    private DeleteHeartbeatResponse deleteHeartbeatResponse;
    private ListHeartbeatsResponse listHeartbeatsResponse;
    private GetHeartbeatResponse getHeartbeatResponse;
    private AddHeartbeatResponse addHeartbeatResponse;
    private UpdateHeartbeatResponse updateHeartbeatResponse;
    private EnableHeartbeatResponse enableHeartbeatResponse;
    private CopyNotificationRulesResponse copyNotificationRulesResponse;
    private HeartbeatPingResponse heartbeatPingResponse;
    private AddAlertAttachmentResponse addAlertAttachmentResponse;

    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#contact()
     */
    public IContactOpsGenieClient contact() {
        return null;
    }
    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#account()
     */
    @Override
    public IAccountOpsGenieClient account() {
        return null;
    }

    /**
     * @see IOpsGenieClient#notificationRule() ()
     */
    public INotificationRuleOpsGenieClient notificationRule() {
        return null;
    }

    public OpsGenieClientMock() {
    }

    @Override
    public IUserOpsGenieClient user() {
        return userOpsGenieClientMock;
    }

    @Override
    public IAlertOpsGenieClient alert() {
        return alertOpsGenieClientMock;
    }

    @Override
    public InnerAlertV2OpsGenieClientMock alertV2() {
        return alertV2OpsGenieClientMock;
    }

    @Override
    public IGroupOpsGenieClient group() {
        return groupOpsGenieClientMock;
    }

    @Override
    public ITeamOpsGenieClient team() {
        return innerTeamOpsGenieClientMock;
    }

    @Override
    public IScheduleOpsGenieClient schedule() {
        return scheduleOpsGenieClientMock;
    }

    @Override
    public IAlertPolicyOpsGenieClient alertPolicy() {
        return alertPolicyOpsGenieClientMock;
    }

    @Override
    public IEscalationOpsGenieClient escalation() {
        return escalationOpsGenieClientMock;
    }

    @Override
    public IIntegrationOpsGenieClient integration() {
        return integrationOpsGenieClientMock;
    }

    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws OpsGenieClientException, IOException {
        requestProcessor.processRequest(heartbeatRequest);
        return heartbeatResponse;
    }

    @Override
    HeartbeatPingResponse pingHeartbeat(HeartbeatPingRequest heartbeatRequest) throws ParseException, OpsGenieClientException, IOException {
        requestProcessor.processRequest(heartbeatRequest);
        return heartbeatPingResponse;
    }

    @Override
    AddAlertAttachmentResponse addAlertAttachment(AddAlertAttachmentRequest addAlertAttachmentRequest) throws ParseException, OpsGenieClientException, IOException {
        requestProcessor.processRequest(addAlertAttachmentRequest)
        this.addAlertAttachmentResponse
    }

    @Override
    public DeleteHeartbeatResponse deleteHeartbeat(DeleteHeartbeatRequest deleteHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(deleteHeartbeatRequest);
        return deleteHeartbeatResponse;
    }

    @Override
    public AddHeartbeatResponse addHeartbeat(AddHeartbeatRequest addHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(addHeartbeatRequest);
        return addHeartbeatResponse;
    }

    @Override
    public UpdateHeartbeatResponse updateHeartbeat(UpdateHeartbeatRequest updateHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(updateHeartbeatRequest);
        return updateHeartbeatResponse;
    }

    @Override
    public EnableHeartbeatResponse enableHeartbeat(EnableHeartbeatRequest enableHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(enableHeartbeatRequest);
        return enableHeartbeatResponse;
    }

    @Override
    public GetHeartbeatResponse getHeartbeat(GetHeartbeatRequest getHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(getHeartbeatRequest);
        return getHeartbeatResponse;
    }

    @Override
    public ListHeartbeatsResponse listHeartbeats(ListHeartbeatsRequest listHeartbeatsRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(listHeartbeatsRequest);
        return listHeartbeatsResponse;
    }

    @Override
    public CopyNotificationRulesResponse copyNotificationRules(CopyNotificationRulesRequest copyNotificationRulesRequest) throws OpsGenieClientException, IOException, ParseException {
        requestProcessor.processRequest(copyNotificationRulesRequest);
        return copyNotificationRulesResponse;
    }

    public void setHeartbeatResponse(HeartbeatResponse heartbeatResponse) {
        this.heartbeatResponse = heartbeatResponse;
    }

    public void setAddHeartbeatResponse(AddHeartbeatResponse addHeartbeatResponse) {
        this.addHeartbeatResponse = addHeartbeatResponse;
    }

    public void setUpdateHeartbeatResponse(UpdateHeartbeatResponse updateHeartbeatResponse) {
        this.updateHeartbeatResponse = updateHeartbeatResponse;
    }

    public void setEnableHeartbeatResponse(EnableHeartbeatResponse enableHeartbeatResponse) {
        this.enableHeartbeatResponse = enableHeartbeatResponse;
    }

    public void setDeleteHeartbeatResponse(DeleteHeartbeatResponse deleteHeartbeatResponse) {
        this.deleteHeartbeatResponse = deleteHeartbeatResponse;
    }

    public void setListHeartbeatsResponse(ListHeartbeatsResponse listHeartbeatsResponse) {
        this.listHeartbeatsResponse = listHeartbeatsResponse;
    }

    public void setGetHeartbeatResponse(GetHeartbeatResponse getHeartbeatResponse) {
        this.getHeartbeatResponse = getHeartbeatResponse;
    }

    public void setCopyNotificationRulesResponse(CopyNotificationRulesResponse copyNotificationRulesResponse) {
        this.copyNotificationRulesResponse = copyNotificationRulesResponse;
    }

    void setHeartbeatPingResponse(HeartbeatPingResponse heartbeatPingResponse) {
        this.heartbeatPingResponse = heartbeatPingResponse
    }

    public String getRootUri() {
        return rootUri;
    }

    @Override
    public void setRootUri(String rootUri) {
        this.rootUri = rootUri;
    }

    public ClientConfiguration getClientConfiguration() {
        return clientConfiguration;
    }

    public void setClientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    public void setException(Exception exception) {
        requestProcessor.setException(exception);
    }

    public void setIOException(IOException ioexception) {
        requestProcessor.setIOException(ioexception);
    }

    public List<Request> getExecutedRequests() {
        return requestProcessor.getExecutedRequests();
    }

    public List<Object> getExecutedRequestsV2() {
        return requestProcessor.getExecutedRequestsV2();
    }

    @Override
    public void close() {
    }
}
