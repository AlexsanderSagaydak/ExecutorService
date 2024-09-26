package edu.taskexecutor.taskservice;


import edu.taskexecutor.taskservice.entity.TreatmentPlan;
import edu.taskexecutor.taskservice.enumeration.TreatmentAction;
import edu.taskexecutor.taskservice.enumeration.TreatmentTaskStatus;
import edu.taskexecutor.taskservice.repository.TreatmentPlanRepository;
import edu.taskexecutor.taskservice.repository.TreatmentTaskRepository;
import edu.taskexecutor.taskservice.service.TaskCreator;
import edu.taskexecutor.taskservice.service.TaskExecutorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Integration tests for the {@link TaskExecutorServiceImpl} class.
 * These tests verify the behavior of task creation based on treatment plans.
 */
@SpringBootTest
public class ExecutorServiceIntegrationTests {
    private static final int WAIT_TIME_MINUTES = 2;
    private static final int EXPECTED_COUNT_OF_TASKS = 2;
    @Autowired
    private TaskExecutorServiceImpl executorService;
    @Autowired
    private TreatmentPlanRepository planRepo;
    @Autowired
    private TreatmentTaskRepository taskRepo;
    @Autowired
    private TaskCreator taskCreator;

    @BeforeEach
    void setUp() {
        var tp = new TreatmentPlan();
            tp.setTreatmentAction(TreatmentAction.ACTION_A);
            tp.setStartDate(LocalDateTime.now());
            tp.setRecurrencePattern("Some pattern");
            tp.setPatient("Some guy");
        planRepo.save(tp);
    }

    @AfterEach
    void tearDown() {
        taskRepo.deleteAll();
        planRepo.deleteAll();
    }

    /**
     * Verifies tasks are correctly created based on a single treatment plan.
     * Waiting for 2 min and expect 2 task (based on one TreatmentPlan that is not in progress)
     */
    @Test
    void createsTasksBasedOnSinglePlan() throws InterruptedException {

        executorService.execute();
        TimeUnit.MINUTES.sleep(WAIT_TIME_MINUTES);

        var tasks = taskRepo.findAll();
        assertThat(tasks).hasSize(EXPECTED_COUNT_OF_TASKS);

        var createdTask = tasks.get(0);
        assertThat(createdTask.getPatient()).isEqualTo("Some guy");
        assertThat(createdTask.getTaskStatus()).isEqualTo(TreatmentTaskStatus.ACTIVE);
        assertThat(createdTask.getTreatmentAction()).isEqualTo(TreatmentAction.ACTION_A);

        executorService.stopExecution();

    }

    /**
     * Asynchronous task creation check.
     * It verifies that the task creation process complete @Tests in a reasonable
     * amount of time -(less than 2 seconds, whereas Thread.sleep(5000) in the {@link TaskCreator#createTasks()}.
     * It means 'main' thread doesn't wait 5s for heavy task simulation and may be finished as non-blocked
     *
     * @Disabled
     * As TaskExecutorServiceImpl is @Autowired It may require new instance of executorService per each test
     */
    @Disabled
    void validateAsyncMode() throws Exception {
        var startTime = System.currentTimeMillis();
        executorService.execute();
        Thread.sleep(1000);
        assertThat(System.currentTimeMillis() - startTime).isLessThan(2000);
        executorService.stopExecution();
    }

}
