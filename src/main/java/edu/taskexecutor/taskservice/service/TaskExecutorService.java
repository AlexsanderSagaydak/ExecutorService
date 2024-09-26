package edu.taskexecutor.taskservice.service;

public interface TaskExecutorService {

    /**
     * Method schedules the task creation to run periodically with an initial delay
     * mentioned in {@link edu.taskexecutor.taskservice.properties.ExecutorConfig} instance if required
     * and a specified delay between runs.
     */
    void execute();


    /**
     * Stops the execution of the scheduled tasks in the executor service.
     * This method shuts down the executor service, preventing
     * any new tasks from being scheduled and completing all currently
     * running tasks. It ensures that resources are released.
     */
    void stopExecution();

}
