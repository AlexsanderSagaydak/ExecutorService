package edu.taskexecutor.taskservice.service;

import edu.taskexecutor.taskservice.properties.ExecutorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * The {@code TaskExecutorServiceImpl} class is responsible for scheduling and executing tasks
 * asynchronously at a fixed rate. It utilizes a scheduled executor service to create
 * tasks based on the provided {@link TaskCreator} instance.
 * Method schedules the task creation to run periodically with an initial delay
 * and a specified delay between runs.
 */
@Service
public class TaskExecutorServiceImpl implements TaskExecutorService {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecutorServiceImpl.class);
    private ExecutorConfig schedulerConfig;
    private ScheduledExecutorService scheduler;
    private final TaskCreator taskCreator;

    private volatile boolean isRunning = false;

    public TaskExecutorServiceImpl(ExecutorConfig schedulerConfig, TaskCreator taskCreator) {
        this.schedulerConfig = schedulerConfig;
        this.taskCreator = taskCreator;
        this.scheduler = Executors.newScheduledThreadPool(schedulerConfig.getCorePoolSize());
    }

    @Override
    public void execute() {
        if (isRunning) {
            logger.warn("Task execution already in progress.");
            return;
        }
        isRunning = true;

        scheduler.scheduleAtFixedRate(() -> {
            try {
                taskCreator.createTasks();
            } catch (Exception e) {
                logger.error("Unexpected error occurred while creating tasks: {}", e.getMessage(), e);
            }
        }, schedulerConfig.getInitialDelay(), schedulerConfig.getDelayBetweenRuns(), TimeUnit.SECONDS);
    }

    @Override
    public void stopExecution() {
        if (scheduler != null) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
            scheduler = null;
        }
    }

}
