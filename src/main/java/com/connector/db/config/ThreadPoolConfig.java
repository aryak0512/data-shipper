package com.connector.db.config;

import com.connector.db.factory.ExecutorFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadPoolConfig {

    private final ThreadPoolExecutor producerExecutor;
    private final ThreadPoolExecutor consumerExecutor;

    public ThreadPoolConfig(ExecutorFactory factory, PropertyManager propertyManager) {


        int inputThreads = (int) propertyManager.getProperty("input.threads");
        int inputThreadsMax = (int) propertyManager.getProperty("input.threads_max");
        int outputThreads = (int) propertyManager.getProperty("output.threads");
        int outputThreadsMax = (int) propertyManager.getProperty("output.threads_max");

        this.consumerExecutor = factory.build(inputThreads, inputThreadsMax);
        this.producerExecutor = factory.build(outputThreads, outputThreadsMax);
    }

    public ThreadPoolExecutor getInputExecutor() {
        return producerExecutor;
    }

    public ThreadPoolExecutor getOutputExecutor() {
        return consumerExecutor;
    }

}