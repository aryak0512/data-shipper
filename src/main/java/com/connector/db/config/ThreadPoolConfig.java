package com.connector.db.config;

import com.connector.db.factory.ExecutorFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadPoolConfig {

    private final ExecutorFactory factory;
    private final ThreadPoolExecutor producerExecutor;
    private final ThreadPoolExecutor consumerExecutor;
    private final PropertyManager propertyManager;

    public ThreadPoolConfig(ExecutorFactory factory, PropertyManager propertyManager) {
        this.factory = factory;
        this.propertyManager = propertyManager;

        var inputThreads = (int) this.propertyManager.getNested("input.threads");
        var inputThreadsMax = (int) this.propertyManager.getNested("input.threads.max");
        var outputThreads = (int) this.propertyManager.getNested("output.threads");
        var outputThreadsMax = (int) this.propertyManager.getNested("output.threads.max");

        this.consumerExecutor = this.factory.build(inputThreads, inputThreadsMax);
        this.producerExecutor = this.factory.build(outputThreads, outputThreadsMax);
    }

    public ThreadPoolExecutor getInputExecutor() {
        return producerExecutor;
    }

    public ThreadPoolExecutor getOutputExecutor() {
        return consumerExecutor;
    }

}