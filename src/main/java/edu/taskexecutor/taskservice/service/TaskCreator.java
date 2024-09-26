package edu.taskexecutor.taskservice.service;

import edu.taskexecutor.taskservice.entity.TreatmentPlan;
import edu.taskexecutor.taskservice.entity.TreatmentTask;
import edu.taskexecutor.taskservice.enumeration.TreatmentAction;
import edu.taskexecutor.taskservice.enumeration.TreatmentTaskStatus;
import edu.taskexecutor.taskservice.repository.TreatmentPlanRepository;
import edu.taskexecutor.taskservice.repository.TreatmentTaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * Component for creating treatment tasks asynchronously from treatment plans.
 */
@Component
@RequiredArgsConstructor
public class TaskCreator {

    private static final Logger logger = LoggerFactory.getLogger(TaskCreator.class);
    private static final int batchSize = 100;

    private final TreatmentPlanRepository planRepo;

    private final TreatmentTaskRepository taskRepo;


    /**
     * Asynchronously creates tasks from treatment plans and saves them in batches.
     *
     * @return a CompletableFuture representing the task creation process (potentially may return the result, now - void)
     */
    @Async
    @Transactional
    public CompletableFuture<Void> createTasks() throws InterruptedException {
        logger.info("Started task creation...");

        Thread.sleep(5000); //heavy task simulation to check async mode
        List<TreatmentPlan> plans = planRepo.findByInProgressFalse();
        planBatchProcessing(plans);

        logger.info("Task creation is finished");
        return CompletableFuture.completedFuture(null);
    }

    private void planBatchProcessing(List<TreatmentPlan> plans) {
        List<TreatmentTask> taskList = new ArrayList<>(batchSize);
        for (TreatmentPlan plan : plans) {
            var task = generateTasksForPlan(plan);
            taskList.add(task);
            logger.info("Generated task for patient: {}", plan.getPatient());

            if (taskList.size() >= batchSize) {
                taskRepo.saveAll(taskList);
                taskList.clear();
            }
        }

        if (!taskList.isEmpty()) {
            taskRepo.saveAll(taskList);
            logger.info("Saved final batch of tasks");
        }

    }

    /**
     * Generates a treatment task (simplified logic) for a given treatment plan.
     *
     * @param plan the treatment plan used to generate the task
     * @return the generated treatment task
     */
    private TreatmentTask generateTasksForPlan(TreatmentPlan plan) {

        //suppose to work via TreatmentTaskDTO, not with @Entity directly, use mapper TreatmentTaskMapper
        return TreatmentTask.builder()
                .patient(plan.getPatient())
                .taskStatus(TreatmentTaskStatus.ACTIVE)
                .treatmentAction(TreatmentAction.ACTION_A)
                .startTime(plan.getStartDate())
                .build();
    }

}

