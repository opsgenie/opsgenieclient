package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.model.account.GetAccountRequest;
import com.ifountain.opsgenie.client.model.account.GetAccountResponse;
import com.ifountain.opsgenie.client.model.customer.*;
import com.ifountain.opsgenie.client.util.ClientConfiguration;

import java.io.IOException;
import java.text.ParseException;

/**
 * Provides the client for accessing the OpsGenie web service.
 * <p></p>
 * <p><code>OpsGenieClient</code> class provides the implementation APIs for OpsGenie operations like creating, closing and getting alerts,
 * adding comments, attaching files, etc. All service calls made using this client are blocking, and will not return until the service call completes.</p>
 * <p></p>
 * <p><strong>Creating an Alerts</strong></p>
 * <p>Construct a <code>CreateAlertRequest</code> object with preferred options and call <code>createAlert</code> method on client.</p>
 * <p>
 * <blockquote><pre>
 * OpsGenieClient client = new OpsGenieClient();
 * CreateAlertRequest request = new CreateAlertRequest();
 * request.setApiKey("ab5454992-fabb2-4ba2-ad44f-1af65ds8b5c079");
 * request.setMessage("appserver1 down");
 * request.setDescription("cpu usage is over 60%");
 * request.setSource("nagios");
 * request.setEntity("appserver1");
 * request.setActions(Arrays.asList("acknowledge", "restart"));
 * request.setTags(Arrays.asList("network", "operations"));
 * request.setRecipients(Arrays.asList("john.smith@acme.com"));
 * CreateAlertResponse response = client.createAlert(request);
 * String alertId = response.getAlertId();
 * </pre></blockquote>
 *
 * <p><strong>Adding Notes</strong></p>
 * <p>Construct a <code>AddNoteRequest</code> object with preferred options and call <code>addNote</code> method on client.</p>
 *
 * <p>
 * <blockquote><pre>
 * OpsGenieClient client = new OpsGenieClient();
 * AddNoteRequest request = new AddNoteRequest();
 * request.setAlertId("29c4d6f6-0919-40ec-8d37-4ab2ed2042c8");
 * request.setApiKey("ab5454992-fabb2-4ba2-ad44f-1af65ds8b5c079");
 * request.setUser("john.smith@acme.com");
 * request.setNote("We should find another solution.");
 * AddNoteResponse response = client.addNote(request);
 * assert response.isSuccess();
 * </pre></blockquote>
 *
 *
 * <p><strong>Attaching Files</strong></p>
 * <p>Construct a <code>FileAttachRequest</code> object with preferred options and call <code>attach</code> method on client.</p>
 * <p><blockquote><pre>
 * OpsGenieClient client = new OpsGenieClient();
 * FileAttachRequest request = new FileAttachRequest();
 * request.setAlertId("29c4d6f6-0919-40ec-8d37-4ab2ed2042c8");
 * request.setApiKey("ab5454992-fabb2-4ba2-ad44f-1af65ds8b5c079");
 * request.setUser("john.smith@acme.com");
 * request.setFile(new File("/home/john/performanceGraphs.zip"));
 * AttachResponse response = client.attach(request);
 * assert response.isSuccess();
 * </pre></blockquote>
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:40 AM
 * @see IOpsGenieClient
 */
public class OpsGenieClient implements IOpsGenieClient {
    private InnerUserOpsGenieClient innerUserOpsGenieClient;
    private InnerGroupOpsGenieClient innerGroupOpsGenieClient;
    private InnerTeamOpsGenieClient innerTeamOpsGenieClient;
    private InnerEscalationOpsGenieClient innerEscalationOpsGenieClient;
    private InnerAlertOpsGenieClient innerAlertOpsGenieClient;
    private InnerScheduleOpsGenieClient innerScheduleOpsGenieClient;
    private InnerAlertPolicyOpsGenieClient innerAlertPolicyOpsGenieClient;
    private IIntegrationOpsGenieClient innerIntegrationOpsGenieClient;
    private IPolicyOpsGenieClient innerPolicyOpsGenieClient;
    private INotificationRuleOpsGenieClient innerNotificationRuleOpsGenieClient;
    private IAccountOpsGenieClient innerAccountOpsGenieClient;
    private IContactOpsGenieClient innerContactOpsGenieClient;
    
