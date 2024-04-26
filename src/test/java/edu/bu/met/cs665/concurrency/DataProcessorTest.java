package edu.bu.met.cs665.concurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: DataProcessorTest.java
 * Description: Tests the DataProcessor class to ensure it manages tasks and threads correctly,
 * confirming that tasks execute properly and that the thread pool shuts down as expected.
 */
public class DataProcessorTest {
    private DataProcessor dataProcessor;

    @BeforeEach
    public void setUp() {
        dataProcessor = new DataProcessor(2);  // Initialize with 2 threads for testing
    }

    @Test
    public void testTaskExecution() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Runnable task = counter::incrementAndGet;

        dataProcessor.submitTask(task);
        dataProcessor.submitTask(task);

        dataProcessor.shutdown();  // Ensure all tasks complete

        assertEquals(2, counter.get(), "Counter should be incremented twice, indicating both tasks executed.");
    }

    @Test
    public void testThreadPoolShutdown() throws InterruptedException {
        dataProcessor.shutdown();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> assertTrue(dataProcessor.isShutdown(), "DataProcessor should be shut down."));
        executor.shutdown();
        assertTrue(executor.awaitTermination(1, TimeUnit.SECONDS), "Ensure the executor terminates.");
    }

    @AfterEach
    public void cleanUp() {
        dataProcessor.shutdown();
    }
}
