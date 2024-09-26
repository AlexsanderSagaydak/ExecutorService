package edu.taskexecutor.taskservice.dto;

import edu.taskexecutor.taskservice.enumeration.TreatmentAction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TreatmentPlanDTO {
    private String patient;
    private TreatmentAction treatmentAction;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String recurrencePattern;
}
