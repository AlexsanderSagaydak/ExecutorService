package edu.taskexecutor.taskservice.repository;

import edu.taskexecutor.taskservice.entity.TreatmentTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentTaskRepository extends JpaRepository<TreatmentTask, Long> {
    //TODO: based on requirements
}
