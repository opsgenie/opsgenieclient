package com.ifountain.opsgenie.client.script;

import com.ifountain.opsgenie.client.script.util.OpsGenieExecutorsUtils;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by burak on 7/22/14.
 */
public class AsyncScriptManager {

    public static final long DEFAULT_SHUTDOWN_WAIT_TIME = 90000;
    public static final int DEFAULT_SCRIPT_EXECUTOR_THREAD_COUNT = 20;
    public static final int DEFAULT_SCRIPT_EXECUTOR_QUEUE_COUNT = 10000;

    private static AsyncScriptManager asyncScriptManager;
    private ExecutorService scriptExecutionService;
    private BlockingQueue<Runnable> workQueue;
    private long shutdownWaitTime = DEFAULT_SHUTDOWN_WAIT_TIME;
    private Logger logger = Logger.getLogger(AsyncScriptManager.class);
    private boolean initialized = false;

    private AsyncScriptManager() {
    }

    public static AsyncScriptManager getInstance() {

        if(asyncScriptManager == null){
            asyncScriptManager = new AsyncScriptManager();
        }
        return asyncScriptManager;
    }

    public void initialize(){
        initialize(DEFAULT_SCRIPT_EXECUTOR_THREAD_COUNT, DEFAULT_SCRIPT_EXECUTOR_QUEUE_COUNT);
    }

    public void initialize(int scriptExecutorThreadCount, int scriptExecutoerQueueCount){
        logger.info(getLogPrefix()+"Starting action executor service with "+scriptExecutorThreadCount +" number of threads.");
        workQueue = new LinkedBlockingQueue<Runnable>(scriptExecutoerQueueCount);
        scriptExecutionService = OpsGenieExecutorsUtils.newNamedFixedThreadPool(AsyncScriptManager.class.getName(), scriptExecutorThreadCount, workQueue);
        initialized = true;
    }

    public static void destroyInstance() {
        if (asyncScriptManager != null) {
            asyncScriptManager.destroy();
        }
        asyncScriptManager = null;
    }

    private void destroy() {
        if(scriptExecutionService != null){
            scriptExecutionService.shutdown();
            try {
                logger.info(getLogPrefix()+"Shuttingdown script execution service");
                scriptExecutionService.awaitTermination(shutdownWaitTime, TimeUnit.MILLISECONDS);
                if(!scriptExecutionService.isTerminated()){
                    scriptExecutionService.shutdownNow();
                }
                logger.info(getLogPrefix()+"Shutdown script execution service");
            } catch (InterruptedException e) {
                logger.info(getLogPrefix() + "Could not shutdown script execution service in "+shutdownWaitTime+" msecs.");
                scriptExecutionService.shutdownNow();
            }
        }
    }

    public void runScript(String scriptPath, Map<String, Object> bindings) throws Exception {
        scriptExecutionService.submit(new RunScriptTask(scriptPath, bindings));
    }

    class RunScriptTask implements Callable<Void> {

        String scriptPath;
        Map<String, Object> bindings;

        public RunScriptTask(String scriptPath, Map<String, Object> bindings){
            this.scriptPath = scriptPath;
            this.bindings = bindings;
        }

        @Override
        public Void call() throws Exception {
            try{
                ScriptManager.getInstance().runScript(scriptPath, bindings);
            } catch (Exception e){
                logger.warn( getLogPrefix() + "Error occured while processing " + scriptPath + ": " + e );
                throw e;
            }
            return null;
        }
    }

    public long getShutdownWaitTime() {
        return shutdownWaitTime;
    }

    public void setShutdownWaitTime(long shutdownWaitTime) {
        this.shutdownWaitTime = shutdownWaitTime;
    }

    private String getLogPrefix() {
        return "[AsyncScriptManager]: ";
    }

    public boolean isInitialized() {
        return initialized;
    }
}
