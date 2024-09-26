package edu.taskexecutor.taskservice;

import edu.taskexecutor.taskservice.service.TaskExecutorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main entry point for the Task Service application.
 * The application is configured as a Spring Boot application and enables asynchronous processing.
 * Starts the application and triggers the execution of the {@link TaskExecutorServiceImpl}.
 */
@SpringBootApplication
@EnableAsync
public class TaskServiceApplication {

    /**
     * Main method to launch the Spring Boot application.
     * <p>
     * Initializes the Spring ApplicationContext and executes tasks using
     * the {@link TaskExecutorServiceImpl}.
     * </p>
     */
    public static void main(String[] args) {
        var context = SpringApplication.run(TaskServiceApplication.class, args);
        var executorService = context.getBean(TaskExecutorServiceImpl.class);
        executorService.execute();
    }

}
