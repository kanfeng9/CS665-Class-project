package edu.bu.met.cs665.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: ThreadPoolManager.java
 * Description: Manages a pool of threads for executing tasks concurrently. This class provides
 * methods to initiate and safely terminate the thread pool, ensuring all tasks are completed
 * before shutdown.
 */
public class ThreadPoolManager {

    private ExecutorService executorService;
    private final int numberOfThreads;

    /**
     * Constructor for ThreadPoolManager with a specified number of threads.
     *
     * @param numberOfThreads the number of threads in the pool.
     */
    public ThreadPoolManager(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    /**
     * Initializes the thread pool with the specified number of threads.
     */
    public void init() {
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(numberOfThreads);
        }
    }

    /**
     * Submits a Runnable task to the thread pool for execution.
     *
     * @param task The task to be executed.
     */
    public void submitTask(Runnable task) {
        if (executorService != null) {
            executorService.submit(task);
        }
    }

    /**
     * Shuts down the thread pool and waits for all tasks to complete.
     * Attempts to terminate the thread pool gracefully and then forcefully if necessary.
     */
    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();  // Disable new tasks from being submitted
            try {
                // Wait for existing tasks to terminate
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();  // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                executorService.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
    }
}
