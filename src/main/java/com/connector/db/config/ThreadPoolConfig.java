package com.connector.db.config;

import com.connector.db.factory.ExecutorFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadPoolConfig {

    private final ThreadPoolExecutor producerExecutor;
    private final ThreadPoolExecutor consumerExecutor;

    public ThreadPoolConfig(ExecutorFactory factory, PropertyManager propertyManager) {

        var inputThreads = (int) propertyManager.getNested("input.threads");
        var inputThreadsMax = (int) propertyManager.getNested("input.threads.max");
        var outputThreads = (int) propertyManager.getNested("output.threads");
        var outputThreadsMax = (int) propertyManager.getNested("output.threads.max");

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