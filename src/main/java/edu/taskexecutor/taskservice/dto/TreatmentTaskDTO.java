package edu.taskexecutor.taskservice.dto;

import edu.taskexecutor.taskservice.enumeration.TreatmentAction;
import edu.taskexecutor.taskservice.enumeration.TreatmentTaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TreatmentTaskDTO {

    private TreatmentAction treatmentAction;
    private String patient;
    private TreatmentTaskStatus taskStatus;
    private LocalDateTime startTime;

}
