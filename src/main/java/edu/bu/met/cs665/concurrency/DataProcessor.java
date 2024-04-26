package edu.bu.met.cs665.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: DataProcessor.java
 * Description: This class is responsible for managing data transformation tasks using a thread pool.
 * It allows for concurrent processing of multiple tasks to enhance performance and efficiency.
 */
public class DataProcessor {

    private final ExecutorService executorService;

    /**
     * Constructs a DataProcessor with a fixed number of threads.
     *
     * @param numberOfThreads The number of threads in the pool.
     */
    public DataProcessor(int numberOfThreads) {
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    /**
     * Submits a new data processing task to the thread pool.
     *
     * @param task The runnable task that performs data transformation.
     */
    public void submitTask(Runnable task) {
        executorService.submit(task);
    }

    /**
     * Shuts down the thread pool and waits for all tasks to complete.
     */
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executorService.shutdownNow();
        }
    }

    /**
     * Checks if the thread pool has been shut down.
     *
     * @return true if the thread pool is shut down, false otherwise.
     */
    public boolean isShutdown() {
        return executorService.isShutdown();
    }
}
