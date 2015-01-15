package com.ifountain.opsgenie.client.script.util;

import java.util.concurrent.*;

public class OpsGenieExecutorsUtils {

    public static ExecutorService newNamedFixedThreadPool(String threadNamePrefix, int numberOfThreads, BlockingQueue<Runnable> workQueue){
        return new ThreadPoolExecutor(numberOfThreads,
                numberOfThreads,
                0L,
                TimeUnit.MILLISECONDS,
                workQueue,
                new NamedThreadFactory(threadNamePrefix));
    }

    public static String createThreadName(String prefix, long id){
        return prefix+"-"+id;
    }

    static class NamedThreadFactory implements ThreadFactory{
        private String threadNamePrefix;
        NamedThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(createThreadName(threadNamePrefix, thread.getId()));
            return thread;
        }
    }


}