    /**
     * Http client object *
     */
    private JsonOpsgenieHttpClient jsonHttpClient;
    private StreamOpsgenieHttpClient streamOpsgenieHttpClient;

    /**
     * Constructs a new client to invoke service methods on OpsGenie using the specified client configuration options.
     */
    public OpsGenieClient(ClientConfiguration config) {
        this(new OpsGenieHttpClient(config));
    }

    /**
     * Constructs a new client to invoke service methods on OpsGenie.
     */
    public OpsGenieClient() {
        this(new OpsGenieHttpClient());
    }


    /**
     * Constructs a new client to invoke service methods on OpsGenie using the specified client.
     */
    public OpsGenieClient(OpsGenieHttpClient httpClient) {
        this.jsonHttpClient = new JsonOpsgenieHttpClient(httpClient);
        this.streamOpsgenieHttpClient = new StreamOpsgenieHttpClient(httpClient);
        innerUserOpsGenieClient = new InnerUserOpsGenieClient(this.jsonHttpClient);
        innerGroupOpsGenieClient = new InnerGroupOpsGenieClient(this.jsonHttpClient);
        innerTeamOpsGenieClient = new InnerTeamOpsGenieClient(this.jsonHttpClient);
        innerEscalationOpsGenieClient = new InnerEscalationOpsGenieClient(this.jsonHttpClient);
        innerAlertOpsGenieClient = new InnerAlertOpsGenieClient(this.jsonHttpClient);
        innerScheduleOpsGenieClient = new InnerScheduleOpsGenieClient(this.jsonHttpClient, this.streamOpsgenieHttpClient);
        innerAlertPolicyOpsGenieClient = new InnerAlertPolicyOpsGenieClient(this.jsonHttpClient);
        innerIntegrationOpsGenieClient = new InnerIntegrationOpsGenieClient(this.jsonHttpClient);
        innerContactOpsGenieClient = new InnerContactOpsGenieClient(this.jsonHttpClient);
        innerPolicyOpsGenieClient = new InnerPolicyOpsGenieClient(this.jsonHttpClient);
        innerNotificationRuleOpsGenieClient = new InnerNotificationRuleOpsGenieClient(this.jsonHttpClient);
        innerAccountOpsGenieClient = new InnerAccountOpsGenieClient(this.jsonHttpClient);
    }

