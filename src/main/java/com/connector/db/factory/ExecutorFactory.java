package com.connector.db.factory;

import com.connector.db.props.ConfigurationLoader;
import com.connector.db.tasks.RejectedTaskHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ExecutorFactory {

    /**
     * Create the executor thread pool
     * @return the ThreadPoolExecutor with appropriate config
     */
    public ThreadPoolExecutor build(int corePoolSize, int maximumPoolSize){

        // get the values from property loader
        int keepAliveTime = 30;
        TimeUnit tu = TimeUnit.SECONDS;
        int queueCapacity = 100;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(queueCapacity);
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedTaskHandler();
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, tu, queue, rejectedExecutionHandler);
    }

}
