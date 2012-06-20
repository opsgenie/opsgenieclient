import com.smarts.remote.SmRemoteBroker
import com.smarts.remote.SmRemoteDomainManager
import com.smarts.remote.SmRemoteServerInfo
import com.smarts.repos.MR_PropertyNameValue
import com.smarts.repos.MR_Ref

public class SmartsDatasource {
    public static final String NON_SECURE_BROKER_USERNAME = "BrokerNonsecure";
    public static final String NON_SECURE_BROKER_PASSWORD = "Nonsecure"
    protected SmRemoteDomainManager domainManager = new SmRemoteDomainManager();
    private SmRemoteBroker smBroker;
    public void connect(Map params) throws Exception {
        smBroker = new SmRemoteBroker(params.broker);
        def brokerUsername = params.brokerUsername?params.brokerUsername:NON_SECURE_BROKER_USERNAME
        def brokerPassword = params.brokerPassword?params.brokerPassword:NON_SECURE_BROKER_PASSWORD
        smBroker.attach(brokerUsername, brokerPassword);
        try {
            SmRemoteServerInfo serverInfo = smBroker.getServerInfo(params.domain);
            def timeout = 30;
            if(params.timeout){
                timeout = (int)(params.timeout/1000);
                if (timeout == 0){
                    timeout = 1;
                }
            }
            domainManager.attach(params.domain, params.username, params.password, serverInfo.getHostIPAddress(), serverInfo.getPort(), timeout);
        }
        finally {
            smBroker.detach();
        }
    }
    public void disconnect() {
        if (domainManager != null) {
            domainManager.detach();
        }
    }

    public Map getAttributes(String className, String instanceName){
        return convertToMap(domainManager.getAllProperties(className, instanceName, MR_PropertyNameValue.MR_ATTRS_ONLY));
    }
    public Map getRelations(String className, String instanceName){
        return convertToMap(domainManager.getAllProperties(className, instanceName, MR_PropertyNameValue.MR_RELATIONS_ONLY))
    }
    public Map getAllProperties(String className, String instanceName){
        return convertToMap(domainManager.getAllProperties(className, instanceName, MR_PropertyNameValue.MR_BOTH))
    }

    private Map convertToMap(MR_PropertyNameValue[] nameValuePairs){
        Map res = new HashMap();
        nameValuePairs.each {MR_PropertyNameValue nameValue->
            def value = nameValue.getPropertyValue().getValue();
            if(value instanceof MR_Ref[]){
                def refs = [];
                value.each{MR_Ref ref->
                    refs << [creationClassName:ref.getClassName(), instanceName:ref.getInstanceName()]
                }
                value = refs;
            }
            if(value instanceof MR_Ref){
                value = [creationClassName:value.getClassName(), instanceName:value.getInstanceName()]
            }
            res.put(nameValue.getPropertyName(), value)

        }
        return res;
    }

    public static Object execute(Map connectionParams, Closure cl){
        SmartsDatasource ds = new SmartsDatasource();
        ds.connect(connectionParams);
        try{
            return cl(ds);
        }
        finally {
            ds.disconnect();
        }
    }
}