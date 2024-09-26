package edu.taskexecutor.taskservice.entity;

import edu.taskexecutor.taskservice.enumeration.TreatmentAction;
import edu.taskexecutor.taskservice.service.TaskCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "treatment_plan")
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_patient", nullable = false)
    private String patient;

    @Column(name = "treatment_action", nullable = false)
    @Enumerated(value = STRING)
    private TreatmentAction treatmentAction;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "recurrence_pattern", nullable = false)
    private String recurrencePattern;

    /**
     * Indicates whether the treatment plan is currently in progress.
     * This field is set to {@code true} when a task for the patient has already been created based on this plan.
     * Plans with this field set to {@code true} are excluded from further task creation in
     * {@link TaskCreator#createTasks()}.
     */
    @Column(name = "in_progress", nullable = false)
    private Boolean inProgress = false;

    //TODO: assume TreatmentTask as @ManyToOne may be here


    public Long getId() {
        return id;
    }


    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public TreatmentAction getTreatmentAction() {
        return treatmentAction;
    }

    public void setTreatmentAction(TreatmentAction treatmentAction) {
        this.treatmentAction = treatmentAction;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreatmentPlan tp)) return false;

        return (Objects.equals(patient, tp.patient))
                && (Objects.equals(treatmentAction, tp.treatmentAction))
                && (Objects.equals(startDate, tp.startDate))
                && (Objects.equals(endDate, tp.endDate))
                && (Objects.equals(recurrencePattern, tp.recurrencePattern));
    }

    @Override
    public String toString() {
        return "TreatmentPlan{" +
                "id=" + id +
                ", patient='" + patient + '\'' +
                ", treatmentAction=" + treatmentAction +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", recurrencePattern='" + recurrencePattern + '\'' +
                ", inProgress=" + inProgress +
                '}';
    }
}