    /**
     * @see IOpsGenieClient#user()
     */
    public IUserOpsGenieClient user() {
        return innerUserOpsGenieClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#group()
     */
    public IGroupOpsGenieClient group() {
        return innerGroupOpsGenieClient;
    }

    /**
     * @see IOpsGenieClient#team() ()
     */
    @Override
    public ITeamOpsGenieClient team() {
        return innerTeamOpsGenieClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#escalation()
     */
    public IEscalationOpsGenieClient escalation() {
        return innerEscalationOpsGenieClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#alert()
     */
    public IAlertOpsGenieClient alert() {
        return innerAlertOpsGenieClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#schedule()
     */
    public IScheduleOpsGenieClient schedule() {
        return innerScheduleOpsGenieClient;
    }

    /**
     * @see IOpsGenieClient#alertPolicy() ()
     */
    public IAlertPolicyOpsGenieClient alertPolicy() {
        return innerAlertPolicyOpsGenieClient;
    }

    /**
     * @see IOpsGenieClient#integration() ()
     */
    public IIntegrationOpsGenieClient integration() {
        return innerIntegrationOpsGenieClient;
    }
    
    
    /**
     * @see IOpsGenieClient#policy() ()
     */
    public IPolicyOpsGenieClient policy() {
        return innerPolicyOpsGenieClient;
    }
    
    /**
     * @see IOpsGenieClient#notificationRule() ()
     */
    public INotificationRuleOpsGenieClient notificationRule() {
        return innerNotificationRuleOpsGenieClient;
    }
    
    /**
     * @see IOpsGenieClient#heartbeat(com.ifountain.opsgenie.client.model.customer.HeartbeatRequest)
     */
    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        return (HeartbeatResponse) jsonHttpClient.doPostRequest(heartbeatRequest);
    }

    /**
     * @see IOpsGenieClient#deleteHeartbeat(com.ifountain.opsgenie.client.model.customer.DeleteHeartbeatRequest)
     */
    @Override
    public DeleteHeartbeatResponse deleteHeartbeat(DeleteHeartbeatRequest deleteHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        return (DeleteHeartbeatResponse) jsonHttpClient.doDeleteRequest(deleteHeartbeatRequest);
    }

    /**
     * @see IOpsGenieClient#addHeartbeat(com.ifountain.opsgenie.client.model.customer.AddHeartbeatRequest)
     */
    @Override
    public AddHeartbeatResponse addHeartbeat(AddHeartbeatRequest addHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        return (AddHeartbeatResponse) jsonHttpClient.doPostRequest(addHeartbeatRequest);
    }

    /**
     * @see IOpsGenieClient#updateHeartbeat(com.ifountain.opsgenie.client.model.customer.UpdateHeartbeatRequest)
     */
    @Override
    public UpdateHeartbeatResponse updateHeartbeat(UpdateHeartbeatRequest updateHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        return (UpdateHeartbeatResponse) jsonHttpClient.doPostRequest(updateHeartbeatRequest);
    }

    /**
     * @see IOpsGenieClient#enableHeartbeat(com.ifountain.opsgenie.client.model.customer.EnableHeartbeatRequest)
     */
    @Override
    public EnableHeartbeatResponse enableHeartbeat(EnableHeartbeatRequest enableHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        return (EnableHeartbeatResponse) jsonHttpClient.doPostRequest(enableHeartbeatRequest);
    }

    /**
     * @see IOpsGenieClient#getHeartbeat(com.ifountain.opsgenie.client.model.customer.GetHeartbeatRequest)
     */
    @Override
    public GetHeartbeatResponse getHeartbeat(GetHeartbeatRequest getHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException {
        return (GetHeartbeatResponse) jsonHttpClient.doGetRequest(getHeartbeatRequest);
    }

    /**
     * @see IOpsGenieClient#listHeartbeats(com.ifountain.opsgenie.client.model.customer.ListHeartbeatsRequest)
     */
    @Override
    public ListHeartbeatsResponse listHeartbeats(ListHeartbeatsRequest listHeartbeatsRequest) throws OpsGenieClientException, IOException, ParseException {
        return (ListHeartbeatsResponse) jsonHttpClient.doGetRequest(listHeartbeatsRequest);
    }

    /**
     * @see IOpsGenieClient#copyNotificationRules(com.ifountain.opsgenie.client.model.customer.CopyNotificationRulesRequest)
     */
    @Override
    public CopyNotificationRulesResponse copyNotificationRules(CopyNotificationRulesRequest copyNotificationRulesRequest) throws OpsGenieClientException, IOException, ParseException {
        return (CopyNotificationRulesResponse) jsonHttpClient.doPostRequest(copyNotificationRulesRequest);
    }

    /**
     * Set root endpoint uri that the client uses to send http requests. Default is https://api.opsgenie.com. Mostly used for testing.
     *
     * @param rootUri Uri to set.
     */
    @Override
    public void setRootUri(String rootUri) {
        this.jsonHttpClient.setRootUri(rootUri);
        this.streamOpsgenieHttpClient.setRootUri(rootUri);
    }

    /**
     * Closes client
     */
    @Override
    public void close() {
        this.jsonHttpClient.close();
        this.streamOpsgenieHttpClient.close();
    }

    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#contact()
     */
	public IContactOpsGenieClient contact() {
		return innerContactOpsGenieClient;
	}
    /**
     * @see com.ifountain.opsgenie.client.IOpsGenieClient#account()
     */
	@Override
	public IAccountOpsGenieClient account() {
		return innerAccountOpsGenieClient;
	}
}
