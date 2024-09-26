package edu.taskexecutor.taskservice.repository;

import edu.taskexecutor.taskservice.entity.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {

    /**
     * Retrieves a list of treatment plans where the 'in progress' status is false.
     *
     * @return a list of treatment plans that are not currently in progress
     */
    List<TreatmentPlan> findByInProgressFalse();
}
